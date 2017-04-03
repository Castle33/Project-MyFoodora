package clui;

import users.Restaurant;
import exceptions.*;
/**
 * RgisterRestaurant 4 <name> <address> <username> <password>
 * @author Pedro León
 *
 */
public class RegisterRestaurant implements CommandProcessor{
	final int nArgs = 4;
	private Restaurant restaurant;
	
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException{
		try{
			if(args[nArgs] == null){
				restaurant = new Restaurant(args[0],args[2],args[3],MyFoodora.stringCast.string2Address(args[1]));
				MyFoodora.core.registerUser(restaurant);
				return ("Restaurant: -" + restaurant.getUsername() + "- registered.");
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			return e.getMessage();
		}catch(UsernameAlreadyRegisteredException e){
			return e.getMessage();
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
	
}
