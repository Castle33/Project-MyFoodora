package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import system.ProfitPolicyType;

/**
 * SetProfitPolicy 1 "ProfitPolicyName"
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
			if(args.length == nArgs){
				if(args[0].toUpperCase().equals(ProfitPolicyType.DELIVERYCOST.toString()) || args[0].toUpperCase().equals(ProfitPolicyType.MARKUP.toString()) || args[0].toUpperCase().equals(ProfitPolicyType.SERVICEFEE.toString())){
					switch(args[0].toUpperCase()){
					case "DELIVERYCOST":
						MyFoodora.core.setTargetProfitToDeliveryCost();;
						break;
					case "MARKUP":
						MyFoodora.core.setTargetProfitToMarkup();
						break;
					case "SERVICEFEE":
						MyFoodora.core.setTargetProfitToServiceFee();
						break;
					}
					return "Profit policy changed to: -" + args[0] + "-.";
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
