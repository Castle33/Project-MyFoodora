package restaurant_structure;

import java.util.List;
import java.util.ArrayList;

public abstract class Meal {
	
	private String name;
	private List<Item> mealItems;
	private double fullPrice;
	private String type;
	
	public Meal (String name){
		this.name = name;
		this.mealItems = new ArrayList<Item>();
	}
	
	public Meal(String name, List<Item> mealItems){
		this.name = name;
		this.mealItems = mealItems;
		
		/*
		 * for getting the meal type we check by pairs of items if they have the same type
		 * to check by pairs we need an auxiliary variable Item to take the values of the precedent item in the list
		 */
		
		Item auxItem = mealItems.get(0);
		boolean resFoodType = true;
		
		for(Item i : mealItems){
			fullPrice += i.getPrice();
			if(auxItem.getFoodType()==i.getFoodType()){
				resFoodType = true;
			}else{
				resFoodType = false;
			}
			auxItem = i;
		}
		
		// finally if the result is false then the food type is standard, for a true result we take the same as dish type
		if(resFoodType == false){
			this.type = "standard";
		}else{
			this.type = auxItem.getFoodType();
		}
		
	}


	@Override
	public String toString() {
		String items = "";
		for(Item i : mealItems){
			items += i.toString();
		}
		return "Meal [name=" + name + ", mealItems" + items + ", fullPrice=" + fullPrice + ", type=" + type + "]";
	}
	
	/***************************************************************************************************/
	/*
	 * Getter and setters
	 */

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Item> getMealItems() {
		return mealItems;
	}


	public void setMealItems(List<Item> mealItems) {
		this.mealItems = mealItems;
	}


	public double getFullPrice() {
		return fullPrice;
	}


	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	

}
