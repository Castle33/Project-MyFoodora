package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.CreateMeal;

public class CreateMealTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		
		String[] args1 = new String[4];
		args1[0] = "Bonheur";
		args1[1] = "1,4";
		args1[2] = "Emporter";
		args1[3] = "ilfautfermer";
		String[] args2 = new String[2];
		args2[0] = "Emporter";
		args2[1] = "ilfautfermer";
		String[] args3 = new String[2];
		args3[0] = "Vegie";
		args3[1] = "fullMeal";
		String[] args4 = new String[2];
		args4[0] = "Vegie";
		args4[1] = "halfMeal";
		CreateMeal cm = new CreateMeal();
		
		mf.processCommand("RegisterRestaurant", MyFoodora.arrayToList(args1));
		mf.processCommand("LogIn", MyFoodora.arrayToList(args2));
		
		assertTrue(cm.process(args3).equals("FullMeal: -Vegie- added to temporal list of meals."));
		assertTrue(cm.process(args4).equals("HalfMeal: -Vegie- added to temporal list of meals."));
		
		String[] args5 = new String[2];
		args5[0] = "Vegie";
		args5[1] = "hello";
		
		assertTrue(cm.process(args5).equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
	}

}
