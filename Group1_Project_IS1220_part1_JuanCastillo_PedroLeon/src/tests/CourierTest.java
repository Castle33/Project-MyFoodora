package tests;

import static org.junit.Assert.*;
import system.Order;
import java.util.*;

import users.*;

import org.junit.Test;

public class CourierTest {

	@Test
	public void testCourier() {
		Courier c = new Courier("pedron","pleon","password1","leon",new Address(2,6),"0754641222");
		Address temp_pos = new Address(2,6);
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
	public void testCompareDeliveryDate() {
		Courier c = new Courier("c","pleon","password1","c",new Address(0,0),"0754641222");
		Courier d = new Courier("d","lucho","password2","d",new Address(0,0),"0654641222");
		Courier e = new Courier("e","lu","password3","e",new Address(0,0),"0664641222");
		Address a = new Address(0,3000); 
		Address a1 = new Address(4000,3000);
		Address a2 = new Address(0,6000); 
		Address a3 = new Address(8000,6000);
		Address a4 = new Address(0,9000); 
		Address a5 = new Address(12000,9000);
		Order o = new Order(new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a));
		Order o1 = new Order(new Customer("Juan", "jcastillo33", "Castillo", a3, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a2));
		Order o2 = new Order(new Customer("Juan", "jcastillo33", "Castillo", a5, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a4));
		c.setCurrentOrder(o); d.setCurrentOrder(o1); 
		e.setCurrentOrder(o2);
		d.getCurrentOrder().calcDeliveryTime(d.getPosition());
		c.getCurrentOrder().calcDeliveryTime(c.getPosition());
		e.getCurrentOrder().calcDeliveryTime(e.getPosition());
		LinkedList<Courier> clist = new LinkedList<Courier>();
		clist.addLast(d);
		clist.addLast(e);
		clist.addLast(c);
		Collections.sort(clist, Courier.compareDeliveryDate());
		assertTrue(clist.get(0).equals(c));
		assertTrue(clist.get(1).equals(d));
		assertTrue(clist.get(2).equals(e));
		
	}

	@Test
	public void testAddNewOrder() {
		Courier c = new Courier("c","pleon","password1","c",new Address(0,0),"0754641222");
		Address a = new Address(0,3000); 
		Address a1 = new Address(4000,3000);
		Order o = new Order(new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a));
		c.addNewOrder(o);
		assertTrue(c.getListPendingOrders().getFirst().equals(o));
		assertTrue(c.getListPendingOrders().getFirst().isAssignedCourier());
	}

	@Test
	public void testAcceptOrder() {
		Courier c = new Courier("c","c","pass","c",new Address(0,0),"07");
		Address a = new Address(0,5000); 
		Address a1 = new Address(0,10000);
		Order o = new Order(new Customer("J", "j", "C", a1, "j@gmail.com", "63", "pass"), new Restaurant("T", "T", "pass", a));
		c.acceptOrder(o);
		assertTrue(c.getCurrentOrder().equals(o));
		assertTrue(c.getCurrentOrder().isAssignedCourier());
		System.out.println(c.getCurrentOrder().getDeliveryDate()); //Expected 12 minutes delay
		assertTrue(c.getCountOfOrdersCompleted()==1);
		assertTrue(c.isOnDuty());
	}

	@Test
	public void testDecideTakingOrder() {
		Courier c = new Courier("c","c","pass","c",new Address(0,0),"07");
		Address a = new Address(0,5000); 
		Address a1 = new Address(0,10000);
		Order o = new Order(new Customer("J", "j", "C", a1, "j@gmail.com", "63", "pass"), new Restaurant("T", "T", "pass", a));
		assertTrue(c.decideTakingOrder(o) || !c.decideTakingOrder(o));
	}
	
	@Test
	public void testDecideAddOrder() {
		Courier c = new Courier("c","c","pass","c",new Address(0,0),"07");
		Address a = new Address(0,5000); 
		Address a1 = new Address(0,10000);
		Order o = new Order(new Customer("J", "j", "C", a1, "j@gmail.com", "63", "pass"), new Restaurant("T", "T", "pass", a));
		c.addNewOrder(o);
		assertTrue(!c.decideAddOrder(o));
		c.getListPendingOrders().removeFirst();
		c.decideAddOrder(o);
		System.out.println(!c.getListPendingOrders().isEmpty());
	}

}
