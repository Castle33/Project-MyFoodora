package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.OnDuty;

public class OnDutyTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[1];
		args1[0] = "PotaBestia";
		OnDuty od = new OnDuty();
		mf.treatCmd("registerCourier \"Angel\" \"Antolin\" \"PotaBestia\" \"0,0\" \"cricri\" \"0695599143\"");
		System.out.println(od.process(args1));
		assertTrue(od.process(args1).equals("Access Denied: Unauthorized user"));
		mf.treatCmd("logIn \"PotaBestia\" \"cricri\"");
		System.out.println(od.process(args1));
		assertTrue(od.process(args1).equals("Courier: -PotaBestia- tried to update its state.\nSystem automatically sets courier 'on dutty' when accepting an order."));
	}

}
