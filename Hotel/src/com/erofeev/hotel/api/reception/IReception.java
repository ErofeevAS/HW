package com.erofeev.hotel.api.reception;


import java.util.List;

import com.erofeev.hotel.api.observer.Observer;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;

public interface IReception {

	void initReception(List<String> parameters);

	List<Room> getAllRooms();

	List<Room> getAllEmptyRooms();

	List<Guest> getAllGuests();

	List<Service> getAllServices();

	Guest findGuestbyName(String name);

	Room findRoombyName(String name);

	Service findServicebyName(String name);

	List<Service> getGuestServices(Guest guest);

	List<Guest> getRoomHistory(Room room);

	float[] getGuestPrice(Guest guest);

	void evictGuest(Guest guest, Room room);

	void evictGuest(Guest guest);

	void occupyGuest(Guest guest, Room room);

	void occupyGuest(String fullName, Room room);

	void changeRoomPrice(Room room, float price);

	void changeServicePrice(Service service, float price);

	void changeRoomStatus(Room room, RoomStatus status);

	List<Guest> getGuestSortedByName() throws ClassCastException;

	List<Guest> getGuestSortedByData() throws ClassCastException;

	List<Service> getServiceSortedByPrice() throws ClassCastException;

	List<Room> getRoomsSortedByCapacity() throws ClassCastException;

	List<Room> getRoomsSortedByStars() throws ClassCastException;

	List<Room> getRoomsSortedByPrice() throws ClassCastException;

	void addService(Service service);

	void addRoom(Room room);

	void addRooms(List<Room> newRooms);

	void addServices(List<Service> newServices);

	void addGuests(List<Guest> newGuests);

	void addGuest(Guest newGuests);

	void removeService(Service service);

	void removeRoom(Room room);

	void removeGuest(Guest guest);

	void addServiceToGuest(Guest guest, Service service);

	void removeServiceToGuest(Guest guest, Service service);

	void addGuestObserver(Observer o);

	void removeGuestObserver(Observer o);

	void notifyGuestObservers();

	void removeRoomsObserver(Observer o);

	void addRoomsObserver(Observer o);

	void notifyRoomsObservers();

	void removeServicesObserver(Observer o);

	void addServicesObserver(Observer o);

	void notifyServicesObservers();

	int getRoomHistorySize();

	void setRoomHistorySize(int roomHistorySize);

	boolean isChangeStatusFlag();

	void setChangeStatusFlag(boolean changeStatusFlag);

	String getROOMS_FILE();

	void setROOMS_FILE(String rOOMS_FILE);

	String getGUESTS_FILE();

	void setGUESTS_FILE(String gUESTS_FILE);

	String getSERVICES_FILE();

	void setSERVICES_FILE(String sERVICES_FILE);

	void importServices(List<Service> services);

	void importRooms(List<Room> rooms);

	void importGuests(List<Guest> guests);

	Guest findGuestbyID(int id);

	Room findRoombyID(int id);

	Service findServicebyID(int id);

}