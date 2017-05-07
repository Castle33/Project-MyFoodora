package clui;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import users.Restaurant;

/**
 * GetParameterToTargetProfit "targetProfit" "initDate" "finDate"
 * @author Pedro León
 *
 */
public class GetParameterToTargetProfit implements CommandProcessor {
	private int nArgs = 3;
	@Override
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				double res = MyFoodora.getCore().getParameterToTargetProfit(MyFoodora.stringCast.string2Double(args[0]), MyFoodora.stringCast.string2Calendar(args[1]), MyFoodora.stringCast.string2Calendar(args[2]));
				return "Target profit policy -" + MyFoodora.getCore().gettProfitPolicy().toString() + "- must be -" + Restaurant.round2dec(res) + "- to obtain target profit -" + args[0] + "-.";
			}else{
				throw  new NumberOfArgumentsException();
			}
		}catch (AccessDeniedException e){
			return e.getMessage();
		}catch (InputMismatchException e){
			return e.getMessage();
		}catch (NumberOfArgumentsException e){
			return e.getMessage();
		}
	}

}
