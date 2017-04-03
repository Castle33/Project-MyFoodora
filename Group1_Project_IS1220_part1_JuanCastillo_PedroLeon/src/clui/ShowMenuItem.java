package clui;

import java.util.HashMap;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import users.*;

/**
 * ShowMenuItem 1 <restaurantName>
 * @author Juan Castillo
 *
 */
public class ShowMenuItem implements CommandProcessor{
	final int nArgs = 1;
	HashMap<String,User> temp_listOfUsers = new HashMap<String,User>();
	
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		try{
			if(args[nArgs] == null){
				temp_listOfUsers = MyFoodora.core.getListOfUsers();
				if(temp_listOfUsers.containsKey(args[0])){
					Restaurant rest = (Restaurant)temp_listOfUsers.get(args[0]);
					return "The restaurant menu is " + rest.getMenu().toString();
				} else {
					return "Error: the restaurant with name "+args[0]+ " does not exist";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
