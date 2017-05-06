package system;

import java.io.Serializable;
import java.util.*;

import exceptions.AccessDeniedException;
import exceptions.UsernameAlreadyRegisteredException;
import users.*;
import users.Observer;
import restaurant_structure.*;

/**
 * The main class of the system and the one in charge of calling all other functions of the system as well as the users.
 * It is also the one that stores the information of the restaurants and users, and is in charge of processing the 
 * orders received from the customers.
 * @author Pedro León
 * @author Juan Castillo
 *
 */

public class Core implements Observable ,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5497781413672498696L;
	/* application attributes */
	final String name;
	final ArrayList<User> listOfMasterManager;
	public final static String creationDate = "2017/03/02";
	
	/* lists of system's users */
	private User currentUser;
	private HashMap<String,User> listOfUsers;
	
	/* list of user to be notified */
	private ArrayList<User> listOfToNotify;
	
	/* Policies strategies */
	private IDeliveryPolicy deliveryPolicy;
	private ITargetProfitPolicy tProfitPolicy;
	
	/* Orders */
	private ArrayList<Order> listOfCompletedOrders;
	private LinkedList<Order> listOfPendingOrders;
	
	/*
	 * profit-related information
	 */
	private double serviceFee;
	private double markupPercentage;
	private double deliveryCost;
	/**
	 * Constructor of the class Core
	 */
	public Core(){
		this.name = "MyFoodora";
		this.serviceFee = 3.0;
		this.markupPercentage = 0.1;
		this.deliveryCost = 2.0;
		
		listOfUsers = new HashMap<String,User>();
		
		listOfToNotify = new ArrayList<User>();
		
		deliveryPolicy = new DeliveryFastest();
		tProfitPolicy = new TargetProfitDeliveryCost();
		
		listOfCompletedOrders = new ArrayList<Order>();
		listOfPendingOrders = new LinkedList<Order>();
		
		/*
		 * initialization of listOfMasterManager adding both app creators
		 */
		this.listOfMasterManager = new ArrayList<User>();
		Manager jc = new Manager("Juan", "ceo", "123456789", "Castillo");
		listOfMasterManager.add(jc);
		Manager pl = new Manager("Pedro", "deputy", "987654321", "Leon");
		listOfMasterManager.add(pl);
		listOfUsers.put(jc.getUsername(), jc);
		listOfUsers.put(pl.getUsername(), pl);
	}
	
	/***************************************************************************************************/
	/**
	 * Registers a user to the system, verifying if the username is already used
	 * @param user to be registered
	 * @throws UsernameAlreadyRegisteredException
	 */
	public void registerUser(User user) throws UsernameAlreadyRegisteredException{
		if(currentUser == null){
			if(listOfUsers.containsKey(user.getUsername())){
				throw new UsernameAlreadyRegisteredException();
			}else{
				listOfUsers.put(user.getUsername(), user);
			}
		}else{
			System.out.println("Already logged in as user: -" + user.getUsername() + "-.");
		}
		
	}
	/**
	 * Logs in a user, setting the currentUser to this one and verifying is password is correct and the user is
	 * registered
	 * @param user that wants to log in
	 */
	public void userLogIn(User user){
		if(listOfUsers.containsKey(user.getUsername())){
			if(listOfUsers.get(user.getUsername()).getPassword().equals(user.getPassword())){
				currentUser = user;
			}else{
				System.out.println("When trying to log in user: -" + user.getUsername() + "- wrong password.");
			}
		}else{
			System.out.println("User " + user.getUsername() + " NOT registered in the system.");
		}
	}
	/**
	 * Sets the current user to null
	 */
	public void logOut(){
		currentUser = null;
	}
	
	/***************************************************************************************************/
	/*
	 * Manager methods
	 * (DONE) add/remove any kind of user & activate/deactivate any kind of user
	 * (DONE) changing the service fee/ markup-percentage / delivery cost
	 * (DONE) compute total income/profit over a time period
	 * (DONE) compute average income per customer
	 * (DONE) determining SF/MP/DC for target profit policy
	 * (DONE) determining most/least selling restaurant
	 * (DONE) determining the most/least active courier
	 * (DONE) setting delivery policy
	 */
	
	/* activate/deactivate & add/remove user */
	/**
	 * Adds a user to the system
	 * @param user
	 * @throws AccessDeniedException
	 */
	public void activateUser(User user) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			listOfUsers.put(user.getUsername(), user);
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Removes a user from the system
	 * @param user
	 * @throws AccessDeniedException
	 */
	public void deactivateUser(User user) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			listOfUsers.remove(user.getUsername(), user);
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Calls the function <code>activateUser</code> if the two exceptions do not occur
	 * @param user
	 * @throws AccessDeniedException
	 * @throws UsernameAlreadyRegisteredException
	 */
	public void addUser(User user) throws AccessDeniedException, UsernameAlreadyRegisteredException{
		if(currentUser instanceof Manager){
			if(listOfUsers.containsKey(user.getUsername())){
				throw new UsernameAlreadyRegisteredException();
			}else{
				activateUser(user);
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Calls the function <code>deactivateUser</code> if the exception do not occur
	 * @param user
	 * @throws AccessDeniedException
	 */
	public void removeUser(User user) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			if(listOfUsers.containsKey(user.getUsername())){
				deactivateUser(user);
			}else{
				System.out.println("User " + user.getUsername() + " already NOT in the system.");
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* changing SF &| MP &| DC */
	public void changeServiceFee(double newServiceFee) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			serviceFee = newServiceFee;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void changeMarkup(double newMarkupPercentage) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			markupPercentage = newMarkupPercentage;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void changeDeliveryCost(double newDeliveryCost) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			deliveryCost = newDeliveryCost;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void setTargetProfitToDeliveryCost() throws AccessDeniedException{
		if(currentUser instanceof Manager){
			tProfitPolicy = new TargetProfitDeliveryCost();
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void setTargetProfitToMarkup() throws AccessDeniedException{
		if(currentUser instanceof Manager){
			tProfitPolicy = new TargetProfitMarkup();
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void setTargetProfitToServiceFee() throws AccessDeniedException{
		if(currentUser instanceof Manager){
			tProfitPolicy = new TargetProfitServiceFee();
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * depending on the target profit strategy this method will return a different parameter to achieve a target profit
	 * for TargetProfitDeliveryCost behavior, delivery cost will be returned
	 * for TargetProfitMarkup behavior, markup percentage will be returned
	 * for TargetProfitServiceFee behavior, service fee will be returned
	 * 
	 * later the manager will be able to change this parameters of the core with changeDeliveryCost/Markup/ServiceFee
	 */
	public double getParameterToTargetProfit(double targetProfit, Calendar initDate, Calendar finDate) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			return tProfitPolicy.computeProfitStrategyBased(serviceFee, markupPercentage, deliveryCost, targetProfit, listOfCompletedOrders, initDate, finDate);
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* compute total income/profit */
	/**
	 * Computes total income between two dates
	 * @param initDate
	 * @param finDate
	 * @return the double number of the total income between the two parameter dates
	 * @throws AccessDeniedException
	 */
	public double computeTotalIncome(Calendar initDate, Calendar finDate) throws AccessDeniedException{
		double totalIncome = 0;
		if(currentUser instanceof Manager){
			for(Order o : listOfCompletedOrders){
				if(o.getDate().after(initDate) && o.getDate().before(finDate)){
					totalIncome += o.calcPrice() + serviceFee;
				}
			}
			if(totalIncome == 0){
				System.out.println("No order completed between initial date: " + initDate.getTime() + " and final date: " + finDate.getTime());
			}
			return totalIncome;
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Computes total profit between two dates
	 * @param initDate
	 * @param finDate
	 * @return the double number of the total profit between the two parameter dates
	 * @throws AccessDeniedException
	 */
	public double computeTotalProfit(Calendar initDate, Calendar finDate) throws AccessDeniedException{
		double totalProfit = 0;
		if(currentUser instanceof Manager){
			for(Order o : listOfCompletedOrders){
				if(o.getDate().after(initDate) && o.getDate().before(finDate)){
					totalProfit += o.calcPrice() * markupPercentage + serviceFee - deliveryCost;
				}
			}
			if(totalProfit == 0){
				System.out.println("No order completed between initial date: " + initDate.getTime() + " and final date: " + finDate.getTime());
			}
			return totalProfit;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/**
	 * Compute average income per customer between two dates
	 * @param initDate
	 * @param finDate
	 * @return the double number of the average income between the two parameter dates
	 * @throws AccessDeniedException
	 */
	public double computeAverageIncome(Calendar initDate, Calendar finDate) throws AccessDeniedException{
		double averageIncome = 0;
		int numOfOrders = 0;
		if(currentUser instanceof Manager){
			for(Order o : listOfCompletedOrders){
				if(o.getDate().after(initDate) && o.getDate().before(finDate)){
					averageIncome += o.calcPrice() * markupPercentage + serviceFee;
					numOfOrders += 1;
				}
			}
			if(averageIncome == 0){
				System.out.println("No order completed between initial date: " + initDate.getTime() + " and final date: " + finDate.getTime());
			}
			averageIncome /= numOfOrders;
			return averageIncome;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* most/least active restaurant/courier*/
	/**
	 * Determines the least active restaurant by number of completed orders
	 * @return the least active restaurant
	 * @throws AccessDeniedException
	 */
	public Restaurant leastActiveRestaurant() throws AccessDeniedException{
		Restaurant leastRestaurant = null;
		if(currentUser instanceof Manager){
			Collection<User> collectionOfUsers = listOfUsers.values();
			for(User user : collectionOfUsers){
				if(user instanceof Restaurant){
					if(leastRestaurant == null){
						leastRestaurant = (Restaurant) user;
					}else if(((Restaurant) user).getCountOfOrdersCompleted() < leastRestaurant.getCountOfOrdersCompleted()){
						leastRestaurant = (Restaurant) user;
					}
				}
			}
			return leastRestaurant;
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Determines the most active restaurant by number of completed orders
	 * @return the most active restaurant
	 * @throws AccessDeniedException
	 */
	public Restaurant mostActiveRestaurant() throws AccessDeniedException{
		Restaurant mostRestaurant = null;
		if(currentUser instanceof Manager){
			Collection<User> collectionOfUsers = listOfUsers.values();
			for(User user : collectionOfUsers){
				if(user instanceof Restaurant){
					if(mostRestaurant == null){
						mostRestaurant = (Restaurant) user;
					}else if(((Restaurant) user).getCountOfOrdersCompleted() > mostRestaurant.getCountOfOrdersCompleted()){
						mostRestaurant = (Restaurant) user;
					}
				}
			}
			return mostRestaurant;
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Determines the least active courier by number of completed orders
	 * @return the least active courier
	 * @throws AccessDeniedException
	 */
	public Courier leastActiveCourier() throws AccessDeniedException{
		Courier leastCourier = null;
		if(currentUser instanceof Manager){
			Collection<User> collectionOfUsers = listOfUsers.values();
			for(User user : collectionOfUsers){
				if(user instanceof Courier){
					if(leastCourier == null){
						leastCourier = (Courier) user;
					}else if(((Courier) user).getCountOfOrdersCompleted() < leastCourier.getCountOfOrdersCompleted()){
						leastCourier = (Courier) user;
					}
				}
			}
			return leastCourier;
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Determines the most active courier by number of completed orders
	 * @return the most active courier
	 * @throws AccessDeniedException
	 */
	public Courier mostActiveCourier() throws AccessDeniedException{
		Courier mostCourier = null;
		if(currentUser instanceof Manager){
			Collection<User> collectionOfUsers = listOfUsers.values();
			for(User user : collectionOfUsers){
				if(user instanceof Courier){
					if(mostCourier == null){
						mostCourier = (Courier) user;
					}else if(((Courier) user).getCountOfOrdersCompleted() > mostCourier.getCountOfOrdersCompleted()){
						mostCourier = (Courier) user;
					}
				}
			}
			return mostCourier;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* setting delivery policy */
	public void setDeliveryToFastest() throws AccessDeniedException{
		if(currentUser instanceof Manager){
			deliveryPolicy = new DeliveryFastest();
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void setDeliveryToFairOccupation() throws AccessDeniedException{
		if(currentUser instanceof Manager){
			deliveryPolicy = new DeliveryFairOccupation();
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public String showCustomersRegistered() throws AccessDeniedException{
		String list = "";
		if(currentUser instanceof Manager){
			for(User u : getListOfUsers().values()){
				if(u instanceof Customer){
					list += ((Customer)u).toString();
				}
			}
			return list;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public String showRestaurantsRegistered() throws AccessDeniedException{
		String list = "";
		if(currentUser instanceof Manager){
			for(User u : getListOfUsers().values()){
				if(u instanceof Restaurant){
					list += "\n" + ((Restaurant)u).toString();
				}
			}
			return list;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public String showCouriersRegistered() throws AccessDeniedException{
		String list = "";
		if(currentUser instanceof Manager){
			for(User u : getListOfUsers().values()){
				if(u instanceof Courier){
					list += "\n" + ((Courier)u).toString();
				}
			}
			return list;
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/***************************************************************************************************/
	/**
	 * notify special offers related
	 * observer pattern
	 */
	/* (non-Javadoc)
	 * @see system.Observable#registerObserver(users.Observer)
	 */
	@Override
	public void registerObserver(Observer observer) {
		Customer c = (Customer) observer;
		listOfToNotify.add(c);
	}

	/* (non-Javadoc)
	 * @see system.Observable#removeObserver(users.Observer)
	 */
	@Override
	public void removeObserver(Observer observer) {
		Customer c = (Customer) observer;
		listOfToNotify.remove(c);
	}

	/* (non-Javadoc)
	 * @see system.Observable#notifyObservers(users.Observer)
	 */
	@Override
	public void notifyObservers(Restaurant r, Meal m) {
		for(User u : listOfToNotify){
			Customer c = (Customer) u;
			c.update( r, m);
		}
	}
	
	/***************************************************************************************************/
	/* Restaurant related
	 * (DONE) see restaurant class
	 * (DONE) set/remove special meal in the menu
	 * (DONE) add meal/item
	 */
	/**
	 * Displays all the information of the restaurant
	 * @throws AccessDeniedException
	 */
	public void displayRestaurantInfo() throws AccessDeniedException{
		if(currentUser instanceof Restaurant){
			System.out.println("Restaurant information display: ");
			System.out.println("\tName: " + currentUser.getName());
			System.out.println("\tUsername: " + currentUser.getUsername());
			System.out.println("\tPassword: " + currentUser.getPassword());
			System.out.println("\tID: " + currentUser.getID());
			System.out.println("\tAddress: " + ((Restaurant) currentUser).getAddress());
			System.out.println("Meal list display:");
			for(Meal m : ((Restaurant) currentUser).getListOfMeal()){
				System.out.println("\t" + m);
			}
			System.out.println("Special meal list display:");
			for(Meal sm : ((Restaurant) currentUser).getListOfSpecialMeal()){
				System.out.println("\t" + sm);
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Call notify observers to send them the new special offer included in 'meal'
	 * @param meal
	 * @throws AccessDeniedException
	 */
	public void setSpecialMeal(Meal meal)  throws AccessDeniedException{
		if(currentUser instanceof Restaurant){
			if(((Restaurant) currentUser).getListOfMeal().contains(meal)){
				((Restaurant) currentUser).getListOfMeal().remove(meal);
				((Restaurant) currentUser).getListOfSpecialMeal().add(meal);
				notifyObservers((Restaurant) currentUser, meal);
			}else if(((Restaurant) currentUser).getListOfSpecialMeal().contains(meal)){
				System.out.println("Meal " + meal.getName() + " already an special meal");
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void removeSpecialMeal(Meal meal)  throws AccessDeniedException{
		if(currentUser instanceof Restaurant){
			if(((Restaurant) currentUser).getListOfSpecialMeal().contains(meal)){
				((Restaurant) currentUser).getListOfSpecialMeal().remove(meal);
				((Restaurant) currentUser).getListOfMeal().add(meal);
			}else if(((Restaurant) currentUser).getListOfMeal().contains(meal)){
				System.out.println("Meal " + meal.getName() + " already NOT an special meal.");
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void addMeal(Meal meal)  throws AccessDeniedException{
		if(currentUser instanceof Restaurant){
			if(((Restaurant) currentUser).getListOfMeal().contains(meal)){
				System.out.println("Meal " + meal.getName() + " already added.");
			}else {
				((Restaurant) currentUser).getListOfMeal().add(meal);
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void addItem(Item item)  throws AccessDeniedException{
		if(currentUser instanceof Restaurant){
			if(((Restaurant) currentUser).getMenu().getStarters().contains(item) || ((Restaurant) currentUser).getMenu().getMainDishes().contains(item) || ((Restaurant) currentUser).getMenu().getDesserts().contains(item)){
				System.out.println("Item " + item.getName() + " already added.");
			}else {
				if(item instanceof Starter){
					((Restaurant) currentUser).addStarter((Starter)item);
				}else if(item instanceof MainDish){
					((Restaurant) currentUser).addMainDish((MainDish)item);
				}else if(item instanceof Dessert){
					((Restaurant) currentUser).addDessert((Dessert)item);
				}
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/***************************************************************************************************/
	/* Customers related
	 * (DONE) place orders
	 * - add items to order
	 * (DONE) register/unregister to/from a fidelity card plan
	 * (DONE) access to information related to their account
	 * (DONE) give/remove consensus to be notified whenever a new special offer
	 */
	/**
	 * Allows a customer to place a new order
	 * @param order
	 * @throws AccessDeniedException
	 */
	public void placeNewOrder(Order order) throws AccessDeniedException{
		if(currentUser instanceof Customer){
			listOfPendingOrders.add(order);
		} else {
			throw new AccessDeniedException();
		}
	}
	/**
	 * Allows a customer to change of fidelity plan
	 * @param fidelityCard
	 * @throws AccessDeniedException
	 */
	public void registerFidelityCard(FidelityCard fidelityCard) throws AccessDeniedException{
		if(currentUser instanceof Customer){
			((Customer) currentUser).setFidelityCard(fidelityCard);
		} else {
			throw new AccessDeniedException();
		}
	}
	/**
	 * Resets the fidelity card of the current customer to Basic
	 * @throws AccessDeniedException
	 */
	public void unregisterFidelityCard() throws AccessDeniedException{
		if(currentUser instanceof Customer){
			((Customer) currentUser).setFidelityCardtoBasic();
		} else {
			throw new AccessDeniedException();
		}
	}
	/**
	 * Creates a list of all orders placed by the current customer
	 * @return a list of all orders placed by the current customer
	 * @throws AccessDeniedException
	 */
	public ArrayList<Order> getHistoryOrders() throws AccessDeniedException{
		if(currentUser instanceof Customer){
			ArrayList<Order> customerOrders = new ArrayList<Order>();
			for(Order o : listOfCompletedOrders){
				if(currentUser.getID() == o.getCustomer().getID()){
					customerOrders.add(o);
				}
			}
			return customerOrders;
		} else {
			throw new AccessDeniedException();
		}
	}
	/*
	 * boolean b
	 * -b = true gives consensus
	 * -b = false removes consensus
	 */
	/**
	 * Allows the customer to set whether or not to receive notifications from the system 
	 * @param b: if TRUE gives consensus, if FALSE removes consensus
	 * @throws AccessDeniedException
	 */
	public void giveRemoveConsensus(boolean b) throws AccessDeniedException{
		if(currentUser instanceof Customer){
			((Customer) currentUser).changeBeNotified(b);
			listOfUsers.put(currentUser.getUsername(), currentUser);
			if(b){
				registerObserver((Customer) currentUser);
			}else{
				removeObserver((Customer) currentUser);
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/***************************************************************************************************/
	/* Couriers related
	 * (DONE) register/unregister their account to the MyFoodora system
	 * (DONE) state their state as on-duty or off-duty
	 * (DONE) accept/refuse to a delivery call
	 */
	
	/* Register/Unregister method, for register use registerUser method*/
	/**
	 * Removes a courier from the system
	 * @param user
	 * @throws AccessDeniedException
	 */
	public void unregisterCourier(User user) throws AccessDeniedException{
		if(user instanceof Courier){
			Courier courier = (Courier) user;
			listOfUsers.remove(courier.getUsername(),courier);
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* Off-duty update (current delivered): we consider that the courier puts him as OffDuty once 
	 * he has delivered his currentOrder.
	 * The courier automatically is set OnDuty when accepting an order (courier.acceptOrder) */
	/**
	 * Off-duty update (current delivered): we consider that the courier puts him as OffDuty once 
	 * he has delivered his currentOrder.
	 * The courier automatically is set OnDuty when accepting an order (courier.acceptOrder) so no need
	 * to implement this functionality
	 * @throws AccessDeniedException
	 */
	public void updateCourierState() throws AccessDeniedException{
		if(currentUser instanceof Courier){
			Courier courier = (Courier) currentUser;
			courier.setPosition(courier.getCurrentOrder().getCustomer().getAddress());
			if(!courier.getListPendingOrders().isEmpty()){
				// Courier still has pending orders
				courier.acceptOrder(courier.getListPendingOrders().removeFirst());
				Restaurant restaurant = courier.getCurrentOrder().getRestaurant();
				restaurant.setCountOfOrdersCompleted(restaurant.getCountOfOrdersCompleted() + 1);
				listOfUsers.put(restaurant.getUsername(), restaurant);
			} else {
				courier.setOnDuty(false);
			}
			currentUser = courier;
			listOfUsers.put(courier.getUsername(), courier); //Updates courier info in the listOfUsers
		}else{
			throw new AccessDeniedException();
		}
	}
	/**
	 * Main function of the core. It distributes all current orders to the couriers, based on the current delivery policy.
	 * Steps:
	 * 1) Creates two main lists : currentSortedCouriers (all couriers sorted with the current delivery policy) and
	 * 							currentCouriersOnDuty (all couriers that are currently treating an order)
	 * 2) Enters a while loop that goes through all orders and finish once all orders are treated
	 * Inside the main while loop there are two other while loops:
	 * 3) First one goes through all available couriers and ask them whether they accept the order or not
	 * 4) The second loop happens only when no available courier accepts the order and tries to assign the order to the
	 * 		list of pending orders of the currently on-duty couriers (that had not previously declined the order), giving
	 * 		 priority to the couriers that will finish their current order before 
	 * 5) If the order is still not assigned it is automatically removed and the customer is informed of the issue
	 * 
	 */
	public void processOrders(){
		Courier courier = null;
		boolean orderAssigned = false;
		//Get all the users and put all Couriers to a LinkedList
		LinkedList<User> currentUsers = new LinkedList<User>(listOfUsers.values());
		LinkedList<Courier> listCouriers = new LinkedList<Courier>();
		for (User u : currentUsers){
			if(u instanceof Courier){
				listCouriers.add((Courier)u);
			}
		}
		//List where all users on duty will be added
		LinkedList<Courier> currentCouriersOnDuty = new LinkedList<Courier>();
		while(listOfPendingOrders.size()!=0){
			orderAssigned = false;
			//Gets the first order of the list and sorts all couriers depending on the delivery policy
			Order order = listOfPendingOrders.getFirst();
			LinkedList<Courier> currentSortedCouriers = this.deliveryPolicy.setDeliveryPolicy(listCouriers, order.getRestaurant().getAddress());
			//This while loop tries to address a Courier to an order until 
			//it does it or no Courier is available or wants to take the order
			while(!order.isAssignedCourier() && !currentSortedCouriers.isEmpty()){
				courier = currentSortedCouriers.getFirst();
				if(!courier.isOnDuty()){
					
					if(courier.decideTakingOrder(order)){
						listOfCompletedOrders.add(courier.getCurrentOrder());
						currentCouriersOnDuty.add(currentSortedCouriers.removeFirst());
						orderAssigned = true;
					} else {
						currentSortedCouriers.removeFirst();
						listOfPendingOrders.add(listOfPendingOrders.getFirst());
					}
					listOfUsers.put(courier.getUsername(), courier);
				} else {
					currentCouriersOnDuty.add(currentSortedCouriers.removeFirst());
				}
			}
			//This while loop tries to add the order to the listPendingOrders of a Courier until
			//it does it or no Courier wants to add the order
			while(currentSortedCouriers.isEmpty() && !currentCouriersOnDuty.isEmpty() && !order.isAssignedCourier()){
				Collections.sort(currentCouriersOnDuty,Courier.compareDeliveryDate());
				if(currentCouriersOnDuty.getFirst().decideAddOrder(order)){
					this.listOfCompletedOrders.add(order);
					orderAssigned = true;
				}
				listOfUsers.put(currentCouriersOnDuty.getFirst().getUsername(), currentCouriersOnDuty.getFirst());
				currentCouriersOnDuty.removeFirst();
			}
			this.listOfPendingOrders.removeFirst();
			/* NOTIFICATION TO CUSTOMER */
			if(!orderAssigned){
				System.out.println("The order with ID "+ order.getID() + " could not be addressed to a courier and has been removed from the system.");
				System.out.println("Please order again in a few minutes!");
			}
		}
	}
	
	/***************************************************************************************************/
	/*
	 * Getters and Setters: no setters for ID and Counter
	 */
	
	
	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * @return the listOfUsers
	 */
	public HashMap<String, User> getListOfUsers() {
		return listOfUsers;
	}

	/**
	 * @param listOfUsers the listOfUsers to set
	 */
	public void setListOfUsers(HashMap<String, User> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	/**
	 * @return the listOfToNotify
	 */
	public ArrayList<User> getListOfToNotify() {
		return listOfToNotify;
	}

	/**
	 * @param listOfToNotify the listOfToNotify to set
	 */
	public void setListOfToNotify(ArrayList<User> listOfToNotify) {
		this.listOfToNotify = listOfToNotify;
	}

	/**
	 * @return the deliveryPolicy
	 */
	public IDeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	/**
	 * @param deliveryPolicy the deliveryPolicy to set
	 */
	public void setDeliveryPolicy(IDeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}

	/**
	 * @return the tProfitPolicy
	 */
	public ITargetProfitPolicy gettProfitPolicy() {
		return tProfitPolicy;
	}

	/**
	 * @param tProfitPolicy the tProfitPolicy to set
	 */
	public void settProfitPolicy(ITargetProfitPolicy tProfitPolicy) {
		this.tProfitPolicy = tProfitPolicy;
	}

	/**
	 * @return the listOfCompletedOrders
	 */
	public ArrayList<Order> getListOfCompletedOrders() {
		return listOfCompletedOrders;
	}

	/**
	 * @param listOfCompletedOrders the listOfCompletedOrders to set
	 */
	public void setListOfCompletedOrders(ArrayList<Order> listOfCompletedOrders) {
		this.listOfCompletedOrders = listOfCompletedOrders;
	}

	/**
	 * @return the listOfPendingOrders
	 */
	public LinkedList<Order> getListOfPendingOrders() {
		return listOfPendingOrders;
	}

	/**
	 * @param listOfPendingOrders the listOfPendingOrders to set
	 */
	public void setListOfPendingOrders(LinkedList<Order> listOfPendingOrders) {
		this.listOfPendingOrders = listOfPendingOrders;
	}

	/**
	 * @return the serviceFee
	 */
	public double getServiceFee() {
		return serviceFee;
	}

	/**
	 * @param serviceFee the serviceFee to set
	 */
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * @return the markupPercentage
	 */
	public double getMarkupPercentage() {
		return markupPercentage;
	}

	/**
	 * @param markupPercentage the markupPercentage to set
	 */
	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}

	/**
	 * @return the deliveryCost
	 */
	public double getDeliveryCost() {
		return deliveryCost;
	}

	/**
	 * @param deliveryCost the deliveryCost to set
	 */
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the listOfMasterManager
	 */
	public ArrayList<User> getListOfMasterManager() {
		return listOfMasterManager;
	}
	
	

}
