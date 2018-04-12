package com.erofeev.hotel.observer;

import com.erofeev.annotation.csvwriter.CsvWriter;
import com.erofeev.annotation.csvwriter.FileManager;
import com.erofeev.annotation.handler.AnnotationsHandler;
import com.erofeev.hotel.api.observer.Observer;
import com.erofeev.hotel.managers.AbstractManager;
import com.erofeev.hotel.print.Printer;

public class RoomsObserver implements Observer {
	@Override
	public void update(AbstractManager manager) {
		FileManager.getInstance().saveToFile(manager);
		AnnotationsHandler.getAllEntitiesFromObject(manager.getAll());
		CsvWriter.write(AnnotationsHandler.getRooms());
		Printer.print("Rooms update");
	}
}
