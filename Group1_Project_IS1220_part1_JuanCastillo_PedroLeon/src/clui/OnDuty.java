package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import users.Courier;

/**
 * OnDuty "username"
 * @author Pedro León
 *
 */
public class OnDuty implements CommandProcessor{
	final int nArgs = 1;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				if(MyFoodora.core.getCurrentUser() instanceof Courier){
					return "Courier: -" + MyFoodora.core.getCurrentUser().getUsername() + "- tried to update its state.\nSystem automatically sets courier 'on dutty' when accepting an order.";
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
