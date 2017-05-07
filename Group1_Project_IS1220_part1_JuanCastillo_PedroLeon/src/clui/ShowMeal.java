package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Meal;
import users.Restaurant;
/**
 * ShowMeal 1 "mealName"
 * @author Pedro León
 *
 */
public class ShowMeal implements CommandProcessor{
	final int nArgs = 1;
	Meal meal;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == 1){
				meal = ((Restaurant) MyFoodora.core.getCurrentUser()).getMealByName(args[0]);
				if(meal != null){
					return "From " + MyFoodora.core.getCurrentUser().getName() + "'s menu, Meal: -" + args[0] + "- has items: " + meal.getMealItems();
				} else {
					if(MyFoodora.listTempMeals.keySet().contains(args[0])){
						return "From listOfTempMeals, Meal: -" + args[0] + "- has items: " + MyFoodora.listTempMeals.get(args[0]) + "\nTo add items use AddDish2Meal <dishName> <mealName>.\nTo save meal to "+ MyFoodora.core.getCurrentUser().getName() + "'s menu use SaveMeal <mealName>.";
					} else {
						return "Error: Meal not found";
					}
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
