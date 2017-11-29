package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.ViewAllServices;
import com.erofeev.menu.actions.ViewServiceSortedByPrice;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.MenuItem;

public class ViewServicesMenu extends AbstractMenu {
	private IReception model;

	public ViewServicesMenu(IReception model) {
		this.model = model;
	}

	public void build() {
		MenuItem menuItemViewAllServices = new MenuItem("view services :", Menu.FINISH);
		MenuItem menuItemViewServiceSortedByPrice = new MenuItem("view services sorted by price :", Menu.FINISH);
		IAction viewAllServices = new ViewAllServices(model);
		IAction viewServiceSortedByPrice = new ViewServiceSortedByPrice(model);
		menuItemViewAllServices.setActiom(viewAllServices);
		menuItemViewServiceSortedByPrice.setActiom(viewServiceSortedByPrice);
		MenuItem[] menuItems = { menuItemViewAllServices, menuItemViewServiceSortedByPrice };

		this.setName(Menu.VIEWSERVICE.toString());
		this.setMenuitems(menuItems);

	}

}
