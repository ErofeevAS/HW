package com.erofeev.hotel.api;

import com.erofeev.hotel.managers.AbstractManager;

public interface Observer {
	void update(AbstractManager manager);

}
