package clui;

import exceptions.*;
import restaurant_structure.Item;
import restaurant_structure.Starter;
import restaurant_structure.MainDish;
import restaurant_structure.Dessert;
import restaurant_structure.ItemType;
/**
 * AddDishRestaurantMenu 4 <dishName> <dishCategory> <foodCategory> <unitPrice>
 * @author Pedro León
 *
 */
public class AddDishRestaurantMenu implements CommandProcessor{
	final int nArgs = 4;
	private Item item;
	String dishCategory; // "Starter"/"MainDish"/"Dessert"
	String message;

	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		try{
			if(args[nArgs] == null){
				if(args[2].toUpperCase() == ItemType.STANDARD.toString() || args[2].toUpperCase() == ItemType.VEGETARIAN.toString() || args[2].toUpperCase() == ItemType.GLUTENFREE.toString()){
					switch(args[1].toUpperCase()){
					case "STARTER":
						item = new Starter(args[0], Integer.parseInt(args[3]), args[1]);
						MyFoodora.core.addItem(item);
						message = "Starter: " + item.getName() + " added to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
						break;
					case "MAINDISH":
						item = new MainDish(args[0], Integer.parseInt(args[3]), args[1]);
						MyFoodora.core.addItem(item);
						message = "MainDish: " + item.getName() + " added to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
						break;
					case "DESSERT":
						item = new Dessert(args[0], Integer.parseInt(args[3]), args[1]);
						MyFoodora.core.addItem(item);
						message = "Dessert: " + item.getName() + " added to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.";
						break;
					}
					return message;
				}else{
					throw new InputMismatchException();
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			System.out.println(e.getMessage());
			return null;
		}catch(AccessDeniedException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	

}
