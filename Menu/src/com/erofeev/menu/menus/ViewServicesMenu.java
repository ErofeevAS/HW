package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.ViewAllServices;
import com.erofeev.menu.actions.ViewServiceSortedByPrice;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;



public class ViewServicesMenu extends AbstractMenu {	
	private IReception model;

	public ViewServicesMenu(IReception model) {
		this.model = model;
	}
	
	public void build() {
		AbstractMenuItem menuItemViewAllServices = new MenuItem("view services :", Menu.FINISH);
		AbstractMenuItem menuItemViewServiceSortedByPrice = new MenuItem("view services sorted by price :",
				Menu.FINISH);
		IAction viewAllServices = new ViewAllServices(model);
		IAction viewServiceSortedByPrice = new ViewServiceSortedByPrice(model);
		menuItemViewAllServices.setActiom(viewAllServices);
		menuItemViewServiceSortedByPrice.setActiom(viewServiceSortedByPrice);
		AbstractMenuItem[] menuItems = { menuItemViewAllServices, menuItemViewServiceSortedByPrice };
				
		this.setName(Menu.VIEWSERVICE.toString());
		this.setMenuitems(menuItems);
		
	}

}
