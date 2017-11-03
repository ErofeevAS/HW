package com.erofeev.hotel.ui.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;
import com.erofeev.hotel.print.Printer;
import com.erofeev.hotel.ui.api.AbstractController;
import com.erofeev.hotel.ui.api.AbstractView;

public class CmdController implements AbstractController {
	private IReception model;
	private AbstractView view;
	private static volatile CmdController instance;
	private static final Logger loggerCmdController = LogManager.getLogger(CmdController.class);

	private CmdController() {
	};

	private void loggin(String log, Exception e) {

	}

	public static CmdController getInstance() {
		CmdController localInstance = instance;
		if (localInstance == null) {
			synchronized (CmdController.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CmdController();
				}
			}
		}
		return localInstance;
	}

	public void initCmdController(IReception model, AbstractView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public IReception getModel() {
		return model;
	}

	@Override
	public AbstractView getView() {
		return view;
	}

	@Override
	public void startController() {
		loggerCmdController.debug("CmdController on");
		while (true) {

			Printer.print("Enter command: ");
			BufferedReader reader = null;
			String readValue = "";
			try {
				reader = new BufferedReader(new InputStreamReader(System.in));
				readValue = reader.readLine();
			} catch (IOException e) {
				loggerCmdController.error("Cant read inputStream:" + e);

			}

			if (readValue.equals("stop")) {
				loggerCmdController.debug("CmdController off");
				break;
			} else {
				switch (readValue) {
				case "addService":
					this.addService();
					break;
				case "addRoom":
					this.addRoom();
					break;
				case "viewAllEmptyRooms":
					this.getAllEmptyRooms();
					break;

				case "viewAllRooms":
					this.getAllRooms();
					break;
				case "viewAllGuests":
					this.getAllGuests();
					break;
				case "viewAllServices":
					this.getAllServices();
					break;
				case "removeRoom":
					this.removeRoom();
					break;
				case "findGuest":
					this.findGuest();
					break;
				case "findRoom":
					this.findRoom();
					break;
				case "findService":
					this.findService();
					break;
				case "occupyGuest":
					this.occupyGuest();
					break;
				case "evictGuest":
					this.evictGuest();
					break;
				case "addServiceToGuest":
					this.addServiceToGuest();
					break;
				case "removeServiceToGuest":
					this.removeServiceToGuest();
					break;

				case "viewRoomDetails":
					this.getRoomDetails();
					break;

				case "viewRoomHistory":
					this.getRoomHistory();
					break;
				case "viewGuestPrice":
					this.getGuestPrice();
					break;

				case "viewGuestServices":
					this.getGuestServices();
					break;
				case "viewGuestSortedByDate":
					this.getGuestSortedByDate();
					break;
				case "viewGuestSortedByName":
					this.getGuestSortedByName();
					break;

				case "viewRoomsSortedByPrice":
					this.getRoomsSortedByPrice();
					break;
				case "viewRoomsSortedByStars":
					this.getRoomsSortedByStars();
					break;
				case "viewRoomsSortedByCapacity":
					this.getRoomsSortedByCapacity();
					break;
				case "viewServicesSortedByPrice":
					this.getServiceSortedByPrice();
					break;
				case "changeServicePrice":
					this.changeServicePrice();
					break;
				case "changeRoomPrice":
					this.changeRoomPrice();
					break;
				case "changeRoomStatus":
					this.changeRoomStatus();
					break;

				default:
					view.unknownCommand();
					break;
				}

			}
		}

	}

	@Override
	public void setModel(IReception model) {
		this.model = model;
	}

	@Override
	public void setView(AbstractView view) {
		this.view = view;
	}

	private Guest findGuest() {
		String name = view.findEntity("Guest");
		Guest guest = model.findGuestbyName(name);
		return guest;
	}

	private Room findRoom() {
		String name = view.findEntity("Room");
		Room room = model.findRoombyName(name);
		return room;
	}

	private Service findService() {
		String name = view.findEntity("Service");
		Service service = model.findServicebyName(name);
		return service;
	}

	private Guest createGuest() {
		Guest guest = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String[] strGuest = new String[4];
		strGuest = view.createGuest();
		String firstName = strGuest[0];
		String secondName = strGuest[1];
		try {
			Date arrivalDate = dateFormat.parse(strGuest[2]);
			Date leavingDate = dateFormat.parse(strGuest[3]);
			guest = new Guest(firstName, secondName, arrivalDate, leavingDate);
		} catch (ParseException e) {
			loggerCmdController.error("Wrong date format: " + e);
		}
		return guest;

	}

	@Override
	public void addService() {
		try {
			String[] strService = view.addService();
			Service service = new Service(strService[0], Float.parseFloat(strService[1]));
			model.addService(service);
		} catch (NumberFormatException e) {
			loggerCmdController.error("Wrong service price format: " + e);
		} catch (NullPointerException e) {
			loggerCmdController.error("Wrong service price format: string is NULL. " + e);
		}

	}

	@Override
	public void addServiceToGuest() {
		Service service = model.findServicebyName(view.findEntity("Service"));
		if (service == null) {
			Printer.print("Service not found");
			loggerCmdController.info("Service not found");

		} else {
			Guest guest = model.findGuestbyName(view.findEntity("Guest"));
			if (guest == null) {
				Printer.print("Guest not found");
				loggerCmdController.info("Guest not found");
			} else {
				model.addServiceToGuest(guest, service);
			}
		}

	}

	@Override
	public void addRoom() {
		try {
			String[] strRoom = view.addRoom();
			String number = strRoom[0];
			int stars = Integer.parseInt(strRoom[1]);
			float price = Float.parseFloat(strRoom[2]);
			int capacity = Integer.parseInt(strRoom[3]);
			Room room = new Room(number, stars, price, capacity);
			model.addRoom(room);
		}

		catch (NumberFormatException e) {
			loggerCmdController.info("Error: can't create Room, wrong number format: " + e);

		}

	}

	@Override
	public void getRoomDetails() {
		String name = view.findEntity("Room");
		Room room = model.findRoombyName(name);
		view.viewEntity(room);
	}

	// rework

	@Override
	public void getRoomHistory() {
		String name = view.findEntity("Room");
		Room room = model.findRoombyName(name);
		model.getRoomHistory(room);
	}

	@Override
	public void getGuestPrice() {
		String name = view.findEntity("Guest");
		Guest guest = model.findGuestbyName(name);
		view.viewGuestPrice(model.getGuestPrice(guest));

	}

	@Override
	public void getGuestServices() {
		String name = view.findEntity("Guest");
		Guest guest = model.findGuestbyName(name);
		MyList<Service> services = model.getGuestServices(guest);
		view.viewGuestServices(services);
	}

	@Override
	public void getAllRooms() {
		MyList<Room> rooms = model.getAllRooms();
		view.viewEmptyRooms(rooms);
	}

	@Override
	public void getAllEmptyRooms() {
		MyList<Room> emptyRooms = model.getAllEmptyRooms();
		view.viewEmptyRooms(emptyRooms);

	}

	@Override
	public void getAllGuests() {
		MyList<Guest> guests = model.getAllGuests();
		view.viewAllGuests(guests);

	}

	@Override
	public void getAllServices() {
		MyList<Service> services = model.getAllServices();
		view.viewAllServices(services);

	}

	@Override
	public void removeRoom() {
		model.removeRoom(this.findRoom());
		this.getAllRooms();

	}

	@Override
	public void removeServiceToGuest() {
		Service service = model.findServicebyName(view.findEntity("Service"));
		Guest guest = model.findGuestbyName(view.findEntity("Guest"));
		model.removeServiceToGuest(guest, service);
	}

	@Override
	public void occupyGuest() {
		Guest guest = this.createGuest();
		Room room = this.findRoom();
		model.occupyGuest(guest, room);

	}

	@Override
	public void evictGuest() {
		Guest guest = model.findGuestbyName(view.findEntity("Guest"));
		model.evictGuest(guest);
	}

	@Override
	public void getGuestSortedByDate() {
		MyList<Guest> guests = model.getGuestSortedByData();
		view.viewGuestSortedByDate(guests);

	}

	@Override
	public void getGuestSortedByName() {
		MyList<Guest> guests = model.getGuestSortedByName();
		view.viewGuestSortedByName(guests);

	}

	@Override
	public void getRoomsSortedByPrice() {
		MyList<Room> rooms = model.getRoomsSortedByPrice();
		view.viewRoomsSortedByPrice(rooms);

	}

	@Override
	public void getRoomsSortedByCapacity() {
		MyList<Room> rooms = model.getRoomsSortedByCapacity();
		view.viewRoomsSortedByCapacity(rooms);

	}

	@Override
	public void getRoomsSortedByStars() {
		MyList<Room> rooms = model.getRoomsSortedByStars();
		view.viewRoomsSortedByStars(rooms);

	}

	@Override
	public void getServiceSortedByPrice() {
		MyList<Service> services = model.getServiceSortedByPrice();
		view.viewServiceSortedByPrice(services);

	}

	@Override
	public void changeServicePrice() {
		String[] servicePrice = view.changeRoomPrice();
		Service serivice = model.findServicebyName(servicePrice[0]);
		float price = Float.parseFloat(servicePrice[1]);
		model.changeServicePrice(serivice, price);

	}

	@Override
	public void changeRoomPrice() {

		String[] roomPrice = view.changeRoomPrice();
		Room room = model.findRoombyName(roomPrice[0]);
		float price = 0f;
		try {
			price = Float.parseFloat(roomPrice[1]);
		} catch (NumberFormatException e) {
			loggerCmdController.error("Wrong room price format: " + e);
		}

		model.changeRoomPrice(room, price);
	}

	@Override
	public void changeRoomStatus() {

		String[] roomPrice = view.changeRoomStatus();
		Room room = model.findRoombyName(roomPrice[0]);
		String status = roomPrice[1];
		RoomStatus roomstatus = RoomStatus.SERVICED;
		try {
			switch (status) {
			case "REPAIRABLE":
				roomstatus = RoomStatus.REPAIRABLE;
				break;
			case "SERVICED":
				roomstatus = RoomStatus.SERVICED;
				break;
			default:
				throw new IllegalArgumentException();
			}
			model.changeRoomStatus(room, roomstatus);

		} catch (IllegalArgumentException e) {
			loggerCmdController.error("Wrong room status: " + e);

		}

	}

}
