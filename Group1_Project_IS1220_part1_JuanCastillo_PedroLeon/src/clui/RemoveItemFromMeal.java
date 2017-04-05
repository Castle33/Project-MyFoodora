package clui;

import java.util.ArrayList;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;

/**
 * RemoveItem 2 "dishName" "mealName"
 * @author Pedro León
 *
 */
public class RemoveItemFromMeal implements CommandProcessor {
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
				if(MyFoodora.listTempMeals.keySet().contains(args[1])){
					itemFound = MyFoodora.getItemByName(args[0], args[1]);
					if(itemFound != null){
						itemList = MyFoodora.listTempMeals.get(args[1]);
						itemList.remove(itemFound);
						MyFoodora.listTempMeals.remove(args[1]);
						MyFoodora.listTempMeals.put(args[1],itemList);
						return "Item: -" + args[0] + "- removed from Meal: -" + args[1] + "-.";
					}else{
						return "Item: -" + args[0] + "- not added in Meal: -" + args[1] + "-.\nTo add items use AddDish2Meal <dishName> <mealName>.";
					}
				}else{
					return "Meal: -" + args[1] + "- not in temporary meal list.\nTo add it use CreateMeal <mealName> <mealCategory>.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
