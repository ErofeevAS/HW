
package com.erofeev.hotel.observer;

import java.io.IOException;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.api.Observer;
import com.erofeev.hotel.file.FileManager;

public class ServicesObserver implements Observer {

	@Override
	public void update(IManager manager) {
		try {
		FileManager.getInstance().saveToFile(manager);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		
	}

}
