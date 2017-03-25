package restaurant_structure;

import java.util.List;
import java.util.ArrayList;

public abstract class Meal {
	
	private String name;
	private List<Item> mealItems = new ArrayList<Item>();
	private double fullPrice;
	private String type;
	
	
	public Meal(String name, List<Item> mealItems){
		this.name = name;
		this.mealItems = mealItems;
		
		Item aux = mealItems.get(0);
		boolean resFoodType = true;
		
		for(Item i : mealItems){
			fullPrice += i.getPrice();
			if(aux.getFoodType()==i.getFoodType()){
				resFoodType = true;
			}else{
				resFoodType = false;
			}
			aux = i;
		}
		
		if(resFoodType == false){
			this.type = "standard";
		}else{
			this.type = aux.getFoodType();
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
