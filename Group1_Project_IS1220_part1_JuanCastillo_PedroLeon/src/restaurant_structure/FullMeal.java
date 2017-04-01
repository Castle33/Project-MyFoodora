package restaurant_structure;

import java.util.List;

public class FullMeal extends Meal {
	
	public FullMeal(String name) {
		super(name);
	}
	
	public FullMeal(String name, List<Item> mealItems) throws Exception{
		super(name, mealItems);
		if(mealItems.size() != 2){
			System.out.println("Error: FullMeal must contain only three Items");
			throw new Exception();
			
		}
	}

	@Override
	public void setMealItems(List<Item> mealItems) throws Exception {
		if(mealItems.size() != 3){
			System.out.println("Error: HalfMeal must contain only two Items");
			throw new Exception();
		}
		super.setMealItems(mealItems);
	}
	
	

}
