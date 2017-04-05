package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import users.Restaurant;
import java.util.ArrayList;
/**
 * AddDish2Meal 2 "dishName" "mealName"
 * @author Pedro León
 *
 */
public class AddDish2Meal implements CommandProcessor {
	final int nArgs = 2;
	Item itemFound;
	ArrayList<Item> itemList;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				itemFound = ((Restaurant) MyFoodora.core.getCurrentUser()).getItemByName(args[0]);
				if(itemFound != null){
					if(MyFoodora.listTempMeals.keySet().contains(args[1])){
						itemList = MyFoodora.listTempMeals.get(args[1]);
						itemList.add(itemFound);
						MyFoodora.listTempMeals.remove(args[1]);
						MyFoodora.listTempMeals.put(args[1],itemList);
						return "Item: -" + args[0] + "- added to Meal: -" + args[1]+ "-.";
					}else{
						return "Meal: -" + args[1] + "- not in temporary meal list. To add it use CreateMeal <mealName> <mealCategory>.";
					}
				}else{
					return "Item: -" + args[0] + "- not in " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
