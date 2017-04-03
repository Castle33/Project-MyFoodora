package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import restaurant_structure.Item;
import restaurant_structure.Meal;
/**
 * SaveMeal 1 <mealName>
 * @author Pedro León
 *
 */
public class SaveMeal implements CommandProcessor{
	final int nArgs = 1;
	Item item;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				for( Meal m : MyFoodora.listTempMeals){
					if(m.getName() == args[1]){
						MyFoodora.core.addMeal(m);
						message = "Meal: " + m.getName() + " saved to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
					}else{
						message = "Meal: " + m.getName() + " not found in list of temporal meals. For meal creation use CreateMeal <mealName> <mealCategory>/AddDish2Meal <dishName> <mealName>/SaveMeal <mealName>";
					}
				}
				return message;
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
