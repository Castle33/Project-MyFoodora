package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.SetProfitPolicy;

public class SetProfitPolicyTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "DELIVERYCOST";
		String[] args2 = new String [1];
		args2[0] = "MARKUP";
		String[] args3 = new String [1];
		args3[0] = "SERVICEFEE";
		SetProfitPolicy spp = new SetProfitPolicy();
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		assertTrue(spp.process(args1).equals("Profit policy changed to: -DELIVERYCOST-."));
		assertTrue(spp.process(args2).equals("Profit policy changed to: -MARKUP-."));
		assertTrue(spp.process(args3).equals("Profit policy changed to: -SERVICEFEE-."));
	}

}
