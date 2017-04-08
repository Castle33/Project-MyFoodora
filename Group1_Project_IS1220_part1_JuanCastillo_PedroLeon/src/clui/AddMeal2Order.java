package clui;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Meal;
import system.Order;
/**
 * AddMeal2Order 3 "orderName" "mealName" "mealQuantity"
 * @author Pedro León
 *
 */
public class AddMeal2Order implements CommandProcessor{
	final int nArgs = 3;
	private Order orderFound;
	private Meal mealFound;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				for(Order o : MyFoodora.listTempOrders){
					if(o.getName().equals(args[0])){
						orderFound = o;
					}
				}
				if(orderFound != null){
					mealFound = orderFound.getRestaurant().getMealByName(args[1]);
					if(mealFound != null){
						orderFound.addMeal(mealFound, MyFoodora.stringCast.string2Integer(args[2]));
						return "Customer: -" + MyFoodora.core.getCurrentUser().getUsername() + "- added Meal: -" + args[1] + "- to Order -" + args[0] + "-.";
					}else{
						return "Meal: -" + args[1] + "- not in " + orderFound.getRestaurant().getName() + "'s menu.";
					}
				}else{
					return "Order: -" + args[0] + "- not created in MyFoodora.\nFor order creation use createOrder <restaurantUsername> <orderName>.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(InputMismatchException e){
			return e.getMessage();
		}
	}

}
