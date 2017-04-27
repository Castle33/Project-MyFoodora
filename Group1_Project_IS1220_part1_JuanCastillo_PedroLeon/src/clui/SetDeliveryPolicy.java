package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import system.DeliveryPolicyType;

/**
 * SetDeliveryPolicy 1 "delPolicyName"
 * @author Pedro León
 *
 */
public class SetDeliveryPolicy implements CommandProcessor{
	final int nArgs = 1;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				if(args[0].toUpperCase() == DeliveryPolicyType.FASTEST.toString() || args[0].toUpperCase() == DeliveryPolicyType.FAIROCCUPATION.toString()){
					switch(args[0].toUpperCase()){
					case "FASTEST":
						MyFoodora.core.setDeliveryToFastest();
						break;
					case "FAIROCCUPATION":
						MyFoodora.core.setDeliveryToFairOccupation();
						break;
					}
					return "Delivery policy changed to: -" + args[0] + "-.";
				}else{
					throw new InputMismatchException();
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
