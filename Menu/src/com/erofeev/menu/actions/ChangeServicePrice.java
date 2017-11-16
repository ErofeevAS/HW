package com.erofeev.menu.actions;



import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ChangeServicePrice implements IAction {
	private IReception model;	

	public ChangeServicePrice(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
	
		String name = Viewer.findEntity("Service");
		Service service = model.findServicebyName(name);
		float price = Float.parseFloat(Viewer.changePrice());		
		model.changeServicePrice(service, price);
		
	}

}