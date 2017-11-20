package com.erofeev.menu.menus;



import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;



public class FinishMenu extends AbstractMenu {


	public void build() {
		AbstractMenuItem menuItemYes = new MenuItem("Yes ", Menu.STOP);
		AbstractMenuItem menuItemNo = new MenuItem("No", Menu.MAIN);
		AbstractMenuItem[] menuItems = { menuItemYes, menuItemNo };	
		this.initDefault(menuItems);
		this.setName(Menu.FINISH.toString());
		this.setMenuitems(menuItems);
		
	}

}