package users;

import java.util.ArrayList;

import restaurant_structure.*;

public class Restaurant extends User{
	
	private Address address;
	private double mealDiscount;
	private double discountFactor;
	private Menu menu;
	private ArrayList<Meal> listOfMeal;
	
	public Restaurant(String name, String username, String password, Address address) {
		super(name, username, password);
		this.address = address;
		this.mealDiscount = 0.05;
		this.discountFactor = 0.1;
		this.menu = new Menu();
		this.listOfMeal = new ArrayList<Meal>();
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
}
