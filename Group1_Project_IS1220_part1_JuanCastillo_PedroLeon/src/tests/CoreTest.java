package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import restaurant_structure.Dessert;
import restaurant_structure.HalfMeal;
import restaurant_structure.Item;
import restaurant_structure.MainDish;
import restaurant_structure.Starter;
import system.Core;
import system.DeliveryFastest;
import system.Order;
import system.TargetProfitDeliveryCost;
import users.Manager;
import users.Restaurant;
import users.User;
import users.Address;
import users.Customer;

public class CoreTest {

	@Test
	public void testCore() {
		Core c = new Core();
		assertTrue(c.getName() == "MyFoodora" && c.getServiceFee() == 3.0 && c.getMarkupPercentage() == 0.1 && c.getDeliveryCost() == 2.0);
		assertTrue(c.getListOfUsers().get("jcastillo33").getUsername() == "jcastillo33");
		assertTrue(c.getListOfUsers().get("pleonpita").getUsername() == "pleonpita");
		assertTrue(c.getDeliveryPolicy() instanceof DeliveryFastest && c.gettProfitPolicy() instanceof TargetProfitDeliveryCost);
		assertTrue(c.getListOfToNotify().isEmpty() && c.getListOfCompletedOrders().isEmpty() && c.getListOfPendingOrders().isEmpty());
		assertTrue(c.getListOfMasterManager().get(0).getUsername() == "jcastillo33");
		assertTrue(c.getListOfMasterManager().get(1).getUsername() == "pleonpita");
	}

	@Test
	public void testRegisterUser() {
		Core c = new Core();
		Manager m = new Manager("John", "jdewasseige", "pjaoshsnword", "DeWasseige");
		c.registerUser(m);
		assertTrue(c.getListOfUsers().get("jdewasseige").getUsername() == "jdewasseige");
	}

	@Test
	public void testUserLogIn() {
		Core c = new Core();
		Manager m = new Manager("John", "jdewasseige", "pjaoshsnword", "DeWasseige");
		c.registerUser(m);
		c.userLogIn(m);
		assertTrue(c.getCurrentUser().getUsername() == "jdewasseige");
	}

	@Test
	public void testLogOut() {
		Core c = new Core();
		Manager m = new Manager("John", "jdewasseige", "pjaoshsnword", "DeWasseige");
		c.registerUser(m);
		c.userLogIn(m);
		assertTrue(c.getCurrentUser().getUsername() == "jdewasseige");
		c.logOut();
		assertNull(c.getCurrentUser());
	}

	@Test
	public void testRegisterObserver() {
		Core c = new Core();
		Address a = new Address(3,4);
		Customer cu = new Customer("Patrick", "pvonplaten", "VonPlaten", a, "pvonplaten@gmail.com", "630285192", "newpassword");
		c.registerUser(cu);
		c.userLogIn(cu);
		c.registerObserver(cu);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "pvonplaten");
	}

	@Test
	public void testRemoveObserver() {
		Core c = new Core();
		Address a = new Address(3,4);
		Customer cu = new Customer("Patrick", "pvonplaten", "VonPlaten", a, "pvonplaten@gmail.com", "630285192", "newpassword");
		c.registerUser(cu);
		c.userLogIn(cu);
		c.registerObserver(cu);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "pvonplaten");
		c.removeObserver(cu);
		assertTrue(c.getListOfToNotify().isEmpty());
	}

	@Test
	public void testNotifyObservers() {
		Core c = new Core();
		
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
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m1);
		
		Address a2 = new Address(3,4);
		Customer cu = new Customer("Patrick", "pvonplaten", "VonPlaten", a2, "pvonplaten@gmail.com", "630285192", "newpassword");
		c.registerUser(cu);
		c.userLogIn(cu);
		c.registerObserver(cu);
		c.notifyObservers(r, m1);
	}

	@Test
	public void testActivateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeactivateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeServiceFee() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeMarkup() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeDeliveryCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTargetProfitToDeliveryCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTargetProfitToMarkup() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTargetProfitToServiceFee() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParameterToTargetProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testComputeTotalIncome() {
		fail("Not yet implemented");
	}

	@Test
	public void testComputeTotalProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testComputeAverageIncome() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeastActiveRestaurant() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostActiveRestaurant() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeastActiveCourier() {
		fail("Not yet implemented");
	}

	@Test
	public void testMostActiveCourier() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDeliveryToFastest() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDeliveryToFairOccupation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayRestaurantInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSpecialMeal() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveSpecialMeal() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceNewOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegisterFidelityCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnregisterFidelityCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHistoryOrders() {
		fail("Not yet implemented");
	}

	@Test
	public void testGiveRemoveConsensus() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnregisterCourier() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCourierState() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCourierPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testProcessOrders() {
		fail("Not yet implemented");
	}

}
