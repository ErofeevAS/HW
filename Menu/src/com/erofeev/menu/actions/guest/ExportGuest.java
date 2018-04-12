
package com.erofeev.menu.actions.guest;


import java.io.IOException;
import java.util.List;

import com.erofeev.annotation.csvwriter.TextFileManager;
import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.parsercsv.ParserGuestsCSV;

public class ExportGuest implements IAction {
	private IReception model;

	public ExportGuest(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		List<Guest> guests = model.getAllGuests();

		ParserGuestsCSV serialCSV = new ParserGuestsCSV();
		TextFileManager fileManager = new TextFileManager();

		String[] serialGuests = serialCSV.listToCSV(guests);
		fileManager.writeToFile(Viewer.getFileName(), serialGuests);
	}
}
