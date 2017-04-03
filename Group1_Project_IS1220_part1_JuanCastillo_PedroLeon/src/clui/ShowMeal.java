package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import restaurant_structure.Meal;
/**
 * ShowMeal 1 <mealName>
 * @author Pedro León
 *
 */
public class ShowMeal implements CommandProcessor{
	final int nArgs = 1;
	Item item;
	Meal meal;
	boolean mealFound = true;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				for( Meal m : MyFoodora.listTempMeals){
					if(m.getName() == args[1]){
						meal = m;
					} else {
						mealFound = false;
					}
				}
				if(mealFound){
					return "Meal: " + meal.getName() + " has items: " + meal.getMealItems();
				} else {
					return "Error: Meal not found";
				}				
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
