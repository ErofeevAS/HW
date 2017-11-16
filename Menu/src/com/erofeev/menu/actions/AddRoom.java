package com.erofeev.menu.actions;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;

import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class AddRoom implements IAction {
	private IReception model;

	public AddRoom(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IllegalArgumentException, IOException {
		Room newRoom;
		newRoom = Viewer.createRoom();
		model.addRoom(newRoom);

	}

}