package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.MenuItem;

public class MainMenu extends AbstractMenu {

	public MainMenu() {
		this.build();
	}

	public void build() {
		MenuItem menuItemAction = new MenuItem("Action menu:", Menu.ACTION);
		MenuItem menuItemAView = new MenuItem("View menu:", Menu.VIEW);
		MenuItem[] menuItems = { menuItemAction, menuItemAView };
		this.initDefault(menuItems);
		this.setName(Menu.MAIN.toString());
		this.setMenuitems(menuItems);

	}

}
