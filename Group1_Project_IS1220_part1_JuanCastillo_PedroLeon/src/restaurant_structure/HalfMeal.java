package restaurant_structure;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

import java.util.List;

public class HalfMeal extends Meal {

	public HalfMeal(String name, List<Item> mealItems) {
		super(name, mealItems);
		if(mealItems.size() > 2){
			System.out.println("Error: HalfMeal must contain only two Items");
		}
	}
}
