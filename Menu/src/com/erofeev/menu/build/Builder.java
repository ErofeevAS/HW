package com.erofeev.menu.build;

import java.util.EnumMap;
import java.util.Map;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.actions.NoAction;
import com.erofeev.menu.actions.guest.AddGuest;
import com.erofeev.menu.actions.guest.AddServiceToGuest;
import com.erofeev.menu.actions.guest.EvictGuest;
import com.erofeev.menu.actions.guest.ExportGuest;
import com.erofeev.menu.actions.guest.ImportGuests;
import com.erofeev.menu.actions.guest.OccupyGuest;
import com.erofeev.menu.actions.guest.RemoveGuest;
import com.erofeev.menu.actions.guest.RemoveServiceToGues;
import com.erofeev.menu.actions.guest.ViewAllGuests;
import com.erofeev.menu.actions.guest.ViewGuestPrice;
import com.erofeev.menu.actions.guest.ViewGuestServices;
import com.erofeev.menu.actions.guest.ViewGuestsSortedbyDate;
import com.erofeev.menu.actions.guest.ViewGuestsSortedbyName;
import com.erofeev.menu.actions.room.AddRoom;
import com.erofeev.menu.actions.room.ChangeRoomPrice;
import com.erofeev.menu.actions.room.ChangeRoomStatus;
import com.erofeev.menu.actions.room.CloneRoom;
import com.erofeev.menu.actions.room.ExportRooms;
import com.erofeev.menu.actions.room.ImportRooms;
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
import com.erofeev.menu.actions.service.ExportServices;
import com.erofeev.menu.actions.service.ImportServices;
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
		this.createCloneMenu();
		this.createCloneRoomMenu();

		this.createExportMenu();
		this.createExportServiceMenu();
		this.createExportRoomMenu();
		this.createExportGuestMenu();

		this.createImportMenu();
		this.createImportGuestsMenu();
		this.createImportRoomsMenu();
		this.createImportServicesMenu();

	}

	private void createMainMenu() {
		Menu mainMenu = new Menu();
		MenuItem menuItemAction = new MenuItem("Action menu:", Menus.ACTION);
		MenuItem menuItemAView = new MenuItem("View menu:", Menus.VIEW);
		MenuItem menuItemClone = new MenuItem("Clone menu:", Menus.CLONE);
		MenuItem menuItemExport = new MenuItem("Export menu:", Menus.EXPORT);
		MenuItem menuItemImport = new MenuItem("Import menu:", Menus.IMPORT);
		MenuItem[] menuItems = { menuItemAction, menuItemAView, menuItemClone, menuItemExport, menuItemImport };

		mainMenu.initDefault(menuItems);
		mainMenu.setName(Menus.MAIN.toString());
		mainMenu.setMenuitems(menuItems);
		menus.put(Menus.MAIN, mainMenu);

	}

	private void createCloneMenu() {
		Menu cloneMenu = new Menu();

		MenuItem menuItemCloneRoom = new MenuItem("Clone menu:", Menus.CLONEROOM);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.MAIN);
		MenuItem[] menuItems = { menuItemCloneRoom, menuItemBack };
		cloneMenu.initDefault(menuItems);
		cloneMenu.setName(Menus.CLONE.toString());
		cloneMenu.setMenuitems(menuItems);
		menus.put(Menus.CLONE, cloneMenu);
	}

	private void createCloneRoomMenu() {
		Menu cloneRoomMenu = new Menu();
		MenuItem menuItemmodifyRoom = new MenuItem("modify room :", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.CLONE);
		IAction cloneRoom = new CloneRoom(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemmodifyRoom.setAction(cloneRoom);
		MenuItem[] menuItems = { menuItemmodifyRoom, menuItemBack };
		cloneRoomMenu.setName(Menus.CLONEROOM.toString());
		cloneRoomMenu.setMenuitems(menuItems);
		menus.put(Menus.CLONEROOM, cloneRoomMenu);
	}

	private void createExportMenu() {
		Menu exportMenu = new Menu();

		MenuItem menuItemExportRoom = new MenuItem("Export services menu:", Menus.EXPORTSERVICE);
		MenuItem menuItemExportService = new MenuItem("Export rooms menu:", Menus.EXPORTROOM);
		MenuItem menuItemExprotGuest = new MenuItem("Export guests menu:", Menus.EXPORTGUEST);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.MAIN);
		MenuItem[] menuItems = { menuItemExportRoom, menuItemExportService, menuItemExprotGuest, menuItemBack };
		exportMenu.initDefault(menuItems);
		exportMenu.setName(Menus.EXPORT.toString());
		exportMenu.setMenuitems(menuItems);
		menus.put(Menus.EXPORT, exportMenu);
	}

	private void createExportServiceMenu() {
		Menu exportMenu = new Menu();

		MenuItem menuItemExportService = new MenuItem("Export service:", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.EXPORT);
		MenuItem[] menuItems = { menuItemExportService, menuItemBack };
		IAction exportService = new ExportServices(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemExportService.setAction(exportService);
		exportMenu.setName(Menus.EXPORTSERVICE.toString());
		exportMenu.setMenuitems(menuItems);
		menus.put(Menus.EXPORTSERVICE, exportMenu);
	}

	private void createExportRoomMenu() {
		Menu exportMenu = new Menu();

		MenuItem menuItemExprotRoom = new MenuItem("Export rooms:", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.EXPORT);
		MenuItem[] menuItems = { menuItemExprotRoom, menuItemBack };
		IAction exportRooms = new ExportRooms(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemExprotRoom.setAction(exportRooms);
		exportMenu.setName(Menus.EXPORTROOM.toString());
		exportMenu.setMenuitems(menuItems);
		menus.put(Menus.EXPORTROOM, exportMenu);
	}

	private void createExportGuestMenu() {
		Menu exportMenu = new Menu();

		MenuItem menuItemCloneRoom = new MenuItem("Export guests:", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.EXPORT);
		MenuItem[] menuItems = { menuItemCloneRoom, menuItemBack };
		IAction exportGuest = new ExportGuest(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemCloneRoom.setAction(exportGuest);
		exportMenu.setName(Menus.EXPORTGUEST.toString());
		exportMenu.setMenuitems(menuItems);
		menus.put(Menus.EXPORTGUEST, exportMenu);
	}

	private void createImportMenu() {
		Menu importmenu = new Menu();
		MenuItem menuItemImportRoom = new MenuItem("Import services menu:", Menus.IMPORTSERVICES);
		MenuItem menuItemImportService = new MenuItem("Import rooms menu:", Menus.IMPORTROOMS);
		MenuItem menuItemImportGuest = new MenuItem("Import guests menu:", Menus.IMPORTGUESTS);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.MAIN);
		MenuItem[] menuItems = { menuItemImportRoom, menuItemImportService, menuItemImportGuest, menuItemBack };
		importmenu.initDefault(menuItems);
		importmenu.setName(Menus.IMPORT.toString());
		importmenu.setMenuitems(menuItems);
		menus.put(Menus.IMPORT, importmenu);
	}

	private void createImportServicesMenu() {
		Menu importmenu = new Menu();

		MenuItem menuItemImportService = new MenuItem("Import service:", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.IMPORT);
		MenuItem[] menuItems = { menuItemImportService, menuItemBack };
		IAction importServices = new ImportServices(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemImportService.setAction(importServices);
		importmenu.setName(Menus.IMPORTSERVICES.toString());
		importmenu.setMenuitems(menuItems);
		menus.put(Menus.IMPORTSERVICES, importmenu);
	}

	private void createImportGuestsMenu() {
		Menu importmenu = new Menu();

		MenuItem menuItemImport = new MenuItem("Import guests:", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.IMPORT);
		MenuItem[] menuItems = { menuItemImport, menuItemBack };
		IAction importGuests = new ImportGuests(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemImport.setAction(importGuests);
		importmenu.setName(Menus.IMPORTGUESTS.toString());
		importmenu.setMenuitems(menuItems);
		menus.put(Menus.IMPORTGUESTS, importmenu);
	}

	private void createImportRoomsMenu() {
		Menu importmenu = new Menu();

		MenuItem menuItemImportRoom = new MenuItem("Import rooms:", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.IMPORT);
		MenuItem[] menuItems = { menuItemImportRoom, menuItemBack };
		IAction importRooms = new ImportRooms(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemImportRoom.setAction(importRooms);
		importmenu.setName(Menus.IMPORTROOMS.toString());
		importmenu.setMenuitems(menuItems);
		menus.put(Menus.IMPORTROOMS, importmenu);
	}

	private void createViewMenu() {
		Menu viewMenu = new Menu();

		MenuItem menuItemGuest = new MenuItem("Guest menu:", Menus.VIEWGUEST);
		MenuItem menuItemRoom = new MenuItem("Room menu:", Menus.VIEWROOM);
		MenuItem menuItemService = new MenuItem("Service menu:", Menus.VIEWSERVICE);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.MAIN);
		MenuItem[] menuItems = { menuItemGuest, menuItemRoom, menuItemService, menuItemBack };

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
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.VIEW);

		IAction viewAllRooms = new ViewAllRooms(model);
		IAction viewAllEmptyRooms = new ViewAllEmptyRooms(model);
		IAction viewRoomsSotredByCapacity = new ViewRoomsSortedByCapacity(model);
		IAction viewRoomsSotredByStars = new ViewRoomsSortedByStars(model);
		IAction viewRoomsSotredByPrice = new ViewRoomsSortedByPrice(model);
		IAction viewRoomDetails = new ViewRoomDetails(model);
		IAction viewRoomHistory = new ViewRoomHistory(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemViewAllRooms.setAction(viewAllRooms);
		menuItemViewAllEmptyRooms.setAction(viewAllEmptyRooms);
		menuItemViewRoomsSotredByCapacity.setAction(viewRoomsSotredByCapacity);
		menuItemViewRoomsSotredByStars.setAction(viewRoomsSotredByStars);
		menuItemViewRoomsSotredByPrice.setAction(viewRoomsSotredByPrice);
		menuItemViewRoomDetails.setAction(viewRoomDetails);
		menItemViewRoomHistory.setAction(viewRoomHistory);
		MenuItem[] menuItems = { menuItemViewAllRooms, menuItemViewAllEmptyRooms, menuItemViewRoomsSotredByCapacity,
				menuItemViewRoomsSotredByPrice, menuItemViewRoomsSotredByStars, menuItemViewRoomDetails,
				menItemViewRoomHistory, menuItemBack };
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
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.VIEW);
		IAction viewAllGuest = new ViewAllGuests(model);
		IAction viewGuestServices = new ViewGuestServices(model);
		IAction viewGuestPrice = new ViewGuestPrice(model);
		IAction viewGuestsSortedbyDate = new ViewGuestsSortedbyDate(model);
		IAction viewGuestsSortedbyName = new ViewGuestsSortedbyName(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemViewAllGuest.setAction(viewAllGuest);
		menuItemViewGuestServices.setAction(viewGuestServices);
		menuItemViewGuestPrice.setAction(viewGuestPrice);
		menuItemViewGuestsSortedbyDate.setAction(viewGuestsSortedbyDate);
		menuItemViewGuestsSortedbyName.setAction(viewGuestsSortedbyName);
		MenuItem[] menuItems = { menuItemViewAllGuest, menuItemViewGuestServices, menuItemViewGuestPrice,
				menuItemViewGuestsSortedbyDate, menuItemViewGuestsSortedbyName, menuItemBack };
		viewGuestMenu.setName(Menus.VIEWGUEST.toString());
		viewGuestMenu.setMenuitems(menuItems);
		menus.put(Menus.VIEWGUEST, viewGuestMenu);

	}

	private void createViewServiceMenu() {
		Menu viewServiceMenu = new Menu();
		MenuItem menuItemViewAllServices = new MenuItem("view services :", Menus.FINISH);
		MenuItem menuItemViewServiceSortedByPrice = new MenuItem("view services sorted by price :", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.VIEW);
		IAction viewAllServices = new ViewAllServices(model);
		IAction viewServiceSortedByPrice = new ViewServiceSortedByPrice(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		menuItemViewAllServices.setAction(viewAllServices);
		menuItemViewServiceSortedByPrice.setAction(viewServiceSortedByPrice);
		MenuItem[] menuItems = { menuItemViewAllServices, menuItemViewServiceSortedByPrice, menuItemBack };
		viewServiceMenu.setName(Menus.VIEWSERVICE.toString());
		viewServiceMenu.setMenuitems(menuItems);
		menus.put(Menus.VIEWSERVICE, viewServiceMenu);

	}

	private void createActionMenu() {
		Menu viewActionMenu = new Menu();
		MenuItem menuItemAdd = new MenuItem("add menu :", Menus.ACTIONADD);
		MenuItem menuItemRemove = new MenuItem("remove menu :", Menus.ACTIONREMOVE);
		MenuItem menuItemChange = new MenuItem("change menu :", Menus.ACTIONCHANGE);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.MAIN);

		MenuItem[] menuItems = { menuItemAdd, menuItemRemove, menuItemChange, menuItemBack };
		viewActionMenu.initDefault(menuItems);
		viewActionMenu.setName(Menus.ACTION.toString());
		viewActionMenu.setMenuitems(menuItems);
		menus.put(Menus.ACTION, viewActionMenu);
	}

	private void createActionAddMenu() {
		Menu viewActionAddMenu = new Menu();
		MenuItem menuItemAddRoom = new MenuItem("add room :", Menus.FINISH);
		MenuItem menuItemAddService = new MenuItem("add service :", Menus.FINISH);
		MenuItem menuItemAddGuest = new MenuItem("add guest :", Menus.FINISH);
		MenuItem menuItemOccupyGuest = new MenuItem("occupy guest :", Menus.FINISH);
		MenuItem menuItemAddServiceToGuest = new MenuItem("add service to guest :", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.ACTION);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);
		IAction addRoom = new AddRoom(model);
		IAction addService = new AddService(model);
		IAction addGuest = new AddGuest(model);
		IAction occupyGuest = new OccupyGuest(model);
		IAction addServiceToGuest = new AddServiceToGuest(model);
		menuItemAddRoom.setAction(addRoom);
		menuItemAddService.setAction(addService);
		menuItemAddGuest.setAction(addGuest);
		menuItemOccupyGuest.setAction(occupyGuest);
		menuItemAddServiceToGuest.setAction(addServiceToGuest);
		MenuItem[] menuItems = { menuItemAddRoom, menuItemAddService, menuItemAddGuest, menuItemOccupyGuest,
				menuItemAddServiceToGuest, menuItemBack };
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
		MenuItem menuItemRemoveGuest = new MenuItem("remove guest :", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.ACTION);
		IAction removeRoom = new RemoveRoom(model);
		IAction removeService = new RemoveService(model);
		IAction evictGuest = new EvictGuest(model);
		IAction removeServiceToGues = new RemoveServiceToGues(model);
		IAction removeGuest = new RemoveGuest(model);
		IAction noAction = new NoAction();
		menuItemBack.setAction(noAction);

		menuItemRemoveRoom.setAction(removeRoom);
		menuItemRemoveService.setAction(removeService);
		menuItemEvictGuest.setAction(evictGuest);
		menuItemEvictGuest.setAction(removeGuest);
		menuItemRemoveServiceToGuest.setAction(removeServiceToGues);
		MenuItem[] menuItems = { menuItemRemoveRoom, menuItemRemoveService, menuItemEvictGuest,
				menuItemRemoveServiceToGuest, menuItemRemoveGuest, menuItemBack };
		viewActionRemoveMenu.setName(Menus.ACTIONREMOVE.toString());
		viewActionRemoveMenu.setMenuitems(menuItems);
		menus.put(Menus.ACTIONREMOVE, viewActionRemoveMenu);
	}

	private void createActionChangeMenu() {
		Menu viewActionChangeMenu = new Menu();
		MenuItem menuItemChangeRoomPrice = new MenuItem("change room price :", Menus.FINISH);
		MenuItem menuItemChangeServicePrice = new MenuItem("change serivce price :", Menus.FINISH);
		MenuItem menuItemChangeRoomStatus = new MenuItem("change room status :", Menus.FINISH);
		MenuItem menuItemBack = new MenuItem("back to previous menu :", Menus.ACTION);
		IAction changeRoomPrice = new ChangeRoomPrice(model);
		IAction changeServicePrice = new ChangeServicePrice(model);
		IAction changeRoomStatus = new ChangeRoomStatus(model);
		IAction noAction = new NoAction();

		menuItemBack.setAction(noAction);
		menuItemChangeRoomPrice.setAction(changeRoomPrice);
		menuItemChangeServicePrice.setAction(changeServicePrice);
		menuItemChangeRoomStatus.setAction(changeRoomStatus);
		MenuItem[] menuItems = { menuItemChangeRoomPrice, menuItemChangeServicePrice, menuItemChangeRoomStatus,
				menuItemBack };
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
