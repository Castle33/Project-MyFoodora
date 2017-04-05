package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.LogOut;
import clui.MyFoodora;

public class LogOutTest {

	@Test
	public void testProcess() {
		MyFoodora mf = new MyFoodora();
		String[] args1 = new String[7];
		args1[0] = "Pedro";
		args1[1] = "Leon";
		args1[2] = "pleon";
		args1[3] = "0,0";
		args1[4] = "password";
		args1[5] = "630285192";
		args1[6] = "pleonpita@gmail.com";
		String[] args2 = new String[2];
		args2[0] = "pleon";
		args2[1] = "password";
		String[] args3 = new String[1];
		String message;
		
		mf.processCommand("RegisterCustomer", MyFoodora.arrayToList(args1));
		mf.processCommand("LogIn", MyFoodora.arrayToList(args2));
		LogOut lo = new LogOut();
		message = lo.process(args3);
		assertTrue(message.equals("User correctly logged out."));
		
		String[] args4 = new String[2];
		message = lo.process(args4);
		assertTrue(message.equals("Incorrect number of arguments - type help for further command information."));
		
	}
}
