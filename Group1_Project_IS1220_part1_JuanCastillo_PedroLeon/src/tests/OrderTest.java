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
import system.FidelityCardBasic;
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
		Order o3 = new Order(c1,r);
		Order o4 = new Order(c2,r);
		assertTrue(o3.getCustomer().getUsername().equals("jcastillo33") && o3.getRestaurant().getName().equals("TGF") && o3.getID() == 3 && o4.getID() == 4  && o3.getItems().isEmpty() && o3.getMeals().isEmpty());
	}

	@Test
	public void testOrderCustomerRestaurantCalendar() {
		Address a1 = new Address(0,0);
		Address a2 = new Address(3,4);
		Address a3 = new Address(6,8);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a1, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer c2 = new Customer("Pedro", "pleonpita", "Leon", a2, "pleonpita@gmail.com", "0695599143", "newpassword2");
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Order o1 = new Order(c1,r,cal1);
		Order o2 = new Order(c2,r,cal2);
		assertTrue(o1.getDate().getTime().getYear() == 117);
	}

	@Test
	public void testCalcDeliveryTime() {
		Address a1 = new Address(0,0);
		Address a2 = new Address(3000,4000);
		Address a3 = new Address(6000,8000);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Calendar cal1 = Calendar.getInstance();
		Order o1 = new Order(c1,r,cal1);
		o1.setDeliveryDate(o1.calcDeliveryTime());
		long time = o1.getDeliveryDate().getTimeInMillis() - o1.getDate().getTimeInMillis();
		System.out.println(time);
		assertTrue(time == 360000 || time == 360001 || time == 360002 || time == 360003 || time == 360004 || time == 360005);
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
		Order o1 = new Order(c1,r,cal1);
		
		o1.addMeal(hm, 1);
		o1.addMeal(fm, 2);
		o1.addMeal(hm, 3);
		assertTrue(o1.getMeals().get(hm) == 4 && o1.getMeals().get(fm) == 2);
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
		Order o1 = new Order(c1,r);
		
		o1.addItem(s, 1);
		o1.addItem(m, 2);
		o1.addItem(d, 3);
		assertTrue(o1.getItems().get(s) == 1 && o1.getItems().get(m) == 2 && o1.getItems().get(d) == 3);
	}

	@Test
	public void testIsFidelityCardBasic() {
		Address a2 = new Address(3000,4000);
		Address a3 = new Address(6000,8000);
		Customer c1 = new Customer("Juan", "jcastillo33", "Castillo", a2, "jcastillo@gmail.com", "630285192", "newpassword");
		Customer c2 = new Customer("Pedro", "pleonpita", "Leon", a2, "pleonpita@gmail.com", "0695599143", "newpassword2");
		c2.setFidelityCardtoLottery();
		Restaurant r = new Restaurant("TGF", "TGFParis", "newpasswordr", a3);
		Order o1 = new Order(c1,r);
		Order o2= new Order(c2,r);
		assertTrue(o1.isFidelityCardBasic() && !o2.isFidelityCardBasic());
	}

	@Test
	public void testCalcPrice() {
		 bdouble price = 0.0;
		if(isFidelityCardBasic()){
			for (Meal m : meals.keySet()){
				price += meals.get(m)*restaurant.getPriceMeal(m);
			}
		} else {
			for (Meal m : meals.keySet()){
				price += meals.get(m)*m.getFullPrice();
			}
		}
		for (Item i : items.keySet()){
			price += items.get(i)*i.getPrice();
		}
		price *= customer.getFidelityCard().applyFidelityPlan();
		price = restaurant.round2dec(price);
		this.priceFood = price;
		return price;
	}

}
