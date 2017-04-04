package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import restaurant_structure.Meal;
import users.Restaurant;
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
			if(args.length == 1){
				meal = ((Restaurant) MyFoodora.core.getCurrentUser()).getMealByName(args[0]);
				if(meal != null){
					return "Meal: " + meal + " has items: " + meal.getMealItems();
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
