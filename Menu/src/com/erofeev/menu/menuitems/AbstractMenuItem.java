package com.erofeev.menu.menuitems;


import java.io.IOException;

import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menus.Menu;


abstract public class AbstractMenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	abstract public  void doAction() throws IllegalArgumentException, IOException;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IAction getActiom() {
		return action;
	}

	public void setActiom(IAction actiom) {
		this.action = actiom;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

}
