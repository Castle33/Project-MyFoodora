package clui;

import users.Address;
import java.util.Calendar;
import exceptions.InputMismatchException;;

public class StringCast {
	
	public Address string2Address(String input) throws InputMismatchException {
		String[] coordinates = input.split(",");
		if(coordinates.length == 2){
			return(new Address(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1])));
		}else{
			throw new InputMismatchException();
		}
	}
	
	public Calendar string2Calendar(String input) throws InputMismatchException {
		String[] coordinates = input.split("/");
		if(coordinates.length == 3){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[0]));
			return(calendar);
		}else{
			throw new InputMismatchException();
		}
	}

}
