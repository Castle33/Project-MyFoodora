package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro Le�n (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

import java.util.List;

public class MealFactory extends AbstractFactory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8621024039082677065L;

	@Override
	public Item getItem(String itemType, String name, double price, String foodType) {
		return null;
	}

	@Override
	public Meal getMeal(String mealType, String name, List<Item> mealItems) {
		if(mealType.equalsIgnoreCase("HALFMEAL")){
			return new HalfMeal(name,mealItems);
		}else if(mealType.equalsIgnoreCase("FULLMEAL")){
			return new FullMeal(name,mealItems);
		}
		return null;
	}
	
	

}
