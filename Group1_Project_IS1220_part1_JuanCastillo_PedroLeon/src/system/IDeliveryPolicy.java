package system;

import users.Courier;
import java.util.LinkedList;
import users.Address;
public interface IDeliveryPolicy {
	
	public LinkedList<Courier> setDeliveryPolicy(LinkedList<Courier> listCouriers, Address address);

}
