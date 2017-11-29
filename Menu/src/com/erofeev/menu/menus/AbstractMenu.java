package com.erofeev.menu.menus;

import java.util.Arrays;

import com.erofeev.menu.actions.NoAction;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.MenuItem;

public abstract class AbstractMenu {
	private String name;
	private MenuItem menuitems[];

	public String getName() {
		return name;
	}

	public AbstractMenu() {
		super();

	}

	public AbstractMenu(String name, MenuItem[] menuitems) {
		super();
		this.name = name;
		this.menuitems = menuitems;
	}

	public MenuItem[] getMenuitems() {
		return menuitems;
	}

	public void setMenuitems(MenuItem[] menuitems) {
		this.menuitems = menuitems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuItem[] initDefault(MenuItem[] menuItem) {
		IAction noAction = new NoAction();
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i].setActiom(noAction);
		}
		return menuItem;

	}

	abstract public void build();

	@Override
	public String toString() {
		return "AbstractMenu [name=" + name + ", menuitems=" + Arrays.toString(menuitems) + "]";
	}

}
