package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import system.FidelityCardBasic;
import system.FidelityCardPoint;
import system.FidelityCardLottery;
import system.FidelityCardType;
import java.util.ArrayList;
import users.Customer;

/**
 * AssociateCard 2 "userName" "cardType"
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
			if(args.length == nArgs){
				if(MyFoodora.core.getCurrentUser().getUsername().equals(args[0])){
					if(args[1].toUpperCase().equals(FidelityCardType.BASIC.toString()) || args[1].toUpperCase().equals(FidelityCardType.POINT.toString()) || args[1].toUpperCase().equals(FidelityCardType.LOTTERY.toString())){
						switch(args[0].toUpperCase()){
						case "BASIC":
							MyFoodora.core.registerFidelityCard(new FidelityCardBasic());
							break;
						case "POINT":
							MyFoodora.core.registerFidelityCard(new FidelityCardPoint());
							break;
						case "LOTTERY":
							MyFoodora.core.registerFidelityCard(new FidelityCardLottery());
							break;
						}
						return "Cutomer: -" + args[0] + "- changed to fidelity card -" + args[1] + "-.";
					}else{
						throw new InputMismatchException();
					}
				}else{
					return "Customer: -" + args[0] + "- not logged in the system.";
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
