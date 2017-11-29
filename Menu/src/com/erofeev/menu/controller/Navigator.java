package com.erofeev.menu.controller;

import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.menuitems.MenuItem;
import com.erofeev.menu.menus.Menu;
import com.erofeev.menu.menus.Menus;

public class Navigator {
	private Menu currentMenu;

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public void printMenu() {
		System.out.println((getCurrentMenu().getName()));
		MenuItem[] menuItems = currentMenu.getMenuitems();
		for (int i = 0; i < menuItems.length; i++) {
			Printer.print(i + " - " + menuItems[i].getTitle());
		}
	}

	public Menus navigate(Integer index) {
		try {
			Menus menu = currentMenu.getMenuitems()[index].getNextMenu();

			return menu;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}

	}

}
