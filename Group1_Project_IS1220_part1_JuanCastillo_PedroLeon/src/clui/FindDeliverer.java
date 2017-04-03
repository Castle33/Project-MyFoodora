package clui;

import exceptions.NumberOfArgumentsException;
import java.util.LinkedList;
import java.util.Collection;
import system.Order;
import users.User;
import users.Courier;

/**
 * FindDeliverer 1 <OrderName>
 * @author Pedro León
 *
 */
public class FindDeliverer implements CommandProcessor {
	final int nArgs = 1;
	LinkedList<Order> tempPendingOrders = new LinkedList<Order> ();
	Order order;
	EndOrder endOrder;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				tempPendingOrders = MyFoodora.core.getListOfPendingOrders();
				for(Order o : tempPendingOrders){
					if(o.getName() == args[0]){
						order = o;
						tempPendingOrders.remove(order);
						MyFoodora.core.setListOfPendingOrders(new LinkedList<Order>());
						String[] array = new String[1];
						array[0] = order.getName();
						endOrder.process(array);
						Collection<User> users = MyFoodora.core.getListOfUsers().values();
						for(User u : users){
							if(u instanceof Courier){
								if(((Courier)u).getCurrentOrder().equals(order)){
									return "Order: " + order.getName() + " is being delivered by Courier: " + u.getUsername();
								}else{
									return "Order: " + order.getName() + " not in the system.";
								}
							}
						}
						MyFoodora.core.setListOfPendingOrders(tempPendingOrders);
					}
				}if(order == null){
					return "Order: " + args[0] + " not created. For creating orders use CreateOrder <restaurantName>/AddItem2Order <orderName> <itemName> <itemQuantity>/EndOrder <orderName> commands.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
}
