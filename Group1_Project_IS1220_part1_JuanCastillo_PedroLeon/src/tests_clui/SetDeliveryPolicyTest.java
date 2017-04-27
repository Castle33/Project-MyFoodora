package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.SetDeliveryPolicy;

public class SetDeliveryPolicyTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String [1];
		args1[0] = "FASTEST";
		String[] args2 = new String [1];
		args2[0] = "FAIROCCUPATION";
		SetDeliveryPolicy sdp = new SetDeliveryPolicy();
		mf.treatCmd("logIn \"deputy\" \"987654321\"");
		assertTrue(sdp.process(args1).equals("Delivery policy changed to: -FASTEST-."));
		assertTrue(sdp.process(args2).equals("Delivery policy changed to: -FAIROCCUPATION-."));
	}

}
