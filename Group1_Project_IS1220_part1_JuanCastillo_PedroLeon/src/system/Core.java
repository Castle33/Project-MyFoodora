package system;

import java.util.*;
import users.*;

/**
 * 
 * @author Pedro León
 *
 */

public class Core {
	
	/* lists of system's users */
	private User currentUser;
	private HashMap<String,User> listOfUsers;
	
	/* Policies strategies */
	private IDeliveryPolicy deliveryPolicy;
	private ITargetProfitPolicy targetProfit;
	
	/*
	 * history of completed orders
	 */
	//TO DO
	
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
	 * target profit policies related
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
	 * computing the total income and profit
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
	 * 
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
	
	/***************************************************************************************************/
	/** Restaurant related
	 * see restaurant class
	 * -sorting of shipped orders with respect to different criteria
	 */
	// TO DO
	
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
	 * -register/unregister their account to the MyFoodora system
	 * -state their state as on-duty or off-duty
	 * -change their position
	 * -accept/refuse to a delivery call
	 */
	// TO DO

}
