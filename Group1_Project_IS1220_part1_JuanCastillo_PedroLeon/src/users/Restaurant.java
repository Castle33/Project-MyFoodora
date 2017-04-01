package users;

import java.util.ArrayList;

import restaurant_structure.*;
/**
 * 
 * @author Juan Castillo (programmer)
 * @author Pedro León (tester)
 *
 */
public class Restaurant extends User{
	
	private Address address;
	private double discountFactor;
	private double specialDiscountFactor;
	private Menu menu;
	private ArrayList<Meal> listOfSpecialMeal;
	private ArrayList<Meal> listOfMeal;
	private int countOfOrdersCompleted;
	/**
	 * tested: YES
	 * @param name
	 * @param username
	 * @param password
	 * @param address
	 */
	public Restaurant(String name, String username, String password, Address address) {
		super(name, username, password);
		this.address = address;
		this.discountFactor = 0.05;
		this.specialDiscountFactor = 0.1;
		this.menu = new Menu();
		this.listOfMeal = new ArrayList<Meal>();
		this.listOfSpecialMeal = new ArrayList<Meal>();
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + getName() + ", username=" + getUsername() + ", ID=" + getID() + "]";
	}
	
	/***************************************************************************************************/
	/*
	 * Add and remove methods
	 */
	/**
	 * tested: YES
	 * @param starter
	 */
	public void addStarter (Starter starter){
		menu.addStarter(starter);
	}
	/**
	 * tested: YES
	 * @param starter
	 */
	public void removeStarter(Starter starter){
		menu.removeStarter(starter);
	}
	/**
	 * tested: YES
	 * @param mainDish
	 */
	public void addMainDish (MainDish mainDish){
		menu.addMainDish(mainDish);
	}
	/**
	 * tested: YES
	 * @param mainDish
	 */
	public void removeMainDish(MainDish mainDish){
		menu.removeMainDish(mainDish);
	}
	/**
	 * tested: YES
	 * @param dessert
	 */
	public void addDessert (Dessert dessert){
		menu.addDessert(dessert);
	}
	/**
	 * tested: YES
	 * @param dessert
	 */
	public void removeDessert(Dessert dessert){
		menu.removeDessert(dessert);
	}
	
	public void addMeal(Meal meal) {
		listOfMeal.add(meal);
	}
	public void removeMeal(Meal meal) {
		listOfMeal.remove(meal);
	}
	
	public void addSpecialMeal(Meal meal) {
		listOfSpecialMeal.add(meal);
	}
	public void removeSpecialMeal(Meal meal) {
		listOfSpecialMeal.remove(meal);
	}
	
	/***************************************************************************************************/
	/*
	 * Various methods: Get Price Meal, Get Meal/Item by name, Determine if Menu is Special, Round Value to 2 decimals
	 */
	
	public double getPriceMeal (Meal meal){
		if(!listOfSpecialMeal.contains(meal) && !listOfMeal.contains(meal)){
			System.out.println("This meal is not in the Restaurant menu");
			throw new NullPointerException();
		} else if(determineIfDiscountMeal(meal)){
			return round2dec(meal.getFullPrice()*(1-getDiscountFactor()));
		}
		return round2dec(meal.getFullPrice()*(1-getSpecialDiscountFactor()));
	}
	
	public Meal getMealByName(String mealName){
		for(Meal m :listOfMeal){
			if(m.getName().equalsIgnoreCase(mealName)){
				return m;
			}
		}
		return null;
	}
	/**
	 * tested: YES
	 * @param itemName
	 * @return
	 */
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
		if(!listOfSpecialMeal.contains(meal) && !listOfMeal.contains(meal)){
			System.out.println("This meal is not in the Restaurant menu");
		} else if(listOfSpecialMeal.contains(meal)){
			return true;
		}
		return false;
	}
	/**
	 * tested: YES
	 * @param n
	 * @return
	 */
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
	 * @return the discountFactor
	 */
	public double getDiscountFactor() {
		return discountFactor;
	}

	/**
	 * @param discountFactor the discountFactor to set
	 */
	public void setDiscountFactor(double mealDiscount) {
		this.discountFactor = mealDiscount;
	}

	/**
	 * @return the specialDiscountFactor
	 */
	public double getSpecialDiscountFactor() {
		return specialDiscountFactor;
	}

	/**
	 * @param specialDiscountFactor the specialDiscountFactor to set
	 */
	public void setSpecialDiscountFactor(double discountFactor) {
		this.specialDiscountFactor = discountFactor;
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
		return listOfSpecialMeal;
	}

	/**
	 * @param listOfDiscountMeal the listOfDiscountMeal to set
	 */
	public void setListOfDiscountMeal(ArrayList<Meal> listOfDiscountMeal) {
		this.listOfSpecialMeal = listOfDiscountMeal;
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

	/**
	 * @return the countOfOrdersCompleted
	 */
	public int getCountOfOrdersCompleted() {
		return countOfOrdersCompleted;
	}

	/**
	 * @param countOfOrdersCompleted the countOfOrdersCompleted to set
	 */
	public void setCountOfOrdersCompleted(int countOfOrdersCompleted) {
		this.countOfOrdersCompleted = countOfOrdersCompleted;
	}
	
	
	
}
