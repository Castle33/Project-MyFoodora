package clui;

import java.util.ArrayList;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Item;
/**
 * CreateMeal 2 "mealName" "mealCategory"
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
	public String process(String[] args) {
		try{
			if(args.length == nArgs){
				switch(MyFoodora.stringCast.string2MealType(args[1])){
				case "HALFMEAL":
					MyFoodora.listTempMeals.put(args[0], new ArrayList<Item>());
					message = "HalfMeal: -" + args[0] + "- added to temporal list of meals.";
					break;
				case "FULLMEAL":
					MyFoodora.listTempMeals.put(args[0], new ArrayList<Item>());
					message = "FullMeal: -" + args[0] + "- added to temporal list of meals.";
					break;
				}
				return message;
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(InputMismatchException e){
			return e.getMessage();
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}
	}
}
