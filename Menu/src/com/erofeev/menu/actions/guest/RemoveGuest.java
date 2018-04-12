
package com.erofeev.menu.actions.guest;

import java.io.IOException;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class RemoveGuest implements IAction {
	private IReception model;

	public RemoveGuest(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String name = Viewer.findEntity("Guest");
		Guest guest = model.findGuestbyName(name);
		model.removeGuest(guest);

	}

}