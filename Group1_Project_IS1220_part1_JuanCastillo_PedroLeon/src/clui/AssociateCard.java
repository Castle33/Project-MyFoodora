package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import system.FidelityCardBasic;
import system.FidelityCardPoint;
import system.FidelityCardLottery;
import system.FidelityCardType;
import java.util.Collection;
import java.util.ArrayList;
import users.User;
import users.Customer;

/**
 * AssociateCard 2 <userName> <cardType>
 * @author Pedro León
 *
 */
public class AssociateCard implements CommandProcessor{
	final int nArgs = 2;
	String message;
	ArrayList<Customer> customers = new ArrayList<Customer>();
	Customer customer;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				Collection<User> users = MyFoodora.core.getListOfUsers().values();
				for(User u : users){
					if(u instanceof Customer){
						customers.add((Customer) u);
						for(Customer cu : customers){
							if(cu.getUsername().equals(args[0])){
								customer = cu;
							}
						}
					}
				}
				if(customer != null){
					if(args[1].toUpperCase() == FidelityCardType.BASIC.toString() || args[1].toUpperCase() == FidelityCardType.POINT.toString() || args[1].toUpperCase() == FidelityCardType.LOTTERY.toString()){
						switch(args[0].toUpperCase()){
						case "BASIC":
							MyFoodora.core.registerFidelityCard(new FidelityCardBasic());
							message = "Cutomer: " + args[0] + " changed to FidelityCardBasic.";
							break;
						case "POINT":
							MyFoodora.core.registerFidelityCard(new FidelityCardPoint());
							message = "Cutomer: " + args[0] + " changed to FidelityCardPoint.";
							break;
						case "LOTTERY":
							MyFoodora.core.registerFidelityCard(new FidelityCardLottery());
							message = "Cutomer: " + args[0] + " changed to FidelityCardLottery.";
							break;
						}
						return message;
					}else{
						throw new InputMismatchException();
					}
				}else{
					return "Customer: " + args[0] + " not registered in the system.";
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(InputMismatchException e){
			return e.getMessage();
		}catch(AccessDeniedException e){
			return e.getMessage();
		}
	}
}
