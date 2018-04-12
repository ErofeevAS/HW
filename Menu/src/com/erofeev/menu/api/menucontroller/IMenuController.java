package com.erofeev.menu.api.menucontroller;

import com.erofeev.menu.api.builder.IBuilder;
import com.erofeev.menu.api.navigator.INavigator;

public interface IMenuController {

	IBuilder getBuilder();

	void setBuilder(IBuilder builder);

	INavigator getNavigator();

	void setNavigator(INavigator navigator);

	void run();

}