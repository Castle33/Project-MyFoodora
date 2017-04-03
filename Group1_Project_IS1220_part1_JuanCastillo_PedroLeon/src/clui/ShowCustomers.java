package clui;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import users.*;

/**
 * ShowCustomers 0
 * @author Juan Castillo
 *
 */
public class ShowCustomers {
	final int nArgs = 0;
	HashMap<String,User> temp_listOfUsers = new HashMap<String,User>();
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		try{
			if(args[nArgs] == null){
				temp_listOfUsers = MyFoodora.core.getListOfUsers();
				users = (ArrayList<User>) temp_listOfUsers.values();
				for(User u : users){
					if(!(u instanceof Customer)){
						users.remove(u);
					}
				}
				return "List of customers " + users.toString();
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
