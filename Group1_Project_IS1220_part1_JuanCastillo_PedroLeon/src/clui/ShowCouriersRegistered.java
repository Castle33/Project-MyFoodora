package clui;

import exceptions.AccessDeniedException;
import exceptions.NumberOfArgumentsException;
/**
 * ShowCouriersRegistered ""
 * Command to show the list of couriers registered, no input argument required
 * @author Pedro León
 *
 */
public class ShowCouriersRegistered implements CommandProcessor{
	
	final int nArgs = 1;
	@Override
	public String process(String[] args) {
		
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
