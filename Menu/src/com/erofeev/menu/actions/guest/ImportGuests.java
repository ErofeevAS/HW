package com.erofeev.menu.actions.guest;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.serializator.SerializatorServiceCSV;

public class ImportGuests implements IAction {
	private IReception model;

	public ImportGuests(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		ArrayList<Service> services = model.getAllServices();

		SerializatorServiceCSV serialCSV = new SerializatorServiceCSV();
		TextFileManager fileManager = new TextFileManager();
		System.out.println("Guests was imported");

		// String[] serialServices = serialCSV.serialize(services);
		// fileManager.writeToFile(Viewer.getFileName(), serialServices);

	}

}