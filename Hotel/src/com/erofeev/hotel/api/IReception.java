package com.erofeev.hotel.api;


import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;

public interface IReception {
	public void addService(Service service);

	public void addRoom(Room room);

	public void addServiceToGuest(Guest guest, Service service);

	public void removeServiceToGuest(Guest guest, Service service);

	public void removeService(Service service);

	public void removeRoom(Room room);

	//public void viewAllRooms();

	//public void viewAllEmptyRooms();

	//public void viewAllGuests();

	//public void viewAllServices();

	public void viewRoomDetails(Room room);

	public void viewRoomHistory(Room room);

	public void viewGuestPrice(Guest guest);

	public void viewGuestServices(Guest guest);

	public void evictGuest(Guest guest, Room room);
	
	public void evictGuest(Guest guest);

	public void occupyGuest(Guest guest, Room room);

	public void viewGuestSortedByData();

	public void viewGuestSortedByName();

	public void viewRoomsSortedByPrice();

	public void viewRoomsSortedByCapacity();

	public void viewRoomsSortedByStars();

	public void viewServiceSortedByPrice();
	
	public void changeRoomPrice(Room room, float price);

	public void changeServicePrice(Service service, float price);	

	public void changeRoomStatus(Room room, RoomStatus status);
	
	public MyList<Room> getAllRooms();
	
	public MyList<Room> getAllEmptyRooms();
	
	public MyList<Guest> getAllGuests();
	
	public MyList<Service> getAllServices();
	
	public Guest findGuestbyName(String name);
	
	public Room findRoombyName(String name);
	
	public Service findServicebyName(String name);

	
	

	
}
