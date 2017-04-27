package clui;

import java.util.Calendar;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import system.Core;

/**
 * ShowTotalProfit 1 "startDate" "endDate"
 * @author Juan Castillo
 *
 */
public class ShowTotalProfit implements CommandProcessor{
	StringCast stringCast = new StringCast();
	
	public String process(String[] args) {
		try{
			if(args.length == 2){
				Calendar cal1;
				Calendar cal2;
				try {
					cal1 = stringCast.string2Calendar(args[0]);
					cal2 = stringCast.string2Calendar(args[1]);
					return "The total profit for the specified time interval is: " +String.valueOf(MyFoodora.core.computeTotalProfit(cal1, cal2));
					
				} catch (InputMismatchException e) {
					return e.getMessage();
				}
			}else if(args.length == 0){
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				try {
					cal1 = stringCast.string2Calendar(Core.creationDate);
					return "The total profit for the specified time interval is: " +String.valueOf(MyFoodora.core.computeTotalProfit(cal1, cal2));
					
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
