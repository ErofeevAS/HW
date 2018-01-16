package com.erofeev.menu.actions.guest;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.parsercsv.ParserGuestsCSV;

public class ImportGuests implements IAction {
	private IReception model;

	public ImportGuests(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		ParserGuestsCSV serialCSV = new ParserGuestsCSV();
		TextFileManager fileManager = new TextFileManager();
		Printer.print("Enter file name:");
		String fileName = Viewer.readLine();
		ArrayList<String> lines = (ArrayList<String>) fileManager.readFromFile(fileName);
		ArrayList<Guest> guests = serialCSV.parseCSV(lines, model);

		model.importGuests(guests);

	}

}