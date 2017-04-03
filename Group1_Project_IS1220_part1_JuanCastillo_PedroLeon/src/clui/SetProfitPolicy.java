package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import system.ProfitPolicyType;

/**
 * SetProfitPolicy 1 <ProfitPolicyName>
 * @author Pedro León
 *
 */
public class SetProfitPolicy implements CommandProcessor{
	final int nArgs = 1;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				if(args[0].toUpperCase() == ProfitPolicyType.DELIVERYCOST.toString() || args[0].toUpperCase() == ProfitPolicyType.MARKUP.toString() || args[0].toUpperCase() == ProfitPolicyType.SERVICEFEE.toString()){
					switch(args[0].toUpperCase()){
					case "DELIVERYCOST":
						MyFoodora.core.setTargetProfitToDeliveryCost();;
						message = "Delivery policy changed to: TargetProfitToDeliveryCost";
						break;
					case "MARKUP":
						MyFoodora.core.setTargetProfitToMarkup();
						message = "Delivery policy changed to: TargetProfitToMarkup";
						break;
					case "SERVICEFEE":
						MyFoodora.core.setTargetProfitToServiceFee();
						message = "Delivery policy changed to: TargetProfitToServiceFee";
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
			return e.getMessage();
		}catch(InputMismatchException e){
			return e.getMessage();
		}catch(AccessDeniedException e){
			return e.getMessage();
		}
	}

}
