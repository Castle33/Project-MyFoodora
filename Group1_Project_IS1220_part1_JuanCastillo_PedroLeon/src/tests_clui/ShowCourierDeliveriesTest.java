package tests_clui;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowCourierDeliveries;
import system.Core;
import users.Courier;
import users.User;

public class ShowCourierDeliveriesTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "";
		ShowCourierDeliveries scd = new ShowCourierDeliveries();
		Courier courier;
		HashMap<String,User> users = new HashMap<String,User>();
		Core core;
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"12,0\" \"cricri\" \"0695599143\"");
		mf.treatCmd("registerCourier \"Yisus\" \"Martinez\" \"Tulipanes\" \"9,0\" \"cenicienta\" \"0695599143\"");
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		core = MyFoodora.getCore();
		courier = (Courier) core.getListOfUsers().get("PotaBestia");
		courier.setCountOfOrdersCompleted(4);
		users.put(courier.getUsername(), courier);
		courier = (Courier) core.getListOfUsers().get("Tulipanes");
		courier.setCountOfOrdersCompleted(8);
		users.put(courier.getUsername(), courier);
		core.setListOfUsers(users);
		MyFoodora.setCore(core);
		assertTrue(scd.process(args1).equals("List of ordered couriers:\n-Tulipanes-.\n-PotaBestia-."));
	}

}
