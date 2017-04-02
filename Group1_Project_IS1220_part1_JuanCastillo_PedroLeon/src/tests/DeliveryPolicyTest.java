package tests;

import static org.junit.Assert.*;
import java.util.LinkedList;
import users.Address;
import users.Courier;
import system.DeliveryFastest;
import system.DeliveryFairOccupation;

import org.junit.Test;



public class DeliveryPolicyTest {
	
	Courier c = new Courier("c","c","password1","c",new Address(0,0),"0754641222");
	Courier d = new Courier("d","d","password2","d",new Address(1,1),"0654641222");
	Courier e = new Courier("e","e","password3","e",new Address(3,3),"0664641222");
	
	
	@Test
	public void testDeliveryFastest() {
		LinkedList<Courier> clist = new LinkedList<Courier>();
		clist.addLast(d);
		clist.addLast(c);
		clist.addLast(e);
		Address a = new Address(9,9);
		DeliveryFastest df1 = new DeliveryFastest();
			assertTrue(df1.getListSortedCouriers().isEmpty());
		DeliveryFastest df2 = new DeliveryFastest(clist);
			assertTrue(!df2.getListSortedCouriers().isEmpty());
		df2.setDeliveryPolicy(clist, a);
			assertTrue(clist.get(0).equals(e));
			assertTrue(clist.get(1).equals(d));
			assertTrue(clist.get(2).equals(c));
	}
	
	@Test
	public void testDeliveryFairOccupation() {
		c.setCountOfOrdersCompleted(1);
		d.setCountOfOrdersCompleted(10);
		e.setCountOfOrdersCompleted(5);
		LinkedList<Courier> clist = new LinkedList<Courier>();
		clist.addLast(d);
		clist.addLast(c);
		clist.addLast(e);
		Address a = new Address(9,9);
		DeliveryFairOccupation dfo = new DeliveryFairOccupation(clist);
			assertTrue(!dfo.getListSortedCouriers().isEmpty());
		dfo.setDeliveryPolicy(clist, a);
			assertEquals(clist.get(0), c);
			assertEquals(clist.get(1), e);
			assertEquals(clist.get(2), d);
	}

}
