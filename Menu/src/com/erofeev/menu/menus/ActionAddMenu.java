package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.AddRoom;
import com.erofeev.menu.actions.AddService;
import com.erofeev.menu.actions.AddServiceToGuest;
import com.erofeev.menu.actions.OccupyGuest;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;


public class ActionAddMenu extends AbstractMenu {
	private IReception model;
	
	

	public ActionAddMenu(IReception model) {
		super();
		this.model = model;
	}


	public void build(){
		AbstractMenuItem menuItemAddRoom = new MenuItem("add room :", Menu.FINISH);
		AbstractMenuItem menuItemAddService = new MenuItem("add service :", Menu.FINISH);
		AbstractMenuItem menuItemOccupyGuest = new MenuItem("occupy guest :", Menu.FINISH);
		AbstractMenuItem menuItemAddServiceToGuest = new MenuItem("add service to guest :", Menu.FINISH);
		IAction addRoom = new AddRoom(model);
		IAction addService = new AddService(model);
		IAction occupyGuest = new OccupyGuest(model);
		IAction addServiceToGuest = new AddServiceToGuest(model);
		menuItemAddRoom.setActiom(addRoom);
		menuItemAddService.setActiom(addService);
		menuItemOccupyGuest.setActiom(occupyGuest);
		menuItemAddServiceToGuest.setActiom(addServiceToGuest);
		AbstractMenuItem[] menuItems = { menuItemAddRoom, menuItemAddService, menuItemOccupyGuest,
				menuItemAddServiceToGuest };		
		//this.initDefault(menuItems);
		this.setName(Menu.ACTIONADD.toString());
		this.setMenuitems(menuItems);
	}

}
