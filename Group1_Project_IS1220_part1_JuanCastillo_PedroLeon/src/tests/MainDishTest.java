package tests;

import restaurant_structure.*;

import org.junit.Test;

public class MainDishTest {

	@Test
	public void testMainDishStringDoubleString() {
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		System.out.println(m.toString());
	}

	@Test
	public void testMainDishStringDouble() {
		MainDish m = new MainDish("Pescado a la sal",12.4);
		System.out.println(m.toString());
	}

}
