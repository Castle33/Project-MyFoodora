package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowMeal;

public class ShowMealTest {

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
		String[] args10 = new String[1];
		args10[0] = "Vegie";
		ShowMeal sm = new ShowMeal();
		
		mf.processCommand("RegisterRestaurant", MyFoodora.arrayToList(args1));
		mf.processCommand("LogIn", MyFoodora.arrayToList(args2));
		mf.processCommand("CreateMeal", MyFoodora.arrayToList(args3));
		mf.processCommand("AddDishRestaurantMenu", MyFoodora.arrayToList(args4));
		mf.processCommand("AddDishRestaurantMenu", MyFoodora.arrayToList(args5));
		mf.processCommand("AddDishRestaurantMenu", MyFoodora.arrayToList(args6));
		mf.processCommand("AddDish2Meal", MyFoodora.arrayToList(args7));
		mf.processCommand("AddDish2Meal", MyFoodora.arrayToList(args8));
		mf.processCommand("AddDish2Meal", MyFoodora.arrayToList(args9));
		
		assertTrue(sm.process(args10).equals("From listOfTempMeals, Meal: -Vegie- has items: [Starter [toString()=Item [name=Dumplings, price=4.8, type=VEGETARIAN]], MainDish [toString()=Item [name=Noodles, price=10.4, type=VEGETARIAN]], Dessert [toString()=Item [name=Douhua, price=3.5, type=VEGETARIAN]]]\nTo add items use AddDish2Meal <dishName> <mealName>.\nTo save meal to Bonheur's menu use SaveMeal <mealName>."));

		mf.processCommand("SaveMeal", MyFoodora.arrayToList(args10));
		System.out.println(sm.process(args10));
		assertTrue(sm.process(args10).equals("From Bonheur's menu, Meal: -Vegie- has items: [Starter [toString()=Item [name=Dumplings, price=4.8, type=VEGETARIAN]], MainDish [toString()=Item [name=Noodles, price=10.4, type=VEGETARIAN]], Dessert [toString()=Item [name=Douhua, price=3.5, type=VEGETARIAN]]]"));

		
	}

}
