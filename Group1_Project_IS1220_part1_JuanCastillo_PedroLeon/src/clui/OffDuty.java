package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
/**
 * OffDuty 1 <username>
 * @author Pedro León
 *
 */
public class OffDuty implements CommandProcessor{
	final int nArgs = 1;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				MyFoodora.core.updateCourierState();
				return "Courier: " + MyFoodora.core.getCurrentUser().getUsername() + " updated its state to: OffDuty.";
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
