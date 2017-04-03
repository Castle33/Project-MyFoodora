package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import system.Order;

/**
 * EndOrder 1 <orderName>
 * @author Pedro León
 *
 */
public class EndOrder implements CommandProcessor{
	final int nArgs = 1;
	private Order order;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				for(Order o : MyFoodora.listTempOrders){
					if(o.getName() == args[0]){
						order = o;
					}
				}
				if(order != null){
					if(!order.getItems().isEmpty() && !order.getMeals().isEmpty()){
						MyFoodora.core.placeNewOrder(order);
						MyFoodora.core.processOrders();
						return "Customer: " + order.getCustomer().getUsername() + " placed Order: " + order.getName() + " to Restaurant: " + order.getRestaurant().getName();
					}else{
						return "Order: " + args[0] + " has nor meals nor items";
					}
				}else{
					return "Order: " + args[0] + " not created in MyFoodora.";
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
