package system;

import java.util.*;
import java.util.Calendar;
import restaurant_structure.Meal;
import restaurant_structure.Item;
import users.Customer;
import users.Restaurant;
import users.Courier;

public class Order {
	
	private int ID;
	private static int counter = 0;
	private Customer customer;
	private Restaurant restaurant;
	private HashMap<Meal,Integer> meals;
	private HashMap<Item,Integer> items;
	private Courier courier;
	private double priceFood;
	private double priceTotal;
	private Calendar date;
	private Calendar deliveryDate;
	
	public Order(Customer customer, Restaurant restaurant) {
		super();
		this.ID = (++counter);
		this.customer = customer;
		this.restaurant = restaurant;
		this.date = Calendar.getInstance();
		this.deliveryDate = null;
		meals = new HashMap<Meal,Integer>();
		items = new HashMap<Item,Integer>();
	}

	public Order(Customer customer, Restaurant restaurant, Calendar date) {
		super();
		this.ID = (++counter);
		this.customer = customer;
		this.restaurant = restaurant;
		this.date = date;
		this.deliveryDate = null;
		meals = new HashMap<Meal,Integer>();
		items = new HashMap<Item,Integer>();
	}
	
	/***************************************************************************************************/
	/* Calculate Delivery Time Method:
	 * Considering the Coordinates in meters and the average speed of 50 km/h (13.9 m/s)
	 * 
	 */
	
	public Calendar calcDeliveryTime(){
		int deliveryTime = (int) Math.round(customer.getAddress().calcDistance(restaurant.getAddress())/13.9);
		deliveryDate = date;
		deliveryDate.add(Calendar.SECOND, deliveryTime);
		return deliveryDate;
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
		double price = 0.0;
		
		if(isFidelityCardBasic()){
			for (Meal m : meals.keySet()){
				price += meals.get(m)*restaurant.getPriceMeal(m);
			}
		} else {
			for (Meal m : meals.keySet()){
				price += meals.get(m)*m.getFullPrice();
			}
		}
		for (Item i : items.keySet()){
			price += items.get(i)*i.getPrice();
		}
		price *= customer.getFidelityCard().applyFidelityPlan();
		price = restaurant.round2dec(price);
		this.priceFood = price;
		return price;
	}

	/***************************************************************************************************/
	/* toString method */
	
	@Override
	public String toString() {
		return "Order [ID=" + ID + ", customer=" + customer + ", restaurant=" + restaurant + ", meals=" + meals
				+ ", items=" + items + ", courier=" + courier + ", priceFood=" + priceFood + ", priceTotal="
				+ priceTotal + ", date=" + date + "]";
	}

	/***************************************************************************************************/
	/*
	 * Getters and Setters: no setters for ID and Counter
	 */
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public HashMap<Meal, Integer> getMeals() {
		return meals;
	}

	public void setMeals(HashMap<Meal, Integer> meals) {
		this.meals = meals;
	}

	public HashMap<Item, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<Item, Integer> items) {
		this.items = items;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public double getPriceFood() {
		return priceFood;
	}

	public void setPriceFood(double priceFood) {
		this.priceFood = priceFood;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getID() {
		return ID;
	}

	public static int getCounter() {
		return counter;
	}

	public Calendar getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
}
