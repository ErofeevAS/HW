package com.erofeev.menu.menuitems;

import java.io.IOException;

import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menus.Menus;

public class MenuItem {

	private String title;
	private IAction action;
	private Menus nextMenu;

	public MenuItem(String title, Menus menu) {
		this.setTitle(title);
		this.setNextMenu(menu);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IAction getActiom() {
		return action;
	}

	public void setAction(IAction actiom) {
		this.action = actiom;
	}

	public Menus getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menus nextMenu) {
		this.nextMenu = nextMenu;
	}

	public void doAction() throws IllegalArgumentException, IOException {
		this.getActiom().execute();
	}
}
