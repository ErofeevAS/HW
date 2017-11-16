package com.erofeev.menu.menus;
import com.erofeev.menu.menuitems.AbstractMenuItem;

public  class AbstractMenu {
	private String name;	
	private AbstractMenuItem menuitems[];

	public String getName() {
		return name;
	}	
	

	public AbstractMenu() {
		super();
		
		
	}	

	public AbstractMenu(String name, AbstractMenuItem[] menuitems) {
		super();
		this.name = name;
		this.menuitems = menuitems;
	}


	public AbstractMenuItem[] getMenuitems() {
		return menuitems;
	}


	public void setMenuitems(AbstractMenuItem[] menuitems) {
		this.menuitems = menuitems;
	}

	public void setName(String name) {
		this.name = name;
	}



}
