package clui;

import exceptions.NumberOfArgumentsException;
import system.Order;
import restaurant_structure.Item;

/**
 * AddItem2Order 2 <orderName> <itemName> <itemQuantity>
 * @author Pedro León
 *
 */
public class AddItem2Order implements CommandProcessor{
	final int nArgs = 3;
	private Order order;
	private Item item;
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
					item = order.getRestaurant().getItemByName(args[1]);
					if( item != null){
						order.addItem(item, Integer.parseInt(args[2]));
						return "Customer: " + MyFoodora.core.getCurrentUser().getUsername() + " added Item: " + item.getName() + " to Order " + order.getName();
					}else{
						return "Item: " + args[1] + " not in " + order.getRestaurant().getName() + "'s menu.";
					}
				}else{
					return "Order: " + args[0] + " not created in MyFoodora.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
