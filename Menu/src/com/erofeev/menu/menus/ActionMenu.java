package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;



public class ActionMenu extends AbstractMenu {

	public void build() {
		AbstractMenuItem menuItemAdd = new MenuItem("add menu :", Menu.ACTIONADD);
		AbstractMenuItem menuItemRemove = new MenuItem("remove menu :", Menu.ACTIONREMOVE);
		AbstractMenuItem menuItemChange = new MenuItem("change menu :", Menu.ACTIONCHANGE);
		AbstractMenuItem[] menuItems = { menuItemAdd, menuItemRemove, menuItemChange };
	
		this.initDefault(menuItems);
		this.setName(Menu.ACTION.toString());
		this.setMenuitems(menuItems);
		
	}

}
