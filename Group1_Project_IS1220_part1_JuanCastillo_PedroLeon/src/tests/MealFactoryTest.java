package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import restaurant_structure.*;

import org.junit.Test;

public class MealFactoryTest {

	@Test
	public void testGetFullMeal() {
		MealFactory mf = new MealFactory();
		ArrayList<Item> fmList = new ArrayList<Item>();
		Starter s = new Starter("Tapas",3.3,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		Dessert d = new Dessert("Cake",4.3,"vegetarian");
		fmList.add(s);
		fmList.add(m);
		fmList.add(d);
		
		Meal fm = mf.getMeal("FULLMEAL", "Menu entero", fmList);
		System.out.println(fm.toString());
		assertTrue(fm instanceof FullMeal);
		
	}
	
	@Test
	public void testGetHalfMeal() {
		MealFactory mf = new MealFactory();
		ArrayList<Item> fmList = new ArrayList<Item>();
		Starter s = new Starter("Tapas",3.3,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		fmList.add(s);
		fmList.add(m);
		
		Meal hm = mf.getMeal("HALFMEAL", "Medio Menu", fmList);
		System.out.println(hm.toString());
		assertTrue(hm instanceof HalfMeal);
	}
}
