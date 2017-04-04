package clui;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Meal;
import users.Restaurant;
/**
 * RemoveSpecialOffer 1 <mealName>
 * @author Pedro León
 *
 */
public class RemoveSpecialOffer implements CommandProcessor {
	final int nArgs = 1;
	Meal meal;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				meal = ((Restaurant) MyFoodora.core.getCurrentUser()).getMealByName(args[0]);
				if(meal!=null){
					MyFoodora.core.removeSpecialMeal(meal);
					message = "Meal: " + meal.getName() + " removed from " + MyFoodora.core.getCurrentUser().getName() + "'s list of special offer.";
				}else{
					message = "Meal: " + meal.getName() + " not found in " + MyFoodora.core.getCurrentUser().getName() + "'s list of special offer.";
				}
				return message;
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(AccessDeniedException e){
			return e.getMessage();
		}
	}
}
