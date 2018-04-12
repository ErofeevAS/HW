package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.erofeev.annotation.dataobjects.EntityData;
import com.erofeev.annotation.handler.AnnotationsHandler;
import com.erofeev.annotation.injector.Injector;
import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.properties.Config;
import com.erofeev.menu.api.builder.IBuilder;
import com.erofeev.menu.api.menucontroller.IMenuController;
import com.erofeev.menu.api.navigator.INavigator;

public class Test {

	public static void main(String[] args) throws ParseException {

		List<Service> services = new ArrayList<Service>();

		Service service = new Service(55, "breakfast", 100);
		Service service2 = new Service(22, "lunch", 55);
		Service service3 = new Service(33, "supper", 11);

		services.add(service);
		services.add(service2);
		services.add(service3);

		Room room = new Room("1", 2, 3, 4);
		room.setId(15442);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date arrivalDate1 = dateFormat.parse("2017-10-10");
		Date leavingDate1 = dateFormat.parse("2017-11-15");

		Guest guest = new Guest("Anka", "Pulimetchica", arrivalDate1, leavingDate1);
		guest.setId(33);
		service.setId(666);

		guest.setRoom(room);
		guest.addGuestService(service);
		guest.addGuestService(service2);
		guest.addGuestService(service3);

		// AnnotationsHandler.getAllEntitiesFromObject(room);

		IReception model = (IReception) Injector.createObject(IReception.class);
		INavigator navigator = (INavigator) Injector.createObject(INavigator.class);
		IBuilder builder = (IBuilder) Injector.createObject(IBuilder.class);
		model.initReception(Config.getConfig());
		builder.setModel(model);

		// model.occupyGuest(guest, room);

		IMenuController menuController = (IMenuController) Injector.createObject(IMenuController.class);
		menuController.setBuilder(builder);
		menuController.setNavigator(navigator);

		try {
			menuController.run();
		} catch (NullPointerException e) {
			System.out.println("FATAL ERROR");
			e.printStackTrace();
		}

	}

}
