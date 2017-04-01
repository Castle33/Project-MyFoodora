package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import users.Address;

public class AddressTest {

	@Test
	public void testAddress() {
		Address a = new Address(1, 2);
		assertTrue(a.getX()==1 && a.getY()==2);
	}

	@Test
	public void testCalcDistance() {
		Address a = new Address(0, 0);
		Address b = new Address(3, 4);
		assertTrue(a.calcDistance(b) == 5.0);
	}

}
