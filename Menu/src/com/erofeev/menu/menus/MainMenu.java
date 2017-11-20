package com.erofeev.menu.menus;

import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;



public class MainMenu extends  AbstractMenu {	

	public MainMenu(){
		this.build();
	}

	public void build() {
		AbstractMenuItem menuItemAction = new MenuItem("Action menu:", Menu.ACTION);
		AbstractMenuItem menuItemAView = new MenuItem("View menu:", Menu.VIEW);
		AbstractMenuItem[] menuItems = { menuItemAction, menuItemAView };		
		this.initDefault(menuItems);
		this.setName(Menu.MAIN.toString());
		this.setMenuitems(menuItems);
		
		
	}

}
