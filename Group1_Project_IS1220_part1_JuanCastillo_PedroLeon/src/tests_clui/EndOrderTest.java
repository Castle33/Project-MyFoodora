package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.EndOrder;
import clui.MyFoodora;

public class EndOrderTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[1];
		args1[0] = "Chinois";
		EndOrder eo = new EndOrder();
		mf.treatCmd("registerRestaurant \"Bonheur\" \"1,4\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"6,6\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		System.out.println(eo.process(args1));
		assertTrue(eo.process(args1).equals("Order: -Chinois- not created in MyFoodora.\nFor order creation use createOrder <restaurantUsername> <orderName>."));
		mf.treatCmd("createOrder \"Emporter\" \"Chinois\"");
		System.out.println(eo.process(args1));
		assertTrue(eo.process(args1).equals("Order: -Chinois- has nor meals nor items."));
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("createMeal \"Vegie\" \"fullmeal\"");
		mf.treatCmd("addDishRestaurantMenu \"Dumplings\" \"starter\" \"glutenfree\" \"4.8\"");
		mf.treatCmd("addDishRestaurantMenu \"Noodles\" \"maindish\" \"vegetarian\" \"10.40\"");
		mf.treatCmd("addDishRestaurantMenu \"Douhua\" \"dessert\" \"standard\" \"3.5\"");
		mf.treatCmd("addDish2Meal \"Dumplings\" \"Vegie\"");
		mf.treatCmd("addDish2Meal \"Noodles\" \"Vegie\"");
		mf.treatCmd("addDish2Meal \"Douhua\" \"Vegie\"");
		mf.treatCmd("saveMeal \"Vegie\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("addItem2Order \"Chinois\" \"Dumplings\" \"3\"");
		mf.treatCmd("addMeal2Order \"Chinois\" \"Vegie\" \"3\"");
		assertTrue(eo.process(args1).equals("Customer: -mocho- placed Order: -Chinois- to Restaurant: -Bonheur-."));
	}

}
