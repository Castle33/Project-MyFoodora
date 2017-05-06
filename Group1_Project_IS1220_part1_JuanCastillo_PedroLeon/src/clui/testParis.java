package clui;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import users.Courier;
import users.Customer;
import users.User;
import users_theards.CourierThread;
import users_theards.CustomerThread;

public class TestParis {
	public static Lock l;
	MyFoodora mf;
	HashMap<String,User> auxUsers = new HashMap<String, User>();
	private Thread tcustomer1 ;
	private Thread tcourier1;
	
	public TestParis(){
		l = new ReentrantLock();
		mf = new MyFoodora();
		auxUsers = MyFoodora.getCore().getListOfUsers();
		tcustomer1 = new Thread(castToCustThread((Customer) auxUsers.get("c1username1")));
		tcourier1 = new Thread(castToCourThread((Courier) auxUsers.get("cuusername1")));
	}
	
	public void runTest(){
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
