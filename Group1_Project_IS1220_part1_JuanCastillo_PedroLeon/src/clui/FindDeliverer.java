package clui;

import exceptions.NumberOfArgumentsException;
import java.util.Collection;
import system.Order;
import users.User;
import users.Courier;

/**
 * FindDeliverer 1 "OrderName"
 * @author Pedro León
 *
 */
public class FindDeliverer implements CommandProcessor {
	final int nArgs = 1;
	Order order = null;
	Courier courier = null;
	EndOrder endOrder = new EndOrder();
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				Collection<User> users = MyFoodora.core.getListOfUsers().values();
				// we could implement in core a functionality to findOrderByName
				for(User u : users){
					if(u instanceof Courier){
						for(Order o : ((Courier)u).getListPendingOrders()){
							if(o.getName().equals(args[0])){
								courier = (Courier) u;
							}
						}
						if(courier == null && ((Courier)u).getCurrentOrder().getName().equals(args[0])){
							courier = (Courier) u;
						}
					}
				}
				if(courier != null){
					return "Order: -" + args[0] + "- is being delivered by Courier: -" + courier.getUsername() + "-.";
				}else{
					return "Order: -" + order.getName() + "- not assigned to any courier.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
