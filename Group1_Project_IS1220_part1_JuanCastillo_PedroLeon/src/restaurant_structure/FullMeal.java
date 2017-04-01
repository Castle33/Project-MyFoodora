package restaurant_structure;

import java.util.List;

public class FullMeal extends Meal {
	
	public FullMeal(String name, List<Item> mealItems) {
		super(name, mealItems);
		if(mealItems.size() != 3){
			System.out.println("Error: FullMeal must contain only three Items");
		}
	}
}
