package com.erofeev.hotel.ui.api;


import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;

public interface AbstractView {

	String[] addService();

	String[] addRoom();

	String[] createGuest();

	String[] evictGuest();

	String removeRoom();

	String removeService();

	String[] removeServiceToGuest();

	String[] changeRoomPrice();

	String[] changeServicePrice();

	String[] changeRoomStatus();

	void unknownCommand();

	public void viewEmptyRooms(MyList<Room> rooms);

	public void viewAllRooms(MyList<Room> rooms);

	public void viewAllGuests(MyList<Guest> guests);

	public void viewAllServices(MyList<Service> services);

	public String findEntity(String type);

	public void viewEntity(IEntity entity);

	public void viewGuestPrice(float[] price);

	public void viewGuestServices(MyList<Service> services);

	public void viewGuestSortedByDate(MyList<Guest> guests);

	public void viewGuestSortedByName(MyList<Guest> guests);

	public void viewRoomsSortedByPrice(MyList<Room> rooms);

	public void viewRoomsSortedByCapacity(MyList<Room> rooms);

	public void viewRoomsSortedByStars(MyList<Room> rooms);

	public void viewServiceSortedByPrice(MyList<Service> services);

}
