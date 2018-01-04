package com.erofeev.menu.actions.service;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.serializator.SerializatorServiceCSV;

public class ImportServices implements IAction {
	private IReception model;

	public ImportServices(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {

		SerializatorServiceCSV serialCSV = new SerializatorServiceCSV();
		TextFileManager fileManager = new TextFileManager();
		System.out.println("Enter file name:");

		String fileName = Viewer.readLine();
		ArrayList<String> lines = (ArrayList<String>) fileManager.readFromFile(fileName);

		ArrayList<Service> services = serialCSV.desrialize(lines);

		for (Service service : services) {
			System.out.println(service);
		}
		model.importServices(services);
		// String[] serialServices = serialCSV.serialize(services);
		// fileManager.writeToFile(Viewer.getFileName(), serialServices);

	}

}