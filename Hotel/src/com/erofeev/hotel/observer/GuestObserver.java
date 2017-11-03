package com.erofeev.hotel.observer;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.api.Observer;
import com.erofeev.hotel.file.FileManager;

public class GuestObserver implements Observer {

	@Override
	public void update(IManager manager) {		
		FileManager.getInstance().saveToFile(manager);
		
	}

}
