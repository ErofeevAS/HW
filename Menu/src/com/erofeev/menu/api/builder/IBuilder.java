package com.erofeev.menu.api.builder;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.menu.menus.Menu;
import com.erofeev.menu.menus.Menus;

public interface IBuilder {

	IReception getModel();

	void setModel(IReception model);

	Menu getRootMenu();

	Menu getMenu(Menus menu);

	void createMenus();

}