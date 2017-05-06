package restaurant_structure;
import java.util.List;

public abstract class AbstractFactory {
	/**
	 * 
	 */
	abstract Item getItem(String itemType, String name, double price, String foodType);
	abstract Meal getMeal(String mealType, String name, List<Item> mealItems);

}
