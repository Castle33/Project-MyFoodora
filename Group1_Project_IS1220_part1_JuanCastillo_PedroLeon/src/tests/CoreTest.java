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
		Manager m = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		c.registerUser(m);
		assertTrue(c.getListOfUsers().get("mbataillou").getUsername() == "mbataillou");
	}

	@Test
	public void testUserLogIn() {
		Core c = new Core();
		Manager m = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		c.registerUser(m);
		c.userLogIn(m);
		assertTrue(c.getCurrentUser().getUsername() == "mbataillou");
	}

	@Test
	public void testLogOut() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.registerUser(m);
		c.userLogIn(m);
		assertTrue(c.getCurrentUser().getUsername() == "pleonpita");
		c.logOut();
		assertNull(c.getCurrentUser());
	}

	@Test
	public void testRegisterObserver() {
		Core c = new Core();
		Address a = new Address(3,4);
		Customer cu = new Customer("Luis", "luiscobas", "Cobas", a, "lcobas@gmail.com", "630285192", "newpassword");
		c.registerUser(cu);
		c.userLogIn(cu);
		c.registerObserver(cu);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "luiscobas");
	}

	@Test
	public void testRemoveObserver() {
		Core c = new Core();
		Address a = new Address(3,4);
		Customer cu = new Customer("Luis", "luiscobas", "Cobas", a, "lcobas@gmail.com", "630285192", "newpassword");
		c.registerUser(cu);
		c.userLogIn(cu);
		c.registerObserver(cu);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "luiscobas");
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
		Address a3 = new Address(3,4);
		Address a4 = new Address(6,8);
		Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", a2, "lcobas@gmail.com", "630285192", "newpassword");
		Customer cu2 = new Customer("Juan", "jcastillo33", "Castillo", a3, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer cu3 = new Customer("Pedro", "pleonpita", "Leon", a4, "pleonpita@gmail.com", "0695599143", "newpassword2");
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
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);

		Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", new Address(3,4), "lcobas@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr",  new Address(3,4));
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(4,8),"0654641222");
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
		
		c.activateUser(cu1);
		c.activateUser(r);
		c.activateUser(d);
		
		assertTrue(c.getListOfUsers().get("luiscobas").getUsername() == "luiscobas");
		assertTrue(c.getListOfUsers().get("LaPlayaBilbao").getUsername() == "LaPlayaBilbao");
		assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
	}

	@Test
	public void testDeactivateUser() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);

		Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", new Address(3,4), "lcobas@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr",  new Address(3,4));
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(4,8),"0654641222");
		
		c.registerUser(cu1);
		c.registerUser(r);
		c.registerUser(d);
		
		c.deactivateUser(cu1);
		c.deactivateUser(r);
		c.deactivateUser(d);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
	}

	@Test
	public void testAddUser() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);

		Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", new Address(3,4), "lcobas@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr",  new Address(3,4));
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(4,8),"0654641222");
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
		
		c.addUser(cu1);
		c.addUser(r);
		c.addUser(d);
		
		assertTrue(c.getListOfUsers().get("luiscobas").getUsername() == "luiscobas");
		assertTrue(c.getListOfUsers().get("LaPlayaBilbao").getUsername() == "LaPlayaBilbao");
		assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
	}

	@Test
	public void testRemoveUser() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);

		Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", new Address(3,4), "lcobas@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr",  new Address(3,4));
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(4,8),"0654641222");
		
		c.registerUser(cu1);
		c.registerUser(r);
		c.registerUser(d);
		
		c.removeUser(cu1);
		c.removeUser(r);
		c.removeUser(d);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
	}

	@Test
	public void testChangeServiceFee() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);
		
		c.changeServiceFee(4.0);
		
		assertTrue(c.getServiceFee() == 4.0);
	}

	@Test
	public void testChangeMarkup() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);
		
		c.changeMarkup(0.15);
		
		assertTrue(c.getMarkupPercentage() == 0.15);
	}

	@Test
	public void testChangeDeliveryCost() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);
		
		c.changeDeliveryCost(3.0);
		
		assertTrue(c.getDeliveryCost() == 3.0);
	}

	@Test
	public void testSetTargetProfitToDeliveryCost() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);
		
		c.setTargetProfitToDeliveryCost();
		
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitDeliveryCost);
	}

	@Test
	public void testSetTargetProfitToMarkup() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
		c.userLogIn(m);
		
		c.setTargetProfitToMarkup();
		
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitMarkup);
	}

	@Test
	public void testSetTargetProfitToServiceFee() {
		Core c = new Core();
		Manager m = (Manager) c.getListOfMasterManager().get(1);
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
