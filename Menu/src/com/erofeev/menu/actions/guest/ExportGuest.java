
package com.erofeev.menu.actions.guest;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.serializator.SerializatorGuestCSV;

public class ExportGuest implements IAction {
	private IReception model;

	public ExportGuest(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		ArrayList<Guest> guests = model.getAllGuests();

		SerializatorGuestCSV serialCSV = new SerializatorGuestCSV();
		TextFileManager fileManager = new TextFileManager();

		String[] serialGuests = serialCSV.serialize(guests);
		fileManager.writeToFile(Viewer.getFileName(), serialGuests);

	}

}
