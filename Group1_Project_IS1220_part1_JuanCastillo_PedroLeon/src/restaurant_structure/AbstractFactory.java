package restaurant_structure;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractFactory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3609860497105035476L;
	abstract Item getItem(String itemType, String name, double price, String foodType);
	abstract Meal getMeal(String mealType, String name, List<Item> mealItems);

}
