package com.erofeev.menu.actions.room;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.parsercsv.ParserRoomsCSV;

public class ExportRooms implements IAction {
	private IReception model;

	public ExportRooms(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		ArrayList<Room> rooms = model.getAllRooms();

		ParserRoomsCSV serialCSV = new ParserRoomsCSV();
		TextFileManager fileManager = new TextFileManager();

		String[] serialServices = serialCSV.listToCSV(rooms);
		fileManager.writeToFile(Viewer.getFileName(), serialServices);

	}

}