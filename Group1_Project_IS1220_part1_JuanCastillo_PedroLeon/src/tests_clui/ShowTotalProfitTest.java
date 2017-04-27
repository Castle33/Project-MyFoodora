package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowTotalProfit;

public class ShowTotalProfitTest {

	/**
	 * Error found while testing. When processing several orders to few couriers if we add a pending order to a courier, this order won't enter the list of completed orders
	 * clui/EndOrder/process - system/core/processOrders
	 * changed: from add courier's current order to add directly the order we are assigning
	 */
	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [2];
		args1[0] = "01/02/2017";
		args1[1] = "01/06/2017";
		String[] args2 = new String [1];
		args2[0] = "";
		ShowTotalProfit stp = new ShowTotalProfit();
		// register users
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"0,0\" \"cricri\" \"0695599143\"");
		mf.treatCmd("registerCourier \"Yisus\" \"Martinez\" \"Tulipanes\" \"2,0\" \"cenicienta\" \"0695599143\"");
		mf.treatCmd("registerRestaurant \"Bonheur\" \"1,1\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,1\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("registerCustomer \"Marc\" \"Bataillou\" \"rata\" \"1,2\" \"tofeliz\" \"0695599143\" \"bataillou@gmail.com\"");
		mf.treatCmd("registerCustomer \"Emilio\" \"Emilio\" \"emilio\" \"1,0\" \"rae\" \"0695599143\" \"emilioemilio@gmail.com\"");
		// create one order for each customer
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois2\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"emilio\" \"rae\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois3\"");
		mf.treatCmd("logOut \"\"");
		// add items and dishes to restaurant menu
		mf.treatCmd("logIn \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("createMeal \"VegieS\" \"halfmeal\"");
		mf.treatCmd("addDishRestaurantMenu \"Dumplings\" \"starter\" \"glutenfree\" \"5.5\"");
		mf.treatCmd("addDishRestaurantMenu \"Noodles\" \"maindish\" \"vegetarian\" \"15.5\"");
		mf.treatCmd("addDish2Meal \"Dumplings\" \"VegieS\"");
		mf.treatCmd("addDish2Meal \"Noodles\" \"VegieS\"");
		mf.treatCmd("saveMeal \"VegieS\"");
		mf.treatCmd("createMeal \"VegieD\" \"halfmeal\"");
		mf.treatCmd("addDishRestaurantMenu \"Noodles\" \"maindish\" \"vegetarian\" \"15.5\"");
		mf.treatCmd("addDishRestaurantMenu \"Douhua\" \"dessert\" \"standard\" \"4.0\"");
		mf.treatCmd("addDish2Meal \"Noodles\" \"VegieD\"");
		mf.treatCmd("addDish2Meal \"Douhua\" \"VegieD\"");
		mf.treatCmd("saveMeal \"VegieD\"");
		mf.treatCmd("logOut \"\"");
		/*
		 *  add items and dishes from restaurant menu to customer's orders
		 *  then we assign each order to a courier through endOrder command
		 *  when assigning an order it automatically enters the completed order list
		 *  even if it hasn't been delivered yet we suppose that if a courier accepts an order, it will be delivered in the near future
		 */
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("addMeal2Order \"Chinois1\" \"VegieS\" \"4\"");
		mf.treatCmd("endOrder \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("addMeal2Order \"Chinois2\" \"VegieD\" \"1\"");
		mf.treatCmd("addItem2Order \"Chinois2\" \"Dumplings\" \"1\"");
		mf.treatCmd("endOrder \"Chinois2\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"emilio\" \"rae\"");
		mf.treatCmd("addMeal2Order \"Chinois3\" \"VegieD\" \"4\"");
		mf.treatCmd("endOrder \"Chinois3\"");
		mf.treatCmd("logOut \"\"");
		// log in as manager and calc profit
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		assertTrue(stp.process(args1).equals("The total profit for the specified time interval is: -20.79-."));
		assertTrue(stp.process(args2).equals("The total profit from init date until now is: -20.79-."));
		
	}
}
