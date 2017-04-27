package clui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import exceptions.NumberOfArgumentsException;
import users.Restaurant;
import users.User;

/**
* ShowRestaurantTop 0 ""
* @author Juan Castillo
*
*/
public class ShowRestaurantTop implements CommandProcessor{
	final int nArgs = 1;
	HashMap<String,User> temp_listOfUsers = new HashMap<String,User>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Restaurant> rest = new ArrayList<Restaurant>();
	String message;
	
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				temp_listOfUsers = MyFoodora.core.getListOfUsers();
				for(User u : temp_listOfUsers.values()){
					users.add(u);
				}
				for(User u : users){
					if(!(u instanceof Restaurant)){
						users.remove(u);
					} else {
						rest.add((Restaurant)u);
					}
				}
				Collections.sort(rest, Restaurant.compareNumOrdersCompleted());
				Collections.reverse(rest);
				for(Restaurant restaurant : rest){
					message += "\n-" + restaurant.getUsername() + "-.";
				}
				return "The restaurants sorted by number of completed orders in decreasing order are:" + message;
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
