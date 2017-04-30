package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowRestaurantsRegistered;

public class ShowRestaurantsRegisteredTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "";
		ShowRestaurantsRegistered srr = new ShowRestaurantsRegistered();
		mf.treatCmd("registerRestaurant \"Bonheur\" \"4,3\" \"Emporter\" \"ilfautfermer\"");
		mf.treatCmd("registerRestaurant \"Dominos\" \"0,5\" \"pizza\" \"barbecueistop\"");
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		System.out.println(srr.process(args1));
		assertTrue(srr.process(args1).equals("Restaurant list: Restaurant [name=Dominos, username=pizza, ID=4]\nRestaurant [name=Bonheur, username=Emporter, ID=3]\n"));
	}

}
