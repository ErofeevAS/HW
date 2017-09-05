package com.erofeev.hotel.reception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.danco.training.TextFileWorker;
import com.erofeev.hotel.api.IReception;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;
import com.erofeev.hotel.mylist.MyList;
import com.erofeev.hotel.sort.GuestSortedByDate;
import com.erofeev.hotel.sort.GuestSotredByName;
import com.erofeev.hotel.sort.RoomsSortedByCapacity;
import com.erofeev.hotel.sort.RoomsSortedByPrice;
import com.erofeev.hotel.sort.RoomsSortedByStars;
import com.erofeev.hotel.sort.ServiceSortedByPrice;

public class Reception implements IReception {
	private RoomManager rooms;
	private GuestManager guests;
	private ServicesManager services;

	public Reception() {
		this.rooms = new RoomManager();
		this.guests = new GuestManager();
		this.services = new ServicesManager();
	}

	public RoomManager getRooms() {
		return rooms;
	}

	public GuestManager getGuests() {
		return guests;
	}

	public void setGuests(GuestManager guests) {
		this.guests = guests;
	}

	public ServicesManager getServices() {
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
		System.out.println(room);
	}

	@Override
	public void viewRoomHistory(Room room) {
		MyList<Guest> guestHistory = guests.getGuestsHistory();
		System.out.println("Room History:");
		for (int i = 0; i < guestHistory.length(); i++) {
			if ((guestHistory.get(i).getRoom()).equals(room)) {
				System.out.println(guestHistory.get(i));
			}
		}
	}

	@Override
	public void viewGuestPrice(Guest guest) {
		float servicesPrice = guests.getGuestServicesPrice(guest);
		float roomPrice = guests.getGuestRoomPrice(guest);
		float totalPrice = servicesPrice + roomPrice;

		System.out.println("Room price: " + roomPrice);
		System.out.println("Services price: " + servicesPrice);
		System.out.println("Total price: " + totalPrice);

	}

	@Override
	public void evicGuest(Guest guest) {
		guest.getRoom().setEmpty(true);
		guests.remove(guest);
	}

	@Override
	public void occupyGuest(Guest guest, Room room) {
		room.setEmpty(false);
		guest.setRoom(room);
		guests.add(guest);
	}

	@Override
	public void saveToFile(String TEST_FILE) throws IOException {
		String[] strGuest = guests.read();
		String[] strRooms = rooms.read();
		String[] strServices = services.read();
		int arrayLenght = strGuest.length + strRooms.length + strServices.length;
		String[] testValues = new String[arrayLenght];
		testValues[0] = "";

		String[] tempValue = new String[strGuest.length + strRooms.length];
		System.arraycopy(strGuest, 0, tempValue, 0, strGuest.length);
		System.arraycopy(strRooms, 0, tempValue, strGuest.length, strRooms.length);
		System.arraycopy(tempValue, 0, testValues, 0, tempValue.length);
		System.arraycopy(strServices, 0, testValues, tempValue.length, strServices.length);

		Path filePath = Paths.get(TEST_FILE);
		Files.deleteIfExists(filePath);
		Files.createFile(filePath);

		try {
			TextFileWorker fileWorker = new TextFileWorker(TEST_FILE);
			fileWorker.writeToFile(testValues);
			String[] readedValues = fileWorker.readFromFile();

			for (int i = 0; i < testValues.length; i++) {
				if (!readedValues[i].equals(testValues[i])) {
					throw new RuntimeException("Error. Not equal values: " + readedValues[i] + " and " + testValues[i]);
				}
			}
			System.out.println("File was saved");
		} finally {

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
		room.setStatus(status);

	}

	@Override
	public void viewGuestSortedByName() {
		Guest[] currentGuests = convertGuestsCollectionToArray();

		Arrays.sort(currentGuests, new GuestSotredByName());
		System.out.println("Guest sorted by name: ");
		viewArray(currentGuests);
	}

	@Override
	public void viewGuestSortedByData() {
		Guest[] currentGuests = convertGuestsCollectionToArray();
		Arrays.sort(currentGuests, new GuestSortedByDate());
		System.out.println("Guest sorted by Date: ");
		viewArray(currentGuests);
	}

	@Override
	public void viewServiceSortedByPrice() {
		Service[] currentServices = convertServicesCollectionToArray();
		Arrays.sort(currentServices, new ServiceSortedByPrice());
		System.out.println("Services sorted by price: ");
		viewArray(currentServices);
	}

	@Override
	public void viewRoomsSortedByCapacity() {
		Room[] currentRooms = convertRoomsCollectionToArray();
		Arrays.sort(currentRooms, new RoomsSortedByCapacity());
		System.out.println("Rooms sorted by capacity: ");
		viewArray(currentRooms);
	}

	@Override
	public void viewRoomsSortedByStars() {
		Room[] currentRooms = convertRoomsCollectionToArray();
		Arrays.sort(currentRooms, new RoomsSortedByStars());
		System.out.println("Rooms sorted by stars: ");
		viewArray(currentRooms);
	}

	@Override
	public void viewRoomsSortedByPrice() {
		Room[] currentRooms = convertRoomsCollectionToArray();
		Arrays.sort(currentRooms, new RoomsSortedByPrice());
		System.out.println("Rooms sorted by price: ");
		viewArray(currentRooms);
	}

	@Override
	public void addService(Service service) {
		services.add(service);

	}

	@Override
	public void addRoom(Room room) {
		rooms.add(room);

	}

	@Override
	public void removeService(Service service) {
		services.remove(service);

	}

	@Override
	public void removeRoom(Room room) {
		rooms.remove(room);

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
			System.out.println(list.get(i));
		}
	}

	private void viewArray(Object[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
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
		guest.addGuestService(service);
		
		
	}

	@Override
	public void removeServiceToGuest(Guest guest, Service service) {
		guest.removeGuestService(service);
		
	}
	
	

}
