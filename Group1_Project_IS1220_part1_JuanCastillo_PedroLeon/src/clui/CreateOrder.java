package clui;

import exceptions.NumberOfArgumentsException;
import users.Restaurant;
import users.Customer;
import system.Order;

/**
 * CreateOrder <restaurantName>
 * @author Pedro León
 *
 */
public class CreateOrder implements CommandProcessor{
	final int nArgs = 1;
	private Restaurant restaurant;
	private Order order;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				restaurant = (Restaurant) MyFoodora.core.getListOfUsers().get(args[0]);
				if(restaurant != null){
					order = new Order((Customer) MyFoodora.core.getCurrentUser(), restaurant);
					order.setName();
					MyFoodora.listTempOrders.add(order);
					return "Customer: " + MyFoodora.core.getCurrentUser().getUsername() + " created Order: " + order.getName();
				}else{
					return "Restaurant: " + args[0] + " not registered in MyFoodora.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
