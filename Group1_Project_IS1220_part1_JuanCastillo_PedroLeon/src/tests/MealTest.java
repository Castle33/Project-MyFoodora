package tests;

import static org.junit.Assert.*;
import restaurant_structure.*;

import org.junit.Test;

public class MealTest {

	@Test
	public void testMealString() {
		Meal fm = new FullMeal("Menu del dia");
		Meal hm = new HalfMeal("Medio menu del dia");
		
		
	}

	@Test
	public void testMealStringListOfItem() {
		fail("Not yet implemented");
	}

}
