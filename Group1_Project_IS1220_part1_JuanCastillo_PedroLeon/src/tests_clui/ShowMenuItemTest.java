package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowMenuItem;

public class ShowMenuItemTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "Emporter";
		ShowMenuItem smi = new ShowMenuItem();
		mf.treatCmd("registerRestaurant \"Bonheur\" \"4,3\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("logIn \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("addDishRestaurantMenu \"Dumplings\" \"starter\" \"glutenfree\" \"4.8\"");
		mf.treatCmd("addDishRestaurantMenu \"Noodles\" \"maindish\" \"vegetarian\" \"10.40\"");
		mf.treatCmd("addDishRestaurantMenu \"Douhua\" \"dessert\" \"standard\" \"3.5\"");
		mf.treatCmd("logOut \"\"");
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		assertTrue(smi.process(args1).equals("Restaurant: -Emporter- menu is -Starter [toString()=Item [name=Dumplings, price=4.8, type=GLUTENFREE]]MainDish [toString()=Item [name=Noodles, price=10.4, type=VEGETARIAN]]Dessert [toString()=Item [name=Douhua, price=3.5, type=STANDARD]]-."));
	}
}
