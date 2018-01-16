package com.erofeev.menu.actions.room;

import java.io.IOException;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
import com.erofeev.menu.export.TextFileManager;
import com.erofeev.menu.parsercsv.ParserRoomsCSV;

public class ImportRooms implements IAction {
	private IReception model;

	public ImportRooms(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {

		ParserRoomsCSV serialCSV = new ParserRoomsCSV();
		TextFileManager fileManager = new TextFileManager();
		Printer.print("Enter file name:");
		String fileName = Viewer.readLine();
		ArrayList<String> lines = (ArrayList<String>) fileManager.readFromFile(fileName);
		ArrayList<Room> rooms = serialCSV.parseCSV(lines, model);

		model.importRooms(rooms);
	}

}