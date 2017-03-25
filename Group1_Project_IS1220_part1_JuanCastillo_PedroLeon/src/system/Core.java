package system;

import java.util.List;
import users.*;

/**
 * 
 * @author Pedro León
 *
 */

public class Core {
	
	/*
	 * lists of system's users
	 */
	List<Manager> listOfManager;
	List<Restaurant> listOfRestaurant;
	List<Customers> listOfCustomers;
	List<Courier> listOfCourier;
	
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
	/*
	 * delivery policies related
	 * behavioral pattern
	 */
	private String deliveryPolicy;
	/*
	 * to be used by the Manager
	 */
	public void setDeliveryPolicy(IDeliveryPolicy b){
		deliveryPolicy = b.changeDeliveryPolicy();
	}
	/*
	 * to be used by when searching for courier
	 */
	public String getDeliveryPolicy() {
		return deliveryPolicy;
	}
	
	
	/***************************************************************************************************/
	/*
	 * target profit policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/***************************************************************************************************/
	/*
	 * shipped order sorting policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/***************************************************************************************************/
	/*
	 * computing the total income and profit
	 */
	// TO DO
	
	/***************************************************************************************************/
	/*
	 * special orders notifications related
	 * observer pattern
	 */
	
	/***************************************************************************************************/
	/*
	 * pricing and fidelity related
	 */
	// TO DO
	
	/***************************************************************************************************/
	/*
	 * manager related
	 * -add/remove any kind of user
	 * -activate/deactivate any kind of user
	 * -changing the service fee/ markup-percentage / delivery cost
	 * -compute total income/profit over a time period
	 * -compute average income per customer
	 * -determining SF/MP/DC for target profit policy
	 * -determining most/least selling restaurant
	 * -determining the most/least active courier
	 * -setting delivery policy
	 * 
	 */
	// TO DO
	
	/***************************************************************************************************/
	/* restaurant related
	 * see restaurant class
	 * -sorting of shipped orders with respect to different criteria
	 */
	// TO DO
	
	/***************************************************************************************************/
	/* customers related
	 * -place orders
	 * -register/unregister to/from a fidelity card plan
	 * -access to information related to their account
	 * -give/remove consensus to be notified whenever a new special offer
	 */
	// TO DO
	
	/***************************************************************************************************/
	/* couriers related
	 * -register/unregister their account to the MyFoodora system
	 * -state their state as on-duty or off-duty
	 * -change their position
	 * -accept/refuse to a delivery call
	 */
	// TO DO

}
