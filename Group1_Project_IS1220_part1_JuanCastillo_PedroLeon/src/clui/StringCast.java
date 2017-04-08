package clui;

import users.Address;
import java.util.Calendar;
import exceptions.InputMismatchException;

public class StringCast {
	
	public Address string2Address(String input) throws InputMismatchException {
		String[] coordinates = input.split(",");
		if(coordinates.length == 2){
			if(isNumeric(coordinates[0])){
				return(new Address(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1])));
			}else{
				throw new InputMismatchException();
			}
		}else{
			throw new InputMismatchException();
		}
	}
	
	public Calendar string2Calendar(String input) throws InputMismatchException {
		String[] coordinates = input.split("/");
		if(coordinates.length == 3){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[0]));
			return calendar;
		}else{
			throw new InputMismatchException();
		}
	}
	
	public double string2Double(String input) throws InputMismatchException {
		if(isNumeric(input)){
			double toDouble = Double.parseDouble(input);
			return toDouble;
		}else{
			throw new InputMismatchException();
		}
	}
	
	public String string2Dish(String input) throws InputMismatchException {
		if(input.toUpperCase().equals("STARTER") || input.toUpperCase().equals("MAINDISH") || input.toUpperCase().equals("DESSERT")){
			return input.toUpperCase();
		}else{
			throw new InputMismatchException();
		}
	}
	
	public String string2DishType(String input) throws InputMismatchException {
		if(input.toUpperCase().equals("STANDARD") || input.toUpperCase().equals("VEGETARIAN") || input.toUpperCase().equals("GLUTENFREE")){
			return input.toUpperCase();
		}else{
			throw new InputMismatchException();
		}
	}
	
	public String string2MealType(String input) throws InputMismatchException {
		if(input.toUpperCase().equals("HALFMEAL") || input.toUpperCase().equals("FULLMEAL")){
			return input.toUpperCase();
		}else{
			throw new InputMismatchException();
		}
	}
	
	public int string2Integer(String input) throws InputMismatchException {
		if(isNumeric(input)){
			int toInteger = Integer.parseInt(input);
			return toInteger;
		}else{
			throw new InputMismatchException();
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
