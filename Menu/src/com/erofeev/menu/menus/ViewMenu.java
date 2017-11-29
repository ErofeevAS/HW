package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.MenuItem;

public class ViewMenu extends AbstractMenu {

	public void build() {
		MenuItem menuItemGuest = new MenuItem("Guest menu:", Menu.VIEWGUEST);
		MenuItem menuItemRoom = new MenuItem("Room menu:", Menu.VIEWROOM);
		MenuItem menuItemService = new MenuItem("Service menu:", Menu.VIEWSERVICE);
		MenuItem[] menuItems = { menuItemGuest, menuItemRoom, menuItemService };

		this.initDefault(menuItems);
		this.setName(Menu.VIEW.toString());
		this.setMenuitems(menuItems);

	}

}
