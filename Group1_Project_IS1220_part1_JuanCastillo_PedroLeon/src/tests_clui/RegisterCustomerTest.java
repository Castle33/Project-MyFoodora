package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.RegisterCustomer;
import clui.MyFoodora;

public class RegisterCustomerTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[7];
		args1[0] = "Pedro";
		args1[1] = "Leon";
		args1[2] = "pleon";
		args1[3] = "0,0";
		args1[4] = "password";
		args1[5] = "630285192";
		args1[6] = "pleonpita@gmail.com";
		String message;
		RegisterCustomer rc = new RegisterCustomer();
		
		message = rc.process(args1);
		assertTrue(message.equals("Customer: -pleon- registered."));
		

		String[] args2 = new String[6];
		args2[0] = "Pedro";
		args2[1] = "Leon";
		args2[2] = "pleon";
		args2[3] = "0,0";
		args2[4] = "password";
		args2[5] = "630285192";
		
		message = rc.process(args2);
		assertTrue(message.equals("Incorrect number of arguments - type help for further command information."));
		
		message= rc.process(args1);
		assertTrue(message.equals("This username already exists: try a different username"));
		
		args1[3] = "(0,0)";
		message= rc.process(args1);
		assertTrue(message.equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
	}
}
