package system;

import users.Courier;
import users.Address;

import java.util.Collections;
import java.util.LinkedList;

public class DeliveryFastest implements IDeliveryPolicy{
	
	private LinkedList<Courier> listSortedCouriers;
	Address restAddress;
	
	
	
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
	
}
