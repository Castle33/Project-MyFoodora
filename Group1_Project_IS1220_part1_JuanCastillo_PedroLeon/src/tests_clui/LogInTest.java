package tests_clui;

import static org.junit.Assert.*;

import org.junit.Test;

import clui.MyFoodora;
import clui.LogIn;

public class LogInTest {

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
		String message;
		
		mf.processCommand("RegisterCustomer", MyFoodora.arrayToList(args1));
		LogIn li = new LogIn();
		message = li.process(args2);
		assertTrue(message.equals("User: -pleon- correctly logged in."));
		
		String[] args3 = new String[3];
		message = li.process(args3);
		assertTrue(message.equals("Incorrect number of arguments - type help for further command information."));
		
	}
}