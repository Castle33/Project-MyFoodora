package clui;

import java.util.HashMap;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
import users.*;

/**
 * ShowMenuItem 1 "restaurantUsername"
 * @author Juan Castillo
 *
 */
public class ShowMenuItem implements CommandProcessor{
	final int nArgs = 1;
	HashMap<String,User> temp_listOfUsers = new HashMap<String,User>();
	
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				if(MyFoodora.core.getCurrentUser() instanceof Manager){
					temp_listOfUsers = MyFoodora.core.getListOfUsers();
					if(temp_listOfUsers.containsKey(args[0])){
						Restaurant rest = (Restaurant)temp_listOfUsers.get(args[0]);
						return "Restaurant: -" + args[0] + "- menu is -" + rest.getMenu().toString() + "-.";
					} else {
						return "Error: the Restaurant: -"+args[0]+ "- does not exist.";
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
