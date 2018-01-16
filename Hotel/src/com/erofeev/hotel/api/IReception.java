package com.erofeev.hotel.api;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;

public interface IReception {
	public void addService(Service service);

	public void addRoom(Room room);

	public void addGuest(Guest guest);

	public void addServiceToGuest(Guest guest, Service service);

	public void removeServiceToGuest(Guest guest, Service service);

	public void removeService(Service service);

	public void removeRoom(Room room);

	public ArrayDeque<Guest> getRoomHistory(Room room);

	public float[] getGuestPrice(Guest guest);

	public ArrayList<Service> getGuestServices(Guest guest);

	public void evictGuest(Guest guest, Room room);

	public void evictGuest(Guest guest);

	public void occupyGuest(Guest guest, Room room);

	public ArrayList<Guest> getGuestSortedByData();

	public ArrayList<Guest> getGuestSortedByName();

	public ArrayList<Room> getRoomsSortedByPrice();

	public ArrayList<Room> getRoomsSortedByCapacity();

	public ArrayList<Room> getRoomsSortedByStars();

	public ArrayList<Service> getServiceSortedByPrice();

	public void changeRoomPrice(Room room, float price);

	public void changeServicePrice(Service service, float price);

	public void changeRoomStatus(Room room, RoomStatus status);

	public ArrayList<Room> getAllRooms();

	public ArrayList<Room> getAllEmptyRooms();

	public ArrayList<Guest> getAllGuests();

	public ArrayList<Service> getAllServices();

	public Guest findGuestbyName(String name);

	public Room findRoombyName(String name);

	public Service findServicebyName(String name);

	public Guest findGuestbyID(int id);

	public Room findRoombyID(int id);

	public Service findServicebyID(int id);

	public void initReception(ArrayList<String> parameters);

	public void importServices(ArrayList<Service> services);

	public void importRooms(ArrayList<Room> rooms);

	public void importGuests(ArrayList<Guest> guests);

	public void removeGuest(Guest guest);

}
