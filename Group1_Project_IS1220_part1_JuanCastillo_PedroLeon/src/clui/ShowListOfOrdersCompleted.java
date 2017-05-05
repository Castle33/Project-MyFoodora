package clui;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
import users.Manager;
import system.Order;

public class ShowListOfOrdersCompleted implements CommandProcessor{
	
	final int nArgs = 1;
	@Override
	public String process(String[] args) {
		
		try {
			if (args.length == nArgs){
				if(MyFoodora.core.getCurrentUser() instanceof Manager){
					String message = "";
					for(Order o : MyFoodora.core.getListOfCompletedOrders()){
						message += "\n" + o.toString();
					}
					return "Orders list: "+ message; 
				}else{
					throw new AccessDeniedException();
				}
			} else {
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(AccessDeniedException e){
			return e.getMessage();
		}
	}

}
