package clui;

import java.util.Calendar;

import exceptions.AccessDeniedException;
import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;

/**
 * ShowTotalProfit 1 <startDate> <endDate>
 * @author Juan Castillo
 *
 */
public class ShowTotalProfit implements CommandProcessor{
	final int nArgs = 2;
	StringCast stringCast = new StringCast();
	
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args[nArgs] == null){
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				try {
					cal1 = stringCast.string2Calendar(args[0]);
					cal2 = stringCast.string2Calendar(args[1]);
					System.out.println("The total profit for the specified time interval is: " +MyFoodora.core.computeTotalProfit(cal1, cal2));
					
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			e.getMessage();
			return null;
		}catch(AccessDeniedException e){
			System.out.println(e.getMessage());
			return null;
		}
		return null;
	}
}
