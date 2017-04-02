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
import system.TargetProfitMarkup;
import system.TargetProfitServiceFee;
import users.Manager;
import users.Restaurant;
import users.User;
import users.Address;
import users.Courier;
import users.Customer;

public class CoreTest {
	
	/* Core */
	Core c = new Core();

	/* Address */
	Address a1 = new Address(3,4);
	Address a2 = new Address(3,4);
	Address a3 = new Address(3,4);
	Address a4 = new Address(6,8);
	
	/* Users - register/logIn must be done in each test */
	Manager m = (Manager) c.getListOfMasterManager().get(1);
	Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", a1, "lcobas@gmail.com", "630285192", "newpassword");
	Customer cu2 = new Customer("Juan", "jcastillo33", "Castillo", a3, "jcastillo@gmail.com", "630285192", "newpassword");
	Customer cu3 = new Customer("Pedro", "pleonpita", "Leon", a4, "pleonpita@gmail.com", "0695599143", "newpassword2");
	Restaurant r1 = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
	Courier co1 = new Courier("luiso","lucho","password1","cobas", a2,"0654641222");
	
	/* Items */
	Starter s1 = new Starter("Tortilla", 5.5, "Standard");
	MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
	Dessert d1 = new Dessert("Melon", 4, "Standard");
	
	/* Meals - items must be added in each test */
	ArrayList<Item> list1 = new ArrayList<Item>();
	HalfMeal m1 = new HalfMeal("Mediterranea", list1);

	@Test
	public void testCore() {
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
		Manager man = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		c.registerUser(man);
		assertTrue(c.getListOfUsers().get("mbataillou").getUsername() == "mbataillou");
	}

	@Test
	public void testUserLogIn() {
		Manager man = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		c.registerUser(man);
		c.userLogIn(man);
		assertTrue(c.getCurrentUser().getUsername() == "mbataillou");
	}

	@Test
	public void testLogOut() {
		Manager man = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		c.userLogIn(man);
		assertTrue(c.getCurrentUser().getUsername() == "mbataillou");
		c.logOut();
		assertNull(c.getCurrentUser());
	}

	@Test
	public void testRegisterObserver() {
		c.registerUser(cu1);
		c.userLogIn(cu1);
		c.registerObserver(cu1);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "luiscobas");
	}

	@Test
	public void testRemoveObserver() {
		c.registerUser(cu1);
		c.userLogIn(cu1);
		c.registerObserver(cu1);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "luiscobas");
		c.removeObserver(cu1);
		assertTrue(c.getListOfToNotify().isEmpty());
	}

	@Test
	public void testNotifyObservers() {
		list1.add(s1);
		list1.add(md1);
		m1.setMealItems(list1);
		r1.addMeal(m1);
		c.registerUser(cu1);
		c.registerObserver(cu1);
		c.registerUser(cu2);
		c.registerObserver(cu2);
		c.registerUser(cu3);
		c.registerObserver(cu3);
		//c.notifyObservers(r, m1);
	}

	@Test
	public void testActivateUser() {
		c.userLogIn(m);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
		
		c.activateUser(cu1);
		c.activateUser(r1);
		c.activateUser(co1);
		
		assertTrue(c.getListOfUsers().get("luiscobas").getUsername() == "luiscobas");
		assertTrue(c.getListOfUsers().get("LaPlayaBilbao").getUsername() == "LaPlayaBilbao");
		assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
	}

	@Test
	public void testDeactivateUser() {
		c.userLogIn(m);
		
		c.registerUser(cu1);
		c.registerUser(r1);
		c.registerUser(co1);
		
		c.deactivateUser(cu1);
		c.deactivateUser(r1);
		c.deactivateUser(co1);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
	}

	@Test
	public void testAddUser() {
		c.userLogIn(m);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
		
		c.addUser(cu1);
		c.addUser(r1);
		c.addUser(co1);
		
		assertTrue(c.getListOfUsers().get("luiscobas").getUsername() == "luiscobas");
		assertTrue(c.getListOfUsers().get("LaPlayaBilbao").getUsername() == "LaPlayaBilbao");
		assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
	}

	@Test
	public void testRemoveUser() {
		c.userLogIn(m);
		
		c.registerUser(cu1);
		c.registerUser(r1);
		c.registerUser(co1);
		
		c.removeUser(cu1);
		c.removeUser(r1);
		c.removeUser(co1);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
	}

	@Test
	public void testChangeServiceFee() {
		c.userLogIn(m);
		c.changeServiceFee(4.0);
		assertTrue(c.getServiceFee() == 4.0);
	}

	@Test
	public void testChangeMarkup() {
		c.userLogIn(m);
		c.changeMarkup(0.15);
		assertTrue(c.getMarkupPercentage() == 0.15);
	}

	@Test
	public void testChangeDeliveryCost() {
		c.userLogIn(m);
		c.changeDeliveryCost(3.0);
		assertTrue(c.getDeliveryCost() == 3.0);
	}

	@Test
	public void testSetTargetProfitToDeliveryCost() {
		c.userLogIn(m);
		c.setTargetProfitToDeliveryCost();
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitDeliveryCost);
	}

	@Test
	public void testSetTargetProfitToMarkup() {
		c.userLogIn(m);
		c.setTargetProfitToMarkup();
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitMarkup);
	}

	@Test
	public void testSetTargetProfitToServiceFee() {
		c.userLogIn(m);
		c.setTargetProfitToServiceFee();
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitServiceFee);
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
