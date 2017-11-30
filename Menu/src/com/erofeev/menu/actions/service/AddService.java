package com.erofeev.menu.actions.service;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;

import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class AddService implements IAction {
	private IReception model;

	public AddService(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IllegalArgumentException, IOException {
		Service service;

		service = Viewer.createService();
		model.addService(service);

	}

}