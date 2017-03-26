package users;

public class Courier extends User {
	private String surname;
	private Address position;
	private String phoneNumber;
	private int numDeliveredOrders;
	private boolean onDuty;
	private int countOfOrdersCompleted;
	
	public Courier(String name, String username, String password, String surname, Address position, String phoneNumber,
			int numDeliveredOrders) {
		super(name, username, password);
		this.surname = surname;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.numDeliveredOrders = numDeliveredOrders;
		this.onDuty = false;
	}

	@Override
	public String toString() {
		return "Courier [surname=" + surname + ", name=" + getName() + ", username=" + getUsername() 
				+ ", position=" + position + ", phoneNumber=" + phoneNumber
				+ ", ID=" + getID() + ", onDuty=" + onDuty + "]";
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
	 * @return the numDeliveredOrders
	 */
	public int getNumDeliveredOrders() {
		return numDeliveredOrders;
	}

	/**
	 * @param numDeliveredOrders the numDeliveredOrders to set
	 */
	public void setNumDeliveredOrders(int numDeliveredOrders) {
		this.numDeliveredOrders = numDeliveredOrders;
	}

	/**
	 * @return the onDuty
	 */
	public boolean isOnDuty() {
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
	
	
	
}
