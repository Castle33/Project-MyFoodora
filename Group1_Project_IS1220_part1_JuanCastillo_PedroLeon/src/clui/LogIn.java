package clui;

import exceptions.NumberOfArgumentsException;

/**
 * LogIn 2 "username" "password"
 * @author Pedro León
 *
 */
public class LogIn implements CommandProcessor{
	final int nArgs = 2;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == 2){
				if(MyFoodora.core.getListOfUsers().containsKey(args[0])){
					if(MyFoodora.core.getCurrentUser() == null){
						MyFoodora.core.userLogIn(MyFoodora.core.getListOfUsers().get(args[0]));
						return "User: -" + args[0] + "- correctly logged in.";
					}else{
						return "User: -" + MyFoodora.core.getCurrentUser().getUsername() + "- logged in.";
					}
				}else{
					return "User not registered in system.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
