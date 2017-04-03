package clui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import exceptions.NumberOfArgumentsException;
import users.Restaurant;
import users.User;

/**
* ShowRestaurantTop 0 <>
* @author Juan Castillo
*
*/
public class ShowRestaurantTop implements CommandProcessor{
	final int nArgs = 0;
	HashMap<String,User> temp_listOfUsers = new HashMap<String,User>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Restaurant> rest = new ArrayList<Restaurant>();
	
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				temp_listOfUsers = MyFoodora.core.getListOfUsers();
				users = (ArrayList<User>) temp_listOfUsers.values();
				for(User u : users){
					if(!(u instanceof Restaurant)){
						users.remove(u);
					} else {
						rest.add((Restaurant)u);
					}
				}
				Collections.sort(rest, Restaurant.compareNumOrdersCompleted());
				Collections.reverse(rest);
				return "The restaurants sorted by number of completed orders in decreasing order are:\n" + rest.toString();
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
