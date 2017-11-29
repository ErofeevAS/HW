package com.erofeev.menu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.build.Builder;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menus.Menu;

public class MenuController {

	private Builder builder;
	private Navigator navigator;

	private static final Logger loggerMenuControllerManager = LogManager.getLogger(MenuController.class);

	public MenuController(IReception model) {
		this.navigator = new Navigator();
		this.builder = new Builder(model);
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	public Navigator getNavigator() {
		return navigator;
	}

	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}

	public void run() {
		builder.createMenus();
		navigator.setCurrentMenu(builder.getRootMenu());
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

			while (true) {

				navigator.printMenu();
				Printer.print("choose line:");
				try {
					int index = Integer.parseInt(reader.readLine());
					Menu newMenu = navigator.navigate(index);
					AbstractMenuItem menuItem = navigator.getCurrentMenu().getMenuitems()[index];
					if (menuItem.getNextMenu().equals(Menu.STOP)) {
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
