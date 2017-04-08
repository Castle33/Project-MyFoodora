package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.AddItem2Order;
import clui.MyFoodora;

public class AddItem2OrderTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[3];
		args1[0] = "Chinois";
		args1[1] = "Potstickers";
		args1[2] = "3";
		AddItem2Order aito = new AddItem2Order();
		mf.treatCmd("registerRestaurant \"Bonheur\" \"1,4\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"6,6\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		assertTrue(aito.process(args1).equals("Order: -Chinois- not created in MyFoodora.\nFor order creation use createOrder <restaurantUsername> <orderName>."));
		mf.treatCmd("createOrder \"Emporter\" \"Chinois\"");
		assertTrue(aito.process(args1).equals("Item: -Potstickers- not in Bonheur's menu."));
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("addDishRestaurantMenu \"Potstickers\" \"starter\" \"standard\" \"3.5\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		assertTrue(aito.process(args1).equals("Customer: -mocho- added Item: -Potstickers- to Order -Chinois-."));

	}

}
