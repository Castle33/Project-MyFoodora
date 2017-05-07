package users_theards;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import clui.MyFoodora;

public class TestParis {
	public static Lock l;
	MyFoodora mf;
	private Thread tcustomer1 ;
	private Thread tcourier1;
	
	public TestParis(){
		l = new ReentrantLock();
		mf = new MyFoodora("Paris");
		tcustomer1 = new Thread(new CustomerThread(l,mf));
		tcourier1 = new Thread(new CourierThread(l,mf));
	}
	
	public void runTest(){
		tcustomer1.start();
		tcourier1.start();
	}
}
