package users;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

import java.util.*;
import system.Order;

public class Courier extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1073852164817064065L;
	private String surname;
	private Address position;
	private String phoneNumber;
	private boolean onDuty;
	private int countOfOrdersCompleted;
	private LinkedList<Order> listPendingOrders;
	private Order currentOrder;
	private double distanceToRest;
	
	public Courier(String name, String username, String password, String surname, Address position, String phoneNumber) {
		super(name, username, password);
		this.surname = surname;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.countOfOrdersCompleted = 0;
		this.onDuty = false;
		listPendingOrders = new LinkedList<Order>();
		this.currentOrder = null;
		this.distanceToRest = 0.0;
	}

	@Override
	public String toString() {
		return "Courier [surname=" + surname + ", name=" + getName() + ", username=" + getUsername() 
				+ ", position=" + position + ", phoneNumber=" + phoneNumber
				+ ", ID=" + getID() + ", onDuty=" + onDuty + ", currentOrder=" + getCurrentOrder() + "]";
	}
	
	/***************************************************************************************************/
	/* Two Comparators methods depending on distance to restaurant or number of orders of Class Courier */
	/**
	 * Comparator use to sort couriers in the Delivery Fastest Policy depending on their distance to the restaurant
	 * @return a comparator that compares couriers based on the attribute <code>distanceToRest</code>
	 */
	public static Comparator<Courier> compareDistance(){
		return new Comparator<Courier>(){
			@Override
			public int compare(Courier arg0, Courier arg1) {
				if(arg0.distanceToRest > arg1.distanceToRest){
					return 1;
				} else if(arg0.distanceToRest == arg1.distanceToRest){
					return 0;
				} else {
					return -1;
				}
			}
			
		};
	}
	/**
	 * Comparator used to sort couriers in the Delivery Fair Occupation Policy depending on their number of
	 * completed orders.
	 * @return a comparator that compares couriers based on the attribute <code>countOfOrdersCompleted</code>
	 */
	public static Comparator<Courier> compareNumOrders(){
		return new Comparator<Courier>(){
			@Override
			public int compare(Courier arg0, Courier arg1) {
				if(arg0.countOfOrdersCompleted > arg1.countOfOrdersCompleted){
					return 1;
				} else if(arg0.countOfOrdersCompleted == arg1.countOfOrdersCompleted){
					return 0;
				} else {
					return -1;
				}
			}
			
		};
	}
	/**
	 * Comparator used to sort couriers in the <code>processOrders</code> function by comparing their delivery
	 * time of their current order. It allows to assign an order to a courier when all couriers are currently on duty
	 * @return a comparator that compares couriers based on the attribute <code>deliveryDate</code> of the object
	 * <code>currentOrder</code>
	 */
	public static Comparator<Courier> compareDeliveryDate(){
		return new Comparator<Courier>(){
			@Override
			public int compare(Courier arg0, Courier arg1) {
				if(arg0.currentOrder.getDeliveryDate().after(arg1.currentOrder.getDeliveryDate())){
					return 1;
				} else if(arg0.currentOrder.getDeliveryDate().before(arg1.currentOrder.getDeliveryDate())){
					return -1;
				} else {
					return 0;
				}
			}
			
		};
	}
	
	/***************************************************************************************************/
	/* Methods for treating with Orders */
	
	/**
	 * Adds an order to the list of pending orders of a courier.
	 * @param order
	 * 		the order that wants to be added.
	 */
	public void addNewOrder(Order order){
		Order tempOrder = order;
		tempOrder.setAssignedCourier(true);
		this.listPendingOrders.add(tempOrder);
	}
	
	/**
	 * The courier accepts an order, setting it to its current order, changing his state to OnDuty, calculating
	 * the delivery date estimation and adding 1 to the num of orders completed
	 * @param order
	 * the order to be accepted
	 * @return
	 * TRUE, as it is used in the function <code>decideAddOrder</code> that decides whether or not to accept the order
	 */
	public boolean acceptOrder(Order order){
		this.currentOrder = order;
		this.currentOrder.setAssignedCourier(true);
		this.currentOrder.calcDeliveryTime(this.getPosition());
		countOfOrdersCompleted++;
		order.getRestaurant().setCountOfOrdersCompleted(order.getRestaurant().getCountOfOrdersCompleted()+1);
		this.onDuty = true;
		return true;
	}
	
	/**
	 * The courier refuses to take an order
	 * @return
	 * FALSE
	 */
	public boolean refuseOrder(){
		return false;
	}
	
	/**
	 * Function that simulates the reaction of a courier to an order: assigns a probability of 5% that the courier will
	 * decline the order and calls the functions <code>acceptOrder</code> or <code>refuseOrder</code> accordingly.
	 * @param order
	 * the order to be accepted of refused
	 * @return
	 * TRUE if the order is accepted and FALSE if refused
	 */
	public boolean decideTakingOrder(Order order){	
		if(Math.random() < 0.05){
			return refuseOrder();
		} else {
			return acceptOrder(order);
		}
	}
	
	/**
	 * Function that simulates the reaction of a courier to an order: assigns a probability of 1% that the courier will
	 * decline to add the order to its list of pending orders and calls the functions <code>acceptOrder</code> or
	 * <code>refuseOrder</code> accordingly.
	 * @param order
	 * the order to be added to the list of pending orders of a courier
	 * @return
	 * TRUE if the order is added and FALSE if not
	 */
	public boolean decideAddOrder(Order order){
		if(listPendingOrders.isEmpty()){
			if(Math.random() < 0.1){
				return false;
			} else {
				addNewOrder(order);
				return true;
			}
		} else {
			return false;
		}
	}
	
	/***************************************************************************************************/
	/*
	 * Getters and Setters: no setters for ID and Counter
	 */
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the position
	 */
	public Address getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Address position) {
		this.position = position;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the onDuty
	 */
	public boolean isOnDuty(){
		return onDuty;
	}

	/**
	 * @param onDuty the onDuty to set
	 */
	public void setOnDuty(boolean onDuty) {
		this.onDuty = onDuty;
	}

	/**
	 * @return the countOfOrdersCompleted
	 */
	public int getCountOfOrdersCompleted() {
		return countOfOrdersCompleted;
	}

	/**
	 * @param countOfOrdersCompleted the countOfOrdersCompleted to set
	 */
	public void setCountOfOrdersCompleted(int countOfOrdersCompleted) {
		this.countOfOrdersCompleted = countOfOrdersCompleted;
	}

	public LinkedList<Order> getListPendingOrders() {
		return listPendingOrders;
	}

	public void setListPendingOrders(LinkedList<Order> listPendingOrders) {
		this.listPendingOrders = listPendingOrders;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public double getDistanceToRest() {
		return distanceToRest;
	}

	public void setDistanceToRest(double distanceToRest) {
		this.distanceToRest = distanceToRest;
	}
	
	
	
}
