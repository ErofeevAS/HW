package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.ChangeRoomPrice;
import com.erofeev.menu.actions.ChangeRoomStatus;
import com.erofeev.menu.actions.ChangeServicePrice;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;

public class ActionChangeMenu extends AbstractMenu {	
	private IReception model;

	public ActionChangeMenu(IReception model) {
		this.model = model;
	}
	

	public void build() {
		AbstractMenuItem menuItemChangeRoomPrice = new MenuItem("change room price :", Menu.FINISH);
		AbstractMenuItem menuItemChangeServicePrice = new MenuItem("change serivce price :", Menu.FINISH);
		AbstractMenuItem menuItemChangeRoomStatus = new MenuItem("change room status :", Menu.FINISH);

		IAction changeRoomPrice = new ChangeRoomPrice(model);
		IAction changeServicePrice = new ChangeServicePrice(model);
		IAction changeRoomStatus = new ChangeRoomStatus(model);
		menuItemChangeRoomPrice.setActiom(changeRoomPrice);
		menuItemChangeServicePrice.setActiom(changeServicePrice);
		menuItemChangeRoomStatus.setActiom(changeRoomStatus);

		AbstractMenuItem[] menuItems = { menuItemChangeRoomPrice, menuItemChangeServicePrice,
						menuItemChangeRoomStatus };
		
		//this.initDefault(menuItems);
		this.setName(Menu.ACTIONCHANGE.toString());
		this.setMenuitems(menuItems);
		
	}

}
