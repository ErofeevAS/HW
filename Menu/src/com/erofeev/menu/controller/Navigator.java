package com.erofeev.menu.controller;

import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.build.Menu;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menus.AbstractMenu;

public class Navigator {
	private AbstractMenu currentMenu;

	public AbstractMenu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(AbstractMenu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public void printMenu() {
		System.out.println((getCurrentMenu().getName()));
		AbstractMenuItem[] menuItems = currentMenu.getMenuitems();
		for (int i = 0; i < menuItems.length; i++) {
			Printer.print(i + " - " + menuItems[i].getTitle());
		}
	}

	public Menu navigate(Integer index) {
		try {
			Menu menu = currentMenu.getMenuitems()[index].getNextMenu();
			return menu;
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}

	}

}
