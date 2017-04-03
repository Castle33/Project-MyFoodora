package clui;

import exceptions.NumberOfArgumentsException;
import users.User;
/**
 * LogOut 0 <>
 * @author Pedro León
 *
 */
public class LogOut implements CommandProcessor {
	final int nArgs = 2;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				for(User user : MyFoodora.core.getListOfUsers().values()){
					if(user.getUsername().equals(args[0])){
						if(user.equals(MyFoodora.core.getCurrentUser())){
							MyFoodora.core.logOut();
							message = "User " + args[0] + " correctly logged out.";
						}else{
							message = "User " + args[0] + " not logged in.";
						}
					}else{
						message = "User " + args[0] + " not registered in system.";
					}
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
