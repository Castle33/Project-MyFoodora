package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import system.FidelityCardLottery;
import system.FidelityCardBasic;
import users.Address;
import users.Customer;

public class CustomerTest {

	@Test
	public void testCustomer() {
		Address a = new Address(3,4);
		Customer c = new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword");
		assertTrue(c.getName() == "Juan" && c.getUsername() == "jcastillo33" && c.getSurname() == "Castillo" && c.getAddress() == a && c.getEmail() == "jcastillo@gmail.com" && c.getPhoneNumber() == "630285192" && c.getPassword() == "newpassword");
	}

	@Test
	public void testSetFidelityCard() {
		Address a = new Address(3,4);
		Customer c = new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword");
		FidelityCardLottery fcl = new FidelityCardLottery();
		c.setFidelityCard(fcl);
		assertTrue(c.getFidelityCard() == fcl);
	}

	@Test
	public void testSetFidelityCardtoBasic() {
		Address a = new Address(3,4);
		Customer c = new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword");
		c.setFidelityCardtoBasic();
		assertTrue((c.getFidelityCard() instanceof FidelityCardBasic) == true) ;
	}

	@Test
	public void testSetFidelityCardtoPoints() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFidelityCardtoLottery() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberFidelityPoints() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFidelityPoints() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeBeNotified() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
