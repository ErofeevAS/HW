package com.erofeev.hotel.ui.api;



import com.erofeev.hotel.api.IReception;

public interface AbstractController {	
	
	IReception getModel();

	AbstractView getView();

	void startController() ;

	void setModel(IReception model);

	void setView(AbstractView view);

	void addService();

	void addServiceToGuest();

	void addRoom();

	void getRoomDetails();

	void getRoomHistory();

	void getGuestPrice();

	void getGuestServices();

	void getAllRooms();

	void getAllEmptyRooms();

	void getAllGuests();

	void getAllServices();

	void removeRoom();

	void removeServiceToGuest();

	void occupyGuest();

	void evictGuest();

	void getGuestSortedByDate();

	void getGuestSortedByName();

	void getRoomsSortedByPrice();

	void getRoomsSortedByCapacity();

	void getRoomsSortedByStars();

	void getServiceSortedByPrice();

	void changeServicePrice();

	void changeRoomPrice();

	void changeRoomStatus();
	
	
}
