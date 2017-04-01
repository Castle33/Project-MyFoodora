package tests;

import restaurant_structure.*;

import org.junit.Test;

public class StarterTest {

	@Test
	public void testStarterStringDoubleString() {
		Starter s = new Starter("Tapas",2.5,"vegetarian");
		System.out.println(s.toString());
	}

	@Test
	public void testStarterStringDouble() {
		Starter s = new Starter("Tapas",2.5);
		System.out.println(s.toString());
	}

}
