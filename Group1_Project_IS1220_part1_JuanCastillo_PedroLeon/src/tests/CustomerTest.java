package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import system.FidelityCardLottery;
import system.FidelityCardBasic;
import system.FidelityCardPoint;
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
		Address a = new Address(3,4);
		Customer c = new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword");
		c.setFidelityCardtoPoints();
		assertTrue((c.getFidelityCard() instanceof FidelityCardPoint) == true) ;
	}

	@Test
	public void testSetFidelityCardtoLottery() {
		Address a = new Address(3,4);
		Customer c = new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword");
		c.setFidelityCardtoLottery();
		assertTrue((c.getFidelityCard() instanceof FidelityCardLottery) == true) ;
	}

	@Test
	public void testGetNumberFidelityPoints() {
		Address a1 = new Address(3,4);
		Address a2 = new Address(6,8);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer c2 = new Customer("Pedro", "pleonpita", "Leon", a2, "pleonpita@gmail.com", "0695599143", "newpassword2");
		c1.setFidelityCardtoPoints();
		c2.setFidelityCardtoBasic();
		assertTrue(c1.getNumberFidelityPoints() == 0 && c2.getNumberFidelityPoints() == 0) ;
	}

	@Test
	public void testAddFidelityPoints() {
		Address a1 = new Address(3,4);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		c1.setFidelityCardtoPoints();
		c1.addFidelityPoints(10);
		FidelityCardPoint fcp = (FidelityCardPoint) c1.getFidelityCard();
		System.out.println(fcp.getPoints() == 10);
		
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
