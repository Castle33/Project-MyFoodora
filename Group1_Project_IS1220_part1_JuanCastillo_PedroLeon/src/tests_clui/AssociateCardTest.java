package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.AssociateCard;
import clui.MyFoodora;

public class AssociateCardTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [2];
		args1[0] = "mocho";
		args1[1] = "BASIC";
		String[] args2 = new String [2];
		args2[0] = "mocho";
		args2[1] = "POINT";
		String[] args3 = new String [2];
		args3[0] = "mocho";
		args3[1] = "LOTTERY";
		AssociateCard ac = new AssociateCard();
		mf.treatCmd("registerCustomer \"Luis\" \"Cobas\" \"mocho\" \"0,3\" \"fallingInMIX\" \"0695599143\" \"luchocobas@gmail.com\"");
		mf.treatCmd("logIn \"mocho\" \"fallingInMIX\"");
		assertTrue(ac.process(args1).equals("Cutomer: -mocho- changed to fidelity card -BASIC-."));
		assertTrue(ac.process(args2).equals("Cutomer: -mocho- changed to fidelity card -POINT-."));
		assertTrue(ac.process(args3).equals("Cutomer: -mocho- changed to fidelity card -LOTTERY-."));
	}

}
