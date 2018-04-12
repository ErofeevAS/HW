package com.erofeev.menu.navigator;

import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.navigator.INavigator;
import com.erofeev.menu.menuitems.MenuItem;
import com.erofeev.menu.menus.Menu;
import com.erofeev.menu.menus.Menus;

public class Navigator implements INavigator {
	private Menu currentMenu;

	
	
	public Navigator() {
		super();
	}


	@Override
	public Menu getCurrentMenu() {
		return currentMenu;
	}

	
	@Override
	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	
	@Override
	public void printMenu() {
		Printer.print((getCurrentMenu().getName()));
		MenuItem[] menuItems = currentMenu.getMenuitems();
		for (int i = 0; i < menuItems.length; i++) {
			Printer.print(i + " - " + menuItems[i].getTitle());
		}
	}

	
	@Override
	public Menus navigate(Integer index) {
		try {
			Menus menu = currentMenu.getMenuitems()[index].getNextMenu();

			return menu;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}

	}

}
