package com.erofeev.menu.actions.service;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.parsercsv.ParserServicesCSV;

public class ExportServices implements IAction {
	private IReception model;

	public ExportServices(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		ArrayList<Service> services = model.getAllServices();

		ParserServicesCSV serialCSV = new ParserServicesCSV();
		TextFileManager fileManager = new TextFileManager();

		String[] serialServices = serialCSV.listToCSV(services);
		fileManager.writeToFile(Viewer.getFileName(), serialServices);

	}

}