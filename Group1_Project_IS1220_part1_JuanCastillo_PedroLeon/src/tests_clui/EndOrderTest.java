package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.EndOrder;
import clui.MyFoodora;
import users.User;
import users.Courier;

public class EndOrderTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[1];
		args1[0] = "Chinois1";
		String[] args2 = new String[1];
		args2[0] = "Chinois2";
		String[] args3 = new String[1];
		args3[0] = "Chinois3";
		EndOrder eo = new EndOrder();
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"0,0\" \"cricri\" \"0695599143\"");
		mf.treatCmd("registerCourier \"Yisus\" \"Martinez\" \"Tulipanes\" \"9,0\" \"cenicienta\" \"0695599143\"");
		mf.treatCmd("registerRestaurant \"Bonheur\" \"4,3\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerRestaurant \"Dominos\" \"0,5\" \"pizza\" \"barbecueistop\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,3\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("registerCustomer \"Marc\" \"Bataillou\" \"rata\" \"5,5\" \"tofeliz\" \"0695599143\" \"bataillou@gmail.com\"");
		mf.treatCmd("registerCustomer \"Emilio\" \"Emilio\" \"emilio\" \"6,0\" \"rae\" \"0695599143\" \"emilioemilio@gmail.com\"");
		
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		System.out.println(eo.process(args1));
		assertTrue(eo.process(args1).equals("Order: -Chinois1- not created in MyFoodora.\nFor order creation use createOrder <restaurantUsername> <orderName>."));
		mf.treatCmd("createOrder \"Emporter\" \"Chinois1\"");
		System.out.println(eo.process(args1));
		assertTrue(eo.process(args1).equals("Order: -Chinois1- has nor meals nor items."));
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("createOrder \"pizza\" \"Chinois2\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"emilio\" \"rae\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois3\"");
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
		assertTrue(eo.process(args1).equals("Customer: -mocho- placed Order: -Chinois1- to Restaurant: -Bonheur-."));
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("addItem2Order \"Chinois2\" \"Dumplings\" \"2\"");
		assertTrue(eo.process(args2).equals("Customer: -rata- placed Order: -Chinois2- to Restaurant: -Dominos-."));
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"mocho\" \"rae\"");
		mf.treatCmd("addMeal2Order \"Chinois3\" \"Vegie\" \"3\"");
		assertTrue(eo.process(args3).equals("Customer: -emilio- placed Order: -Chinois3- to Restaurant: -Bonheur-."));
		mf.treatCmd("logOut \"\"");
		for(User u : MyFoodora.getCore().getListOfUsers().values()){
			if(u instanceof Courier){
				System.out.println(u.getUsername());
				System.out.println(((Courier) u).getCurrentOrder());
				System.out.println(((Courier) u).getListPendingOrders());
			}
		}
	}

}
