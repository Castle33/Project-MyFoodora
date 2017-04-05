package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.RegisterRestaurant;

public class RegisterRestaurantTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[4];
		args1[0] = "Bonheur";
		args1[1] = "1,4";
		args1[2] = "Emporter";
		args1[3] = "ilfautfermer";
		String message;
		RegisterRestaurant rr = new RegisterRestaurant();
		
		message = rr.process(args1);
		assertTrue(message.equals("Restaurant: -Emporter- registered."));
		

		String[] args2 = new String[3];
		args2[0] = "Bonheur";
		args2[1] = "1,4";
		args2[2] = "Emporter";
		
		message = rr.process(args2);
		assertTrue(message.equals("Incorrect number of arguments - type help for further command information."));
		
		message= rr.process(args1);
		assertTrue(message.equals("This username already exists: try a different username"));
		
		args1[1] = "(0,0)";
		message= rr.process(args1);
		assertTrue(message.equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
	}

}
