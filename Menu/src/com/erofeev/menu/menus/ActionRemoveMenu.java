package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.EvictGuest;
import com.erofeev.menu.actions.RemoveRoom;
import com.erofeev.menu.actions.RemoveService;
import com.erofeev.menu.actions.RemoveServiceToGues;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;



public class ActionRemoveMenu extends AbstractMenu {	
	private IReception model;

	public ActionRemoveMenu(IReception model) {
		super();
		this.model = model;
	}
	

	public void build() {

		AbstractMenuItem menuItemRemoveRoom = new MenuItem("remove room :", Menu.FINISH);
		AbstractMenuItem menuItemRemoveService = new MenuItem("remove service :", Menu.FINISH);
		AbstractMenuItem menuItemEvictGuest = new MenuItem("evict guest :", Menu.FINISH);
		AbstractMenuItem menuItemRemoveServiceToGuest = new MenuItem("remove service from guest :", Menu.FINISH);
		IAction removeRoom = new RemoveRoom(model);
		IAction removeService = new RemoveService(model);
		IAction evictGuest = new EvictGuest(model);
		IAction removeServiceToGues = new RemoveServiceToGues(model);
		menuItemRemoveRoom.setActiom(removeRoom);
		menuItemRemoveService.setActiom(removeService);
		menuItemEvictGuest.setActiom(evictGuest);
		menuItemRemoveServiceToGuest.setActiom(removeServiceToGues);
		AbstractMenuItem[] menuItems = { menuItemRemoveRoom, menuItemRemoveService, menuItemEvictGuest,
				menuItemRemoveServiceToGuest };
		
		//this.initDefault(menuItems);
		this.setName(Menu.ACTIONREMOVE.toString());
		this.setMenuitems(menuItems);
		
	}

}
