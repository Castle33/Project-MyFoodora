package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import restaurant_structure.HalfMeal;
import restaurant_structure.Item;
import restaurant_structure.Starter;
import restaurant_structure.MainDish;
import restaurant_structure.Dessert;
import users.Address;
import users.Restaurant;

public class RestaurantTest {

	@Test
	public void testRestaurant() {
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a1);
		assertTrue(r.getName() == "TGF" && r.getUsername() == "TGFParis" && r.getPassword() == "newpasswordr" && r.getAddress() == a1 && r.getDiscountFactor() == 0.05 && r.getSpecialDiscountFactor() == 0.1 && r.getListOfMeal().isEmpty() && r.getListOfSpecialMeal().isEmpty());
	}

	@Test
	public void testAddStarter() {
		Starter s = new Starter("Tortilla", 5.5, "Standard");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a1);
		r.addStarter(s);
		assertTrue(r.getMenu().getStarters().get(0) == s);
	}

	@Test
	public void testRemoveStarter() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		Starter s2 = new Starter("Papas", 4, "Standard");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a1);
		r.addStarter(s1);
		r.addStarter(s2);
		r.removeStarter(s1);
		assertTrue(r.getMenu().getStarters().get(0) == s2);
		r.removeStarter(s2);
		assertTrue(r.getMenu().getStarters().isEmpty());
	}

	@Test
	public void testAddMainDish() {
		MainDish md = new MainDish("Bacalao", 15.5, "GlutenFree");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMainDish(md);
		assertTrue(r.getMenu().getMainDishes().get(0)== md);
	}

	@Test
	public void testRemoveMainDish() {
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		MainDish md2 = new MainDish("Lassaña Veg", 15.5, "Veg");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMainDish(md1);
		r.addMainDish(md2);
		r.removeMainDish(md1);
		assertTrue(r.getMenu().getMainDishes().get(0)== md2);
		r.removeMainDish(md2);
		assertTrue(r.getMenu().getMainDishes().isEmpty());
	}

	@Test
	public void testAddDessert() {
		Dessert d = new Dessert("Melon", 4, "Standard");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addDessert(d);
		assertTrue(r.getMenu().getDesserts().get(0)== d);
	}

	@Test
	public void testRemoveDessert() {
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		Dessert d2 = new Dessert("Helado", 2, "Standard");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addDessert(d1);
		r.addDessert(d2);
		r.removeDessert(d1);
		assertTrue(r.getMenu().getDesserts().get(0)== d2);
		r.removeDessert(d2);
		assertTrue(r.getMenu().getDesserts().isEmpty());
	}

	@Test
	public void testAddMeal() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md = new MainDish("Bacalao", 15.5, "GlutenFree");
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(s1);
		list.add(md);
		HalfMeal m = new HalfMeal("Mediterranea", list);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m);
		assertTrue(r.getListOfMeal().get(0) == m);
		
	}

	@Test
	public void testRemoveMeal() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
		list1.add(s1);
		list1.add(md1);
		list2.add(md1);
		list2.add(d1);
		HalfMeal m1 = new HalfMeal("Mediterranea", list1);
		HalfMeal m2 = new HalfMeal("Verano", list2);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m1);
		r.addMeal(m2);
		r.removeMeal(m1);
		assertTrue(r.getListOfMeal().get(0) == m2);
		r.removeMeal(m2);
		assertTrue(r.getListOfMeal().isEmpty());
	}

	@Test
	public void testAddSpecialMeal() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md = new MainDish("Bacalao", 15.5, "GlutenFree");
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(s1);
		list.add(md);
		HalfMeal m = new HalfMeal("Mediterranea", list);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addSpecialMeal(m);
		assertTrue(r.getListOfSpecialMeal().get(0) == m);
	}

	@Test
	public void testRemoveSpecialMeal() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
		list1.add(s1);
		list1.add(md1);
		list2.add(md1);
		list2.add(d1);
		HalfMeal m1 = new HalfMeal("Mediterranea", list1);
		HalfMeal m2 = new HalfMeal("Verano", list2);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addSpecialMeal(m1);
		r.addSpecialMeal(m2);
		r.removeSpecialMeal(m1);
		assertTrue(r.getListOfSpecialMeal().get(0) == m2);
		r.removeSpecialMeal(m2);
		assertTrue(r.getListOfSpecialMeal().isEmpty());
	}

	@Test
	public void testGetPriceMeal() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
		list1.add(s1);
		list1.add(md1);
		list2.add(md1);
		list2.add(d1);
		HalfMeal m1 = new HalfMeal("Mediterranea", list1);
		HalfMeal m2 = new HalfMeal("Verano", list2);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m1);
		r.addSpecialMeal(m2);
		assertTrue(r.getPriceMeal(m1) == 19.95 && r.getPriceMeal(m2) == 17.55);
	}

	@Test
	public void testGetMealByName() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
		list1.add(s1);
		list1.add(md1);
		list2.add(md1);
		list2.add(d1);
		HalfMeal m1 = new HalfMeal("Mediterranea", list1);
		HalfMeal m2 = new HalfMeal("Verano", list2);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m1);
		r.addSpecialMeal(m2);
		assertTrue(r.getMealByName("Mediterranea") == m1 && r.getMealByName("Verano") == m2);
	}

	@Test
	public void testGetItemByName() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Helado", 2, "Standard");
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addStarter(s1);
		r.addMainDish(md1);
		r.addDessert(d1);
		assertTrue(r.getItemByName("Tortilla") == s1 && r.getItemByName("Bacalao") == md1 && r.getItemByName("Helado") == d1);
		
	}

	@Test
	public void testDetermineIfDiscountMeal() {
		Starter s1 = new Starter("Tortilla", 5.5, "Standard");
		MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
		Dessert d1 = new Dessert("Melon", 4, "Standard");
		ArrayList<Item> list1 = new ArrayList<Item>();
		ArrayList<Item> list2 = new ArrayList<Item>();
		list1.add(s1);
		list1.add(md1);
		list2.add(md1);
		list2.add(d1);
		HalfMeal m1 = new HalfMeal("Mediterranea", list1);
		HalfMeal m2 = new HalfMeal("Verano", list2);
		Address a1 = new Address(3,4);
		Restaurant r = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
		r.addMeal(m1);
		r.addSpecialMeal(m2);
		assertTrue(r.determineIfDiscountMeal(m1) == false && r.determineIfDiscountMeal(m2) == true);
	}

	@Test
	public void testRound2dec() {
		assertTrue(Restaurant.round2dec(3.563) == 3.56 && Restaurant.round2dec(3.567) == 3.57);
	}

}
