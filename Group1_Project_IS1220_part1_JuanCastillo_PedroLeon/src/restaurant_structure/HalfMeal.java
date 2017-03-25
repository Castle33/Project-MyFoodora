package restaurant_structure;

import java.util.ArrayList;
import java.util.List;

public class HalfMeal extends Meal {
	
	public HalfMeal(String name, List<Item> mealItems){
		super(name, mealItems);
	}
	
	public static void main (String[] args){

		Starter s = new Starter("s", 2);
		MainDish md = new MainDish("md", 5);
		
		List<Item> items = new ArrayList<Item>();
		
		items.add(s);
		items.add(md);
		
		Meal m = new HalfMeal("mediterraneo", items);
		
		System.out.println(m.getType());
		
	}

}
