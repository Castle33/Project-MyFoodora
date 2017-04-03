package clui;

import exceptions.*;
import users.Customer;
/**
 * RegisterCustomer 7 <firstName> <lastName> <username> <address> <password> <phoneNumber> <email>
 * @author Pedro León
 *
 */
public class RegisterCustomer implements CommandProcessor{
	final int nArgs = 7;
	private Customer customer;
	
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				customer = new Customer(args[0],args[2],args[1],MyFoodora.stringCast.string2Address(args[3]),args[6],args[5],args[4]);
				MyFoodora.core.registerUser(customer);
				return ("Courier: -" + customer.getUsername() + "- registered.");
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
