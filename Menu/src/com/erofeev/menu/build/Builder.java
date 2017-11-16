package com.erofeev.menu.build;

import java.util.EnumMap;
import java.util.Map;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.*;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.AbstractMenuItem;
import com.erofeev.menu.menuitems.MenuItem;

import com.erofeev.menu.menus.AbstractMenu;

public class Builder {
	private Map<Menu, AbstractMenu> table = new EnumMap<>(Menu.class);
	private IReception model;

	public Builder(IReception model) {
		this.model = model;

	}

	public IReception getModel() {
		return model;
	}

	public void setModel(IReception model) {
		this.model = model;
	}

	private AbstractMenuItem[] initDefault(AbstractMenuItem[] menuItem) {
		IAction noAction = new NoAction();
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i].setActiom(noAction);
		}
		return menuItem;

	}

	private void createFinishMenu() {
		AbstractMenuItem menuItemYes = new MenuItem("Yes ", Menu.STOP);
		AbstractMenuItem menuItemNo = new MenuItem("No", Menu.MAIN);
		AbstractMenuItem[] menuItems = { menuItemYes, menuItemNo };
		menuItems = this.initDefault(menuItems);
		table.put(Menu.FINISH, new AbstractMenu(Menu.FINISH.toString(), menuItems));
		table.put(Menu.STOP, new AbstractMenu(Menu.STOP.toString(), menuItems));

	}

	private void createMainMenu() {

		AbstractMenuItem menuItemAction = new MenuItem("Action menu:", Menu.ACTION);
		AbstractMenuItem menuItemAView = new MenuItem("View menu:", Menu.VIEW);
		AbstractMenuItem[] menuItems = { menuItemAction, menuItemAView };
		menuItems = this.initDefault(menuItems);
		table.put(Menu.MAIN, new AbstractMenu(Menu.MAIN.toString(), menuItems));

	}

	private void createViewMenu() {
		AbstractMenuItem menuItemGuest = new MenuItem("Guest menu:", Menu.VIEWGUEST);
		AbstractMenuItem menuItemRoom = new MenuItem("Room menu:", Menu.VIEWROOM);
		AbstractMenuItem menuItemService = new MenuItem("Service menu:", Menu.VIEWSERVICE);
		AbstractMenuItem[] menuItems = { menuItemGuest, menuItemRoom, menuItemService };
		menuItems = this.initDefault(menuItems);
		table.put(Menu.VIEW, new AbstractMenu(Menu.VIEW.toString(), menuItems));

	}

	private void createViewRoomMenu() {
		AbstractMenuItem menuItemViewAllRooms = new MenuItem("view rooms :", Menu.FINISH);
		AbstractMenuItem menuItemViewAllEmptyRooms = new MenuItem("view empty rooms :", Menu.FINISH);
		AbstractMenuItem menuItemViewRoomsSotredByCapacity = new MenuItem("view rooms sorted by capacity :",
				Menu.FINISH);
		AbstractMenuItem menuItemViewRoomsSotredByPrice = new MenuItem("view rooms sorted by price :", Menu.FINISH);
		AbstractMenuItem menuItemViewRoomsSotredByStars = new MenuItem("view rooms sorted by stars :", Menu.FINISH);
		AbstractMenuItem menuItemViewRoomDetails = new MenuItem("view room details :", Menu.FINISH);
		AbstractMenuItem menItemViewRoomHistory = new MenuItem("view roomhistory :", Menu.FINISH);

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
		AbstractMenuItem[] viewRoomItems = { menuItemViewAllRooms, menuItemViewAllEmptyRooms,
				menuItemViewRoomsSotredByCapacity, menuItemViewRoomsSotredByPrice, menuItemViewRoomsSotredByStars,
				menuItemViewRoomDetails, menItemViewRoomHistory };
		table.put(Menu.VIEWROOM, new AbstractMenu(Menu.VIEWROOM.toString(), viewRoomItems));

	}

	private void createViewGuestMenu() {
		AbstractMenuItem menuItemViewAllGuest = new MenuItem("view guests :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestServices = new MenuItem("view guest service :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestPrice = new MenuItem("view guest price :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestsSortedbyDate = new MenuItem("view guests sorted by date :", Menu.FINISH);
		AbstractMenuItem menuItemViewGuestsSortedbyName = new MenuItem("view guests sorted by name :", Menu.FINISH);
		IAction viewAllGuest = new ViewAllGuests(model);
		IAction viewGuestServices = new ViewGuestServices(model);
		IAction viewGuestPrice = new ViewGuestPrice(model);
		IAction viewGuestsSortedbyDate = new ViewGuestsSortedbyDate(model);
		IAction viewGuestsSortedbyName = new ViewGuestsSortedbyName(model);
		menuItemViewAllGuest.setActiom(viewAllGuest);
		menuItemViewGuestServices.setActiom(viewGuestServices);
		menuItemViewGuestPrice.setActiom(viewGuestPrice);
		menuItemViewGuestsSortedbyDate.setActiom(viewGuestsSortedbyDate);
		menuItemViewGuestsSortedbyName.setActiom(viewGuestsSortedbyName);
		AbstractMenuItem[] viewGuesItemst = { menuItemViewAllGuest, menuItemViewGuestServices, menuItemViewGuestPrice,
				menuItemViewGuestsSortedbyDate, menuItemViewGuestsSortedbyName };
		table.put(Menu.VIEWGUEST, new AbstractMenu(Menu.VIEWGUEST.toString(), viewGuesItemst));

	}

	private void createViewServiceMenu() {
		AbstractMenuItem menuItemViewAllServices = new MenuItem("view services :", Menu.FINISH);
		AbstractMenuItem menuItemViewServiceSortedByPrice = new MenuItem("view services sorted by price :",
				Menu.FINISH);
		IAction viewAllServices = new ViewAllServices(model);
		IAction viewServiceSortedByPrice = new ViewServiceSortedByPrice(model);
		menuItemViewAllServices.setActiom(viewAllServices);
		menuItemViewServiceSortedByPrice.setActiom(viewServiceSortedByPrice);
		AbstractMenuItem[] viewServiceItems = { menuItemViewAllServices, menuItemViewServiceSortedByPrice };
		table.put(Menu.VIEWSERVICE, new AbstractMenu(Menu.VIEWSERVICE.toString(), viewServiceItems));

	}

	private void createActionMenu() {
		AbstractMenuItem menuItemAdd = new MenuItem("add menu :", Menu.ACTIONADD);
		AbstractMenuItem menuItemRemove = new MenuItem("remove menu :", Menu.ACTIONREMOVE);
		AbstractMenuItem menuItemChange = new MenuItem("change menu :", Menu.ACTIONCHANGE);
		AbstractMenuItem[] menuItems = { menuItemAdd, menuItemRemove, menuItemChange };
		menuItems = this.initDefault(menuItems);
		table.put(Menu.ACTION, new AbstractMenu(Menu.ACTION.toString(), menuItems));
	}

	private void createActionAddMenu() {
		AbstractMenuItem menuItemAddRoom = new MenuItem("add room :", Menu.FINISH);
		AbstractMenuItem menuItemAddService = new MenuItem("add service :", Menu.FINISH);
		AbstractMenuItem menuItemOccupyGuest = new MenuItem("occupy guest :", Menu.FINISH);
		AbstractMenuItem menuItemAddServiceToGuest = new MenuItem("add service to guest :", Menu.FINISH);
		IAction addRoom = new AddRoom(model);
		IAction addService = new AddService(model);
		IAction occupyGuest = new OccupyGuest(model);
		IAction addServiceToGuest = new AddServiceToGuest(model);
		menuItemAddRoom.setActiom(addRoom);
		menuItemAddService.setActiom(addService);
		menuItemOccupyGuest.setActiom(occupyGuest);
		menuItemAddServiceToGuest.setActiom(addServiceToGuest);
		AbstractMenuItem[] actionAddItems = { menuItemAddRoom, menuItemAddService, menuItemOccupyGuest,
				menuItemAddServiceToGuest };
		table.put(Menu.ACTIONADD, new AbstractMenu(Menu.ACTIONADD.toString(), actionAddItems));
	}

	private void createActionRemoveMenu() {

		AbstractMenuItem menuItemRemoveRoom = new MenuItem("remove room :", Menu.FINISH);
		AbstractMenuItem menuItemRemoveService = new MenuItem("remove service :", Menu.FINISH);
		AbstractMenuItem menuItemEvictGuest = new MenuItem("evict guest :", Menu.FINISH);
		AbstractMenuItem menuItemRemoveServiceToGuest = new MenuItem("remove service from guest :", Menu.FINISH);
		IAction removeRoom = new RemoveRoom(model);
		IAction removeService = new RemoveService(model);
		IAction evictGuest = new EvictGuest(model);
		IAction removeServiceToGues = new RemoveServiceToGues(model);
		menuItemRemoveRoom.setActiom(removeRoom);
		menuItemRemoveService.setActiom(removeService);
		menuItemEvictGuest.setActiom(evictGuest);
		menuItemRemoveServiceToGuest.setActiom(removeServiceToGues);

		AbstractMenuItem[] actionRemoveItems = { menuItemRemoveRoom, menuItemRemoveService, menuItemEvictGuest,
				menuItemRemoveServiceToGuest };
		table.put(Menu.ACTIONREMOVE, new AbstractMenu(Menu.ACTIONREMOVE.toString(), actionRemoveItems));
	}

	private void createActionChangeMenu() {

		AbstractMenuItem menuItemChangeRoomPrice = new MenuItem("change room price :", Menu.FINISH);
		AbstractMenuItem menuItemChangeServicePrice = new MenuItem("change serivce price :", Menu.FINISH);
		AbstractMenuItem menuItemChangeRoomStatus = new MenuItem("change room status :", Menu.FINISH);

		IAction changeRoomPrice = new ChangeRoomPrice(model);
		IAction changeServicePrice = new ChangeServicePrice(model);
		IAction changeRoomStatus = new ChangeRoomStatus(model);
		menuItemChangeRoomPrice.setActiom(changeRoomPrice);
		menuItemChangeServicePrice.setActiom(changeServicePrice);
		menuItemChangeRoomStatus.setActiom(changeRoomStatus);

		AbstractMenuItem[] actionChangeItems = { menuItemChangeRoomPrice, menuItemChangeServicePrice,
				menuItemChangeRoomStatus };
		table.put(Menu.ACTIONCHANGE, new AbstractMenu(Menu.ACTIONCHANGE.toString(), actionChangeItems));

	}

	public void createMenus() {
		
		this.createMainMenu();
		this.createViewMenu();
		this.createActionMenu();
		this.createViewGuestMenu();
		this.createViewRoomMenu();
		this.createViewServiceMenu();
		this.createActionAddMenu();
		this.createActionChangeMenu();
		this.createActionRemoveMenu();
		this.createFinishMenu();

	}

	public AbstractMenu getRootMenu() {
		return table.get(Menu.MAIN);

	}

	public AbstractMenu getMenu(Menu menu) {
		return table.get(menu);

	}

}
