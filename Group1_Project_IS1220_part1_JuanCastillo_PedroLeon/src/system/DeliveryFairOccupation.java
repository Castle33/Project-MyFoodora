package system;

import users.Courier;
import users.Address;

import java.util.Collections;
import java.util.LinkedList;

public class DeliveryFairOccupation implements IDeliveryPolicy {
	
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

}
