package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.ShowCustomers;

public class ShowCustomersTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "";
		ShowCustomers sc = new ShowCustomers();
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,3\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("registerCustomer \"Marc\" \"Bataillou\" \"rata\" \"5,5\" \"tofeliz\" \"0695599143\" \"bataillou@gmail.com\"");
		mf.treatCmd("registerCustomer \"Emilio\" \"Emilio\" \"emilio\" \"6,0\" \"rae\" \"0695599143\" \"emilioemilio@gmail.com\"");
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		assertTrue(sc.process(args1).equals("List of customers:\n-rata-.\n-mocho-.\n-emilio-."));
	}

}
