package clui;

import exceptions.*;
import users.Courier;
/**
 * RegisterCourier 6 <firstName> <lastName> <username> <position> <password> <phoneNumber>
 * @author Pedro León
 *
 */
public class RegisterCourier implements CommandProcessor {
	final int nArgs = 6;
	private Courier courier;
	
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		try{
			if(args[nArgs] == null){
				courier = new Courier(args[0],args[2],args[4],args[1],MyFoodora.stringCast.string2Address(args[4]),args[5]);
				MyFoodora.core.registerUser(courier);
				return ("Courier: -" + courier.getUsername() + "- registered.");
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			System.out.println(e.getMessage());
			return null;
		}catch(UsernameAlreadyRegisteredException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
