package clui;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.NumberOfArgumentsException;
import users.Restaurant;
import users.User;

/**
* ShowRestaurantTop 1 ""
* @author Juan Castillo
*
*/
public class ShowRestaurantTop implements CommandProcessor{
	final int nArgs = 1;
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Restaurant> rest = new ArrayList<Restaurant>();
	String message = "";
	
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				for(User u : MyFoodora.core.getListOfUsers().values()){
					if(u instanceof Restaurant){
						rest.add((Restaurant)u);
					}
				}
				Collections.sort(rest, Restaurant.compareNumOrdersCompleted());
				Collections.reverse(rest);
				for(Restaurant restaurant : rest){
					message += "\n-" + restaurant.getUsername() + " " + restaurant.getCountOfOrdersCompleted() + "-.";
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
