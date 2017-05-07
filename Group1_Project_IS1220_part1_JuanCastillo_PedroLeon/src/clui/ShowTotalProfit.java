package clui;

import java.util.Calendar;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import system.Core;
import users.Restaurant;

/**
 * ShowTotalProfit 1 "startDate" "endDate"
 * @author Juan Castillo
 *
 */
public class ShowTotalProfit implements CommandProcessor{
	
	public String process(String[] args) {
		try{
			if(args.length == 2){
				Calendar cal1;
				Calendar cal2;
				try {
					cal1 = MyFoodora.stringCast.string2Calendar(args[0]);
					cal2 = MyFoodora.stringCast.string2Calendar(args[1]);
					System.out.println(args[0]);
					System.out.println(cal1.getTime());
					return "The total profit for the specified time interval is: -" + Restaurant.round2dec(MyFoodora.core.computeTotalProfit(cal1, cal2)) + "-.";
					
				} catch (InputMismatchException e) {
					return e.getMessage();
				}
			}else if(args.length == 1){
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				try {
					cal1 = MyFoodora.stringCast.string2Calendar(Core.creationDate);
					cal2 = MyFoodora.stringCast.string2Calendar(args[0]);
					return "The total profit from init date until now is: -" + Restaurant.round2dec(MyFoodora.core.computeTotalProfit(cal1, cal2)) + "-.";
					
				} catch (InputMismatchException e) {
					return e.getMessage();
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(AccessDeniedException e){
			return e.getMessage();
		}
	}
}
