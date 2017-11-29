package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.MenuItem;

public class FinishMenu extends AbstractMenu {

	public void build() {
		MenuItem menuItemYes = new MenuItem("Yes ", Menu.STOP);
		MenuItem menuItemNo = new MenuItem("No", Menu.MAIN);
		MenuItem[] menuItems = { menuItemYes, menuItemNo };
		this.initDefault(menuItems);
		this.setName(Menu.FINISH.toString());
		this.setMenuitems(menuItems);

	}

}