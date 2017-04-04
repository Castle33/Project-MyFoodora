package clui;

import java.util.ArrayList;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import users.Restaurant;

/**
 * RemoveItem 2 <dishName> <mealName>
 * @author Pedro León
 *
 */
public class RemoveItemFromMeal implements CommandProcessor {
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
				for( String m : MyFoodora.listTempMeals.keySet()){
					if(m.equals(args[1])){
						mealFound = m;
						for(Item item : MyFoodora.listTempMeals.get(mealFound)){
							if(item.getName() == args[0]){
								itemFound = item;
							}
						}
						if(itemFound != null){
							itemAdded = MyFoodora.listTempMeals.get(mealFound);
							itemAdded.remove(itemFound);
							MyFoodora.listTempMeals.put(mealFound,itemAdded);
						}
					}
				}
				if(mealFound != null){
					return "Item: " + args[0] + " removed from Meal: " + args[1];
				}else if(itemFound == null){
					return "Item: " + args[0] + " not in added to " + args[1] + ".\nTo add items use AddDish2Meal <dishName> <mealName>.";
				}else{
					return "Meal: " + args[1] + " not in temporary meal list. To add it use CreateMeal <mealName> <mealCategory>";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
