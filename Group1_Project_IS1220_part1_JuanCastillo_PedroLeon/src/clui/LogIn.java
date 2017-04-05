package clui;

import exceptions.NumberOfArgumentsException;

/**
 * LogIn 2 <username> <password>
 * @author Pedro León
 *
 */
public class LogIn implements CommandProcessor{
	final int nArgs = 2;
	String message = null;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == 2){
				if(MyFoodora.core.getCurrentUser() == null){
					if(MyFoodora.core.getListOfUsers().containsKey(args[0])){
						MyFoodora.core.userLogIn(MyFoodora.core.getListOfUsers().get(args[0]));
						message = "User -" + args[0] + "- correctly logged in.";
					}
				}else{
					message = "User " + args[0] + " already logged in.";
				}
				if(message == null){
					message = "User not registered in system.";
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
