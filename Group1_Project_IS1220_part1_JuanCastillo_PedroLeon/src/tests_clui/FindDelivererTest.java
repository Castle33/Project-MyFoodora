package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.FindDeliverer;
import clui.MyFoodora;

public class FindDelivererTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[1];
		args1[0] = "Chinois2";
		FindDeliverer fd = new FindDeliverer();
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"0,0\" \"cricri\" \"0695599143\"");
		mf.treatCmd("registerCourier \"Yisus\" \"Martinez\" \"Tulipanes\" \"9,0\" \"cenicienta\" \"0695599143\"");
		mf.treatCmd("registerRestaurant \"Bonheur\" \"4,3\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerRestaurant \"Dominos\" \"0,5\" \"pizza\" \"barbecueistop\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,3\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("registerCustomer \"Marc\" \"Bataillou\" \"rata\" \"5,5\" \"tofeliz\" \"0695599143\" \"bataillou@gmail.com\"");
		
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("createOrder \"pizza\" \"Chinois2\"");
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
		
		mf.treatCmd("logIn \"pizza\" \"barbecueistop\"");
		mf.treatCmd("addDishRestaurantMenu \"Dumplings\" \"starter\" \"glutenfree\" \"4.8\"");
		mf.treatCmd("logOut \"\"");
		
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("addItem2Order \"Chinois1\" \"Noodles\" \"1\"");
		mf.treatCmd("addMeal2Order \"Chinois1\" \"Vegie\" \"1\"");
		mf.treatCmd("endOrder \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("addItem2Order \"Chinois2\" \"Dumplings\" \"2\"");
		mf.treatCmd("endOrder \"Chinois2\"");
		assertTrue(fd.process(args1).equals("Order: -Chinois2- is being delivered by Courier: -Tulipanes-."));
		mf.treatCmd("logOut \"\"");
	}
}
