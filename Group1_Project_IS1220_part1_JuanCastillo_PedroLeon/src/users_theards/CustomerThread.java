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
	private static VirtualCalendar vc;
	private static ArrayList<Integer> hoursToOrder;
	
	/**
	 * class constructor receiving thread lock and common MyFoodora
	 * @param l
	 * @param myf
	 */
	public CustomerThread(Lock l, MyFoodora myf) {
		myfoodora = l;
		mf = myf;
		vc = VirtualCalendar.getUnique();
		hoursToOrder = new ArrayList<Integer>();
		hoursToOrder.add(7);hoursToOrder.add(8);hoursToOrder.add(9);hoursToOrder.add(10);hoursToOrder.add(11);hoursToOrder.add(12);hoursToOrder.add(13);hoursToOrder.add(14);hoursToOrder.add(15);hoursToOrder.add(16);hoursToOrder.add(17);hoursToOrder.add(18);hoursToOrder.add(19);hoursToOrder.add(20);hoursToOrder.add(21);hoursToOrder.add(22);hoursToOrder.add(23);hoursToOrder.add(0);
	}
	
	/**
	 * thread will execute makeAnOrder until 1 virtual hour has passed
	 */
	@Override
	public void run() {
		Calendar fin = Calendar.getInstance();
		fin.add(Calendar.MINUTE, 60);
		while(vc.getVirtualDate().before(fin)){
			myfoodora.lock();
			makeAnOrder();
			myfoodora.unlock();
		}
		System.out.println("*********************Customer thread ended.*********************");
	}
	
	/**
	 * for 1 out of 5 times customer will pick up a restaurant and a meal/items
	 * we don't take into consideration of current customer has done earlier another order
	 * we suppose the probability of repeating one customer pick up out of 100 customers can be neglected 
	 */
	public void makeAnOrder(){
		try{
			Thread.sleep(100);
			Customer c = pickUpACust();
			Calendar currentTime = vc.getVirtualDate();
			if(Math.random() < wantToOrder){
				if(hoursToOrder.contains(currentTime.getTime().getHours())){
					System.out.println("Customer, time: " + vc.getVirtualDate().getTime() + ". ");
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
				}
			}
			
		}catch(InterruptedException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * returns a random customer out of list of users
	 * @return Customer
	 */
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
		return customer;
	}
	
	/**
	 * returns a random restaurant out of list of users
	 * @return Restaurant
	 */
	public Restaurant pickUpARest(){
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		for(User u : MyFoodora.getCore().getListOfUsers().values()){
			if(u instanceof Restaurant){
				restaurants.add((Restaurant)u);
			}
		}
		int position = (int) (Math.random()*restaurants.size());
		Restaurant res = null;
		if(restaurants.size() > position){
			return restaurants.get(position);
		}
		return res;
	}
	
	/**
	 * for a random restaurant returns a random meal of its menu
	 * @param res
	 * @return Meal
	 */
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
	
	/**
	 * for a random restaurant returns a random combination of 3 items (Starter,Main Dish and Dessert)
	 * @param res
	 * @return ArrayList
	 */
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
