package users_theards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;

import clui.MyFoodora;
import users.Courier;
import users.User;

public class CourierThread implements Serializable, Runnable{
	private static final long serialVersionUID = -1073852164817064065L;
	private Lock myfoodora;
	private MyFoodora mf;
	private static VirtualCalendar vc;
	
	/**
	 * class constructor receiving thread lock and common MyFoodora
	 * @param l
	 * @param myf
	 */
	public CourierThread(Lock l, MyFoodora myf) {
		mf = myf;
		vc = VirtualCalendar.getUnique();
		myfoodora = l;
	}
	
	/**
	 * thread will execute putOffDuty until 1 virtual hour has passed
	 */
	@Override
	public void run() {
		Calendar fin = Calendar.getInstance();
		fin.add(Calendar.MINUTE, 60);
		while(vc.getVirtualDate().before(fin)){
			myfoodora.lock();
			putOffDutty();
			myfoodora.unlock();
		}
		System.out.println("*********************Courier thread finished.*********************");
		System.out.println("PARIS TEST SUCCESFULLY FINISHED\nNow you can go to the top of the console and observe all the orders recieved and delivered.\nOnce too many orders have been accepted we can check that some offers were refused.\nCourier's commands have indentation to distinguish them from Customer's commands.\n\nTo verify test please introduce these commands:\n\nlogIn \"ceo\" \"123456789\"\nshowCourierDeliveries \"\"\nshowRestaurantTop \"\"\nshowListOfOrdersCompleted \"\"\nshowTotalProfit \"7/5/2018\"\nlogOut \"\"\n\n");
		System.out.print(">");
	}
	
	/**
	 * if random courier current order delivery date is earlier than current order then off duty
	 */
	public void putOffDutty(){
		Calendar currentTime = vc.getVirtualDate();
		Courier cou = pickUpACour();
		if(cou != null){
			if(cou.getCurrentOrder().getDeliveryDate().before(currentTime)){
				System.out.println("\tCourier, delivery time : " + cou.getCurrentOrder().getDeliveryDate().getTime() + " - current time : " + currentTime.getTime());
				System.out.print("\t");
				mf.treatCmd("logIn \"" + cou.getUsername() + "\" \"" + cou.getPassword() + "\"");
				System.out.print("\t");
				mf.treatCmd("offDuty \"" + cou.getUsername() + "\"");
				System.out.print("\t");
				mf.treatCmd("logOut \"\"");
			}
		}
	}
	
	/**
	 * from list of users pick up a random on dutty courier
	 * @return Courier
	 */
	public Courier pickUpACour(){
		int count = 0;
		ArrayList<Courier> couriers = new ArrayList<Courier>();
		for(User u : MyFoodora.getCore().getListOfUsers().values()){
			if(u instanceof Courier){
				if(((Courier)u).getCurrentOrder() != null)
				couriers.add(((Courier)u));
			}
		}
		if(couriers.size() != 0){
			int position = (int) (Math.random()*couriers.size());
			Courier courier = null;
			for(Courier co : couriers){
				if (count == position){
					courier = co;
				}
				count ++;
			}
			return courier;
		}else{
			return null;
		}
	}
}
