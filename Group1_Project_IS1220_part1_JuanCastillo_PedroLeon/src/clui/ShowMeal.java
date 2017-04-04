package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Meal;
import users.Restaurant;
/**
 * ShowMeal 1 <mealName>
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
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args.length == 1){
				meal = ((Restaurant) MyFoodora.core.getCurrentUser()).getMealByName(args[0]);
				if(meal != null){
					message = "From " + MyFoodora.core.getCurrentUser().getUsername() + "'s menu, Meal: " + meal + " has items: " + meal.getMealItems();
				} else {
					for(String s : MyFoodora.listTempMeals.keySet()){
						if(s.equals(args[0])){
							message = "From listOfTempMeals, Meal: " + args[0] + " has items: " + MyFoodora.listTempMeals.get(args[0]) + "\nTo add items use AddDish2Meal <dishName> <mealName>.\nTo save meal to "+ MyFoodora.core.getCurrentUser().getUsername() + "'s menu use SaveMeal <mealName>.";
						}
					}
				}
				if(message == null){
					message = "Error: Meal not found";
				}
				return message;
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
