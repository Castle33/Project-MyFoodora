package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import users.Restaurant;
import users.Customer;
import system.Order;

/**
 * CreateOrder "restaurantUsername" "orderName"
 * @author Pedro León
 *
 */
public class CreateOrder implements CommandProcessor{
	final int nArgs = 2;
	private Restaurant restaurant;
	private Order order;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				restaurant = (Restaurant) MyFoodora.core.getListOfUsers().get(args[0]);
				//System.out.println(MyFoodora.core.getListOfUsers());
				if(restaurant != null){
					if(MyFoodora.core.getCurrentUser() instanceof Customer){
						order = new Order((Customer) MyFoodora.core.getCurrentUser(), restaurant);
						order.setName(args[1]);
						MyFoodora.listTempOrders.add(order);
						return "Customer: -" + MyFoodora.core.getCurrentUser().getUsername() + "- created Order: -" + order.getName() + "-.";
					}else{
						throw new AccessDeniedException();
					}
				}else{
					return "Restaurant: -" + args[0] + "- not registered in MyFoodora.";
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
