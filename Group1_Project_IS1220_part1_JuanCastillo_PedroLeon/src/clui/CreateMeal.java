package clui;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;
import restaurant_structure.Meal;
import restaurant_structure.Item;
import restaurant_structure.MealType;
import restaurant_structure.HalfMeal;
import restaurant_structure.FullMeal;

import java.util.ArrayList;
/**
 * CreateMeal 2 <mealName> <mealCategory>
 * @author Pedro León
 *
 */
public class CreateMeal implements CommandProcessor{
	final int nArgs = 2;
	private Meal meal;
	private ArrayList<Item> items = new ArrayList<Item>();
	String message;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException, InputMismatchException {
		try{
			if(args[nArgs] == null){
				if(args[1].toUpperCase() == MealType.HALFMEAL.toString() || args[1].toUpperCase() == MealType.FULLMEAL.toString()){
					switch(args[1].toUpperCase()){
					case "HALFMEAL":
						meal = new HalfMeal(args[0], items);
						MyFoodora.listTempMeals.add(meal);
						message = "HalfMeal: " + meal.getName() + " added to temporal list of meals.";
						break;
					case "FULLMEAL":
						meal = new FullMeal(args[0], items);
						MyFoodora.listTempMeals.add(meal);
						message = "FullMeal: " + meal.getName() + " added to temporal list of meals.";
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
			System.out.println(e.getMessage());
			return null;
		}
	}
}
