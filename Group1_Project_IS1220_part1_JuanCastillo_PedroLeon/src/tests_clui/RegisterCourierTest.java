package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.RegisterCourier;

public class RegisterCourierTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[6];
		args1[0] = "Angel";
		args1[1] = "Antolin";
		args1[2] = "PotaBestia";
		args1[3] = "6,9";
		args1[4] = "cricri";
		args1[5] = "630285192";
		String message;
		RegisterCourier rc = new RegisterCourier();
		
		message = rc.process(args1);
		assertTrue(message.equals("Courier: -PotaBestia- registered."));

		String[] args2 = new String[4];
		args1[0] = "Angel";
		args1[1] = "Antolin";
		args1[2] = "PotaBestia";
		args1[3] = "6,9";
		args1[4] = "cricri";
		
		message = rc.process(args2);
		assertTrue(message.equals("Incorrect number of arguments - type help for further command information."));
		
		message= rc.process(args1);
		assertTrue(message.equals("This username already exists: try a different username"));
		
		args1[3] = "(0,0)";
		message= rc.process(args1);
		assertTrue(message.equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
	}

}
