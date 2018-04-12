package com.erofeev.hotel.reception;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.annotation.csvwriter.FileManager;
import com.erofeev.hotel.api.observer.Observable;
import com.erofeev.hotel.api.observer.Observer;
import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;
import com.erofeev.hotel.observer.GuestObserver;
import com.erofeev.hotel.observer.RoomsObserver;
import com.erofeev.hotel.observer.ServicesObserver;
import com.erofeev.hotel.print.Printer;
import com.erofeev.hotel.sort.GuestSortedByDate;
import com.erofeev.hotel.sort.GuestSotredByName;
import com.erofeev.hotel.sort.RoomsSortedByCapacity;
import com.erofeev.hotel.sort.RoomsSortedByPrice;
import com.erofeev.hotel.sort.RoomsSortedByStars;
import com.erofeev.hotel.sort.ServiceSortedByPrice;

public class Reception implements IReception, Observable {
	private static final Logger loggerReception = LogManager.getLogger(Reception.class);

	private RoomManager roomManager;
	private GuestManager guestManager;
	private ServicesManager servicesManager;
	private FileManager fileManager;
	private int roomHistorySize = 0;
	private boolean changeStatusFlag = true;
	private String ROOMS_FILE;
	private String GUESTS_FILE;
	private String SERVICES_FILE;
	private List<Observer> guestObservers;
	private List<Observer> roomsObservers;
	private List<Observer> servicesObservers;

	public Reception() {
		this.roomManager = new RoomManager();
		this.guestManager = new GuestManager();
		this.servicesManager = new ServicesManager();
		this.fileManager = FileManager.getInstance();

		this.guestObservers = new ArrayList<Observer>();
		this.roomsObservers = new ArrayList<Observer>();
		this.servicesObservers = new ArrayList<Observer>();

		this.guestObservers.add(new GuestObserver());
		this.roomsObservers.add(new RoomsObserver());
		this.servicesObservers.add(new ServicesObserver());
	}

	private void initFileManager() throws IOException, ParseException {
		FileManager.getInstance().initFileManager(this.ROOMS_FILE, this.GUESTS_FILE, this.SERVICES_FILE);
		loggerReception.info("Start initilization from files:");
		try {
			this.addServices(fileManager.readServicesFromFile());
			this.addRooms(fileManager.readRoomsFromFile());
			this.addGuests(fileManager.readGuestsFromFile());
		}

		catch (NumberFormatException e) {
			loggerReception.error("Can't read  file: " + e);
		}
		loggerReception.info("Initilization finished.");
	}

	@Override
	public void initReception(List<String> parameters) {
		String changeStatusFlagStr = parameters.get(0);
		String roomHistorySizeStr = parameters.get(1);
		String ROOMS_FILE = parameters.get(2);
		String GUESTS_FILE = parameters.get(3);
		String SERVICES_FILE = parameters.get(4);
		if (changeStatusFlagStr.equals("true")) {
			this.changeStatusFlag = true;
		} else {
			this.changeStatusFlag = false;
		}
		this.roomHistorySize = Integer.parseInt(roomHistorySizeStr);
		this.ROOMS_FILE = ROOMS_FILE;
		this.GUESTS_FILE = GUESTS_FILE;
		this.SERVICES_FILE = SERVICES_FILE;
		try {
			this.initFileManager();
		} catch (IOException | ParseException e) {
			Printer.print("Cant init FileManager");
			e.printStackTrace();
		}
	}

	@Override
	public List<Room> getAllRooms() {
		List<Room> currentRooms = roomManager.getAll();
		return currentRooms;
	}

	@Override
	public List<Room> getAllEmptyRooms() {
		List<Room> emptyRooms = roomManager.getEmptyRooms();
		return emptyRooms;
	}

	@Override
	public List<Guest> getAllGuests() {
		List<Guest> currentGuests = guestManager.getAll();
		return currentGuests;
	}

	@Override
	public List<Service> getAllServices() {
		List<Service> currentServices = servicesManager.getAll();
		return currentServices;
	}

	@Override
	public Guest findGuestbyName(String name) {
		return guestManager.findbyName(name);
	}

	@Override
	public Room findRoombyName(String name) {
		return roomManager.findbyName(name);
	}

	@Override
	public Service findServicebyName(String name) {
		return servicesManager.findbyName(name);
	}

	@Override
	public List<Service> getGuestServices(Guest guest) {
		List<Service> guestServices = guest.getGuestServices();
		return guestServices;
	}

	@Override
	public List<Guest> getRoomHistory(Room room) {
		return roomManager.getRoomHistory(room);
	}

	@Override
	public float[] getGuestPrice(Guest guest) {
		float servicesPrice = guestManager.getGuestServicesPrice(guest);
		float roomPrice = guestManager.getGuestRoomPrice(guest);
		float totalPrice = servicesPrice + roomPrice;
		float[] guestPrice = new float[3];
		guestPrice[0] = servicesPrice;
		guestPrice[1] = roomPrice;
		guestPrice[2] = totalPrice;
		return guestPrice;
	}

	@Override
	public void evictGuest(Guest guest, Room room) {
		Guest currentGuest = guestManager.findExistingEntity(guest);
		Room currentRoom = roomManager.findExistingEntity(room);
		if ((currentGuest != null) && ((currentRoom != null))) {
			currentRoom.setEmpty(true);
			guestManager.evict(currentGuest);
			this.notifyRoomsObservers();
			this.notifyGuestObservers();
		}
		if (currentGuest == null) {
			loggerReception.info("Guest not found");
		}
		if (currentRoom == null) {
			loggerReception.info("Room not found");
		}
	}

	@Override
	public void evictGuest(Guest guest) {
		Guest currentGuest = guestManager.findExistingEntity(guest);
		Room currentRoom = currentGuest.getRoom();
		if ((currentGuest != null) && ((currentRoom != null))) {
			currentRoom.setEmpty(true);
			guestManager.evict(currentGuest);
			this.notifyRoomsObservers();
			this.notifyGuestObservers();
		}
	}

	@Override
	public void occupyGuest(Guest guest, Room room) {
		Room currentRoom = roomManager.findExistingEntity(room);
		if (currentRoom != null) {
			if (currentRoom.isEmpty()) {
				currentRoom.setEmpty(false);
				guest.setRoom(currentRoom);
				guestManager.add(guest);
				room.getRoomHistory().add(guest);
				this.notifyRoomsObservers();
				this.notifyGuestObservers();
				loggerReception.info("Guest was occupy");
			} else {
				loggerReception.info("Room is busy");
			}
		}
	}

	@Override
	public void occupyGuest(String fullName, Room room) {
		Room currentRoom = roomManager.findExistingEntity(room);
		Guest guest = guestManager.findbyName(fullName);
		if (currentRoom != null) {
			if (currentRoom.isEmpty()) {
				currentRoom.setEmpty(false);
				guest.setRoom(currentRoom);
				guestManager.add(guest);
				room.getRoomHistory().add(guest);
				this.notifyRoomsObservers();
				this.notifyGuestObservers();
				loggerReception.info("Guest was occupy");
			} else {
				loggerReception.info("Room is busy");
			}
		}
	}

	@Override
	public void changeRoomPrice(Room room, float price) {
		room.setPrice(price);
		this.notifyRoomsObservers();
		loggerReception.info(room.toString() + " price was changed");
	}

	@Override
	public void changeServicePrice(Service service, float price) {
		service.setPrice(price);
		this.notifyServicesObservers();
		loggerReception.info(service.toString() + " price was changed");
	}

	@Override
	public void changeRoomStatus(Room room, RoomStatus status) {
		if (!this.changeStatusFlag) {
			loggerReception.info("You cant change status!");
		} else {
			roomManager.findExistingEntity(room).setRoomStatus(status);
			loggerReception.info(room.toString() + " status was changed");
			this.notifyRoomsObservers();
		}
	}

	@Override
	public List<Guest> getGuestSortedByName() throws ClassCastException {
		List<Guest> guestList = guestManager.getAll();
		Guest[] currentGuests = guestList.toArray(new Guest[guestList.size()]);
		Arrays.sort(currentGuests, new GuestSotredByName());
		List<Guest> sortedGuests = new ArrayList<Guest>(Arrays.asList(currentGuests));
		return sortedGuests;
	}

	@Override
	public List<Guest> getGuestSortedByData() throws ClassCastException {
		List<Guest> guestList = guestManager.getAll();
		Guest[] currentGuests = guestList.toArray(new Guest[guestList.size()]);
		Arrays.sort(currentGuests, new GuestSortedByDate());
		List<Guest> sortedGuests = new ArrayList<Guest>(Arrays.asList(currentGuests));
		return sortedGuests;
	}

	@Override
	public List<Service> getServiceSortedByPrice() throws ClassCastException {
		List<Service> servicesList = servicesManager.getAll();
		Service[] arrServices = servicesList.toArray(new Service[servicesList.size()]);
		Arrays.sort(arrServices, new ServiceSortedByPrice());
		List<Service> sortedServices = new ArrayList<Service>(Arrays.asList(arrServices));
		return sortedServices;
	}

	@Override
	public List<Room> getRoomsSortedByCapacity() throws ClassCastException {
		List<Room> roomsList = roomManager.getAll();
		Room[] arrRooms = roomsList.toArray(new Room[roomsList.size()]);
		Arrays.sort(arrRooms, new RoomsSortedByCapacity());
		ArrayList<Room> sortedRooms = new ArrayList<Room>(Arrays.asList(arrRooms));
		return sortedRooms;
	}

	@Override
	public List<Room> getRoomsSortedByStars() throws ClassCastException {
		List<Room> roomsList = roomManager.getAll();
		Room[] arrRooms = roomsList.toArray(new Room[roomsList.size()]);
		Arrays.sort(arrRooms, new RoomsSortedByStars());
		ArrayList<Room> sortedRooms = new ArrayList<Room>(Arrays.asList(arrRooms));
		return sortedRooms;
	}

	@Override
	public List<Room> getRoomsSortedByPrice() throws ClassCastException {
		List<Room> roomsList = roomManager.getAll();
		Room[] arrRooms = roomsList.toArray(new Room[roomsList.size()]);
		Arrays.sort(arrRooms, new RoomsSortedByPrice());
		List<Room> sortedRooms = new ArrayList<Room>(Arrays.asList(arrRooms));
		return sortedRooms;
	}

	@Override
	public void addService(Service service) {
		servicesManager.add(service);
		this.notifyServicesObservers();
	}

	@Override
	public void addRoom(Room room) {		
		if ((this.roomHistorySize != 0)&&(room.getRoomHistory().isEmpty())) {
			room.setRoomHistory(new ArrayList<Guest>(roomHistorySize));
			roomManager.add(room);
		} else {
			roomManager.add(room);
		}
		this.notifyRoomsObservers();
	}

	@Override
	public void addRooms(List<Room> newRooms) {
		roomManager.add(newRooms);
		this.notifyRoomsObservers();
	}

	@Override
	public void addServices(List<Service> newServices) {
		servicesManager.add(newServices);
		this.notifyServicesObservers();
	}

	@Override
	public void addGuests(List<Guest> newGuests) {
		guestManager.add(newGuests);
		this.notifyGuestObservers();
	}

	@Override
	public void addGuest(Guest newGuests) {
		guestManager.add(newGuests);
		this.notifyGuestObservers();
	}

	@Override
	public void removeService(Service service) {
		servicesManager.remove(service);
		this.notifyServicesObservers();
	}

	@Override
	public void removeRoom(Room room) {
		roomManager.remove(room);
		this.notifyRoomsObservers();
	}

	@Override
	public void removeGuest(Guest guest) {
		guestManager.remove(guest);
		this.notifyGuestObservers();
	}

	@Override
	public void addServiceToGuest(Guest guest, Service service) {
		Guest currentGuest = guestManager.findExistingEntity(guest);
		if (currentGuest != null) {
			currentGuest.addGuestService(service);
			this.notifyServicesObservers();
			this.notifyGuestObservers();
		}
	}

	@Override
	public void removeServiceToGuest(Guest guest, Service service) {
		Guest currentGuest = guestManager.findExistingEntity(guest);
		if (currentGuest != null) {
			currentGuest.removeGuestService(service);
			this.notifyServicesObservers();
			this.notifyGuestObservers();
		} else {
			Printer.print("Guest not found");
		}
	}

	@Override
	public void addGuestObserver(Observer o) {
		guestObservers.add(o);
	}

	@Override
	public void removeGuestObserver(Observer o) {
		guestObservers.remove(o);
	}

	@Override
	public void notifyGuestObservers() {
		for (int i = 0; i < guestObservers.size(); i++) {
			guestObservers.get(i).update(this.guestManager);
		}
	}

	@Override
	public void removeRoomsObserver(Observer o) {
		roomsObservers.remove(o);
	}

	@Override
	public void addRoomsObserver(Observer o) {
		roomsObservers.add(o);
	}

	@Override
	public void notifyRoomsObservers() {
		for (int i = 0; i < roomsObservers.size(); i++) {
			roomsObservers.get(i).update(this.roomManager);
		}
	}

	@Override
	public void removeServicesObserver(Observer o) {
		servicesObservers.remove(o);
	}

	@Override
	public void addServicesObserver(Observer o) {
		servicesObservers.add(o);
	}

	@Override
	public void notifyServicesObservers() {
		for (int i = 0; i < servicesObservers.size(); i++) {
			servicesObservers.get(i).update(this.servicesManager);
		}
	}

	@Override
	public int getRoomHistorySize() {
		return roomHistorySize;
	}

	@Override
	public void setRoomHistorySize(int roomHistorySize) {
		this.roomHistorySize = roomHistorySize;
	}

	@Override
	public boolean isChangeStatusFlag() {
		return changeStatusFlag;
	}

	@Override
	public void setChangeStatusFlag(boolean changeStatusFlag) {
		this.changeStatusFlag = changeStatusFlag;
	}

	@Override
	public String getROOMS_FILE() {
		return ROOMS_FILE;
	}

	@Override
	public void setROOMS_FILE(String rOOMS_FILE) {
		ROOMS_FILE = rOOMS_FILE;
	}

	@Override
	public String getGUESTS_FILE() {
		return GUESTS_FILE;
	}

	@Override
	public void setGUESTS_FILE(String gUESTS_FILE) {
		GUESTS_FILE = gUESTS_FILE;
	}

	@Override
	public String getSERVICES_FILE() {
		return SERVICES_FILE;
	}

	@Override
	public void setSERVICES_FILE(String sERVICES_FILE) {
		SERVICES_FILE = sERVICES_FILE;
	}

	@Override
	public void importServices(List<Service> services) {
		servicesManager.update(services);
		this.notifyServicesObservers();
	}

	@Override
	public void importRooms(List<Room> rooms) {
		roomManager.update(rooms);
		this.notifyRoomsObservers();
	}

	@Override
	public void importGuests(List<Guest> guests) {
		guestManager.update(guests);
		this.notifyGuestObservers();
	}

	@Override
	public Guest findGuestbyID(int id) {		
		return guestManager.findbyId(id);
	}

	@Override
	public Room findRoombyID(int id) {		
		return roomManager.findbyId(id);
	}

	@Override
	public Service findServicebyID(int id) {		
		return servicesManager.findbyId(id);
	}

}
