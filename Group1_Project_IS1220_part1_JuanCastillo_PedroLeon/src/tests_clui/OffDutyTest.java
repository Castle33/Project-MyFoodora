package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.OffDuty;

public class OffDutyTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[1];
		args1[0] = "PotaBestia";
		OffDuty od = new OffDuty();
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"0,0\" \"cricri\" \"0695599143\"");
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,3\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("registerRestaurant \"Bonheur\" \"4,3\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		mf.treatCmd("createOrder \"Emporter\" \"Chinois1\"");
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
		mf.treatCmd("addItem2Order \"Chinois1\" \"Noodles\" \"1\"");
		mf.treatCmd("addMeal2Order \"Chinois1\" \"Vegie\" \"1\"");
		mf.treatCmd("endOrder \"Chinois1\"");
		mf.treatCmd("logOut \"\"");
		System.out.println(od.process(args1));
		assertTrue(od.process(args1).equals("Access Denied: Unauthorized user"));
		mf.treatCmd("logIn \"PotaBestia\" \"cricri\"");
		System.out.println(od.process(args1));
		assertTrue(od.process(args1).equals("Courier: -PotaBestia- updated its state to 'offDuty'."));
	}

}
