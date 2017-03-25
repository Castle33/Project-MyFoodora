package users;

import java.util.ArrayList;

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
		this.Menu = new Menu();
		this.listOfMeal = new ArrayList<Meal>();
	}

	@Override
	public String toString() {
		return "Restaurant [name=" + getName() + ", username=" + getUsername() + ", ID=" + getID() + "]";
	}
	
	
}
