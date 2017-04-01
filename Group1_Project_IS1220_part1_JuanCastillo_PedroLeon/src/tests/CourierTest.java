package tests;

import static org.junit.Assert.*;
import system.Order;
import java.util.Collections;
import java.util.ArrayList;
import users.*;

import org.junit.Test;

public class CourierTest {

	@Test
	public void testCourier() {
		Courier c = new Courier("pedron","pleon","password1","leon",new Address(2,6),"0754641222");
		Address temp_pos = new Address(2,6);
		System.out.println(c.toString());
		assertTrue(c.getSurname().equals("leon") && c.getPosition().equals(temp_pos) && c.getPhoneNumber().equals("0754641222"));
		assertTrue(c.getCountOfOrdersCompleted()==0 && c.isOnDuty()==false && c.getListPendingOrders().isEmpty() && c.getCurrentOrder()==null && c.getDistanceToRest()==0.0);
	}

	@Test
	public void testCompareDistance(){
		Courier c = new Courier("pedron","pleon","password1","leon",new Address(2,6),"0754641222");
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(4,8),"0654641222");
		ArrayList<Courier> clist = new ArrayList<Courier>();
		clist.add(c); clist.add(d);
		Collections.sort(clist, Courier.compareDistance());
		assertTrue(clist.get(0).equals(c));
		clist.get(0).setDistanceToRest(5);
		Collections.sort(clist, Courier.compareDistance());
		assertTrue(clist.get(0).equals(d));
		clist.get(0).setDistanceToRest(10);
		Collections.sort(clist, Courier.compareDistance());
		assertTrue(clist.get(0).equals(c));
	}

	@Test
	public void testCompareNumOrders() {
		Courier c = new Courier("pedron","pleon","password1","leon",new Address(2,6),"0754641222");
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(4,8),"0654641222");
		ArrayList<Courier> clist = new ArrayList<Courier>();
		clist.add(c); clist.add(d);
		Collections.sort(clist, Courier.compareNumOrders());
		assertTrue(clist.get(0).equals(c));
		clist.get(0).setCountOfOrdersCompleted(5);
		Collections.sort(clist, Courier.compareNumOrders());
		assertTrue(clist.get(0).equals(d));
		clist.get(0).setCountOfOrdersCompleted(10);
		Collections.sort(clist, Courier.compareNumOrders());
		assertTrue(clist.get(0).equals(c));
	}

	@Test
	public void testCompareDeliveyDate() {
		Courier c = new Courier("pedron","pleon","password1","leon",new Address(2,6),"0754641222");
		Courier d = new Courier("luiso","lucho","password1","cobas",new Address(50,100),"0654641222");
		Address a = new Address(3,4);
		Address a1 = new Address(10,20);
		Order o = new Order(new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a1));
		Order o1 = new Order(new Customer("Juan", "jcastillo33", "Castillo", a, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a1));
		c.setCurrentOrder(o);
		d.setCurrentOrder(o1);
	}

	@Test
	public void testAddNewOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testAcceptOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testRefuseOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecideTakingOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecideAddOrder() {
		fail("Not yet implemented");
	}

}
