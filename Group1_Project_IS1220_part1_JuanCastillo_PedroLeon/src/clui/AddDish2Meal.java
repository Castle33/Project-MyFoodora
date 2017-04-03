package clui;

import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import restaurant_structure.Meal;
import users.Restaurant;
import java.util.ArrayList;
import java.util.List;
/**
 * AddDish2Meal 2 <dishName> <mealName>
 * @author Pedro León
 *
 */
public class AddDish2Meal implements CommandProcessor {
	final int nArgs = 2;
	Item item;
	public int index = 0;
	List<Item> itemAdded = new ArrayList<Item>();
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				if(((Restaurant) MyFoodora.core.getCurrentUser()).getItemByName(args[0]) != null){
					for( Meal m : MyFoodora.listTempMeals){
						if(m.getName() == args[1]){
							itemAdded = m.getMealItems();
							itemAdded.add(item);
							m.setMealItems(itemAdded);
							MyFoodora.listTempMeals.set(index, m);
						}
						index++;
					}
					if(itemAdded.isEmpty()){
						return "Meal: " + args[1] + " not in temporal list of meals. To add this meal use CreateMeal <mealName> <mealCategory>.";
					}
					return "Item: " + args[0] + " has been added to Meal: " + args[1] +". ShowMeal <mealName> to check temporal meal state.";
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
