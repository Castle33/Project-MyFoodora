package clui;

import exceptions.NumberOfArgumentsException;
/**
 * LogOut 0 <>
 * @author Pedro León
 *
 */
public class LogOut implements CommandProcessor {
	final int nArgs = 0;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == 1){
				if(MyFoodora.core.getCurrentUser() != null){
					MyFoodora.core.logOut();
					message = "User correctly logged out.";
				}else{
					message = "No user logged in.";
				}
				return message;
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
