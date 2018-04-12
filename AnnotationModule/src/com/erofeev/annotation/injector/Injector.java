package com.erofeev.annotation.injector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.properties.Config;

public class Injector {

	private static final Logger loggerInjector = LogManager.getLogger(Injector.class);

	public static Object createObject(Class interfaceType) {
		String className = "";
		if (interfaceType.getName().equals("com.erofeev.hotel.api.reception.IReception")) {
			className = Config.getConfig().get(6);
		}
		if (interfaceType.getName().equals("com.erofeev.menu.api.navigator.INavigator")) {
			className = Config.getConfig().get(7);
		}
		if (interfaceType.getName().equals("com.erofeev.menu.api.builder.IBuilder")) {
			className = Config.getConfig().get(8);
		}
		if (interfaceType.getName().equals("com.erofeev.menu.api.menucontroller.IMenuController")) {
			className = Config.getConfig().get(9);
		}

		Class c = null;

		try {
			c = Class.forName(className);
			Object obj = c.newInstance();
			loggerInjector.info(c.getSimpleName() + " was create");
			return obj;

		} catch (ClassNotFoundException e) {
			loggerInjector.error("Can't find class " + className);

		} catch (InstantiationException e) {
			loggerInjector.error("Can't create instatnce "+ className);

		} catch (IllegalAccessException e) {
			loggerInjector.error("Can't get access  to object "+ className);

		}

		return null;
	}

}
