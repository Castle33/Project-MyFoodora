package tests;

import static org.junit.Assert.*;
import restaurant_structure.*;

import org.junit.Test;

public class DessertTest {

	@Test
	public void testDessertStringDoubleString() {
		Dessert d = new Dessert("Cake",4.3,"vegetarian");
		System.out.println(d.toString());
	}

	@Test
	public void testDessertStringDouble() {
		Dessert d = new Dessert("Cake",4.3);
		System.out.println(d.toString());
	}

}
