package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.AddDish2Meal;
import clui.MyFoodora;

public class AddDish2MealTest {

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
		args3[1] = "FullMeal";
		String[] args4 = new String[4];
		args4[0] = "Dumplings";
		args4[1] = "Starter";
		args4[2] = "vegetarian";
		args4[3] = "4.80";
		String[] args5 = new String[4];
		args5[0] = "Noodles";
		args5[1] = "MainDish";
		args5[2] = "vegetarian";
		args5[3] = "10.40";
		String[] args6 = new String[4];
		args6[0] = "Douhua";
		args6[1] = "Dessert";
		args6[2] = "vegetarian";
		args6[3] = "3.50";
		String[] args7 = new String[2];
		args7[0] = "Dumplings";
		args7[1] = "Vegie";
		String[] args8 = new String[2];
		args8[0] = "Noodles";
		args8[1] = "Vegie";
		String[] args9 = new String[2];
		args9[0] = "Douhua";
		args9[1] = "Vegie";
		AddDish2Meal adtm1 = new AddDish2Meal();
		
		mf.processCommand("RegisterRestaurant", MyFoodora.arrayToList(args1));
		mf.processCommand("LogIn", MyFoodora.arrayToList(args2));
		mf.processCommand("CreateMeal", MyFoodora.arrayToList(args3));
		mf.processCommand("AddDishRestaurantMenu", MyFoodora.arrayToList(args4));
		mf.processCommand("AddDishRestaurantMenu", MyFoodora.arrayToList(args5));
		mf.processCommand("AddDishRestaurantMenu", MyFoodora.arrayToList(args6));
		assertTrue(adtm1.process(args7).equals("Item: -Dumplings- added to Meal: -Vegie-."));
		assertTrue(adtm1.process(args8).equals("Item: -Noodles- added to Meal: -Vegie-."));
		assertTrue(adtm1.process(args9).equals("Item: -Douhua- added to Meal: -Vegie-."));

	}

}
