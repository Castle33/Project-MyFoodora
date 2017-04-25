package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

import java.util.List;

public class HalfMeal extends Meal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5126998047406642741L;

	public HalfMeal(String name, List<Item> mealItems) {
		super(name, mealItems);
		if(mealItems.size() > 2){
			System.out.println("Error: HalfMeal must contain only two Items");
		}
	}
}
