package tests;

import restaurant_structure.*;
import java.util.*;

import org.junit.Test;

public class MealTest {

	@Test
	public void testMealString() {
		ArrayList<Item> hmList = new ArrayList<Item>();
		Starter s = new Starter("Tapas",2.5,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		Dessert d = new Dessert("Cake",4.3,"vegetarian");
		hmList.add(s);
		hmList.add(d);
		Meal hm = new HalfMeal("Medio menu del dia",hmList);
		System.out.println(hm.toString());
		hmList.set(0, m);
		hm.setMealItems(hmList);
		System.out.println(hm.toString());
	}

	@Test
	public void testWrongMealSize() {
		ArrayList<Item> fmList = new ArrayList<Item>();
		Starter s = new Starter("Tapas",2.5,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		Dessert d = new Dessert("Cake",4.3,"vegetarian");
		fmList.add(s);
		fmList.add(m);
		Meal fm = new FullMeal("Menu del dia",fmList);
		System.out.println(fm.toString());
		fmList.set(1, d);
		fm.setMealItems(fmList);
		System.out.println(fm.toString());
	}

}
