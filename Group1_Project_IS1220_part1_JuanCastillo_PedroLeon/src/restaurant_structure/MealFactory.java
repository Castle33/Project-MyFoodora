package restaurant_structure;

import java.util.List;

public class MealFactory extends AbstractFactory{

	@Override
	Item getItem(String itemType, String name, double price, String foodType) {
		return null;
	}

	@Override
	Meal getMeal(String mealType, String name, List<Item> mealItems) {
		if(mealType.equalsIgnoreCase("HALFMEAL")){
			return new HalfMeal(name,mealItems);
		}else if(mealType.equalsIgnoreCase("FULLMEAL")){
			return new FullMeal(name,mealItems);
		}
		return null;
	}
	
	

}
