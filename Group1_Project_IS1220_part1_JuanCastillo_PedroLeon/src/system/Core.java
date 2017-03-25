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
	
	/*
	 * user's specific operations related
	 */
	// TO DO
	
	/*
	 * delivery policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/*
	 * target profit policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/*
	 * shipped order sorting policies related
	 * behavioral pattern
	 */
	// TO DO
	
	/*
	 * computing the total income and profit
	 */
	// TO DO
	
	/*
	 * special orders notifications related
	 * observer pattern
	 */
	
	/*
	 * pricing and fidelity related
	 */
	// TO DO

}
