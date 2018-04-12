package com.erofeev.menu.actions.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.erofeev.annotation.csvwriter.TextFileManager;
import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;
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
		List<String> lines = (ArrayList<String>) fileManager.readFromFile(fileName);
		List<Room> rooms = serialCSV.parseCSV(lines, model);
		model.importRooms(rooms);
	}

}