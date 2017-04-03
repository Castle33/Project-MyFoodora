package clui;

import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;

import exceptions.NumberOfArgumentsException;
import system.DeliveryFairOccupation;
import users.Courier;
import users.User;
import users.Address;

/**
 * ShowCourierDeliveries 0 <>
 * @author Pedro León
 *
 */
public class ShowCourierDeliveries implements CommandProcessor{
	final int nArgs = 0;
	String message;
	LinkedList<Courier> couriers = new LinkedList<Courier>();
	LinkedList<Courier> orderedCouriers = new LinkedList<Courier>();
	DeliveryFairOccupation dfo = new DeliveryFairOccupation();
	Courier courier;
	/* (non-Javadoc)
	 * @see clui.CommandProcessor#process(java.lang.String[])
	 */
	@Override
	public String process(String[] args) {
		try{
			if(args[nArgs] == null){
				Collection<User> users = MyFoodora.core.getListOfUsers().values();
				for(User u : users){
					if(u instanceof Courier){
						couriers.add((Courier) u);
					}
				}
				orderedCouriers = dfo.setDeliveryPolicy(couriers, new Address(0,0));
				Collections.reverse(orderedCouriers);
				return orderedCouriers.toString();
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

}
