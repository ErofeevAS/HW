package com.erofeev.hotel.reception;

import java.util.Arrays;

import com.erofeev.hotel.print.Printer;
import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.api.Observable;
import com.erofeev.hotel.api.Observer;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;
import com.erofeev.hotel.mylist.MyList;
import com.erofeev.hotel.observer.GuestObserver;
import com.erofeev.hotel.observer.RoomsObserver;
import com.erofeev.hotel.observer.ServicesObserver;
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
	private MyList<Observer> guestObservers = new MyList<Observer>();
	private MyList<Observer> roomsObservers = new MyList<Observer>();
	private MyList<Observer> servicesObservers = new MyList<Observer>();

	public Reception() {
		this.rooms = new RoomManager();
		this.guests = new GuestManager();
		this.services = new ServicesManager();

		this.addGuestObserver(new GuestObserver());
		this.addRoomsObserver(new RoomsObserver());
		this.addServicesObserver(new ServicesObserver());
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

	@Override
	public void viewAllRooms() {
		MyList<Room> currentRooms = rooms.getRooms();
		viewCmd(currentRooms);

	}

	@Override
	public void viewAllEmptyRooms() {
		MyList<Room> emptyRooms = rooms.getEmptyRooms();
		viewCmd(emptyRooms);

	}

	@Override
	public void viewAllGuests() {
		MyList<Guest> currentGuest = guests.getAllGuest();
		viewCmd(currentGuest);
	}

	@Override
	public void viewAllServices() {
		MyList<Service> currentServices = services.getServices();
		viewCmd(currentServices);
	}

	@Override
	public void viewGuestServices(Guest guest) {
		MyList<Service> guestServices = guest.getGuestServices();
		viewCmd(guestServices);
	}

	@Override
	public void viewRoomDetails(Room room) {
		Printer.print(room);
	}

	@Override
	public void viewRoomHistory(Room room) {
		MyList<Guest> guestsHistory = guests.getGuestsHistory();
		Printer.print("Room History:");
		for (int i = 0; i < guestsHistory.length(); i++) {
			if ((guestsHistory.get(i).getRoom()).equals(room)) {
				Printer.print(guestsHistory.get(i));
			}
		}
	}

	@Override
	public void viewGuestPrice(Guest guest) {
		float servicesPrice = guests.getGuestServicesPrice(guest);
		float roomPrice = guests.getGuestRoomPrice(guest);
		float totalPrice = servicesPrice + roomPrice;

		Printer.print("Room price: " + roomPrice);
		Printer.print("Services price: " + servicesPrice);
		Printer.print("Total price: " + totalPrice);

	}

	@Override
	public void evicGuest(Guest guest, Room room) {
		Guest currentGuest = guests.findExistingGuest(guest);
		Room currentRoom = rooms.findExistingRoom(room);
		if ((currentGuest != null) && ((currentRoom != null))) {		
			currentRoom.setEmpty(true);
			guests.remove(currentGuest);
			this.notifyRoomsObservers();
			this.notifyGuestObservers();
		} else if (currentGuest != null) {
			Printer.print("Guest not found");
		} else if (currentRoom != null) {
			Printer.print("Room not found");
		}

	}

	@Override
	public void occupyGuest(Guest guest, Room room) {
		Room currentRoom = rooms.findExistingRoom(room);
		if (currentRoom != null) {
			if (currentRoom.isEmpty()) {
				currentRoom.setEmpty(false);
				guest.setRoom(currentRoom);
				guests.add(guest);
				this.notifyRoomsObservers();
				this.notifyGuestObservers();
			} else {
				Printer.print("Room is busy");
			}
		}

	}

	@Override
	public void changeRoomPrice(Room room, float price) {
		room.setPrice(price);

	}

	@Override
	public void changeServicePrice(Service service, float price) {
		service.setPrice(price);

	}

	@Override
	public void changeRoomStatus(Room room, boolean status) {
		room.setEmpty(status);

	}

	@Override
	public void viewGuestSortedByName() {
		Guest[] currentGuests = convertGuestsCollectionToArray();

		Arrays.sort(currentGuests, new GuestSotredByName());
		Printer.print("Guest sorted by name: ");
		viewArray(currentGuests);
	}

	@Override
	public void viewGuestSortedByData() {
		Guest[] currentGuests = convertGuestsCollectionToArray();
		Arrays.sort(currentGuests, new GuestSortedByDate());
		Printer.print("Guest sorted by Date: ");
		viewArray(currentGuests);
	}

	@Override
	public void viewServiceSortedByPrice() {
		Service[] currentServices = convertServicesCollectionToArray();
		Arrays.sort(currentServices, new ServiceSortedByPrice());
		Printer.print("Services sorted by price: ");
		viewArray(currentServices);
	}

	@Override
	public void viewRoomsSortedByCapacity() {
		Room[] currentRooms = convertRoomsCollectionToArray();
		Arrays.sort(currentRooms, new RoomsSortedByCapacity());
		Printer.print("Rooms sorted by capacity: ");
		viewArray(currentRooms);
	}

	@Override
	public void viewRoomsSortedByStars() {
		Room[] currentRooms = convertRoomsCollectionToArray();
		Arrays.sort(currentRooms, new RoomsSortedByStars());
		Printer.print("Rooms sorted by stars: ");
		viewArray(currentRooms);
	}

	@Override
	public void viewRoomsSortedByPrice() {
		Room[] currentRooms = convertRoomsCollectionToArray();
		Arrays.sort(currentRooms, new RoomsSortedByPrice());
		Printer.print("Rooms sorted by price: ");
		viewArray(currentRooms);
	}

	@Override
	public void addService(Service service) {
		if (!services.add(service)) {
			this.notifyServicesObservers();
		} else {
			Printer.print("Service already exists");
		}
	}

	@Override
	public void addRoom(Room room) {
		if (!rooms.add(room)) {
			this.notifyRoomsObservers();
		} else {
			Printer.print("Room already exists");
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
			// this.occupyGuest(newGuests.get(i), newGuests.get(i).getRoom());
			newGuests.get(i).getRoom().setEmpty(false);
			guests.add(newGuests.get(i));
			// System.out.println(newGuests.get(i).getRoom().isEmpty());
		}
	}

	@Override
	public void removeService(Service service) {
		services.remove(service);
		this.notifyServicesObservers();

	}

	@Override
	public void removeRoom(Room room) {
		rooms.remove(room);
		this.notifyRoomsObservers();

	}

	private Room[] convertRoomsCollectionToArray() {
		MyList<Room> currentRooms = rooms.getRooms();
		int arrayLenght = currentRooms.length();
		Room[] arrayRoom = new Room[arrayLenght];
		for (int i = 0; i < arrayLenght; i++) {
			arrayRoom[i] = currentRooms.get(i);

		}
		return arrayRoom;
	}

	private void viewCmd(MyList list) {
		for (int i = 0; i < (list).length(); i++) {
			Printer.print(list.get(i));
		}
	}

	private void viewArray(Object[] array) {
		for (int i = 0; i < array.length; i++) {
			Printer.print(array[i]);
		}
	}

	private Guest[] convertGuestsCollectionToArray() {
		MyList<Guest> currentGuests = guests.getAllGuest();
		int arrayLenght = currentGuests.length();
		Guest[] arrayGuest = new Guest[arrayLenght];
		for (int i = 0; i < arrayLenght; i++) {
			arrayGuest[i] = currentGuests.get(i);
		}
		return arrayGuest;
	}

	private Service[] convertServicesCollectionToArray() {
		MyList<Service> currentServices = services.getServices();
		int arrayLenght = currentServices.length();
		Service[] arrayService = new Service[arrayLenght];
		for (int i = 0; i < arrayLenght; i++) {
			arrayService[i] = currentServices.get(i);
		}
		return arrayService;
	}

	@Override
	public void addServiceToGuest(Guest guest, Service service) {

		Guest currentGuest = guests.findExistingGuest(guest);
		if (currentGuest != null) {
			currentGuest.addGuestService(service);
			this.notifyServicesObservers();
			this.notifyGuestObservers();
		} else {
			Printer.print("Guest not found");
		}

	}

	@Override
	public void removeServiceToGuest(Guest guest, Service service) {
		Guest currentGuest = guests.findExistingGuest(guest);
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
