package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.AddMeal2Order;
import clui.MyFoodora;

public class AddMeal2OrderTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[3];
		args1[0] = "Chinois";
		args1[1] = "Vegie";
		args1[2] = "3";
		AddMeal2Order amto = new AddMeal2Order();
		mf.treatCmd("registerRestaurant \"Bonheur\" \"1,4\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"6,6\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		assertTrue(amto.process(args1).equals("Order: -Chinois- not created in MyFoodora.\nFor order creation use createOrder <restaurantUsername> <orderName>."));
		mf.treatCmd("createOrder \"Emporter\" \"Chinois\"");
		assertTrue(amto.process(args1).equals("Meal: -Vegie- not in Bonheur's menu."));
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
		assertTrue(amto.process(args1).equals("Customer: -mocho- added Meal: -Vegie- to Order -Chinois-."));
	}

}
