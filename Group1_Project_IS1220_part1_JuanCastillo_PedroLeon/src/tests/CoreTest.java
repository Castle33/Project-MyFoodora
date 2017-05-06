package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import org.junit.Test;

import exceptions.*;
import restaurant_structure.*;
import system.*;
import users.*;

public class CoreTest {
	
	/* Core */
	Core c = new Core();

	/* Address */
	Address a1 = new Address(3,4);
	Address a2 = new Address(3,4);
	Address a3 = new Address(3,4);
	Address a4 = new Address(6,8);
	
	/* Users - register/logIn must be done in each test */
	Manager m = (Manager) c.getListOfMasterManager().get(1);
	Customer cu1 = new Customer("Luis", "luiscobas", "Cobas", a1, "lcobas@gmail.com", "630285192", "newpassword");
	Customer cu2 = new Customer("Juan", "jcastillo", "Castillo", a3, "jcastillo@gmail.com", "630285192", "newpassword");
	Customer cu3 = new Customer("Pedro", "pleon", "Leon", a4, "pleonpita@gmail.com", "0695599143", "newpassword2");
	Restaurant r1 = new Restaurant("La Playa", "LaPlayaBilbao", "newpasswordr", a1);
	Restaurant r2 = new Restaurant("TGF", "TGFParis", "newpasswordr", a2);
	Restaurant r3 = new Restaurant("McDonals", "mcdonalsmadrid", "newpasswordr2", a3);
	Courier co1 = new Courier("Luis","lucho","password1","Cobas", a1,"0654641222");
	Courier co2 = new Courier("Jesus","jisus","password2","Martinez", a2,"0654641222");
	Courier co3 = new Courier("Angel","aantolin","password3","Antolin", a3,"0654641222");
	
	/* Items */
	Starter s1 = new Starter("Tortilla", 5.5, "Standard");
	MainDish md1 = new MainDish("Bacalao", 15.5, "GlutenFree");
	Dessert d1 = new Dessert("Melon", 4, "Standard");
	ArrayList<Item> list1 = new ArrayList<Item>();
	
	/* Order */
	Order o1 = new Order(cu1, r1);
	Order o2 = new Order(cu2, r1);
	Order o3 = new Order(cu3, r1);

	@Test
	public void testCore() {
		assertTrue(c.getName() == "MyFoodora" && c.getServiceFee() == 3.0 && c.getMarkupPercentage() == 0.1 && c.getDeliveryCost() == 2.0);
		assertTrue(c.getListOfUsers().get("ceo").getUsername() == "ceo");
		assertTrue(c.getListOfUsers().get("deputy").getUsername() == "deputy");
		assertTrue(c.getDeliveryPolicy() instanceof DeliveryFastest && c.gettProfitPolicy() instanceof TargetProfitDeliveryCost);
		assertTrue(c.getListOfToNotify().isEmpty() && c.getListOfCompletedOrders().isEmpty() && c.getListOfPendingOrders().isEmpty());
		assertTrue(c.getListOfMasterManager().get(0).getUsername() == "ceo");
		assertTrue(c.getListOfMasterManager().get(1).getUsername() == "deputy");
	}

	@Test
	public void testRegisterUser() throws UsernameAlreadyRegisteredException{
		Manager man = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		try{
			c.registerUser(man);
			c.registerUser(man);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println(e.getMessage());
			System.out.println("User: " + man.getUsername() + " already registered.");
		}
		assertTrue(c.getListOfUsers().get("mbataillou").getUsername() == "mbataillou");
	}

	@Test
	public void testUserLogIn() throws UsernameAlreadyRegisteredException{
		Manager man = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		try{
			c.registerUser(man);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: " + man.getUsername() + " already registered.");
		}
		c.userLogIn(man);
		assertTrue(c.getCurrentUser().getUsername() == "mbataillou");
	}

	@Test
	public void testLogOut() throws UsernameAlreadyRegisteredException{
		Manager man = new Manager("Marc", "mbataillou", "pmaasrscword", "Bataillou");
		try{
			c.registerUser(man);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: " + man.getUsername() + " already registered.");
		}
		c.userLogIn(man);
		assertTrue(c.getCurrentUser().getUsername() == "mbataillou");
		c.logOut();
		assertNull(c.getCurrentUser());
	}

	@Test
	public void testRegisterObserver() throws UsernameAlreadyRegisteredException{
		try{
			c.registerUser(cu1);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: " + cu1.getUsername() + " already registered.");
		}
		c.userLogIn(cu1);
		c.registerObserver(cu1);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "luiscobas");
	}

	@Test
	public void testRemoveObserver() throws UsernameAlreadyRegisteredException{
		try{
			c.registerUser(cu1);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: " + cu1.getUsername() + " already registered.");
		}
		c.userLogIn(cu1);
		c.registerObserver(cu1);
		assertTrue(c.getListOfToNotify().get(0).getUsername() == "luiscobas");
		c.removeObserver(cu1);
		assertTrue(c.getListOfToNotify().isEmpty());
	}

	@Test
	public void testNotifyObservers() throws UsernameAlreadyRegisteredException{
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		m1.setMealItems(list1);
		r1.addMeal(m1);
		try{
			c.registerUser(cu1);
			c.registerObserver(cu1);
			c.registerUser(cu2);
			c.registerObserver(cu2);
			c.registerUser(cu3);
			c.registerObserver(cu3);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
		//c.notifyObservers(r, m1);
	}

	@Test
	public void testActivateUser() throws AccessDeniedException{
		c.userLogIn(m);
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
		try{
			c.activateUser(cu1);
			c.activateUser(r1);
			c.activateUser(co1);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.getListOfUsers().get("luiscobas").getUsername() == "luiscobas");
		assertTrue(c.getListOfUsers().get("LaPlayaBilbao").getUsername() == "LaPlayaBilbao");
		assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
	}

	@Test
	public void testDeactivateUser() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		try{
			c.registerUser(cu1);
			c.registerUser(r1);
			c.registerUser(co1);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
		try{
			c.userLogIn(m);
			c.deactivateUser(cu1);
			c.deactivateUser(r1);
			c.deactivateUser(co1);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
	}

	@Test
	public void testAddUser() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
		try{
			c.userLogIn(m);
			c.addUser(cu1);
			c.addUser(r1);
			c.addUser(co1);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
		assertTrue(c.getListOfUsers().get("luiscobas").getUsername() == "luiscobas");
		assertTrue(c.getListOfUsers().get("LaPlayaBilbao").getUsername() == "LaPlayaBilbao");
		assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
	}

	@Test
	public void testRemoveUser() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		try{
			c.registerUser(cu1);
			c.registerUser(r1);
			c.registerUser(co1);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
		try{
			c.userLogIn(m);
			c.removeUser(cu1);
			c.removeUser(r1);
			c.removeUser(co1);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		
		assertNull(c.getListOfUsers().get("luiscobas"));
		assertNull(c.getListOfUsers().get("LaPlayaBilbao"));
		assertNull(c.getListOfUsers().get("lucho"));
	}

	@Test
	public void testChangeServiceFee() throws AccessDeniedException{
		c.userLogIn(m);
		try{
			c.changeServiceFee(4.0);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.getServiceFee() == 4.0);
	}

	@Test
	public void testChangeMarkup() throws AccessDeniedException{
		c.userLogIn(m);
		try{
			c.changeMarkup(0.15);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.getMarkupPercentage() == 0.15);
	}

	@Test
	public void testChangeDeliveryCost() throws AccessDeniedException{
		c.userLogIn(m);
		try{
			c.changeDeliveryCost(3.0);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.getDeliveryCost() == 3.0);
	}

	@Test
	public void testSetTargetProfitToDeliveryCost() throws AccessDeniedException{
		c.userLogIn(m);
		try{
			c.setTargetProfitToDeliveryCost();
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitDeliveryCost);
	}

	@Test
	public void testSetTargetProfitToMarkup() throws AccessDeniedException{
		c.userLogIn(m);
		try{
			c.setTargetProfitToMarkup();
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitMarkup);
	}

	@Test
	public void testSetTargetProfitToServiceFee() throws AccessDeniedException{
		c.userLogIn(m);
		try{
			c.setTargetProfitToServiceFee();
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.gettProfitPolicy() instanceof TargetProfitServiceFee);
	}

	@Test
	public void testGetParameterToTargetProfit() throws AccessDeniedException{
		c.userLogIn(m);
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		m1.setMealItems(list1);
		list1.remove(0);
		list1.add(d1);
		HalfMeal m2 = new HalfMeal("Medio menu del día - postre", list1);
		r1.addMeal(m2);
		m2.setMealItems(list1);
		o1.addMeal(m1,4);
		o2.addItem(s1,1);
		o2.addMeal(m2,1);
		o3.addMeal(m2,4);
		ArrayList<Order> auxiliarListOfCompletedOrders = new ArrayList<Order>();
		auxiliarListOfCompletedOrders.add(o1);
		auxiliarListOfCompletedOrders.add(o2);
		auxiliarListOfCompletedOrders.add(o3);
		c.setListOfCompletedOrders(auxiliarListOfCompletedOrders);
		Calendar initDate = Calendar.getInstance();
		Calendar finDate = Calendar.getInstance();
		initDate.set(Calendar.MONTH, 2);
		finDate.set(Calendar.MONTH, 5);
		/*
		 * Starts at Core: getParameterToTargetProfit(targetProfit, initDate, finDate);
		 * 1st call from TargetProfitDeliveryCost/TargetProfitServiceFee/TargetProfitMarkup: computeProfitStrategyBased(serviceFee, markupPercentage, deliveryCost, targetProfit, listOfCompletedOrders, initDate, finDate);
		 * for order with date between initDate and finDate it adds order price
		 * 2nd call from Order: calcPrice();
		 * adds all meals and items
		 * 3rd call from Restaurant: getPriceMeal();
		 * returns meal's price (discount applied)
		 * Example: 
		 * - listOfCompletedOrders information:
		 * 		numberOfOrders = 3
		 * 		totalIncome:
		 * 		totalIncome = sum(orderPrice)
		 * 			orderPrice  = ((starterPrice + mainDishPrice)*discount)*numberOfMeals + sum(itemPrice*numberOfItems)
		 * 			order1Price = ((5.5 + 15.5)*0.95)*4 = 79.8
		 * 			order2Price = ((15.5 + 4.0)*0.95)*1 + (5.5)*1 = 24.02
		 * 			order3Price = ((15.5 + 4.0)*0.95)*4 = 74.1
		 * 		totalIncome = 79.8 + 24.02 + 74.1 = 177,92
		 * - Core information:
		 * 		TargetProfitDeliveryCost:	serviceFee = 3.0 / markupPercetange = 0.1
		 * 		TargetProfitServiceFee: 	markupPercentage = 0.1 / deliveryCost = 2.0
		 * 		TargetProfitMarkup:			deliveryCost = 2.0 / serviceFee = 3.0
		 * - Client input information:
		 * 		targetProfit = 25
		 * 
		 * profitOneOrder = orderPrice*markupPercentage + serviceFee - deliveryCost
		 * totalProfit = totalIncome*markupPercentage + (serviceFee - deliveryCost)*numberOfOrders
		 * TargetProfitDeliveryCost:
		 * deliveryCost = (totalIncome*markupPercentage - totalProfit)/numberOfOrders + serviceFee
		 * deliveryCost = (177,92*0.1 - 25)/3 + 3.0 = (17.79 - 25)/3 + 3.0 = -7.21/3 + 3.0 = -2.4 + 3.0 = 0.6
		 * TargetProfitServiceFee:
		 * serviceFee = (totalProfit - totalIncome*markupPercentage)/numberOfOrders + deliveryCost
		 * serviceFee = (25 - 177.92*0.1)/3 + 2.0 = (25 - 17.79)/3 + 2.0 = 7.21/3 + 2.0 = 2.4 + 2.0 = 4.4
		 * TargetProfitMarkup:
		 * markupPercenage = (totalProfit - (serviceFee - deliveryCost)*numberOfOrders)/totalIncome
		 * markupPercentage = (25 - (3.0 - 2.0)*3)/177.92 = (25 - 3)/177.92 = 22/177.92 = 
		 */
		try{
			System.out.println("HELOOOOOOOOO " + c.getParameterToTargetProfit(25, initDate, finDate));
			assertTrue(Restaurant.round2dec(c.getParameterToTargetProfit(25, initDate, finDate)) == 0.6);
			c.setTargetProfitToServiceFee();
			System.out.println("HELOOOOOOOOO " + c.getParameterToTargetProfit(25, initDate, finDate));
			assertTrue(Restaurant.round2dec(c.getParameterToTargetProfit(25, initDate, finDate)) == 4.4);
			c.setTargetProfitToMarkup();
			System.out.println("HELOOOOOOOOO " + c.getParameterToTargetProfit(25, initDate, finDate));
			assertTrue(Restaurant.round2dec(c.getParameterToTargetProfit(25, initDate, finDate)) == 0.12);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		
	}

	@Test
	public void testComputeTotalIncome() throws AccessDeniedException{
		c.userLogIn(m);
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		m1.setMealItems(list1);
		list1.remove(0);
		list1.add(d1);
		HalfMeal m2 = new HalfMeal("Medio menu del día - postre", list1);
		r1.addMeal(m2);
		m2.setMealItems(list1);
		o1.addMeal(m1,4);
		o2.addItem(s1,1);
		o2.addMeal(m2,1);
		o3.addMeal(m2,4);
		ArrayList<Order> auxiliarListOfCompletedOrders = new ArrayList<Order>();
		auxiliarListOfCompletedOrders.add(o1);
		auxiliarListOfCompletedOrders.add(o2);
		auxiliarListOfCompletedOrders.add(o3);
		c.setListOfCompletedOrders(auxiliarListOfCompletedOrders);
		Calendar initDate = Calendar.getInstance();
		Calendar finDate = Calendar.getInstance();
		initDate.set(Calendar.MONTH, 2);
		finDate.set(Calendar.MONTH, 5);
		try{
			assertTrue(Restaurant.round2dec(c.computeTotalIncome(initDate, finDate)) == 186.9);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
	}

	@Test
	public void testComputeTotalProfit() throws AccessDeniedException {
		c.userLogIn(m);
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		m1.setMealItems(list1);
		list1.remove(0);
		list1.add(d1);
		HalfMeal m2 = new HalfMeal("Medio menu del día - postre", list1);
		r1.addMeal(m2);
		m2.setMealItems(list1);
		o1.addMeal(m1,4);
		o2.addItem(s1,1);
		o2.addMeal(m2,1);
		o3.addMeal(m2,4);
		ArrayList<Order> auxiliarListOfCompletedOrders = new ArrayList<Order>();
		auxiliarListOfCompletedOrders.add(o1);
		auxiliarListOfCompletedOrders.add(o2);
		auxiliarListOfCompletedOrders.add(o3);
		c.setListOfCompletedOrders(auxiliarListOfCompletedOrders);
		Calendar initDate = Calendar.getInstance();
		Calendar finDate = Calendar.getInstance();
		initDate.set(Calendar.MONTH, 2);
		finDate.set(Calendar.MONTH, 5);
		try{
			assertTrue(Restaurant.round2dec(c.computeTotalProfit(initDate, finDate)) == 20.79);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
	}

	@Test
	public void testComputeAverageIncome() throws AccessDeniedException {
		// not actually income as it multiplies the order price by 0.1
		c.userLogIn(m);
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		m1.setMealItems(list1);
		list1.remove(0);
		list1.add(d1);
		HalfMeal m2 = new HalfMeal("Medio menu del día - postre", list1);
		r1.addMeal(m2);
		m2.setMealItems(list1);
		o1.addMeal(m1,4);
		o2.addItem(s1,1);
		o2.addMeal(m2,1);
		o3.addMeal(m2,4);
		ArrayList<Order> auxiliarListOfCompletedOrders = new ArrayList<Order>();
		auxiliarListOfCompletedOrders.add(o1);
		auxiliarListOfCompletedOrders.add(o2);
		auxiliarListOfCompletedOrders.add(o3);
		c.setListOfCompletedOrders(auxiliarListOfCompletedOrders);
		Calendar initDate = Calendar.getInstance();
		Calendar finDate = Calendar.getInstance();
		initDate.set(Calendar.MONTH, 2);
		finDate.set(Calendar.MONTH, 5);
		try{
			assertTrue(Restaurant.round2dec(c.computeAverageIncome(initDate, finDate)) == 8.93);
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
	}

	@Test
	public void testLeastActiveRestaurant() throws UsernameAlreadyRegisteredException, AccessDeniedException {
		c.userLogIn(m);
		r1.setCountOfOrdersCompleted(10);
		r2.setCountOfOrdersCompleted(15);
		r3.setCountOfOrdersCompleted(5);
		try{
			c.addUser(r1);
			c.addUser(r2);
			c.addUser(r3);
			assertTrue(c.leastActiveRestaurant().getUsername() == "mcdonalsmadrid");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
	}

	@Test
	public void testMostActiveRestaurant() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		c.userLogIn(m);
		r1.setCountOfOrdersCompleted(10);
		r2.setCountOfOrdersCompleted(15);
		r3.setCountOfOrdersCompleted(5);
		try{
			c.addUser(r1);
			c.addUser(r2);
			c.addUser(r3);
			assertTrue(c.mostActiveRestaurant().getUsername() == "TGFParis");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
	}

	@Test
	public void testLeastActiveCourier() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		c.userLogIn(m);
		co1.setCountOfOrdersCompleted(10);
		co2.setCountOfOrdersCompleted(15);
		co3.setCountOfOrdersCompleted(5);
		try{
			c.addUser(co1);
			c.addUser(co2);
			c.addUser(co3);
			assertTrue(c.leastActiveCourier().getUsername() == "aantolin");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
	}

	@Test
	public void testMostActiveCourier() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		c.userLogIn(m);
		co1.setCountOfOrdersCompleted(10);
		co2.setCountOfOrdersCompleted(15);
		co3.setCountOfOrdersCompleted(5);
		try{
			c.addUser(co1);
			c.addUser(co2);
			c.addUser(co3);
			assertTrue(c.mostActiveCourier().getUsername() == "jisus");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
	}

	@Test
	public void testSetDeliveryToFastest() throws AccessDeniedException {
		c.userLogIn(m);
		try{
			c.setDeliveryToFastest();
			c.logOut();
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.getDeliveryPolicy() instanceof DeliveryFastest);
	}

	@Test
	public void testSetDeliveryToFairOccupation() throws AccessDeniedException {
		c.userLogIn(m);
		try{
			c.setDeliveryToFairOccupation();
			c.logOut();
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}
		assertTrue(c.getDeliveryPolicy() instanceof DeliveryFairOccupation);
	}
	
	@Test
	public void testShowCustomersRegistered() throws AccessDeniedException {
		String list = null;
		try{
			c.registerUser(cu1);
			c.registerUser(cu2);
			c.registerUser(cu3);
			c.userLogIn(m);
			list = c.showCustomersRegistered();
			c.logOut();
		}catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}catch(UsernameAlreadyRegisteredException e){
			System.out.println("Customer registered already exists.");
		}
		assertTrue(list.equals("Customers [name=Pedro, surname=Leon, username=pleon, ID=170, address=[6,8], email=pleonpita@gmail.com, phoneNumber=0695599143]Customers [name=Juan, surname=Castillo, username=jcastillo, ID=169, address=[3,4], email=jcastillo@gmail.com, phoneNumber=630285192]Customers [name=Luis, surname=Cobas, username=luiscobas, ID=168, address=[3,4], email=lcobas@gmail.com, phoneNumber=630285192]"));
	}
	
	@Test
	public void testShowRestaurantRegistered() throws AccessDeniedException {
		String list = null;
		try{
			c.registerUser(r1);
			c.registerUser(r2);
			c.registerUser(r3);
			c.userLogIn(m);
			list = c.showRestaurantsRegistered();
			c.logOut();
		}catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a manager.");
		}catch(UsernameAlreadyRegisteredException e){
			System.out.println("Customer registered already exists.");
		}
		assertTrue(list.equals("Restaurant [name=La Playa, username=LaPlayaBilbao, ID=264]Restaurant [name=TGF, username=TGFParis, ID=265]Restaurant [name=McDonals, username=mcdonalsmadrid, ID=266]"));
	}

	@Test
	public void testDisplayRestaurantInfo() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		list1.remove(0);
		list1.add(d1);
		HalfMeal m2 = new HalfMeal("Medio menu del día - postre", list1);
		r1.addSpecialMeal(m2);
		try{
			c.registerUser(r1);
			c.userLogIn(r1);
			//c.displayRestaurantInfo();
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " already registered.");
		}
		/*
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a restaurant.");
		}*/
	}

	@Test
	public void testSetSpecialMeal() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		cu1.setBeNotified(true);
		cu2.setBeNotified(false);
		cu3.setBeNotified(true);
		try{
			c.registerUser(r1);
			c.registerUser(cu1);
			c.registerUser(cu2);
			c.registerUser(cu3);
			c.registerObserver(cu1);
			c.registerObserver(cu2);
			c.registerObserver(cu3);
			c.userLogIn(r1);
			//c.setSpecialMeal(m1);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User already registered.");
		}
		/*
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a restaurant.");
		}*/
	}

	@Test
	public void testRemoveSpecialMeal() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		r1.addMeal(m1);
		try{
			c.registerUser(r1);
			c.userLogIn(r1);
			c.setSpecialMeal(m1);
			assertTrue(((Restaurant) c.getCurrentUser()).getListOfSpecialMeal().get(0).getName() == "Medio menu del día - entrante");
			c.removeSpecialMeal(m1);
			assertTrue(((Restaurant) c.getCurrentUser()).getListOfSpecialMeal().isEmpty());
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: -" + r1.getUsername() + "- already registered.");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a restaurant.");
		}
		
	}
	
	@Test
	public void addMeal()  throws AccessDeniedException{
		list1.add(s1);
		list1.add(md1);
		HalfMeal m1 = new HalfMeal("Medio menu del día - entrante", list1);
		try{
			c.registerUser(r1);
			c.userLogIn(r1);
			c.addMeal(m1);
			assertTrue(((Restaurant) c.getCurrentUser()).getListOfMeal().contains(m1));
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: -" + r1.getUsername() + "- already registered.");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a restaurant.");
		}
	}
	
	@Test
	public void addItem()  throws AccessDeniedException{
		try{
			c.registerUser(r1);
			c.userLogIn(r1);
			c.addItem(s1);
			c.addItem(d1);
			assertTrue(((Restaurant) c.getCurrentUser()).getMenu().getStarters().contains(s1));
			assertFalse(((Restaurant) c.getCurrentUser()).getMenu().getMainDishes().contains(s1));
			assertTrue(((Restaurant) c.getCurrentUser()).getMenu().getDesserts().contains(d1));
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User: -" + r1.getUsername() + "- already registered.");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " NOT a restaurant.");
		}
	}

	@Test
	public void testPlaceNewOrder() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		try{
			c.registerUser(cu1);
			c.userLogIn(cu1);
			c.placeNewOrder(o1);
			assertTrue(c.getListOfPendingOrders().get(0).getCustomer().getUsername() == "luiscobas" && c.getListOfPendingOrders().get(0).equals(o1));
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " is not a customer.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User -" + cu1.getUsername() + "- already registered.");
		}
	}

	@Test
	public void testRegisterFidelityCard() throws UsernameAlreadyRegisteredException, AccessDeniedException{
		FidelityCardPoint fc = new FidelityCardPoint();
		try{
			c.registerUser(cu1);
			c.userLogIn(cu1);
			c.registerFidelityCard(fc);
			assertTrue(((Customer) c.getCurrentUser()).getFidelityCard() instanceof FidelityCardPoint);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User -" + cu1.getUsername() + "- already registered.");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " is not a customer.");
		}
	}

	@Test
	public void testUnregisterFidelityCard() throws UsernameAlreadyRegisteredException, AccessDeniedException {
		try{
			cu1.setFidelityCardtoLottery();
			c.registerUser(cu1);
			c.userLogIn(cu1);
			assertTrue(((Customer) c.getCurrentUser()).getFidelityCard() instanceof FidelityCardLottery);
			c.unregisterFidelityCard();
			assertTrue(((Customer) c.getCurrentUser()).getFidelityCard() instanceof FidelityCardBasic);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User -" + cu1.getUsername() + "- already registered.");
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " is not a customer.");
		}
	}

	@Test
	public void testGetHistoryOrders() throws AccessDeniedException, UsernameAlreadyRegisteredException{
		
		Order o3 = new Order(new Customer("o3", "o3", "o3", a2, "o3", "o3", "o3"), new Restaurant("TGF", "TGFParis", "newpasswordr", a2));
		Order o2 = new Order(cu1, new Restaurant("TGF", "TGFParis", "newpasswordr", a2));
		Order o1 = new Order(cu1, new Restaurant("TGF", "TGFParis", "newpasswordr", a3));
		ArrayList<Order> completedOrders = new ArrayList<Order>();
		completedOrders.add(o3);
		completedOrders.add(o2);
		completedOrders.add(o1);
		
		c.setListOfCompletedOrders(completedOrders);
		
		try{
			c.registerUser(cu1);
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User -" + cu1.getUsername() + "- already registered.");
		}
		c.userLogIn(m);
		
		ArrayList<Order> historyOrders = new ArrayList<Order>();
		
		try {
			historyOrders = c.getHistoryOrders();
		} catch (AccessDeniedException e) {
			//System.out.println("getHistoryOrders correctly throws exception");
		} finally {
			c.userLogIn(cu1);
			historyOrders = c.getHistoryOrders();
			assertTrue(historyOrders.contains(o1));
			assertTrue(historyOrders.contains(o2));
			assertFalse(historyOrders.contains(o3));
		}
		
		
	}

	@Test
	public void testGiveRemoveConsensus() throws AccessDeniedException, UsernameAlreadyRegisteredException{
		try{
			c.registerUser(cu1);
			c.userLogIn(cu1);
			c.giveRemoveConsensus(true);
			assertTrue(((Customer) c.getCurrentUser()).isBeNotified() == true && c.getListOfToNotify().contains(cu1));
			c.giveRemoveConsensus(false);
			assertTrue(((Customer) c.getCurrentUser()).isBeNotified() == false && !c.getListOfToNotify().contains(cu1));
		}
		catch(AccessDeniedException e){
			System.out.println("User: " + c.getCurrentUser().getUsername() + " is not a customer.");
		}
		catch(UsernameAlreadyRegisteredException e){
			System.out.println("User -" + cu1.getUsername() + "- already registered.");
		}
		
	}

	@Test
	public void testUnregisterCourier() throws AccessDeniedException, UsernameAlreadyRegisteredException{
		try {
			c.registerUser(co1);
			assertTrue(c.getListOfUsers().get("lucho").getUsername() == "lucho");
			c.userLogIn(co1);
			c.unregisterCourier(co1);
		} catch(AccessDeniedException e) {
			System.out.println("unregisterCourier correctly throws exception");
		} catch(UsernameAlreadyRegisteredException e){
			System.out.println("User -" + co1.getUsername() + "- already registered.");
		} finally {	
			assertNull(c.getListOfUsers().get("lucho"));
		}
		
	}

	@Test
	public void testUpdateCourierState() throws AccessDeniedException{
		c.getListOfUsers().put(co1.getUsername(), co1);
		Order o = new Order(new Customer("J", "j", "C", a4, "j@gmail.com", "63", "pass"), new Restaurant("T", "T", "pass", a1));
		Order o1 = new Order(new Customer("Juan", "jcastillo33", "Castillo", a3, "jcastillo@gmail.com", "630285192", "newpassword"), new Restaurant("TGF", "TGFParis", "newpasswordr", a4));
		co1.setCurrentOrder(o);
		co1.setOnDuty(true);
		c.setCurrentUser(co1);
		c.updateCourierState();
		Courier tempC = (Courier)c.getListOfUsers().get(co1.getUsername());
			assertFalse(tempC.isOnDuty());
			assertEquals(tempC.getPosition(),a4);
		try {
			c.setCurrentUser(m);
			c.updateCourierState();
		} catch(AccessDeniedException e) {
			// System.out.println("updateCourierState correctly throws exception");
		} finally {
			LinkedList<Order> olist = new LinkedList<Order>();
			olist.addFirst(o1);
			co1.setListPendingOrders(olist);
			c.setCurrentUser(co1);
			c.updateCourierState();
			assertTrue(co1.getListPendingOrders().isEmpty());
			assertEquals(co1.getCurrentOrder().getID(),o1.getID());
		}
		
	}
 
	@Test
	public void testProcessOrders() {
		Courier c1 = new Courier("c","c","password1","c",new Address(1,3),"0754641222");
		Courier d1 = new Courier("d","d","password2","d",new Address(0,0),"0654641222");
		Courier e1 = new Courier("e","e","password3","e",new Address(3,4),"0664641222");
		
		Order o = new Order(new Customer("order", "order", "order", a1, "order", "order", "order"), new Restaurant("TGF", "TGFParis", "newpasswordr", a2));
		Order o1 = new Order(new Customer("o1", "o1", "o1", a3, "o1", "o1", "o1"), new Restaurant("TGF", "TGFParis", "newpasswordr", a3));
		Order o2 = new Order(new Customer("o2", "o2", "o2", a2, "o2", "o2", "o2"), new Restaurant("TGF", "TGFParis", "newpasswordr", a2));
		Order o3 = new Order(new Customer("o3", "o3", "o3", a2, "o3", "o3", "o3"), new Restaurant("TGF", "TGFParis", "newpasswordr", a2));
		
		LinkedList<Order> olist = new LinkedList<Order>();
		olist.add(o1); 
		olist.add(o2);
		olist.add(o3);
		olist.add(o);
		
		c.getListOfUsers().put(c1.getUsername(), d1);
		c.getListOfUsers().put(d1.getUsername(), c1);
		c.getListOfUsers().put(e1.getUsername(), e1);
		c.getListOfUsers().put(m.getUsername(), m);
		
		c.setListOfPendingOrders(olist);
		c.processOrders();
		
			assertTrue(c.getListOfPendingOrders().isEmpty());
			assertTrue(c.getListOfCompletedOrders().contains(o1));
			assertTrue(c.getListOfCompletedOrders().contains(o2));
			assertTrue(c.getListOfCompletedOrders().contains(o3));
			
		
		assertTrue(((Courier)c.getListOfUsers().get("c")).getCurrentOrder().getID() == 21);
		assertTrue(((Courier)c.getListOfUsers().get("d")).getCurrentOrder().getID() == 22);
		assertTrue(((Courier)c.getListOfUsers().get("e")).getCurrentOrder().getID() == 20);
		System.out.println(((Courier)c.getListOfUsers().get("e")).getCurrentOrder());
		Courier temp = (Courier)c.getListOfUsers().get("e");
		Courier temp1 = (Courier)c.getListOfUsers().get("c");
		assertTrue(temp.getListPendingOrders().getFirst().getID() == 19); // 90% of times contains order "o"
		assertTrue(temp1.getListPendingOrders().isEmpty()); // Should be empty
		
	}

}
