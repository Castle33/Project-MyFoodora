package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;

public class ShowCouriersRegistered implements CommandProcessor{
	
	final int nArgs = 1;
	@Override
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		
		try {
			if (args.length == nArgs){
				return "Courier list: "+ MyFoodora.core.showCouriersRegistered(); 
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
