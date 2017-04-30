package tests_clui;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowRestaurantTop;
import system.Core;
import users.Restaurant;
import users.User;

public class ShowRestaurantTopTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "";
		ShowRestaurantTop srt = new ShowRestaurantTop();
		Restaurant restaurant;
		HashMap<String,User> users = new HashMap<String,User>();
		Core core;
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		mf.treatCmd("registerRestaurant \"Bonheur\" \"4,3\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerRestaurant \"Dominos\" \"0,5\" \"pizza\" \"barbecueistop\"");
		core = MyFoodora.getCore();
		restaurant = (Restaurant) core.getListOfUsers().get("Emporter");
		restaurant.setCountOfOrdersCompleted(8);
		users.put(restaurant.getUsername(), restaurant);
		restaurant = (Restaurant) core.getListOfUsers().get("pizza");
		restaurant.setCountOfOrdersCompleted(4);
		users.put(restaurant.getUsername(), restaurant);
		core.setListOfUsers(users);
		MyFoodora.setCore(core);
		assertTrue(srt.process(args1).equals("The restaurants sorted by number of completed orders in decreasing order are:\n-Emporter-.\n-pizza-."));
	}

}
