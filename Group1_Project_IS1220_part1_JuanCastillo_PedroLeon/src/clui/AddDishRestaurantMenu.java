package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import restaurant_structure.ItemFactory;
import users.Restaurant;
/**
 * AddDishRestaurantMenu 4 "dishName" "dishCategory" "foodCategory" "unitPrice"
 * @author Pedro León
 *
 */
public class AddDishRestaurantMenu implements CommandProcessor{
	final int nArgs = 4;
	String dishCategory; // "Starter"/"MainDish"/"Dessert"
	String message;
	ItemFactory itemFactory = new ItemFactory();

	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				if(((Restaurant) MyFoodora.core.getCurrentUser()).getItemByName(args[0]) == null){
					
					MyFoodora.core.addItem(itemFactory.getItem(MyFoodora.stringCast.string2Dish(args[1]), args[0], MyFoodora.stringCast.string2Double(args[3]), MyFoodora.stringCast.string2DishType(args[2])));
					return "Item: -" + args[0] + "- added to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
				}else{
					return "Item: -" + args[0] + "- already added to menu.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(AccessDeniedException e){
			return e.getMessage();
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(InputMismatchException e){
			return e.getMessage();
		}
	}
	

}
