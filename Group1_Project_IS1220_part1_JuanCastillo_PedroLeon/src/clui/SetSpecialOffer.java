package clui;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import restaurant_structure.Meal;
import users.Restaurant;
/**
 * SetSpecialOffer 1 <mealName>
 * @author Pedro León
 *
 */
public class SetSpecialOffer implements CommandProcessor {
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
				for( Meal m : ((Restaurant)MyFoodora.core.getCurrentUser()).getListOfMeal()){
					if(m.getName() == args[1]){
						MyFoodora.core.setSpecialMeal(m);
						message = "Meal: " + m.getName() + " added to " + MyFoodora.core.getCurrentUser().getName() + "'s list of special offer.";
					}else{
						message = "Meal: " + m.getName() + " not found in " + MyFoodora.core.getCurrentUser().getName() + "'s list of special offer.";
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
