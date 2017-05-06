package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import restaurant_structure.MealFactory;
/**
 * SaveMeal 1 "mealName"
 * @author Pedro León
 *
 */
public class SaveMeal implements CommandProcessor{
	final int nArgs = 1;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == 1){
				if(MyFoodora.listTempMeals.keySet().contains(args[0])){
					if(MyFoodora.listTempMeals.get(args[0]).size() == 2){
						MyFoodora.core.addMeal(((MealFactory)MyFoodora.getMealFactory()).getMeal("HALFMEAL", args[0], MyFoodora.listTempMeals.get(args[0])));
						MyFoodora.listTempMeals.remove(args[0]);
						return "HalfMeal: " + args[0] + " saved to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
					}else if(MyFoodora.listTempMeals.get(args[0]).size() == 3){
						MyFoodora.core.addMeal(((MealFactory)MyFoodora.getMealFactory()).getMeal("FULLMEAL", args[0], MyFoodora.listTempMeals.get(args[0])));
						MyFoodora.listTempMeals.remove(args[0]);
						return "FullMeal: -" + args[0]+ "- saved to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
					}else{
						return "Meal must have 2 or 3 items not more.";
					}
				}else{
					return "Meal: -" + args[0] + "- not found in list of temporal meals.\nFor meal creation use CreateMeal <mealName> <mealCategory>/AddDish2Meal <dishName> <mealName>/SaveMeal <mealName>\n";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}catch(AccessDeniedException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
