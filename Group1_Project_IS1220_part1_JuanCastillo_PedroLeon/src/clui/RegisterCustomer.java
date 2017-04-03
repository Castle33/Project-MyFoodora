package clui;

import java.util.Arrays;

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
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				customer = new Customer(args[0],args[2],args[1],MyFoodora.stringCast.string2Address(args[3]),args[6],args[5],args[4]);
				MyFoodora.core.registerUser(customer);
				return ("Customer: -" + customer.getUsername() + "- registered.");
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			return e.getMessage();
		}catch(UsernameAlreadyRegisteredException e){
			return e.getMessage();
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
