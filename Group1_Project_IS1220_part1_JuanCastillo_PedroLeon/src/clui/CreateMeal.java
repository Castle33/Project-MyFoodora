package clui;

import java.util.ArrayList;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
import restaurant_structure.MealType;
/**
 * CreateMeal 2 <mealName> <mealCategory>
 * @author Pedro León
 *
 */
public class CreateMeal implements CommandProcessor{
	final int nArgs = 2;
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		try{
			if(args.length == nArgs){
				if(args[1].toUpperCase().equals(MealType.HALFMEAL.toString()) || args[1].toUpperCase().equals(MealType.FULLMEAL.toString())){
					switch(args[1].toUpperCase()){
					case "HALFMEAL":
						MyFoodora.listTempMeals.put(args[0], new ArrayList<Item>());
						message = "HalfMeal: " + args[0] + " added to temporal list of meals.";
						break;
					case "FULLMEAL":
						MyFoodora.listTempMeals.put(args[0], new ArrayList<Item>());
						message = "FullMeal: " + args[0] + " added to temporal list of meals.";
						break;
					}
					return message;
				}else{
					throw new InputMismatchException();
				}
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			return e.getMessage();
		}
	}
}
