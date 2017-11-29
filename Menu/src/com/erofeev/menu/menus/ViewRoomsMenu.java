package com.erofeev.menu.menus;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.ViewAllEmptyRooms;
import com.erofeev.menu.actions.ViewAllRooms;
import com.erofeev.menu.actions.ViewRoomDetails;
import com.erofeev.menu.actions.ViewRoomHistory;
import com.erofeev.menu.actions.ViewRoomsSortedByCapacity;
import com.erofeev.menu.actions.ViewRoomsSortedByPrice;
import com.erofeev.menu.actions.ViewRoomsSortedByStars;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.MenuItem;

public class ViewRoomsMenu extends AbstractMenu {
	private IReception model;

	public ViewRoomsMenu(IReception model) {
		this.model = model;
	}

	public void build() {
		MenuItem menuItemViewAllRooms = new MenuItem("view rooms :", Menu.FINISH);
		MenuItem menuItemViewAllEmptyRooms = new MenuItem("view empty rooms :", Menu.FINISH);
		MenuItem menuItemViewRoomsSotredByCapacity = new MenuItem("view rooms sorted by capacity :", Menu.FINISH);
		MenuItem menuItemViewRoomsSotredByPrice = new MenuItem("view rooms sorted by price :", Menu.FINISH);
		MenuItem menuItemViewRoomsSotredByStars = new MenuItem("view rooms sorted by stars :", Menu.FINISH);
		MenuItem menuItemViewRoomDetails = new MenuItem("view room details :", Menu.FINISH);
		MenuItem menItemViewRoomHistory = new MenuItem("view roomhistory :", Menu.FINISH);

		IAction viewAllRooms = new ViewAllRooms(model);
		IAction viewAllEmptyRooms = new ViewAllEmptyRooms(model);
		IAction viewRoomsSotredByCapacity = new ViewRoomsSortedByCapacity(model);
		IAction viewRoomsSotredByStars = new ViewRoomsSortedByStars(model);
		IAction viewRoomsSotredByPrice = new ViewRoomsSortedByPrice(model);
		IAction viewRoomDetails = new ViewRoomDetails(model);
		IAction viewRoomHistory = new ViewRoomHistory(model);
		menuItemViewAllRooms.setActiom(viewAllRooms);
		menuItemViewAllEmptyRooms.setActiom(viewAllEmptyRooms);
		menuItemViewRoomsSotredByCapacity.setActiom(viewRoomsSotredByCapacity);
		menuItemViewRoomsSotredByStars.setActiom(viewRoomsSotredByStars);
		menuItemViewRoomsSotredByPrice.setActiom(viewRoomsSotredByPrice);
		menuItemViewRoomDetails.setActiom(viewRoomDetails);
		menItemViewRoomHistory.setActiom(viewRoomHistory);
		MenuItem[] menuItems = { menuItemViewAllRooms, menuItemViewAllEmptyRooms, menuItemViewRoomsSotredByCapacity,
				menuItemViewRoomsSotredByPrice, menuItemViewRoomsSotredByStars, menuItemViewRoomDetails,
				menItemViewRoomHistory };
		this.setName(Menu.VIEWROOM.toString());
		this.setMenuitems(menuItems);

	}

}