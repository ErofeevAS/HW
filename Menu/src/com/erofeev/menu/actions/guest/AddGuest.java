
package com.erofeev.menu.actions.guest;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class AddGuest implements IAction {
	private IReception model;

	public AddGuest(IReception model) {
		super();
		this.model = model;
	}

	@Override
	public void execute() throws IllegalArgumentException, IOException {
		Guest guest = Viewer.createGuest();
		model.addGuest(guest);
	}

}
