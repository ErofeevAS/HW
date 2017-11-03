package com.erofeev.hotel.ui.api;

public interface AbstractUI {
	 
	AbstractView createView();
	AbstractController createController();

}
