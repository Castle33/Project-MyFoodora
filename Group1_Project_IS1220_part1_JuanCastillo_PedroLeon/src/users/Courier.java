package users;

import java.util.*;
import system.Order;

public class Courier extends User {
	private String surname;
	private Address position;
	private String phoneNumber;
	private boolean onDuty;
	private int countOfOrdersCompleted;
	private LinkedList<Order> listPendingOrders;
	private Order currentOrder;
	
	public Courier(String name, String username, String password, String surname, Address position, String phoneNumber,
			int countOfOrdersCompleted) {
		super(name, username, password);
		this.surname = surname;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.countOfOrdersCompleted = countOfOrdersCompleted;
		this.onDuty = false;
		listPendingOrders = new LinkedList<Order>();
		this.currentOrder = null;
	}

	@Override
	public String toString() {
		return "Courier [surname=" + surname + ", name=" + getName() + ", username=" + getUsername() 
				+ ", position=" + position + ", phoneNumber=" + phoneNumber
				+ ", ID=" + getID() + ", onDuty=" + onDuty + "]";
	}
	
	/***************************************************************************************************/
	/* Methods for treating with Orders */
	
	public void addNewOrder(Order order){
		this.listPendingOrders.add(order);
	}
	
	public boolean acceptOrder(Order order){
		this.currentOrder = order;
		this.currentOrder.setCourier(this);
		countOfOrdersCompleted++;
		this.onDuty = true;
		return true;
	}
	
	public boolean refuseOrder(){
		return false;
	}
	
	public void decideTakingOrder(Order order){
		if(Math.random() < 0.05){
			refuseOrder();
		} else {
			acceptOrder(order);
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
		if(onDuty && currentOrder.getDeliveryDate().after(Calendar.getInstance())){
			this.onDuty = false;
			return onDuty;
		} else {
			return onDuty;
		}
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
	
	
	
}
