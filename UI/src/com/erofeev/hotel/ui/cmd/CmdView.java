package com.erofeev.hotel.ui.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;
import com.erofeev.hotel.print.Printer;
import com.erofeev.hotel.ui.api.AbstractView;

public class CmdView implements AbstractView {

	private static volatile CmdView instance;
	private static final Logger loggerCmdView = LogManager.getLogger(CmdView.class);

	private CmdView() {
	}

	public static CmdView getInstance() {
		CmdView localInstance = instance;
		if (localInstance == null) {
			synchronized (CmdView.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CmdView();
				}
			}
		}
		return localInstance;
	}

	private void viewCmd(MyList list) {
		for (int i = 0; i < list.length(); i++) {
			Printer.print(list.get(i));
		}
	}

	private void loggin(Exception e) {
		loggerCmdView.error("Cant read inputStream:" + e);
	}

	@Override
	public String[] addService() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] service = new String[2];
		try {
			Printer.print("Enter Service name:");
			service[0] = reader.readLine();
			Printer.print("Enter Service price:");
			service[1] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return service;
	}

	@Override
	public String[] addRoom() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] room = new String[4];
		try {
			Printer.print("Enter Room name:");
			room[0] = reader.readLine();
			Printer.print("Enter Room stars:");
			room[1] = reader.readLine();
			Printer.print("Enter Room price:");
			room[2] = reader.readLine();
			Printer.print("Enter Room capacity:");
			room[3] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return room;
	}

	@Override
	public String[] createGuest() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] guest = new String[5];
		try {
			Printer.print("Enter Guest firstName:");
			guest[0] = reader.readLine();
			Printer.print("Enter Guest secondName:");
			guest[1] = reader.readLine();
			Printer.print("Enter Guest arrival Date:");
			guest[2] = reader.readLine();
			Printer.print("Enter Guest leaving Date:");
			guest[3] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}

		return guest;

	}

	@Override
	public String[] evictGuest() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] guest = new String[2];
		try {
			Printer.print("Enter Guest firstName:");
			guest[0] = reader.readLine();
			Printer.print("Enter Guest secondName:");
			guest[1] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return guest;

	}

	@Override
	public String removeRoom() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String rommNumber = "";
		try {
			Printer.print("Enter Room number:");
			rommNumber = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}

		return rommNumber;

	}

	@Override
	public String removeService() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Printer.print("Enter Service name:");
		String name = "";
		try {
			name = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return name;

	}

	@Override
	public String[] removeServiceToGuest() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] service = new String[3];
		try {
			Printer.print("Enter Service name:");
			service[0] = reader.readLine();
			Printer.print("Enter Guest firstName:");
			service[1] = reader.readLine();
			Printer.print("Enter Guest secondName:");
			service[2] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return service;
	}

	public String findEntity(String type) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String name = "";
		Printer.print("Enter " + type + " name:");
		try {
			name = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return name;
	}

	@Override
	public String[] changeRoomPrice() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] rooMPrice = new String[2];
		try {
			Printer.print("Enter Room number:");
			rooMPrice[0] = reader.readLine();
			Printer.print("Enter new price:");
			rooMPrice[1] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return rooMPrice;

	}

	@Override
	public String[] changeServicePrice() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] ServicePrice = new String[2];
		try {
			Printer.print("Enter service name:");
			ServicePrice[0] = reader.readLine();
			Printer.print("Enter new price:");
			ServicePrice[1] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return ServicePrice;
	}

	@Override
	public String[] changeRoomStatus() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] rooMPrice = new String[2];
		try {
			Printer.print("Enter Room number:");
			rooMPrice[0] = reader.readLine();
			Printer.print("Enter Room status: REPAIRABLE or SERVICED");
			rooMPrice[1] = reader.readLine();
		} catch (IOException e) {
			this.loggin(e);
		}
		return rooMPrice;
	}

	@Override
	public void unknownCommand() {
		Printer.print("unknownCommand");

	}

	public void viewEntity(IEntity entity) {
		Printer.print(entity);
	}

	public void viewEmptyRooms(MyList<Room> rooms) {
		this.viewCmd(rooms);
	}

	public void viewAllRooms(MyList<Room> rooms) {
		this.viewCmd(rooms);
	}

	public void viewAllGuests(MyList<Guest> guests) {
		this.viewCmd(guests);
	}

	public void viewAllServices(MyList<Service> services) {
		this.viewCmd(services);
	}

	public void viewAll(MyList<Object> obj) {
		this.viewCmd(obj);
	}

	public void viewGuestPrice(float[] price) {
		Printer.print("servicesPrice: " + price[0]);
		Printer.print("roomPrice: " + price[1]);
		Printer.print("totalPrice: " + price[2]);
	}

	@Override
	public void viewGuestServices(MyList<Service> services) {
		this.viewCmd(services);
	}

	@Override
	public void viewGuestSortedByDate(MyList<Guest> guests) {
		Printer.print("Guests sorted by date: ");
		this.viewCmd(guests);

	}

	@Override
	public void viewGuestSortedByName(MyList<Guest> guests) {
		Printer.print("Guests sorted by name: ");
		this.viewCmd(guests);

	}

	@Override
	public void viewRoomsSortedByPrice(MyList<Room> rooms) {
		Printer.print("Rooms sorted by price: ");
		this.viewCmd(rooms);

	}

	@Override
	public void viewRoomsSortedByCapacity(MyList<Room> rooms) {
		Printer.print("Rooms sorted by capacity: ");
		this.viewCmd(rooms);

	}

	@Override
	public void viewRoomsSortedByStars(MyList<Room> rooms) {
		Printer.print("Rooms sorted by stars: ");
		this.viewCmd(rooms);

	}

	@Override
	public void viewServiceSortedByPrice(MyList<Service> services) {
		Printer.print("Services sorted by price: ");
		this.viewCmd(services);

	}

}
