package com.erofeev.menu.build;

import java.util.EnumMap;
import java.util.Map;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.guest.AddServiceToGuest;
import com.erofeev.menu.actions.guest.EvictGuest;
import com.erofeev.menu.actions.guest.OccupyGuest;
import com.erofeev.menu.actions.guest.RemoveServiceToGues;
import com.erofeev.menu.actions.guest.ViewAllGuests;
import com.erofeev.menu.actions.guest.ViewGuestPrice;
import com.erofeev.menu.actions.guest.ViewGuestServices;
import com.erofeev.menu.actions.guest.ViewGuestsSortedbyDate;
import com.erofeev.menu.actions.guest.ViewGuestsSortedbyName;
import com.erofeev.menu.actions.room.AddRoom;
import com.erofeev.menu.actions.room.ChangeRoomPrice;
import com.erofeev.menu.actions.room.ChangeRoomStatus;
import com.erofeev.menu.actions.room.RemoveRoom;
import com.erofeev.menu.actions.room.ViewAllEmptyRooms;
import com.erofeev.menu.actions.room.ViewAllRooms;
import com.erofeev.menu.actions.room.ViewRoomDetails;
import com.erofeev.menu.actions.room.ViewRoomHistory;
import com.erofeev.menu.actions.room.ViewRoomsSortedByCapacity;
import com.erofeev.menu.actions.room.ViewRoomsSortedByPrice;
import com.erofeev.menu.actions.room.ViewRoomsSortedByStars;
import com.erofeev.menu.actions.service.AddService;
import com.erofeev.menu.actions.service.ChangeServicePrice;
import com.erofeev.menu.actions.service.RemoveService;
import com.erofeev.menu.actions.service.ViewAllServices;
import com.erofeev.menu.actions.service.ViewServiceSortedByPrice;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.menuitems.MenuItem;
import com.erofeev.menu.menus.Menu;
import com.erofeev.menu.menus.Menus;

public class Builder {
	private Map<Menus, Menu> menus = new EnumMap<>(Menus.class);
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

	private void createMainMenu() {
		Menu mainMenu = new Menu();
		MenuItem menuItemAction = new MenuItem("Action menu:", Menus.ACTION);
		MenuItem menuItemAView = new MenuItem("View menu:", Menus.VIEW);
		MenuItem[] menuItems = { menuItemAction, menuItemAView };

		mainMenu.initDefault(menuItems);
		mainMenu.setName(Menus.MAIN.toString());
		mainMenu.setMenuitems(menuItems);
		menus.put(Menus.MAIN, mainMenu);

	}

	private void createViewMenu() {
		Menu viewMenu = new Menu();

		MenuItem menuItemGuest = new MenuItem("Guest menu:", Menus.VIEWGUEST);
		MenuItem menuItemRoom = new MenuItem("Room menu:", Menus.VIEWROOM);
		MenuItem menuItemService = new MenuItem("Service menu:", Menus.VIEWSERVICE);
		MenuItem[] menuItems = { menuItemGuest, menuItemRoom, menuItemService };

		viewMenu.initDefault(menuItems);
		viewMenu.setName(Menus.VIEW.toString());
		viewMenu.setMenuitems(menuItems);
		menus.put(Menus.VIEW, viewMenu);

	}

	private void createViewRoomMenu() {
		Menu viewRoomMenu = new Menu();
		MenuItem menuItemViewAllRooms = new MenuItem("view rooms :", Menus.FINISH);
		MenuItem menuItemViewAllEmptyRooms = new MenuItem("view empty rooms :", Menus.FINISH);
		MenuItem menuItemViewRoomsSotredByCapacity = new MenuItem("view rooms sorted by capacity :", Menus.FINISH);
		MenuItem menuItemViewRoomsSotredByPrice = new MenuItem("view rooms sorted by price :", Menus.FINISH);
		MenuItem menuItemViewRoomsSotredByStars = new MenuItem("view rooms sorted by stars :", Menus.FINISH);
		MenuItem menuItemViewRoomDetails = new MenuItem("view room details :", Menus.FINISH);
		MenuItem menItemViewRoomHistory = new MenuItem("view roomhistory :", Menus.FINISH);

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
		viewRoomMenu.setName(Menus.VIEWROOM.toString());
		viewRoomMenu.setMenuitems(menuItems);
		menus.put(Menus.VIEWROOM, viewRoomMenu);

	}

	private void createViewGuestMenu() {
		Menu viewGuestMenu = new Menu();
		MenuItem menuItemViewAllGuest = new MenuItem("view guests :", Menus.FINISH);
		MenuItem menuItemViewGuestServices = new MenuItem("view guest service :", Menus.FINISH);
		MenuItem menuItemViewGuestPrice = new MenuItem("view guest price :", Menus.FINISH);
		MenuItem menuItemViewGuestsSortedbyDate = new MenuItem("view guests sorted by date :", Menus.FINISH);
		MenuItem menuItemViewGuestsSortedbyName = new MenuItem("view guests sorted by name :", Menus.FINISH);
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
		MenuItem[] menuItems = { menuItemViewAllGuest, menuItemViewGuestServices, menuItemViewGuestPrice,
				menuItemViewGuestsSortedbyDate, menuItemViewGuestsSortedbyName };
		viewGuestMenu.setName(Menus.VIEWGUEST.toString());
		viewGuestMenu.setMenuitems(menuItems);
		menus.put(Menus.VIEWGUEST, viewGuestMenu);

	}

	private void createViewServiceMenu() {
		Menu viewServiceMenu = new Menu();
		MenuItem menuItemViewAllServices = new MenuItem("view services :", Menus.FINISH);
		MenuItem menuItemViewServiceSortedByPrice = new MenuItem("view services sorted by price :", Menus.FINISH);
		IAction viewAllServices = new ViewAllServices(model);
		IAction viewServiceSortedByPrice = new ViewServiceSortedByPrice(model);
		menuItemViewAllServices.setActiom(viewAllServices);
		menuItemViewServiceSortedByPrice.setActiom(viewServiceSortedByPrice);
		MenuItem[] menuItems = { menuItemViewAllServices, menuItemViewServiceSortedByPrice };
		viewServiceMenu.setName(Menus.VIEWSERVICE.toString());
		viewServiceMenu.setMenuitems(menuItems);
		menus.put(Menus.VIEWSERVICE, viewServiceMenu);

	}

	private void createActionMenu() {
		Menu viewActionMenu = new Menu();
		MenuItem menuItemAdd = new MenuItem("add menu :", Menus.ACTIONADD);
		MenuItem menuItemRemove = new MenuItem("remove menu :", Menus.ACTIONREMOVE);
		MenuItem menuItemChange = new MenuItem("change menu :", Menus.ACTIONCHANGE);
		MenuItem[] menuItems = { menuItemAdd, menuItemRemove, menuItemChange };
		viewActionMenu.initDefault(menuItems);
		viewActionMenu.setName(Menus.ACTION.toString());
		viewActionMenu.setMenuitems(menuItems);
		menus.put(Menus.ACTION, viewActionMenu);
	}

	private void createActionAddMenu() {
		Menu viewActionAddMenu = new Menu();
		MenuItem menuItemAddRoom = new MenuItem("add room :", Menus.FINISH);
		MenuItem menuItemAddService = new MenuItem("add service :", Menus.FINISH);
		MenuItem menuItemOccupyGuest = new MenuItem("occupy guest :", Menus.FINISH);
		MenuItem menuItemAddServiceToGuest = new MenuItem("add service to guest :", Menus.FINISH);
		IAction addRoom = new AddRoom(model);
		IAction addService = new AddService(model);
		IAction occupyGuest = new OccupyGuest(model);
		IAction addServiceToGuest = new AddServiceToGuest(model);
		menuItemAddRoom.setActiom(addRoom);
		menuItemAddService.setActiom(addService);
		menuItemOccupyGuest.setActiom(occupyGuest);
		menuItemAddServiceToGuest.setActiom(addServiceToGuest);
		MenuItem[] menuItems = { menuItemAddRoom, menuItemAddService, menuItemOccupyGuest, menuItemAddServiceToGuest };
		viewActionAddMenu.setName(Menus.ACTIONADD.toString());
		viewActionAddMenu.setMenuitems(menuItems);
		menus.put(Menus.ACTIONADD, viewActionAddMenu);
	}

	private void createActionRemoveMenu() {
		Menu viewActionRemoveMenu = new Menu();
		MenuItem menuItemRemoveRoom = new MenuItem("remove room :", Menus.FINISH);
		MenuItem menuItemRemoveService = new MenuItem("remove service :", Menus.FINISH);
		MenuItem menuItemEvictGuest = new MenuItem("evict guest :", Menus.FINISH);
		MenuItem menuItemRemoveServiceToGuest = new MenuItem("remove service from guest :", Menus.FINISH);
		IAction removeRoom = new RemoveRoom(model);
		IAction removeService = new RemoveService(model);
		IAction evictGuest = new EvictGuest(model);
		IAction removeServiceToGues = new RemoveServiceToGues(model);
		menuItemRemoveRoom.setActiom(removeRoom);
		menuItemRemoveService.setActiom(removeService);
		menuItemEvictGuest.setActiom(evictGuest);
		menuItemRemoveServiceToGuest.setActiom(removeServiceToGues);
		MenuItem[] menuItems = { menuItemRemoveRoom, menuItemRemoveService, menuItemEvictGuest,
				menuItemRemoveServiceToGuest };
		viewActionRemoveMenu.setName(Menus.ACTIONREMOVE.toString());
		viewActionRemoveMenu.setMenuitems(menuItems);
		menus.put(Menus.ACTIONREMOVE, viewActionRemoveMenu);
	}

	private void createActionChangeMenu() {
		Menu viewActionChangeMenu = new Menu();
		MenuItem menuItemChangeRoomPrice = new MenuItem("change room price :", Menus.FINISH);
		MenuItem menuItemChangeServicePrice = new MenuItem("change serivce price :", Menus.FINISH);
		MenuItem menuItemChangeRoomStatus = new MenuItem("change room status :", Menus.FINISH);
		IAction changeRoomPrice = new ChangeRoomPrice(model);
		IAction changeServicePrice = new ChangeServicePrice(model);
		IAction changeRoomStatus = new ChangeRoomStatus(model);
		menuItemChangeRoomPrice.setActiom(changeRoomPrice);
		menuItemChangeServicePrice.setActiom(changeServicePrice);
		menuItemChangeRoomStatus.setActiom(changeRoomStatus);
		MenuItem[] menuItems = { menuItemChangeRoomPrice, menuItemChangeServicePrice, menuItemChangeRoomStatus };
		viewActionChangeMenu.setName(Menus.ACTIONCHANGE.toString());
		viewActionChangeMenu.setMenuitems(menuItems);
		menus.put(Menus.ACTIONCHANGE, viewActionChangeMenu);

	}

	private void createFinishMenu() {
		Menu viewFinishMenu = new Menu();
		MenuItem menuItemYes = new MenuItem("Yes ", Menus.STOP);
		MenuItem menuItemNo = new MenuItem("No", Menus.MAIN);
		MenuItem[] menuItems = { menuItemYes, menuItemNo };
		viewFinishMenu.initDefault(menuItems);
		viewFinishMenu.setName(Menus.FINISH.toString());
		viewFinishMenu.setMenuitems(menuItems);
		menus.put(Menus.FINISH, viewFinishMenu);
	}

	public Menu getRootMenu() {
		return menus.get(Menus.MAIN);

	}

	public Menu getMenu(Menus menu) {
		return menus.get(menu);

	}

}
