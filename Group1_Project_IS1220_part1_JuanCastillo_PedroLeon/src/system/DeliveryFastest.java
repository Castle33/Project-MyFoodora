package system;

/**
 * Sorts a list of couriers based on the distance to the specified address, which is meant to be
 * the restaurant's one (in ascending order)
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 * tested: YES
 */

import users.Courier;
import users.Address;

import java.util.Collections;
import java.util.LinkedList;

public class DeliveryFastest implements IDeliveryPolicy{
	
	private LinkedList<Courier> listSortedCouriers;
	private Address restAddress;
	
	
	
	public DeliveryFastest() {
		super();
		this.listSortedCouriers = new LinkedList<Courier>();
	}

	public DeliveryFastest(LinkedList<Courier> listSortedCouriers) {
		super();
		this.listSortedCouriers = listSortedCouriers;
	}

	@Override
	public LinkedList<Courier> setDeliveryPolicy(LinkedList<Courier> listCouriers, Address address) {
		this.listSortedCouriers = listCouriers;
		this.restAddress = address;
		for(Courier courier : listSortedCouriers){
			courier.setDistanceToRest(courier.getPosition().calcDistance(restAddress));
		}
		Collections.sort(listSortedCouriers, Courier.compareDistance());
		return listSortedCouriers;
	}

	public LinkedList<Courier> getListSortedCouriers() {
		return listSortedCouriers;
	}

	public void setListSortedCouriers(LinkedList<Courier> listSortedCouriers) {
		this.listSortedCouriers = listSortedCouriers;
	}

	public Address getRestAddress() {
		return restAddress;
	}

	public void setRestAddress(Address restAddress) {
		this.restAddress = restAddress;
	}
	
	
}
