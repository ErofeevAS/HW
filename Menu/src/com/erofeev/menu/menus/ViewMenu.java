package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;



public class ViewMenu extends AbstractMenu{
	
	public void build(){
		AbstractMenuItem menuItemGuest = new MenuItem("Guest menu:", Menu.VIEWGUEST);
		AbstractMenuItem menuItemRoom = new MenuItem("Room menu:", Menu.VIEWROOM);
		AbstractMenuItem menuItemService = new MenuItem("Service menu:", Menu.VIEWSERVICE);
		AbstractMenuItem[] menuItems = { menuItemGuest, menuItemRoom, menuItemService };
		
		this.initDefault(menuItems);
		this.setName(Menu.VIEW.toString());
		this.setMenuitems(menuItems);
		
	}

}
