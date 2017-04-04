package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.AccessDeniedException;
import restaurant_structure.Item;
import restaurant_structure.HalfMeal;
import restaurant_structure.FullMeal;
/**
 * SaveMeal 1 <mealName>
 * @author Pedro León
 *
 */
public class SaveMeal implements CommandProcessor{
	final int nArgs = 1;
	Item item;
	String message = "";
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) throws NumberOfArgumentsException {
		try{
			if(args.length == 1){
				for( String m : MyFoodora.listTempMeals.keySet()){
					if(m.equals(args[0])){
						if(MyFoodora.listTempMeals.get(m).size() == 2){
							HalfMeal hm = new HalfMeal(m, MyFoodora.listTempMeals.get(m));
							MyFoodora.core.addMeal(hm);
							MyFoodora.listTempMeals.remove(args[0]);
							message += "HalfMeal: " + m + " saved to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.\n";
						}else if(MyFoodora.listTempMeals.get(m).size() == 3){
							FullMeal fm = new FullMeal(m, MyFoodora.listTempMeals.get(m));
							MyFoodora.core.addMeal(fm);
							MyFoodora.listTempMeals.remove(args[0]);
							message += "FullMeal: " + m + " saved to " + MyFoodora.core.getCurrentUser().getName() + "'s menu.\n";
						}else{
							message += "Meal must have 2 or 3 items not more.\n";
						}
					}
				}
				if(message == null){
					return message += "Meal: " + args[0] + " not found in list of temporal meals.\nFor meal creation use CreateMeal <mealName> <mealCategory>/AddDish2Meal <dishName> <mealName>/SaveMeal <mealName>\n";
				}
				return message;
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}catch(AccessDeniedException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
}
