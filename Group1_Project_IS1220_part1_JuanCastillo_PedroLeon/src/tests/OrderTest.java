package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurant_structure.Dessert;
import restaurant_structure.HalfMeal;
import restaurant_structure.FullMeal;
import restaurant_structure.Item;
import restaurant_structure.MainDish;
import restaurant_structure.Meal;
import restaurant_structure.Starter;
import system.Order;
import users.Address;
import users.Customer;
import users.Restaurant;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderTest {

	@Test
	public void testOrderCustomerRestaurant() {
		Address a1 = new Address(0,0);
		Address a2 = new Address(3,4);
		Address a3 = new Address(6,8);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer c2 = new Customer("Pedro", "pleonpita", "Leon", a2, "pleonpita@gmail.com", "0695599143", "newpassword2");
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Order o8 = new Order(c1,r);
		Order o9 = new Order(c2,r);
		assertTrue(o8.getCustomer().getUsername().equals("jcastillo33") && o8.getRestaurant().getName().equals("TGF") && o8.getID() == 8 && o9.getID() == 9  && o8.getItems().isEmpty() && o8.getMeals().isEmpty());
	}

	@Test
	public void testOrderCustomerRestaurantCalendar() {
		Address a1 = new Address(0,0);
		Address a2 = new Address(3,4);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a2);
		Order o7 = new Order(c1,r);
		assertTrue(o7.getDate().getTime().getYear() == 117);
	}

	@Test
	public void testCalcDeliveryTime() {
		
		Address a1 = new Address(5560,2780);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		Address a2 = new Address(2224,-1668);
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a2);
		Order o6 = new Order(c1,r);
		
		Address a3 = new Address(0,0);

		Calendar initTime = o6.getDate();
		Calendar time = o6.calcDeliveryTime(a3);
		long courseTime = time.getTimeInMillis() - initTime.getTimeInMillis();
		System.out.println(courseTime);
		assertTrue(courseTime == 600000);
	}

	@Test
	public void testAddMeal() {
		ArrayList<Item> hmList = new ArrayList<Item>();
		ArrayList<Item> fmList = new ArrayList<Item>();
		Starter s = new Starter("Tapas",2.5,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		Dessert d = new Dessert("Cake",4.3,"vegetarian");
		hmList.add(s);
		hmList.add(d);
		fmList.add(s);
		fmList.add(m);
		fmList.add(d);
		Meal hm = new HalfMeal("Medio menu del dia",hmList);
		Meal fm = new FullMeal("Menu del dia",fmList);

		Address a2 = new Address(3000,4000);
		Address a3 = new Address(6000,8000);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Calendar cal1 = Calendar.getInstance();
		Order o5 = new Order(c1,r,cal1);
		
		o5.addMeal(hm, 1);
		o5.addMeal(fm, 2);
		o5.addMeal(hm, 3);
		assertTrue(o5.getMeals().get(hm) == 4 && o5.getMeals().get(fm) == 2);
	}

	@Test
	public void testAddItem() {
		Starter s = new Starter("Tapas",2.5,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		Dessert d = new Dessert("Cake",4.3,"vegetarian");

		Address a2 = new Address(3000,4000);
		Address a3 = new Address(6000,8000);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Order o4 = new Order(c1,r);
		
		o4.addItem(s, 1);
		o4.addItem(m, 2);
		o4.addItem(d, 3);
		assertTrue(o4.getItems().get(s) == 1 && o4.getItems().get(m) == 2 && o4.getItems().get(d) == 3);
	}

	@Test
	public void testIsFidelityCardBasic() {
		Address a2 = new Address(3000,4000);
		Address a3 = new Address(6000,8000);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer c2 = new Customer("Pedro", "pleonpita", "Leon", a2, "pleonpita@gmail.com", "0695599143", "newpassword2");
		c2.setFidelityCardtoLottery();
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Order o2 = new Order(c1,r);
		Order o3= new Order(c2,r);
		assertTrue(o2.isFidelityCardBasic() && !o3.isFidelityCardBasic());
	}

	@Test
	public void testCalcPrice() {
		ArrayList<Item> hmList = new ArrayList<Item>();
		ArrayList<Item> fmList = new ArrayList<Item>();
		Starter s = new Starter("Tapas",2.5,"vegetarian");
		MainDish m = new MainDish("Paella",12.4,"glutenFree");
		Dessert d = new Dessert("Cake",4.3,"vegetarian");
		hmList.add(s);
		hmList.add(d);
		fmList.add(s);
		fmList.add(m);
		fmList.add(d);
		Meal hm = new HalfMeal("Medio menu del dia",hmList);
		
		Address a2 = new Address(3000,4000);
		Address a3 = new Address(6000,8000);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		c1.setFidelityCardtoLottery();
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Order o1 = new Order(c1,r);
		
		r.addMeal(hm);
		o1.addMeal(hm, 1);
		o1.addItem(m, 1);
		
		assertTrue(o1.calcPrice() == 19.2 || o1.calcPrice() == 0.0);
	}

}
