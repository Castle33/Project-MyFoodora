package users_theards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;

import clui.MyFoodora;
import restaurant_structure.Item;
import restaurant_structure.Meal;
import users.User;
import users.Customer;
import users.Restaurant;

public class CustomerThread implements Serializable, Runnable{
	private static final long serialVersionUID = -1073852164817064065L;
	private static final double wantToOrder = 0.2;
	private Lock myfoodora;
	private MyFoodora mf;
	private HashMap<String,Calendar> lastOrder;
	private static VirtualCalendar vc;
	private static ArrayList<Integer> hoursToOrder;
	
	public CustomerThread(Lock l, MyFoodora myf) {
		myfoodora = l;
		mf = myf;
		vc = VirtualCalendar.getUnique();
		lastOrder = new HashMap<String, Calendar>();
		hoursToOrder = new ArrayList<Integer>();
		hoursToOrder.add(11);hoursToOrder.add(12);hoursToOrder.add(16);hoursToOrder.add(17);hoursToOrder.add(18);hoursToOrder.add(19);hoursToOrder.add(20);hoursToOrder.add(21);
		for(User u : MyFoodora.getCore().getListOfUsers().values()){
			if(u instanceof Customer){
				lastOrder.put(u.getUsername(), Calendar.getInstance());
			}
		}
	}

	@Override
	public void run() {
		Calendar fin = Calendar.getInstance();
		fin.add(Calendar.MINUTE, 60);
		while(vc.getVirtualDate().before(fin)){
			myfoodora.lock();
			makeAnOrder();
			myfoodora.unlock();
		}
	}
	
	public void makeAnOrder(){
		try{
			Thread.sleep(100);
			Customer c = pickUpACust();
			Calendar currentTime = vc.getVirtualDate();
			if(Math.random() < wantToOrder){
				if(hoursToOrder.contains(currentTime.getTime().getHours())){
					mf.treatCmd("logIn \"" + c.getUsername() + "\" \"" + c.getPassword() + "\"");
					Restaurant res = pickUpARest();
					mf.treatCmd("createOrder \"" + res.getUsername() + "\" \"Order\"");
					if(Math.random() < 0.6){
						Meal m = pickUpAMeal(res);
						mf.treatCmd("addMeal2Order \"Order\" \"" + m.getName() + "\" \"1\"");
					}else{
						ArrayList<Item> items = pickUpItems(res);
						for(Item i : items){
							mf.treatCmd("addItem2Order \"Order\" \"" + i.getName() + "\" \"1\"");
						}
					}
					mf.treatCmd("endOrder \"Order\"");
					mf.treatCmd("logOut \"\"");
					Calendar auxTime = lastOrder.get(c.getUsername());
					lastOrder.remove(c.getUsername());
					auxTime.setTime(currentTime.getTime());
					lastOrder.put(c.getUsername(),auxTime);
				}
			}
			
		}catch(InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
	
	public Customer pickUpACust(){
		int count = 0;
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for(User u : MyFoodora.getCore().getListOfUsers().values()){
			if(u instanceof Customer){
				customers.add(((Customer)u));
			}
		}
		int position = (int) (Math.random()*customers.size());
		Customer customer = null;
		for(Customer c : customers){
			if (count == position){
				customer = c;
			}
			count ++;
		}
		if(lastOrder.get(customer.getUsername()) == null){
			lastOrder.put(customer.getUsername(),Calendar.getInstance());
		}
		return customer;
	}
	
	public Restaurant pickUpARest(){
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		for(User u : MyFoodora.getCore().getListOfUsers().values()){
			if(u instanceof Restaurant){
				restaurants.add((Restaurant)u);
			}
		}
		int position = (int) (Math.random()*restaurants.size());
		Restaurant res = null;
		
		System.out.println(restaurants);
		System.out.println(restaurants.size());
		if(restaurants.size() > position){
			return restaurants.get(position);
		}
		return res;
	}
	
	public Meal pickUpAMeal(Restaurant res){
		int count = 0;
		Meal meal = null;
		if(Math.random()<1){
			int position = (int) Math.random()*res.getListOfMeal().size();
			for(Meal m : res.getListOfMeal()){
				if (count == position){
					meal = m;
					count ++;
				}
			}
		}else{
			int position = (int) Math.random()*res.getListOfSpecialMeal().size();
			for(Meal m : res.getListOfSpecialMeal()){
				if (count == position){
					meal = m;
					count ++;
				}
			}
		}
		return meal;
	}
	
	public ArrayList<Item> pickUpItems(Restaurant res){
		int countStarter = 0;
		int positionStarter = (int) Math.random()*res.getMenu().getStarters().size();
		int countMain = 0;
		int positionMain = (int) Math.random()*res.getMenu().getMainDishes().size();
		int countDessert = 0;
		int positionDessert = (int) Math.random()*res.getMenu().getDesserts().size();
		ArrayList<Item> items = new ArrayList<Item>();
		for (Item s : res.getMenu().getStarters()){
			if(countStarter == positionStarter){
				items.add(s);
			}
			countStarter++;
		}
		for (Item m : res.getMenu().getMainDishes()){
			if(countMain == positionMain){
				items.add(m);
			}
			countMain++;
		}
		for (Item d : res.getMenu().getDesserts()){
			if(countDessert == positionDessert){
				items.add(d);
			}
			countDessert++;
		}
		return items;
	}
}
