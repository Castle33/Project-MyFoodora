package system;

import java.util.*;

import exceptions.AccessDeniedException;
import exceptions.UsernameAlreadyRegisteredException;
import users.*;
import users.Observer;
import restaurant_structure.*;

/**
 * 
 * @author Pedro León
 *
 */

public class Core implements Observable {
	
	/* application attributes */
	final String name;
	final ArrayList<User> listOfMasterManager;
	
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
		Manager jc = new Manager("Juan", "jcastillo33", "pjausasnword", "Castillo");
		listOfMasterManager.add(jc);
		Manager pl = new Manager("Pedro", "pleonpita", "ppaesdsrwoord", "Leon");
		listOfMasterManager.add(pl);
		listOfUsers.put(jc.getUsername(), jc);
		listOfUsers.put(pl.getUsername(), pl);
	}
	
	/***************************************************************************************************/
	/**
	 * generic user's methods
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
	
	public void logOut(){
		currentUser = null;
	}
	
	/***************************************************************************************************/
	/**
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
	public void activateUser(User user) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			listOfUsers.put(user.getUsername(), user);
		}else{
			throw new AccessDeniedException();
		}
	}

	public void deactivateUser(User user) throws AccessDeniedException{
		if(currentUser instanceof Manager){
			listOfUsers.remove(user.getUsername(), user);
		}else{
			throw new AccessDeniedException();
		}
	}
	
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
	
	public double getParameterToTargetProfit(double targetProfit, Calendar initDate, Calendar finDate) throws AccessDeniedException{
		/*
		 * depending on the target profit strategy this method will return a different parameter to achieve a target profit
		 * for TargetProfitDeliveryCost behavior, delivery cost will be returned
		 * for TargetProfitMarkup behavior, markup percentage will be returned
		 * for TargetProfitServiceFee behavior, service fee will be returned
		 * 
		 * later the manager will be able to change this parameters of the core with changeDeliveryCost/Markup/ServiceFee
		 */
		if(currentUser instanceof Manager){
			return tProfitPolicy.computeProfitStrategyBased(serviceFee, markupPercentage, deliveryCost, targetProfit, listOfCompletedOrders, initDate, finDate);
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* compute total income/profit */
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
	
	/* compute average income per customer */
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
	/** Restaurant related
	 * (DONE) see restaurant class
	 * (DONE) set/remove special meal in the menu
	 * -sorting of shipped orders with respect to different criteria
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
	/*
	 * call notify observers to send them the new special offer included in 'meal'
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
				System.out.println("Meal " + meal.getName() + " already NOT an special meal");
			}
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/***************************************************************************************************/
	/** Customers related
	 * (DONE) place orders
	 * (DONE) register/unregister to/from a fidelity card plan
	 * (DONE) access to information related to their account
	 * (DONE) give/remove consensus to be notified whenever a new special offer
	 */
	
	public void placeNewOrder(Restaurant restaurant) throws AccessDeniedException{
		if(currentUser instanceof Customer){
			listOfPendingOrders.add(new Order((Customer)currentUser,restaurant));
		} else {
			throw new AccessDeniedException();
		}
	}
	
	public void registerFidelityCard(FidelityCard fidelityCard) throws AccessDeniedException{
		if(currentUser instanceof Customer){
			((Customer) currentUser).setFidelityCard(fidelityCard);
		} else {
			throw new AccessDeniedException();
		}
	}
	
	public void unregisterFidelityCard() throws AccessDeniedException{
		if(currentUser instanceof Customer){
			((Customer) currentUser).setFidelityCardtoBasic();
		} else {
			throw new AccessDeniedException();
		}
	}
	
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
	/** Couriers related
	 * (DONE) register/unregister their account to the MyFoodora system
	 * (DONE) state their state as on-duty or off-duty
	 * (DONE) change their position
	 * -accept/refuse to a delivery call
	 */
	
	/* Register/unregister method, for register use registerUser method*/
	public void unregisterCourier(User user) throws AccessDeniedException{
		System.out.println("Unregister demand");
		if(user instanceof Courier){
			listOfUsers.remove(user);
			System.out.println("Courier:" + user + "unregistered.");
		}else{
			throw new AccessDeniedException();
		}
	}
	
	/* off-duty update (current delivered): we consider that the courier puts him as OffDuty once 
	 * he has delivered his currentOrder.
	 * The courier automatically is set OnDuty when accepting an order (courier.acceptOrder) */
	public void updateCourierState(User user) throws AccessDeniedException{
		if(user instanceof Courier){
			Courier courier = (Courier) user;
			courier.setPosition(courier.getCurrentOrder().getCustomer().getAddress());
			if(!courier.getListPendingOrders().isEmpty()){
				// Courier still has pending orders
				courier.acceptOrder(courier.getListPendingOrders().removeFirst());
			} else {
				courier.setOnDuty(false);
			}
			listOfUsers.put(courier.getUsername(), courier); //Updates courier info in the listOfUsers
		}else{
			throw new AccessDeniedException();
		}
	}
	
	public void processOrders(){
		Courier courier = null;
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
			//Gets the first order of the list and sorts all couriers depending on the delivery policy
			Order order = this.listOfPendingOrders.getFirst();
			LinkedList<Courier> currentSortedCouriers = this.deliveryPolicy.setDeliveryPolicy(listCouriers, order.getRestaurant().getAddress());
			//This while loop tries to address a Courier to an order until 
			//it does it or no Courier is available or wants to take the order
			while(!order.isAssignedCourier() && !currentSortedCouriers.isEmpty()){
				courier = currentSortedCouriers.getFirst();
				if(!courier.isOnDuty()){
					if(courier.decideTakingOrder(order)){
						this.listOfCompletedOrders.add(courier.getCurrentOrder());
						this.listOfPendingOrders.removeFirst();
						currentCouriersOnDuty.add(currentSortedCouriers.removeFirst());
					} else {
						currentSortedCouriers.removeFirst();
						this.listOfPendingOrders.add(this.listOfPendingOrders.removeFirst());
					}
				} else {
					currentCouriersOnDuty.add(currentSortedCouriers.removeFirst());
				}
			}
			//This while loop tries to add the order to the listPendingOrders of a Courier until
			//it does it or no Courier wants to add the order
			while(currentSortedCouriers.isEmpty() && !currentCouriersOnDuty.isEmpty() && !order.isAssignedCourier()){
				Collections.sort(currentCouriersOnDuty,Courier.compareDeliveryDate());
				if(currentCouriersOnDuty.getFirst().decideAddOrder(order)){
					this.listOfCompletedOrders.add(currentCouriersOnDuty.removeFirst().getCurrentOrder());
					this.listOfPendingOrders.removeFirst();
				}
			}
			this.listOfPendingOrders.removeFirst();
			/*
			 * NOTIFICATION TO CUSTOMER: NOT YET IMPLEMENTED
			 */
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
