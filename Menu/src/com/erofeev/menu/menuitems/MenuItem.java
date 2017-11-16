package com.erofeev.menu.menuitems;

import java.io.IOException;

import com.erofeev.menu.build.Menu;

public class MenuItem extends AbstractMenuItem {

	public MenuItem(String title, Menu menu) {		
		this.setTitle(title);
		this.setNextMenu(menu);		
	}
	
	public void doAction() throws IllegalArgumentException, IOException{
		this.getActiom().execute();
	}
}
