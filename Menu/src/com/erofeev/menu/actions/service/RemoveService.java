package com.erofeev.menu.actions.service;

import java.io.IOException;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class RemoveService implements IAction {
	private IReception model;	
	

	public RemoveService(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String name = Viewer.findEntity("Service");
		model.removeService(model.findServicebyName(name));		
	}
}