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
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				for( Meal m : MyFoodora.listTempMeals){
					if(m.getName() == args[1]){
						return "Meal: " + m.getName() + " has items: " + m.getMealItems();
					}
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			e.getMessage();
			return null;
		}
		return null;
	}
}
