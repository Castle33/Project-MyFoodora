package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowTotalProfit;

public class ShowTotalProfitTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [2];
		args1[0] = "01/02/2017";
		args1[1] = "01/06/2017";
		ShowTotalProfit stp = new ShowTotalProfit();
		
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"0,0\" \"cricri\" \"0695599143\"");
		mf.treatCmd("registerCourier \"Yisus\" \"Martinez\" \"Tulipanes\" \"2,0\" \"cenicienta\" \"0695599143\"");
		mf.treatCmd("registerRestaurant \"Bonheur\" \"1,1\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,1\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("registerCustomer \"Marc\" \"Bataillou\" \"rata\" \"1,2\" \"tofeliz\" \"0695599143\" \"bataillou@gmail.com\"");
		mf.treatCmd("registerCustomer \"Emilio\" \"Emilio\" \"emilio\" \"1,0\" \"rae\" \"0695599143\" \"emilioemilio@gmail.com\"");

		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois2\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"emilio\" \"rae\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois3\"");
		mf.treatCmd("logOut \"\"");
		
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

		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("addMeal2Order \"Chinois1\" \"VegieS\" \"4\"");
		mf.treatCmd("endOrder \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"rata\" \"tofeliz\"");
		mf.treatCmd("addItem2Order \"Chinois2\" \"VegieD\" \"1\"");
		mf.treatCmd("addItem2Order \"Chinois2\" \"Dumplings\" \"1\"");
		mf.treatCmd("endOrder \"Chinois2\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"mocho\" \"rae\"");
		mf.treatCmd("addMeal2Order \"Chinois3\" \"VegieD\" \"4\"");
		mf.treatCmd("endOrder \"Chinois3\"");
		mf.treatCmd("logOut \"\"");

		mf.treatCmd("logIn \"PotaBestia\" \"cricri\"");
		mf.treatCmd("offDuty \"PotaBestia\"");
		mf.treatCmd("logOut \"\"");

		mf.treatCmd("logIn \"Tulipanes\" \"cenicienta\"");
		mf.treatCmd("offDuty \"Tulipanes\"");
		mf.treatCmd("logOut \"\"");
		
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		System.out.println(stp.process(args1));
	}
}
