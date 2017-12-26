package com.erofeev.menu.actions.service;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.api.IAction;

public class ExportServices implements IAction {
	private IReception model;

	public ExportServices(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {

	}

}