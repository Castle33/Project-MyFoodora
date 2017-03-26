package system;

import java.util.*;
import java.util.Calendar;
import restaurant_structure.Meal;
import restaurant_structure.Item;
import users.Customer;
import users.Restaurant;

public class Order {
	
	private int ID;
	private static int counter = 0;
	private Customer customer;
	private Restaurant restaurant;
	private HashMap<Meal,Integer> meals;
	private HashMap<Item,Integer> items;
	private Calendar date;
	
	public Order(Customer customer, Restaurant restaurant) {
		super();
		this.ID = (++counter);
		this.customer = customer;
		this.restaurant = restaurant;
		this.date = Calendar.getInstance();
		meals = new HashMap<Meal,Integer>();
		items = new HashMap<Item,Integer>();
	}

	public Order(Customer customer, Restaurant restaurant, Calendar date) {
		super();
		this.ID = (++counter);
		this.customer = customer;
		this.restaurant = restaurant;
		this.date = date;
		meals = new HashMap<Meal,Integer>();
		items = new HashMap<Item,Integer>();
	}
	
	/***************************************************************************************************/
	/* Add Meals and Items to the order */
	
	public void addMeal(Meal m, int n){
		if(!meals.containsKey(m)){
			meals.put(m, n);
		} else {
			int prevValue = meals.get(m);
			meals.put(m, prevValue + n);
		}
	}
	
	public void addItem(Item i, int n){
		if(!items.containsKey(i)){
			items.put(i, n);
		} else {
			int prevValue = items.get(i);
			items.put(i, prevValue + n);
		}
	}
	
	/***************************************************************************************************/
	/* Methods to check if Basic Fidelity Card and Calculate Order Price */
	
	public boolean isFidelityCardBasic(){
		return(customer.getFidelityCard() instanceof FidelityCardBasic);
	}
	
	public double calcPrice(){
		double price;
		
		if(isFidelityCardBasic()){
			
		}
	}
}
