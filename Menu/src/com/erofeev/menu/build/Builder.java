package com.erofeev.menu.build;

import java.util.EnumMap;
import java.util.Map;

import com.erofeev.hotel.api.IReception;


import com.erofeev.menu.menus.AbstractMenu;
import com.erofeev.menu.menus.ActionAddMenu;
import com.erofeev.menu.menus.ActionChangeMenu;
import com.erofeev.menu.menus.ActionMenu;
import com.erofeev.menu.menus.ActionRemoveMenu;
import com.erofeev.menu.menus.FinishMenu;
import com.erofeev.menu.menus.MainMenu;
import com.erofeev.menu.menus.Menu;
import com.erofeev.menu.menus.StopMenu;
import com.erofeev.menu.menus.ViewGuestsMenu;
import com.erofeev.menu.menus.ViewMenu;
import com.erofeev.menu.menus.ViewRoomsMenu;
import com.erofeev.menu.menus.ViewServicesMenu;


public class Builder {
	private Map<Menu, AbstractMenu> menus = new EnumMap<>(Menu.class);
	private IReception model;
	
	

	public Builder(IReception model) {
		this.model = model;

	}

	public IReception getModel() {
		return model;
	}

	public void setModel(IReception model) {
		this.model = model;
	}

	

	public void createMenus() {	

		menus.put(Menu.MAIN, new MainMenu());
		menus.put(Menu.ACTION, new ActionMenu());
		menus.put(Menu.ACTIONADD, new ActionAddMenu(model));
		menus.put(Menu.ACTIONCHANGE, new ActionChangeMenu(model));
		menus.put(Menu.ACTIONREMOVE, new ActionRemoveMenu(model));
		menus.put(Menu.VIEW, new ViewMenu());
		menus.put(Menu.VIEWGUEST, new ViewGuestsMenu(model));
		menus.put(Menu.VIEWROOM, new ViewRoomsMenu(model));
		menus.put(Menu.VIEWSERVICE, new ViewServicesMenu(model));
		menus.put(Menu.FINISH, new FinishMenu());
		menus.put(Menu.STOP, new StopMenu());

		for (Menu menu : Menu.values()) {
			AbstractMenu m = menus.get(menu);				
			m.build();			
		}
	

	}

	public AbstractMenu getRootMenu() {
		return menus.get(Menu.MAIN);

	}

	public AbstractMenu getMenu(Menu menu) {
		return menus.get(menu);

	}

}
