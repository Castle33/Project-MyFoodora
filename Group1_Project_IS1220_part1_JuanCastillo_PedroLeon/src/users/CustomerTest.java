package users;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import system.FidelityCardLottery;
import system.FidelityCardBasic;
import system.FidelityCardPoint;
import users.Address;
import users.Customer;
import users.Restaurant;
import restaurant_structure.Dessert;
import restaurant_structure.HalfMeal;
import restaurant_structure.Item;
import restaurant_structure.MainDish;
import restaurant_structure.Starter;

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
		assertTrue(fcp.getPoints() == 10);
	}

	@Test
	public void testChangeBeNotified() {
		Address a1 = new Address(3,4);
		Address a2 = new Address(6,8);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer c2 = new Customer("Pedro", "pleonpita", "Leon", a2, "pleonpita@gmail.com", "0695599143", "newpassword2");
		c1.changeBeNotified(true);
		c2.changeBeNotified(false);
		assertTrue(c1.isBeNotified() == true && c2.isBeNotified() == false);
	}

	@Test
	public void testUpdate() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
		list1.add(s1);
		list1.add(md1);
		list2.add(md1);
		list2.add(d1);
		HalfMeal m1 = new HalfMeal("Mediterranea", list1);
		HalfMeal m2 = new HalfMeal("Verano", list2);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m1);
		r.addSpecialMeal(m2);
		
		Address a2 = new Address(6,8);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		
		c1.update(r, m2);
		
	}

}
