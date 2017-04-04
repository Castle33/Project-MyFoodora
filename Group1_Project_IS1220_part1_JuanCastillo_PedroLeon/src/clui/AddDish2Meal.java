package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import users.Restaurant;
import java.util.ArrayList;
/**
 * AddDish2Meal 2 <dishName> <mealName>
 * @author Pedro León
 *
 */
public class AddDish2Meal implements CommandProcessor {
	final int nArgs = 2;
	String mealFound;
	Item itemFound;
	ArrayList<Item> itemAdded;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				itemFound = ((Restaurant) MyFoodora.core.getCurrentUser()).getItemByName(args[0]);
				if(itemFound != null){
					for( String m : MyFoodora.listTempMeals.keySet()){
						if(m.equals(args[1])){
							mealFound = m;
							itemAdded = MyFoodora.listTempMeals.get(mealFound);
							itemAdded.add(itemFound);
							MyFoodora.listTempMeals.put(mealFound,itemAdded);
						}
					}
					if(mealFound != null){
						return "Item: " + args[0] + " added to Meal: " + args[1];
					}else{
						return "Meal: " + args[1] + " not in temporary meal list. To add it use CreateMeal <mealName> <mealCategory>";
					}
				}else{
					return "Item: " + args[0] + " not in " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
