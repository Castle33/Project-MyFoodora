package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

import java.util.List;

public class FullMeal extends Meal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 207973577065350819L;

	public FullMeal(String name, List<Item> mealItems) {
		super(name, mealItems);
		if(mealItems.size() > 3){
			System.out.println("Error: FullMeal must contain only three Items");
		}
	}
}
