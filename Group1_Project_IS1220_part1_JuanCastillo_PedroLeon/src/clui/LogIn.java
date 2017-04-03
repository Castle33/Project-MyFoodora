package clui;

import exceptions.NumberOfArgumentsException;
import users.User;

/**
 * LogIn 2 <username> <password>
 * @author Pedro Le�n
 *
 */
public class LogIn implements CommandProcessor{
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
						MyFoodora.core.userLogIn(user);
						if(user.equals(MyFoodora.core.getCurrentUser())){
							message = "User " + args[0] + " already logged in.";
						}else{
							message = "User " + args[0] + " correctly logged in.";
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
