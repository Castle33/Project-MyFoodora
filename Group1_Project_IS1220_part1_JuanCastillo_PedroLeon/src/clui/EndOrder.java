package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import system.Order;

/**
 * EndOrder 1 "orderName"
 * @author Pedro León
 *
 */
public class EndOrder implements CommandProcessor{
	final int nArgs = 1;
	private Order orderFound;
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
					if(!orderFound.getItems().isEmpty() || !orderFound.getMeals().isEmpty()){
						MyFoodora.core.placeNewOrder(orderFound);
						MyFoodora.core.processOrders();
						return "Customer: -" + orderFound.getCustomer().getUsername() + "- placed Order: -" + args[0] + "- to Restaurant: -" + orderFound.getRestaurant().getName() + "-.";
					}else{
						return "Order: -" + args[0] + "- has nor meals nor items.";
					}
				}else{
					return "Order: -" + args[0] + "- not created in MyFoodora.\nFor order creation use createOrder <restaurantUsername> <orderName>.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(AccessDeniedException e){
			return e.getMessage();
		}
	}
}
