package com.erofeev.menu.api.navigator;

import com.erofeev.menu.menus.Menu;
import com.erofeev.menu.menus.Menus;

public interface INavigator {

	Menu getCurrentMenu();

	void setCurrentMenu(Menu currentMenu);

	void printMenu();

	Menus navigate(Integer index);

}