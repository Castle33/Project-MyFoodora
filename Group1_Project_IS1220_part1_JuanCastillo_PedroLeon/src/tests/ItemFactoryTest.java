package tests;

import static org.junit.Assert.*;
import restaurant_structure.*;

import org.junit.Test;

public class ItemFactoryTest {

	@Test
	public void testCreateFactory() {
		ItemFactory itemF = new ItemFactory();
		assertTrue(itemF != null);
	}
	
	@Test
	public void testGetStarterFromFactory() {
		ItemFactory itemF = new ItemFactory();
		Item starter = itemF.getItem("STARTER","croquetas",3.4);
		assertTrue(starter instanceof Item);
		assertTrue(starter instanceof Starter);
	}

	@Test
	public void testGetMainDishFromFactory() {
		ItemFactory itemF = new ItemFactory();
		Item md = itemF.getItem("MAINDISH","pescado",0.4);
		assertTrue(md instanceof Item);
		assertTrue(md instanceof MainDish);
	}
	
	@Test
	public void testGetDessertFromFactory() {
		ItemFactory itemF = new ItemFactory();
		Item d = itemF.getItem("DESSERT","tarta de queso",9.33);
		assertTrue(d instanceof Item);
		assertTrue(d instanceof Dessert);
	}

}
