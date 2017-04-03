package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import system.DeliveryPolicyType;

/**
 * SetDeliveryPolicy 1 <delPolicyName>
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
			if(args[nArgs] == null){
				if(args[0].toUpperCase() == DeliveryPolicyType.FASTEST.toString() || args[0].toUpperCase() == DeliveryPolicyType.FAIROCCUPATION.toString()){
					switch(args[0].toUpperCase()){
					case "FASTEST":
						MyFoodora.core.setDeliveryToFastest();
						message = "Delivery policy changed to: DeliveryToFastest";
						break;
					case "FAIROCCUPATION":
						MyFoodora.core.setDeliveryToFairOccupation();
						message = "Delivery policy changed to: DeliveryToFairOccupation";
						break;
					}
					return message;
				}else{
					throw new InputMismatchException();
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}catch(InputMismatchException e){
			System.out.println(e.getMessage());
			return null;
		}catch(AccessDeniedException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
