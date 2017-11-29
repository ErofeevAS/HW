package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.MenuItem;

public class ActionMenu extends AbstractMenu {

	public void build() {
		MenuItem menuItemAdd = new MenuItem("add menu :", Menu.ACTIONADD);
		MenuItem menuItemRemove = new MenuItem("remove menu :", Menu.ACTIONREMOVE);
		MenuItem menuItemChange = new MenuItem("change menu :", Menu.ACTIONCHANGE);
		MenuItem[] menuItems = { menuItemAdd, menuItemRemove, menuItemChange };

		this.initDefault(menuItems);
		this.setName(Menu.ACTION.toString());
		this.setMenuitems(menuItems);

	}

}
