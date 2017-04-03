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
						return "Meal: " + m.getName() + " saved to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
					}
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
		return null;
	}
}
