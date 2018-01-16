package com.erofeev.menu.actions.service;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.parsercsv.ParserServicesCSV;

public class ImportServices implements IAction {
	private IReception model;

	public ImportServices(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {

		ParserServicesCSV serialCSV = new ParserServicesCSV();
		TextFileManager fileManager = new TextFileManager();
		Printer.print("Enter file name:");

		String fileName = Viewer.readLine();
		ArrayList<String> lines = (ArrayList<String>) fileManager.readFromFile(fileName);
		ArrayList<Service> services = serialCSV.parseCSV(lines);

		model.importServices(services);
		// String[] serialServices = serialCSV.serialize(services);
		// fileManager.writeToFile(Viewer.getFileName(), serialServices);

	}

}