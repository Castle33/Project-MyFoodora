package restaurant_structure;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 * changed: Constructor Meal(meal, mealItems) , setMealItems(mealItems)
 * 		changes Constructor: added alerts when size of mealItems doesn't match type of menu
 * 		changes setMealItems: now updates correctly fullPrice and type. Added also same change as in Constructor
 */

import java.util.List;
import java.util.ArrayList;

public abstract class Meal {
	
	private String name;
	private List<Item> mealItems;
	private double fullPrice;
	private String type;
	
	public Meal(String name, List<Item> mealItems){
		this.name = name;
		this.mealItems = mealItems;
		
		if(this instanceof HalfMeal){
			if(mealItems.size() != 2){
				System.out.println("Error: HalfMeal must contain only two Items");
			}
		} else if (this instanceof FullMeal){
			if(mealItems.size() != 3){
				System.out.println("Error: FullMeal must contain only two Items");
			}
		}
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


	public void setMealItems(List<Item> mealItems){
		this.mealItems = mealItems;
		if(this instanceof HalfMeal){
			if(mealItems.size() != 2){
				System.out.println("Error: HalfMeal must contain only two Items");
			}
		} else if (this instanceof FullMeal){
			if(mealItems.size() != 3){
				System.out.println("Error: FullMeal must contain only two Items");
			}
		}
		/*
		 * for getting the meal type we check by pairs of items if they have the same type
		 * to check by pairs we need an auxiliary variable Item to take the values of the precedent item in the list
		 */
		
		Item auxItem = mealItems.get(0);
		boolean resFoodType = true;
		fullPrice = 0.0;
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
