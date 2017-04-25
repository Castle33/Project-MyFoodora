package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

import java.util.List;

public class ItemFactory extends AbstractFactory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7638591989772092120L;

	@Override
	public Item getItem(String itemType, String name, double price, String foodType) {
		if(itemType.equalsIgnoreCase("STARTER")){
			return new Starter(name,price,foodType);
		}else if(itemType.equalsIgnoreCase("MAINDISH")){
			return new MainDish(name,price,foodType);
		}else if(itemType.equalsIgnoreCase("DESSERT")){
			return new Dessert(name,price,foodType);
		}
		return null;
	}
	
	public Item getItem(String itemType, String name, double price) {
		if(itemType.equalsIgnoreCase("STARTER")){
			return new Starter(name,price);
		}else if(itemType.equalsIgnoreCase("MAINDISH")){
			return new MainDish(name,price);
		}else if(itemType.equalsIgnoreCase("DESSERT")){
			return new Dessert(name,price);
		}
		return null;
	}

	@Override
	public Meal getMeal(String mealType, String name, List<Item> mealItems) {
		return null;
	}
	

}
