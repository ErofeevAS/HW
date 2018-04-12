package com.erofeev.menu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.builder.IBuilder;
import com.erofeev.menu.api.menucontroller.IMenuController;
import com.erofeev.menu.api.navigator.INavigator;
import com.erofeev.menu.builder.Builder;
import com.erofeev.menu.menuitems.MenuItem;
import com.erofeev.menu.menus.Menus;
import com.erofeev.menu.navigator.Navigator;

public class MenuController implements IMenuController {
	private static final Logger loggerMenuControllerManager = LogManager.getLogger(MenuController.class);

	private IBuilder builder;
	private INavigator navigator;	
	
	

	public MenuController() {
		super();
	}


	public MenuController(IReception model,IBuilder builder,INavigator navigator) {
		this.navigator = navigator;
		builder.setModel(model);
		this.builder = builder;
	}
	

	@Override
	public IBuilder getBuilder() {
		return builder;
	}

	@Override
	public void setBuilder(IBuilder builder) {
		this.builder = builder;
	}

	@Override
	public INavigator getNavigator() {
		return navigator;
	}

	@Override
	public void setNavigator(INavigator navigator) {
		this.navigator = navigator;
	}

	@Override
	public void run() {
		builder.createMenus();
		navigator.setCurrentMenu(builder.getRootMenu());

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

			while (true) {

				navigator.printMenu();
				Printer.print("choose line:");
				try {
					int index = Integer.parseInt(reader.readLine());
					Menus newMenu = navigator.navigate(index);
					MenuItem menuItem = navigator.getCurrentMenu().getMenuitems()[index];
					if (menuItem.getNextMenu().equals(Menus.STOP)) {
						break;
					}
					menuItem.doAction();
					navigator.setCurrentMenu(builder.getMenu(newMenu));
				} catch (NumberFormatException e) {
					loggerMenuControllerManager.warn("Wrong  DATA format, try again.  " + e);
				} catch (IllegalArgumentException e) {
					loggerMenuControllerManager.warn("Plz enter 0,1,2... , try again.  " + e);
				} catch (NullPointerException e) {
					loggerMenuControllerManager.warn("Cant found  entity, try agian. " + e);

				}

			}

		} catch (IOException e) {
			loggerMenuControllerManager.error("Can't readline  " + e);

		}

	}

}
