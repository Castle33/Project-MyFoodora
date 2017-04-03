package clui;

import exceptions.*;
import users.Courier;
/**
 * RegisterCourier 6 <firstName> <lastName> <username> <position> <password> <phoneNumber>
 * @author Pedro Le�n
 *
 */
public class RegisterCourier implements CommandProcessor {
	final int nArgs = 6;
	private Courier courier;
	
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				courier = new Courier(args[0],args[2],args[4],args[1],MyFoodora.stringCast.string2Address(args[4]),args[5]);
				MyFoodora.core.registerUser(courier);
				return ("Courier: -" + courier.getUsername() + "- registered.");
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			return e.getMessage();
		}catch(UsernameAlreadyRegisteredException e){
			return e.getMessage();
		}
	}
}
