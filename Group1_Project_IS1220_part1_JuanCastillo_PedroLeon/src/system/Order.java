package system;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import restaurant_structure.Meal;
import restaurant_structure.Item;
import users.Customer;
import users.Restaurant;
import users.Address;
/**
 * The class use to create an order that has the information of the customer that ordered it and the restaurant chosen
 * by him. Contains information of the date it was created and the approximate delivery time, as well as a list of all
 * meals and dishes chosen, with its amount and final price.
 * @author Juan Castillo (programmer)
 * @author Pedro León (coder)
 * tested: YES
 */
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4760926302086088767L;
	private int ID;
	private static int counter = 0;
	private Customer customer;
	private Restaurant restaurant;
	private HashMap<Meal,Integer> meals;
	private HashMap<Item,Integer> items;
	private boolean assignedCourier;
	private double priceFood;
	private double priceTotal;
	private Calendar date;
	private Calendar deliveryDate;
	private String name;
	/**
	 * Constructor without date: sets the date to the current local time
	 * tested: YES
	 * @param customer
	 * @param restaurant
	 */
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
	/**
	 * Constructor with date
	 * tested: YES
	 * @param customer
	 * @param restaurant
	 * @param date
	 */
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
	/**
	 * Calculates the approximated delivery time of the order, considering the Coordinates in meters and the average
	 * speed of the courier of 50 km/h (13.9 m/s). The function calculates the time it takes the courier to reach the
	 * restaurant and then arrive to the customer's address.
	 * tested: YES
	 * @return a CALENDAR value of the calculated delivery date
	 */
	public Calendar calcDeliveryTime(Address address){
		int deliveryTime = (int) Math.round(customer.getAddress().calcDistance(restaurant.getAddress())/0.0139);
		int courierRestTime = (int) Math.round(address.calcDistance(restaurant.getAddress())/0.0139);
		deliveryDate = Calendar.getInstance();
		deliveryDate.add(Calendar.SECOND, deliveryTime + courierRestTime);
		return deliveryDate;
	}
	
	/***************************************************************************************************/
	/* Add Meals and Items to the order */
	/*
	 * n refers to the quantity desired of meal m
	 * example: I want two pizzas marguerita
	 * - m = marguerita
	 * - n = 2
	 */
	/**
	 * Adds a meal to the order
	 * tested: YES
	 * @param m
	 * the desired meal to add
	 * @param n
	 * the number of the meal m to add
	 */
	public void addMeal(Meal m, int n){
		if(!meals.containsKey(m)){
			meals.put(m, n);
		} else {
			int prevValue = meals.get(m);
			meals.put(m, prevValue + n);
		}
	}
	/**
	 * Adds a dish to the order
	 * tested: YES
	 * @param i
	 * the desired dish to add
	 * @param n
	 * the number of the dish i to add
	 */
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
	/**
	 * tested: YES
	 * @return
	 */
	public boolean isFidelityCardBasic(){
		return(customer.getFidelityCard() instanceof FidelityCardBasic);
	}
	/**
	 * Calculates the final price of the order, taking into account the special meals and the fdelity card plan of
	 * the customer
	 * tested: YES
	 * @return the final price as a two decimal number
	 */
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
		price = Restaurant.round2dec(price);
		this.priceFood = price;
		return price;
	}

	/***************************************************************************************************/
	/* toString method */
	
	@Override
	public String toString() {
		String mealDisplay = "";
		for(Meal m : meals.keySet()){
			mealDisplay += m.getName() + "-" + meals.get(m) + "2";
		}
		String itemDisplay = "";
		for(Item i : items.keySet()){
			itemDisplay += i.getName() + "-" + items.get(i);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateDisplay = date.getTime().getHours() + ":" + date.getTime().getMinutes() + ":" + date.getTime().getSeconds();
		return "Order [ID=" + ID + ", customer=" + customer.getUsername() + ", restaurant=" + restaurant.getUsername() + ", meals=" + mealDisplay
				+ ", items=" + itemDisplay + ", assignedCourier=" + assignedCourier + ", priceFood=" + priceFood + ", date=" + sdf.format(date.getTime()) + " " + dateDisplay + "]";
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

	public boolean isAssignedCourier() {
		return assignedCourier;
	}

	public void setAssignedCourier(boolean assignedCourier) {
		this.assignedCourier = assignedCourier;
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
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		this.name = n;
	}
	
	
}
