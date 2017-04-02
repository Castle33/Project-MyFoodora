package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import system.Core;
import system.DeliveryFastest;
import system.Order;
import system.TargetProfitDeliveryCost;
import users.Manager;
import users.User;

public class CoreTest {

	@Test
	public void testCore() {
		Core c = new Core();
		assertTrue(c.getN);
		this.name = "MyFoodora";
		this.serviceFee = 3.0;
		this.markupPercentage = 0.1;
		this.deliveryCost = 2.0;
		
		listOfUsers = new HashMap<String,User>();
		
		listOfToNotify = new ArrayList<User>();
		
		deliveryPolicy = new DeliveryFastest();
		tProfitPolicy = new TargetProfitDeliveryCost();
		
		listOfCompletedOrders = new ArrayList<Order>();
		listOfPendingOrders = new LinkedList<Order>();
		
		/*
		 * initialization of listOfMasterManager adding both app creators
		 */
		this.listOfMasterManager = new ArrayList<User>();
		Manager jc = new Manager("Juan", "jcastillo33", "pjausasnword", "Castillo");
		listOfMasterManager.add(jc);
		Manager pl = new Manager("Pedro", "pleonpita", "ppaesdsrwoord", "Leon");
		listOfMasterManager.add(pl);
		listOfUsers.put(jc.getUsername(), jc);
		listOfUsers.put(pl.getUsername(), pl);
	}

	@Test
	public void testRegisterUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserLogIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogOut() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegisterObserver() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveObserver() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyObservers() {
		fail("Not yet implemented");
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
