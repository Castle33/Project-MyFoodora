package clui;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Meal;
import users.Restaurant;
/**
 * RemoveSpecialOffer 1 "mealName"
 * @author Pedro León
 *
 */
public class RemoveSpecialOffer implements CommandProcessor {
	final int nArgs = 1;
	Meal mealFound;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				mealFound = ((Restaurant) MyFoodora.core.getCurrentUser()).getMealByName(args[0]);
				if(mealFound != null){
					if(((Restaurant) MyFoodora.core.getCurrentUser()).getListOfSpecialMeal().contains(mealFound)){
						MyFoodora.core.removeSpecialMeal(mealFound);
						return "Meal: -" + args[0] + "- removed from " + MyFoodora.core.getCurrentUser().getName() + "'s list of special offer.";
					}else{
						return "Meal: -" + args[0] + "- already removed from " + MyFoodora.core.getCurrentUser().getName() + "'s list of special offer.";
					}
				}else{
					return "Meal: -" + args[0] + "- not found in " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
				}
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
