package com.erofeev.hotel.reception;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.api.Observable;
import com.erofeev.hotel.api.Observer;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.file.FileManager;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;
import com.erofeev.hotel.mylist.MyList;
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
	private RoomManager rooms;
	private GuestManager guests;
	private ServicesManager services;
	private FileManager fileManager;
	private MyList<Observer> guestObservers = new MyList<Observer>();
	private MyList<Observer> roomsObservers = new MyList<Observer>();
	private MyList<Observer> servicesObservers = new MyList<Observer>();

	private static final Logger loggerReception = LogManager.getLogger(Reception.class);

	public Reception() {
		this.rooms = new RoomManager();
		this.guests = new GuestManager();
		this.services = new ServicesManager();
		this.fileManager = FileManager.getInstance();

		this.addGuestObserver(new GuestObserver());
		this.addRoomsObserver(new RoomsObserver());
		this.addServicesObserver(new ServicesObserver());
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public RoomManager getRoomManager() {
		return rooms;
	}

	public GuestManager getGuestsManager() {
		return guests;
	}

	public void setGuests(GuestManager guests) {
		this.guests = guests;
	}

	public ServicesManager getServicesManager() {
		return services;
	}

	public void setServices(ServicesManager services) {
		this.services = services;
	}

	public void initFileManager(String ROOMS_FILE, String GUESTS_FILE, String SERVICES_FILE) {
		getFileManager().initFileManager(ROOMS_FILE, GUESTS_FILE, SERVICES_FILE);
		loggerReception.info("Start initilization from files:");

		try {
			this.addServices(fileManager.readServicesFromFile());
		} 
		catch(IOException e){
			loggerReception.error("cant create file: " + e);
		}
		catch (NumberFormatException e) {
			loggerReception.error("Can't read services file: " + e);
		}
		
		
		try {
			this.addRooms(fileManager.readRoomsFromFile());
		}
		catch(IOException e){
			loggerReception.error("cant create file: " + e);
		}
		catch (NumberFormatException e) {
			loggerReception.error("Can't read rooms file: " + e);			
		}
		try {
			this.addGuests(fileManager.readGuestFromFile(this));			
		} 
		catch(IOException e){
			loggerReception.error("cant create file: " + e);
		}
		catch (NumberFormatException | NullPointerException | ParseException e) {
			loggerReception.error("Can't read guests file: " + e);

		} catch (RuntimeException e) {
			loggerReception.error("Can't read file: " + e);
		}

		loggerReception.info("Initilization finished.");

	}
	
	public MyList<Room> getAllRooms() {
		MyList<Room> currentRooms = rooms.getAll();
		return currentRooms;

	}

	
	
	@Override
	public MyList<Room> getAllEmptyRooms() {
		MyList<Room> emptyRooms = rooms.getEmptyRooms();
		return emptyRooms;

	}
	
	public MyList<Guest> getAllGuests() {
		MyList<Guest> currentGuests = guests.getAll();
		return currentGuests;

	}
	
	@Override
	public MyList<Service> getAllServices() {
		MyList<Service> currentServices = services.getAll();
		return currentServices;
	}
	
	public Guest findGuestbyName(String name){		
		return guests.findbyName(name);
	}
	
	public Room findRoombyName(String name){
		return rooms.findbyName(name);
	}
	
	public Service findServicebyName(String name){
		return services.findbyName(name);
	}
	



	@Override
	public MyList<Service> getGuestServices(Guest guest) {
		MyList<Service> guestServices = guest.getGuestServices();
		return guestServices;
	}


	@Override
	public MyList<Guest> getRoomHistory(Room room) {
		MyList<Guest> roomHistory = guests.getGuestsHistory();
		return roomHistory;
	}

	@Override
	public float[] getGuestPrice(Guest guest) {
		float servicesPrice = guests.getGuestServicesPrice(guest);
		float roomPrice = guests.getGuestRoomPrice(guest);
		float totalPrice = servicesPrice + roomPrice;
		float[] guestPrice = new float[3];
		guestPrice[0] = servicesPrice;
		guestPrice[1] = roomPrice;
		guestPrice[2] = totalPrice;
		return guestPrice;

	}

	@Override
	public void evictGuest(Guest guest, Room room) {
		Guest currentGuest = guests.findExistingEntity(guest);
		Room currentRoom = rooms.findExistingEntity(room);
		if ((currentGuest != null) && ((currentRoom != null))) {
			currentRoom.setEmpty(true);
			guests.remove(currentGuest);
			this.notifyRoomsObservers();
			this.notifyGuestObservers();
		} else if (currentGuest != null) {
			// Printer.print("Guest not found");
			loggerReception.info("Guest not found");
		} else if (currentRoom != null) {
			// Printer.print("Room not found");
			loggerReception.info("Room not found");
		}

	}
	
	@Override
	public void evictGuest(Guest guest) {
		Guest currentGuest = guests.findExistingEntity(guest);
		Room currentRoom = currentGuest.getRoom();
		if ((currentGuest != null) && ((currentRoom != null))) {
			currentRoom.setEmpty(true);
			guests.remove(currentGuest);
			this.notifyRoomsObservers();
			this.notifyGuestObservers();
		} else if (currentGuest != null) {
			// Printer.print("Guest not found");
			loggerReception.info("Guest not found");
		} else if (currentRoom != null) {
			// Printer.print("Room not found");
			loggerReception.info("Room not found");
		}

	}

	@Override
	public void occupyGuest(Guest guest, Room room) {
		Room currentRoom = rooms.findExistingEntity(room);
		if (currentRoom != null) {
			if (currentRoom.isEmpty()) {
				currentRoom.setEmpty(false);
				guest.setRoom(currentRoom);
				guests.add(guest);
				this.notifyRoomsObservers();
				this.notifyGuestObservers();
			} else {
				// Printer.print("Room is busy");
				loggerReception.info("Room is busy");
			}
		}

	}

	@Override
	public void changeRoomPrice(Room room, float price) {
		room.setPrice(price);
		loggerReception.info(room.toString() + " price was changed");
	}

	@Override
	public void changeServicePrice(Service service, float price) {
		service.setPrice(price);
		loggerReception.info(service.toString() + " price was changed");

	}

	@Override
	public void changeRoomStatus(Room room, RoomStatus status) {
		rooms.findExistingEntity(room).setRoomStatus(status);		
		loggerReception.info(room.toString() + " status was changed");
		this.notifyRoomsObservers();

	}

	@Override
	public MyList<Guest> getGuestSortedByName() throws ClassCastException {	
		
		MyList<Guest> guestList = guests.getAll();		
		Guest[] currentGuests = new Guest[guests.getAll().length()];	
		currentGuests = guestList.convertToArray(currentGuests);		
		Arrays.sort(currentGuests, new GuestSotredByName());		
		MyList<Guest> sortedGuests = new MyList<Guest>(currentGuests);		
		return sortedGuests;
	}

	@Override
	public MyList<Guest> getGuestSortedByData() throws ClassCastException {		
		MyList<Guest> guestList = guests.getAll();		
		Guest[] currentGuests = new Guest[guests.getAll().length()];		
		currentGuests = guestList.convertToArray(currentGuests);		
		Arrays.sort(currentGuests, new GuestSortedByDate());		
		MyList<Guest> sortedGuests = new MyList<Guest>(currentGuests);			
		return sortedGuests;
		
	}

	@Override
	public MyList<Service> getServiceSortedByPrice() throws ClassCastException {		
		MyList<Service> servicesList = services.getAll();		
		Service[] arrServices = new Service[services.getAll().length()];	
		arrServices = servicesList.convertToArray(arrServices);		
		Arrays.sort(arrServices, new ServiceSortedByPrice());
		MyList<Service> sortedServices = new MyList<Service>(arrServices);	
		return sortedServices;
	
	}

	@Override
	public MyList<Room> getRoomsSortedByCapacity() throws ClassCastException {		
		MyList<Room> roomsList = rooms.getAll();		
		Room[] arrRooms = new Room[rooms.getAll().length()];	
		arrRooms = roomsList.convertToArray(arrRooms);		
		Arrays.sort(arrRooms, new RoomsSortedByCapacity());
		MyList<Room> sortedRooms = new MyList<Room>(arrRooms);	
		return sortedRooms;
	}

	@Override
	public MyList<Room> getRoomsSortedByStars() throws ClassCastException {
		
		MyList<Room> roomsList = rooms.getAll();		
		Room[] arrRooms = new Room[rooms.getAll().length()];	
		arrRooms = roomsList.convertToArray(arrRooms);		
		Arrays.sort(arrRooms, new RoomsSortedByStars());
		MyList<Room> sortedRooms = new MyList<Room>(arrRooms);	
		return sortedRooms;
	}

	@Override

	public MyList<Room> getRoomsSortedByPrice() throws ClassCastException {
		
		MyList<Room> roomsList = rooms.getAll();		
		Room[] arrRooms = new Room[rooms.getAll().length()];	
		arrRooms = roomsList.convertToArray(arrRooms);		
		Arrays.sort(arrRooms, new RoomsSortedByPrice());
		MyList<Room> sortedRooms = new MyList<Room>(arrRooms);	
		return sortedRooms;
	}

	@Override
	public void addService(Service service) {
		if (!services.add(service)) {
			this.notifyServicesObservers();
		} else {
			//Printer.print("Service already exists");
			loggerReception.info(service.toString() + " already exists");
			
		}
	}

	@Override
	public void addRoom(Room room) {
		if (!rooms.add(room)) {
			this.notifyRoomsObservers();
		} else {
			//Printer.print("Room already exists");
			loggerReception.info(room.toString() + " already exists");
		}
	}

	public void addRooms(MyList<Room> newRooms) {
		for (int i = 0; i < newRooms.length(); i++) {
			rooms.add(newRooms.get(i));
		}

	}

	public void addServices(MyList<Service> newServices) {
		for (int i = 0; i < newServices.length(); i++) {
			services.add(newServices.get(i));
		}

	}

	public void addGuests(MyList<Guest> newGuests) {
		for (int i = 0; i < newGuests.length(); i++) {
			newGuests.get(i).getRoom().setEmpty(false);
			guests.add(newGuests.get(i));
		}
	}

	@Override
	public void removeService(Service service) {
		loggerReception.info(service.toString() + " was revomed");
		services.remove(service);		
		this.notifyServicesObservers();

	}

	@Override
	public void removeRoom(Room room) {
		loggerReception.info(room.toString() + " was revomed");
		rooms.remove(room);		
		this.notifyRoomsObservers();

	}


	@Override
	public void addServiceToGuest(Guest guest, Service service) {
		Guest currentGuest = guests.findExistingEntity(guest);
		if (currentGuest != null) {
			currentGuest.addGuestService(service);
			this.notifyServicesObservers();
			this.notifyGuestObservers();
		} 

	}

	@Override
	public void removeServiceToGuest(Guest guest, Service service) {
		Guest currentGuest = guests.findExistingEntity(guest);
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
		for (int i = 0; i < guestObservers.length(); i++) {
			guestObservers.get(i).update(this.getGuestsManager());
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
		for (int i = 0; i < roomsObservers.length(); i++) {
			roomsObservers.get(i).update(this.getRoomManager());
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
		for (int i = 0; i < servicesObservers.length(); i++) {
			servicesObservers.get(i).update(this.getServicesManager());
		}

	}


	

}
