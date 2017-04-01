package restaurant_structure;

import java.util.List;

public class HalfMeal extends Meal {
	
	public HalfMeal(String name) {
		super(name);
	}

	public HalfMeal(String name, List<Item> mealItems) throws Exception{
		super(name, mealItems);
		if(mealItems.size() != 2){
			System.out.println("Error: HalfMeal must contain only two Items");
			throw new Exception();
			
		}
	}

	@Override
	public void setMealItems(List<Item> mealItems) throws Exception {
		if(mealItems.size() != 2){
			System.out.println("Error: HalfMeal must contain only two Items");
			throw new Exception();
		}
		super.setMealItems(mealItems);
	}
	
	

}
