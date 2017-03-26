package system;

import java.util.*;
import users.*;
import restaurant_structure.*;

/**
 * 
 * @author Pedro Le�n
 *
 */

public class Core {
	
	/* lists of system's users */
	private User currentUser;
	private HashMap<String,User> listOfUsers;
	
	/* Policies strategies */
	private IDeliveryPolicy deliveryPolicy;
	private ITargetProfitPolicy targetProfit;
	
	/* Orders */
	private List<Order> listOfCompletedOrders;
	
	/*
	 * profit-related information
	 */
	private double serviceFee;
	private double markupPercentage;
	private double deliveryCost;
	
	public Core(double serviceFee, double markupPercentage, double deliveryCost){
		this.serviceFee = serviceFee;
		this.markupPercentage = markupPercentage;
		this.deliveryCost = deliveryCost;
	}
	
	/***************************************************************************************************/
	/**
	 * generic user's methods
	 */
	public void registerUser(User user){
		if(currentUser == null){
			if(listOfUsers.containsKey(user.getUsername())){
				//throw exception
			}else{
				listOfUsers.put(user.getUsername(), user);
			}
		}else{
			//throw exception if currentUser != null
		}
		
	}
	
	public String userLogIn(User user){
		if(listOfUsers.containsKey(user.getUsername())){
			if(listOfUsers.get(user.getUsername()).getPassword().equals(user.getPassword())){
				currentUser = user;
			}else{
				//throw exception
			}
		}else{
			//throw exception
		}
		return "User not registered!";
	}
	
	public void logOut(){
		currentUser = null;
	}
	
	/***************************************************************************************************/
	/**
	 * delivery policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/***************************************************************************************************/
	/**
	 * shipped order sorting policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/***************************************************************************************************/
	/**
	 * special orders notifications related
	 * observer pattern
	 */
	
	/***************************************************************************************************/
	/**
	 * pricing and fidelity related
	 */
	// TO DO
	
	/***************************************************************************************************/
	/**
	 * Manager methods
	 * - (DONE) add/remove any kind of user & activate/deactivate any kind of user
	 * - (DONE) changing the service fee/ markup-percentage / delivery cost
	 * -compute total income/profit over a time period
	 * -compute average income per customer
	 * -determining SF/MP/DC for target profit policy
	 * -determining most/least selling restaurant
	 * -determining the most/least active courier
	 * -setting delivery policy
	 */
	
	/* activate/deactivate & add/remove user */
	public void activateUser(User user){
		if(currentUser instanceof Manager){
			listOfUsers.put(user.getUsername(), user);
		}else{
			//throw exception
		}
	}
	
	public void deactivateUser(User user){
		if(currentUser instanceof Manager){
			listOfUsers.remove(user.getUsername(), user);
		}else{
			//throw exception
		}
	}
	
	public void addUser(User user){
		if(currentUser instanceof Manager){
			if(listOfUsers.containsKey(user.getUsername())){
				//throw exception
			}else{
				activateUser(user);
			}
		}else{
			//throw exception
		}
	}
	
	public void removeUser(User user){
		if(currentUser instanceof Manager){
			if(listOfUsers.containsKey(user.getUsername())){
				deactivateUser(user);
			}else{
				//throw exception
			}
		}else{
			//throw exception
		}
	}
	
	/* changing SF &| MP &| DC */
	public void changeServiceFee(double newServiceFee){
		if(currentUser instanceof Manager){
			serviceFee = newServiceFee;
		}else{
			//throw exception
		}
	}
	
	public void changeMatkupPercentage(double newMarkupPercentage){
		if(currentUser instanceof Manager){
			markupPercentage = newMarkupPercentage;
		}else{
			//throw exception
		}
	}
	
	public void changeDeliveryCost(double newDeliveryCost){
		if(currentUser instanceof Manager){
			deliveryCost = newDeliveryCost;
		}else{
			//throw exception
		}
	}
	
	/* compute total income/profit */
	public double computeTotalIncome(Calendar initDate, Calendar finDate){
		double income = 0;
		if(currentUser instanceof Manager){
			for(Order o : listOfCompletedOrders){
				if(o.getDate().after(initDate) && o.getDate().before(finDate)){
					income += o.calcPrice() * markupPercentage;
				}
			}
		}
		if(income == 0){
			//throw exception no order between dates
		}
		return income;
	}
	
	public double computeTotalProfit(Calendar initDate, Calendar finDate){
		double profit = 0;
		for(Order o : listOfCompletedOrders){
			if(o.getDate().after(initDate) && o.getDate().before(finDate)){
				profit += targetProfit.computeProfitStrategyBased(o.calcPrice(), markupPercentage, serviceFee, deliveryCost);
			}
		}
		if(profit == 0){
			//throw exception no order between dates
		}
		return profit;
	}
	
	/* most/least active restaurant/courier*/
	public Restaurant LeastActiveRestaurant(){
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
			return null;
		}
	}
	public Restaurant MostActiveRestaurant(){
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
			return null;
		}
	}
	
	public Courier LeastActiveCourier(){
		Courier leastCourier = null;
		if(currentUser instanceof Manager){
			Collection<User> collectionOfUsers = listOfUsers.values();
			for(User user : collectionOfUsers){
				if(user instanceof Courier){
					if(leastCourier == null){
						leastCourier = (Courier) user;
					}else if(((Restaurant) user).getCountOfOrdersCompleted() < leastCourier.getCountOfOrdersCompleted()){
						leastCourier = (Courier) user;
					}
				}
			}
			return leastCourier;
		}else{
			return null;
		}
	}
	public Courier MostActiveCourier(){
		Courier mostCourier = null;
		if(currentUser instanceof Manager){
			Collection<User> collectionOfUsers = listOfUsers.values();
			for(User user : collectionOfUsers){
				if(user instanceof Restaurant){
					if(mostCourier == null){
						mostCourier = (Courier) user;
					}else if(((Restaurant) user).getCountOfOrdersCompleted() > mostCourier.getCountOfOrdersCompleted()){
						mostCourier = (Courier) user;
					}
				}
			}
			return mostCourier;
		}else{
			return null;
		}
	}
	
	/***************************************************************************************************/
	/** Restaurant related
	 * (DONE) see restaurant class
	 * (DONE) set/remove special meal in the menu
	 * -sorting of shipped orders with respect to different criteria
	 */
	
	public void displayRestaurantInfo(){
		if(currentUser instanceof Restaurant){
			System.out.println("Restaurant information display:");
			currentUser.toString();
			System.out.println("Meal list display:");
			for(Meal m : ((Restaurant) currentUser).getListOfMeal()){
				m.toString();
			}
			System.out.println("Special meal list display:");
			for(Meal dm : ((Restaurant) currentUser).getListOfDiscountMeal()){
				dm.toString();
			}
		}else{
			// throw exception
		}
	}
	
	public void setSpecialMeal(Meal meal){
		if(currentUser instanceof Restaurant){
			if(((Restaurant) currentUser).getListOfMeal().contains(meal)){
				((Restaurant) currentUser).getListOfMeal().remove(meal);
				((Restaurant) currentUser).getListOfDiscountMeal().add(meal);
			}else if(((Restaurant) currentUser).getListOfDiscountMeal().contains(meal)){
				//throw exception meal already an special meal
			}else{
				//throw exception meal not in the menu
			}
		}
	}
	
	public void removeSpecialMeal(Meal meal){
		if(currentUser instanceof Restaurant){
			if(((Restaurant) currentUser).getListOfMeal().contains(meal)){
				((Restaurant) currentUser).getListOfDiscountMeal().remove(meal);
				((Restaurant) currentUser).getListOfMeal().add(meal);
			}else if(((Restaurant) currentUser).getListOfMeal().contains(meal)){
				//throw exception meal already not a special meal
			}else{
				//throw exception meal not in the menu
			}
		}
	}
	
	/***************************************************************************************************/
	/** Customers related
	 * -place orders
	 * -register/unregister to/from a fidelity card plan
	 * -access to information related to their account
	 * -give/remove consensus to be notified whenever a new special offer
	 */
	// TO DO
	
	/***************************************************************************************************/
	/** Couriers related
	 * (DONE) register/unregister their account to the MyFoodora system
	 * (DONE) state their state as on-duty or off-duty
	 * (DONE) change their position
	 * -accept/refuse to a delivery call
	 */
	
	/* Register/unregister method, for register use registerUser method*/
	public void unregisterCourier(User user){
		System.out.println("Unregister demand");
		if(user instanceof Courier){
			listOfUsers.remove(user);
			System.out.println("Courier:" + user + "unregistered.");
		}else{
			//throw exception not a courier
		}
	}
	
	/* on-duty/off-duty update*/
	public void updateCourierState(User user){
		if(user instanceof Courier){
			((Courier)user).setOnDuty(true);
		}else{
			//throw exception not a courier
		}
	}
	
	/* updating courier position*/
	public void updateCourierPosition(User user, Address newPosition){
		if(user instanceof Courier){
			((Courier)user).setPosition(newPosition);
		}else{
			//throw exception not a courier
		}
	}

}
