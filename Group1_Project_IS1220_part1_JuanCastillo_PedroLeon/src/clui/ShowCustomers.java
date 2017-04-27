package clui;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
import users.*;

/**
 * ShowCustomers 0 ""
 * @author Juan Castillo
 *
 */

public class ShowCustomers implements CommandProcessor{
	final int nArgs = 1;
	HashMap<String,User> temp_listOfUsers = new HashMap<String,User>();
	Collection<User> users;
	ArrayList<Customer> customers = new ArrayList<Customer>();
	String message = "";
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				if(MyFoodora.core.getCurrentUser() instanceof Manager){
					temp_listOfUsers = MyFoodora.core.getListOfUsers();
					users = temp_listOfUsers.values();
					for(User u : users){
						if(u instanceof Customer){
							message += "\n-" + u.getUsername() + "-.";
						}
					}
					if(message != ""){
						return "List of customers:" + message;
					}else{
						return "No customers registered in the system.";
					}
				}else{
					throw new AccessDeniedException();
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
