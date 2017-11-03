package com.erofeev.hotel.api;

public interface Observable {
	void removeGuestObserver(Observer o);

	void addGuestObserver(Observer o);

	void notifyGuestObservers();
	
	void removeRoomsObserver(Observer o);

	void addRoomsObserver(Observer o);

	void notifyRoomsObservers();
	
	void removeServicesObserver(Observer o);

	void addServicesObserver(Observer o);

	void notifyServicesObservers();

}
