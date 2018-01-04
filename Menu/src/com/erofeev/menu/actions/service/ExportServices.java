package com.erofeev.menu.actions.service;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.serializator.SerializatorServiceCSV;

public class ExportServices implements IAction {
	private IReception model;

	public ExportServices(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		ArrayList<Service> services = model.getAllServices();

		SerializatorServiceCSV serialCSV = new SerializatorServiceCSV();
		TextFileManager fileManager = new TextFileManager();

		String[] serialServices = serialCSV.serialize(services);
		fileManager.writeToFile(Viewer.getFileName(), serialServices);

	}

}