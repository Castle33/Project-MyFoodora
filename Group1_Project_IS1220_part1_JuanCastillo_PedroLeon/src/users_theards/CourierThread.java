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
	
	public CourierThread(Lock l, MyFoodora myf) {
		mf = myf;
		vc = VirtualCalendar.getUnique();
		myfoodora = l;
	}

	@Override
	public void run() {
		Calendar fin = Calendar.getInstance();
		fin.add(Calendar.MINUTE, 60);
		while(vc.getVirtualDate().before(fin)){
			myfoodora.lock();
			putOffDutty();
			myfoodora.unlock();
		}
	}
	
	public void putOffDutty(){
		Calendar currentTime = vc.getVirtualDate();
		Courier cou = pickUpACour();
		if(cou != null){
			if(cou.getCurrentOrder().getDeliveryDate().before(currentTime)){
				System.out.println("Delivery time : " + cou.getCurrentOrder().getDeliveryDate().getTime() + " - current time : " + currentTime.getTime());
				mf.treatCmd("logIn \"" + cou.getUsername() + "\" \"" + cou.getPassword() + "\"");
				mf.treatCmd("offDuty \"" + cou.getUsername() + "\"");
				mf.treatCmd("logOut \"\"");
			}
		}
	}
	
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
