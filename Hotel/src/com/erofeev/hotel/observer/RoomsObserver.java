package com.erofeev.hotel.observer;

import com.erofeev.hotel.api.Observer;
import com.erofeev.hotel.file.FileManager;
import com.erofeev.hotel.managers.AbstractManager;

public class RoomsObserver implements Observer {
	@Override
	public void update(AbstractManager manager) {
		FileManager.getInstance().saveToFile(manager);
	}

}
