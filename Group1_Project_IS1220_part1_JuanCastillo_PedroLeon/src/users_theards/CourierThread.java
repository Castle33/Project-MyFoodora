package users_theards;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;

import clui.MyFoodora;
import clui.VirtualCalendar;
import users.Address;
import users.Courier;
import users.User;

public class CourierThread extends Courier implements Serializable, Runnable{
	private static final long serialVersionUID = -1073852164817064065L;
	private Lock myfoodora;
	private static MyFoodora mf;
	private static VirtualCalendar vc;
	private File log;
	
	public CourierThread(String name, String username, String password, String surname, Address position, String phoneNumber, Lock l) {
		super(name, username, password,surname,position,phoneNumber);
		mf = new MyFoodora();
		vc = VirtualCalendar.getUnique();
		myfoodora = l;
		log = new File("./src/texts/courTestParis.txt");
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
		mf.treatCmd("initCore \"./ser_file/core_Paris.ser\"");
	}
	
	public void putOffDutty(){
		Calendar currentTime = vc.getVirtualDate();
		Courier cou = pickUpACour();
		if(cou != null){
			if(cou.getCurrentOrder().getDeliveryDate().before(currentTime)){
				try{
					PrintWriter writer = new PrintWriter(log);
					writer.append(cou.getUsername() + " delivered to " + cou.getCurrentOrder().getCustomer().getUsername() + " at " + currentTime.getTime());
					writer.close();
					System.out.println("Delivery time : " + cou.getCurrentOrder().getDeliveryDate().getTime() + " - current time : " + currentTime.getTime());
					mf.treatCmd("logIn \"" + cou.getUsername() + "\" \"" + cou.getPassword() + "\"");
					mf.treatCmd("offDuty \"" + cou.getUsername() + "\"");
					mf.treatCmd("logOut \"\"");
				}catch(IOException e){
					System.out.println(e.getMessage());
				}
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
