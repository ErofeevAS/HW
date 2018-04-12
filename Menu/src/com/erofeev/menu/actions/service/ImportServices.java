package com.erofeev.menu.actions.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.erofeev.annotation.csvwriter.CsvWriter;
import com.erofeev.annotation.csvwriter.TextFileManager;
import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
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

		//String fileName = Viewer.readLine();
		//List<String> lines = (ArrayList<String>) fileManager.readFromFile(fileName);
		//List<Service> services = serialCSV.parseCSV(lines);
		List<Service> services = CsvWriter.parseCSVServices();
		System.out.println(services);
		model.importServices(services);
	}
}