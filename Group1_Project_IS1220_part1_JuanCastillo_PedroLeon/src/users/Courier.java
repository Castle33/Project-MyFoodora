package users;

import java.util.*;
import system.Order;

public class Courier extends User{
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
				+ ", ID=" + getID() + ", onDuty=" + onDuty + "]";
	}
	
	/***************************************************************************************************/
	/* Two Comparators methods depending on distance to restaurant or number of orders of Class Courier */
	
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
	//Compares Couriers depending on the delivery Date of their current order
	public static Comparator<Courier> compareDeliveyDate(){
		return new Comparator<Courier>(){
			@Override
			public int compare(Courier arg0, Courier arg1) {
				if(arg0.currentOrder.getDeliveryDate().after(arg1.currentOrder.getDeliveryDate())){
					return 1;
				} else if(arg0.currentOrder.getDeliveryDate().equals(arg1.currentOrder.getDeliveryDate())){
					return 0;
				} else {
					return -1;
				}
			}
			
		};
	}
	
	/***************************************************************************************************/
	/* Methods for treating with Orders */
	
	public void addNewOrder(Order order){
		Order tempOrder = order;
		tempOrder.setAssignedCourier(true);
		this.listPendingOrders.add(tempOrder);
	}
	
	public boolean acceptOrder(Order order){
		this.currentOrder = order;
		this.currentOrder.setAssignedCourier(true);
		this.currentOrder.calcDeliveryTime();
		countOfOrdersCompleted++;
		this.onDuty = true;
		return true;
	}
	
	public boolean refuseOrder(){
		return false;
	}
	
	public boolean decideTakingOrder(Order order){	
		if(Math.random() < 0.05){
			return refuseOrder();
		} else {
			return acceptOrder(order);
		}
	}
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
