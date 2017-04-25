package system;

/**
 * Sorts a list of couriers based on the number of completed orders (in ascending order)
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 * tested: YES
 */

import users.Courier;
import users.Address;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class DeliveryFairOccupation implements IDeliveryPolicy ,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3609713588930528651L;
	private LinkedList<Courier> listSortedCouriers;
	
	public DeliveryFairOccupation() {
		super();
		listSortedCouriers = new LinkedList<Courier>();
	}

	public DeliveryFairOccupation(LinkedList<Courier> listSortedCouriers) {
		super();
		this.listSortedCouriers = listSortedCouriers;
	}
	
	@Override
	public LinkedList<Courier> setDeliveryPolicy(LinkedList<Courier> listCouriers, Address address) {
		this.listSortedCouriers = listCouriers;
		for(Courier courier : listSortedCouriers){
			courier.setDistanceToRest(courier.getPosition().calcDistance(address));
		}
		Collections.sort(listSortedCouriers, Courier.compareNumOrders());
		return listSortedCouriers;
	}

	public LinkedList<Courier> getListSortedCouriers() {
		return listSortedCouriers;
	}

	public void setListSortedCouriers(LinkedList<Courier> listSortedCouriers) {
		this.listSortedCouriers = listSortedCouriers;
	}
	
	

}
