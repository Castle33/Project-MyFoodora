package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;

public class ShowRestaurantsRegistered implements CommandProcessor{
	
	final int nArgs = 1;
	@Override
	public String process(String[] args) {
		
		try {
			if (args.length == nArgs){
				return "Restaurant list: "+ MyFoodora.core.showRestaurantsRegistered(); 
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
