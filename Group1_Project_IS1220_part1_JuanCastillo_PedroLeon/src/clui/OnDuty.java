package clui;

import exceptions.NumberOfArgumentsException;
import users.Courier;

/**
 * OnDuty <username>
 * @author Pedro León
 *
 */
public class OnDuty implements CommandProcessor{
	final int nArgs = 1;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				if(MyFoodora.core.getCurrentUser() instanceof Courier){
					return "Courier: " + MyFoodora.core.getCurrentUser().getUsername() + " tried to update its state.\nSystem automatocally updates courier's state when accepting an order is finished.";
				}else{
					return "Access Denied: Unauthorized user.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
