package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;

import com.erofeev.menu.actions.ViewAllGuests;
import com.erofeev.menu.actions.ViewGuestPrice;
import com.erofeev.menu.actions.ViewGuestServices;
import com.erofeev.menu.actions.ViewGuestsSortedbyDate;
import com.erofeev.menu.actions.ViewGuestsSortedbyName;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;


public class ViewGuestsMenu extends AbstractMenu {	
	private IReception model;

	public ViewGuestsMenu(IReception model) {
		this.model = model;
	}
	

	public void build() {
		AbstractMenuItem menuItemViewAllGuest = new MenuItem("view guests :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestServices = new MenuItem("view guest service :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestPrice = new MenuItem("view guest price :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestsSortedbyDate = new MenuItem("view guests sorted by date :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestsSortedbyName = new MenuItem("view guests sorted by name :", Menu.FINISH);
		IAction viewAllGuest = new ViewAllGuests(model);
		IAction viewGuestServices = new ViewGuestServices(model);
		IAction viewGuestPrice = new ViewGuestPrice(model);
		IAction viewGuestsSortedbyDate = new ViewGuestsSortedbyDate(model);
		IAction viewGuestsSortedbyName = new ViewGuestsSortedbyName(model);
		menuItemViewAllGuest.setActiom(viewAllGuest);
		menuItemViewGuestServices.setActiom(viewGuestServices);
		menuItemViewGuestPrice.setActiom(viewGuestPrice);
		menuItemViewGuestsSortedbyDate.setActiom(viewGuestsSortedbyDate);
		menuItemViewGuestsSortedbyName.setActiom(viewGuestsSortedbyName);
		AbstractMenuItem[] menuItems = { menuItemViewAllGuest, menuItemViewGuestServices, menuItemViewGuestPrice,
				menuItemViewGuestsSortedbyDate, menuItemViewGuestsSortedbyName };
		
		//this.initDefault(menuItems);
		this.setName(Menu.VIEWGUEST.toString());
		this.setMenuitems(menuItems);
		
	}

}
