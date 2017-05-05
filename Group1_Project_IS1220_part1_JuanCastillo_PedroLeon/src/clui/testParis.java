package clui;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import users.Courier;
import users.Customer;
import users_theards.CourierThread;
import users_theards.CustomerThread;

public class testParis {
	public static Lock l = new ReentrantLock();
	
	public static void main (String[] args){
		MyFoodora mf = new MyFoodora();
		mf.MyFoodoraParis();
		
		Thread tcustomer1 = new Thread(testParis.castToCustThread((Customer) MyFoodora.getCore().getListOfUsers().get("c1username1")));
		Thread tcourier1 = new Thread(testParis.castToCourThread((Courier) MyFoodora.getCore().getListOfUsers().get("cuusername1")));
		
		tcustomer1.start();
		tcourier1.start();
	}
	
	public static CustomerThread castToCustThread(Customer c){
		return new CustomerThread(c.getName(), c.getUsername(), c.getSurname(), c.getAddress(), c.getEmail(), c.getPhoneNumber(), c.getPassword(), l);
	}
	
	public static CourierThread castToCourThread(Courier co){
		return new CourierThread(co.getName(), co.getUsername(), co.getPassword(), co.getSurname(), co.getPosition(), co.getPhoneNumber(), l);
	}
}
