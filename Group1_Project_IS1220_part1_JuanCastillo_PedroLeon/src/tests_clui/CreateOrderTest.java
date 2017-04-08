package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.CreateOrder;

public class CreateOrderTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[2];
		args1[0] = "Emporter";
		args1[1] = "Chinois";
		CreateOrder co = new CreateOrder();
		
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"6,6\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		assertTrue(co.process(args1).equals("Restaurant: -Emporter- not registered in MyFoodora."));

		mf.treatCmd("registerRestaurant \"Bonheur\" \"1,4\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		assertTrue(co.process(args1).equals("Customer: -mocho- created Order: -Chinois-."));
		
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"Emporter\" \"ilfautfermer\"");
		assertTrue(co.process(args1).equals("Access Denied: Unauthorized user"));
		
		
		/*
		mf.treatCmd("createMeal \"Vegie\" \"fullmeal\"");
		mf.treatCmd("createMeal \"Beijing\" \"fullmeal\"");
		mf.treatCmd("createMeal \"HongKong\" \"fullmeal\"");
		mf.treatCmd("createMeal \"Tradition\" \"fullmeal\"");
		mf.treatCmd("addDishRestaurantMenu \"Potstickers\" \"starter\" \"standard\" \"3.5\"");
		mf.treatCmd("addDishRestaurantMenu \"Dumplings\" \"starter\" \"glutenfree\" \"4.8\"");
		mf.treatCmd("addDishRestaurantMenu \"SpicySoyRibs\" \"starter\" \"glutenfree\" \"6.00\"");
		mf.treatCmd("addDishRestaurantMenu \"Wotons\" \"starter\" \"standard\" \"2.00\"");
		mf.treatCmd("addDishRestaurantMenu \"Noodles\" \"maindish\" \"vegetarian\" \"10.40\"");
		mf.treatCmd("addDishRestaurantMenu \"Chicken\" \"maindish\" \"glutenfree\" \"13.00\"");
		mf.treatCmd("addDishRestaurantMenu \"Sushi\" \"maindish\" \"standard\" \"9.99\"");
		mf.treatCmd("addDishRestaurantMenu \"3SeasonRice\" \"maindish\" \"vegetarian\" \"7.00\"");
		mf.treatCmd("addDishRestaurantMenu \"Chao-hong-guo\" \"dessert\" \"glutenfree\" \"6.99\"");
		mf.treatCmd("addDishRestaurantMenu \"Banana-roll\" \"dessert\" \"standard\" \"5.00\"");
		mf.treatCmd("addDishRestaurantMenu \"Douhua\" \"dessert\" \"standard\" \"3.5\"");
		mf.treatCmd("addDishRestaurantMenu \"Guilinggao\" \"dessert\" \"glutenfree\" \"4.10\"");
		mf.treatCmd("addDish2Meal \"Dumplings\" \"Vegie\"");
		mf.treatCmd("addDish2Meal \"Noodles\" \"Vegie\"");
		mf.treatCmd("addDish2Meal \"Douhua\" \"Vegie\"");
		mf.treatCmd("saveMeal \"Vegie\"");
		mf.treatCmd("addDish2Meal \"Potstickers\" \"Beijing\"");
		mf.treatCmd("addDish2Meal \"Chicken\" \"Beijing\"");
		mf.treatCmd("addDish2Meal \"Banana-roll\" \"Beijing\"");
		mf.treatCmd("saveMeal \"Beijing\"");
		mf.treatCmd("addDish2Meal \"SpicySoyRibs\" \"HongKong\"");
		mf.treatCmd("addDish2Meal \"Sushi\" \"HongKong\"");
		mf.treatCmd("addDish2Meal \"Chao-hong-guol\" \"HongKong\"");
		mf.treatCmd("saveMeal \"HongKong\"");
		mf.treatCmd("addDish2Meal \"Wontons\" \"Tradition\"");
		mf.treatCmd("addDish2Meal \"3SeasonRice\" \"Tradition\"");
		mf.treatCmd("addDish2Meal \"Guilinggao\" \"Tradition\"");
		mf.treatCmd("saveMeal \"Tradition\"");
		*/
	}
}
