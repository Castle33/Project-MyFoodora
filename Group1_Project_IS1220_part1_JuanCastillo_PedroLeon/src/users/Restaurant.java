package users;

import java.util.ArrayList;

import restaurant_structure.*;

public class Restaurant extends User{
	
	private Address address;
	private double mealDiscount;
	private double discountFactor;
	private Menu menu;
	private ArrayList<Meal> listOfDiscountMeal;
	private ArrayList<Meal> listOfMeal;
	
	public Restaurant(String name, String username, String password, Address address) {
		super(name, username, password);
		this.address = address;
		this.mealDiscount = 0.05;
		this.discountFactor = 0.1;
		this.menu = new Menu();
		this.listOfMeal = new ArrayList<Meal>();
		this.listOfDiscountMeal = new ArrayList<Meal>();
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + getName() + ", username=" + getUsername() + ", ID=" + getID() + "]";
	}
	
	/***************************************************************************************************/
	/*
	 * Add and remove methods
	 */
	
	public void addStarter (Starter starter){
		menu.addStarter(starter);
	}
	public void removeStarter(Starter starter){
		menu.removeStarter(starter);
	}
	
	public void addMainDish (MainDish mainDish){
		menu.addMainDish(mainDish);
	}
	public void removeMainDish(MainDish mainDish){
		menu.removeMainDish(mainDish);
	}
	
	public void addDessert (Dessert dessert){
		menu.addDessert(dessert);
	}
	public void removeDessert(Dessert dessert){
		menu.removeDessert(dessert);
	}
	
	public void addMeal(Meal meal) {
		listOfMeal.add(meal);
	}
	public void removeMeal(Meal meal) {
		listOfMeal.remove(meal);
	}
	
	public void addDiscountMeal(Meal meal) {
		listOfDiscountMeal.add(meal);
	}
	public void removeDiscountMeal(Meal meal) {
		listOfDiscountMeal.remove(meal);
	}
	
	/***************************************************************************************************/
	/*
	 * Various methods: Get Price Meal, Get Meal/Item by name, Determine if Menu is Special, Round Value to 2 decimals
	 */
	
	public double getPriceMeal (Meal meal){
		if(!listOfDiscountMeal.contains(meal) && !listOfMeal.contains(meal)){
			System.out.println("This meal is not in the Restaurant menu");
			throw new NullPointerException();
		} else if(determineIfDiscountMeal(meal)){
			return round2dec(meal.getFullPrice()*(1-getMealDiscount()));
		}
		return round2dec(meal.getFullPrice()*(1-getDiscountFactor()));
	}
	
	public Meal getMealByName(String mealName){
		for(Meal m :listOfMeal){
			if(m.getName().equalsIgnoreCase(mealName)){
				return m;
			}
		}
		return null;
	}
	
	public Item getItemByName(String itemName){
		for(Item i : menu.getStarters()){
			if(i.getName().equalsIgnoreCase(itemName)){
				return i;
			}
		}
		for(Item i : menu.getMainDishes()){
			if(i.getName().equalsIgnoreCase(itemName)){
				return i;
			}
		}
		for(Item i : menu.getDesserts()){
			if(i.getName().equalsIgnoreCase(itemName)){
				return i;
			}
		}
		return null;
	}
	
	public boolean determineIfDiscountMeal(Meal meal){
		if(!listOfDiscountMeal.contains(meal) && !listOfMeal.contains(meal)){
			System.out.println("This meal is not in the Restaurant menu");
		} else if(listOfDiscountMeal.contains(meal)){
			return true;
		}
		return false;
	}
	
	public double round2dec (double n){
		return Math.round(n*100.0)/100.0;
	}

	/***************************************************************************************************/
	/*
	 * Getters and Setters: no setters for ID and Counter
	 */
	
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the mealDiscount
	 */
	public double getMealDiscount() {
		return mealDiscount;
	}

	/**
	 * @param mealDiscount the mealDiscount to set
	 */
	public void setMealDiscount(double mealDiscount) {
		this.mealDiscount = mealDiscount;
	}

	/**
	 * @return the discountFactor
	 */
	public double getDiscountFactor() {
		return discountFactor;
	}

	/**
	 * @param discountFactor the discountFactor to set
	 */
	public void setDiscountFactor(double discountFactor) {
		this.discountFactor = discountFactor;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @return the listOfDiscountMeal
	 */
	public ArrayList<Meal> getListOfDiscountMeal() {
		return listOfDiscountMeal;
	}

	/**
	 * @param listOfDiscountMeal the listOfDiscountMeal to set
	 */
	public void setListOfDiscountMeal(ArrayList<Meal> listOfDiscountMeal) {
		this.listOfDiscountMeal = listOfDiscountMeal;
	}

	/**
	 * @return the listOfMeal
	 */
	public ArrayList<Meal> getListOfMeal() {
		return listOfMeal;
	}

	/**
	 * @param listOfMeal the listOfMeal to set
	 */
	public void setListOfMeal(ArrayList<Meal> listOfMeal) {
		this.listOfMeal = listOfMeal;
	}
	
}
