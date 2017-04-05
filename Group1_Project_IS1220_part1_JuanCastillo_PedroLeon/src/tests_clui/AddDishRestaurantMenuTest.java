package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.AddDishRestaurantMenu;

public class AddDishRestaurantMenuTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[4];
		args1[0] = "Potstickers";
		args1[1] = "starter";
		args1[2] = "standard";
		args1[3] = "3.50";
		String[] args2 = new String[4];
		args2[0] = "Bonheur";
		args2[1] = "1,4";
		args2[2] = "Emporter";
		args2[3] = "ilfautfermer";
		String[] args3 = new String[2];
		args3[0] = "Emporter";
		args3[1] = "ilfautfermer";
		AddDishRestaurantMenu adrm = new AddDishRestaurantMenu();
		
		mf.processCommand("RegisterRestaurant", MyFoodora.arrayToList(args2));
		mf.processCommand("LogIn", MyFoodora.arrayToList(args3));
		
		assertTrue(adrm.process(args1).equals("Item: -Potstickers- added to Bonheur's menu."));
		assertTrue(adrm.process(args1).equals("Item: -Potstickers- already added to menu."));

		String[] args4 = new String[3];
		args4[0] = "Potstickers";
		args4[1] = "starter";
		args4[2] = "standard";
		
		assertTrue(adrm.process(args4).equals("Incorrect number of arguments - type help for further command information."));

		String[] args5 = new String[4];
		args5[0] = "Dumplins";
		args5[1] = "starter";
		args5[2] = "standard";
		args5[3] = "hello";
		
		assertTrue(adrm.process(args5).equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
		String[] args6 = new String[4];
		args6[0] = "Noodles";
		args6[1] = "hello";
		args6[2] = "standard";
		args6[3] = "3.50";
		
		assertTrue(adrm.process(args6).equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
		String[] args7 = new String[4];
		args7[0] = "Douhua";
		args7[1] = "starter";
		args7[2] = "hello";
		args7[3] = "3.50";
		
		assertTrue(adrm.process(args7).equals("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n"));
		
	}

}
