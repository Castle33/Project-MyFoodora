package users;

public class Courier extends User {
	private String surname;
	private Address position;
	private String phoneNumber;
	private int numDeliveredOrders;
	
	public Courier(String name, String username, String password, String surname, Address position, String phoneNumber,
			int numDeliveredOrders) {
		super(name, username, password);
		this.surname = surname;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.numDeliveredOrders = numDeliveredOrders;
	}

	@Override
	public String toString() {
		return "Courier [surname=" + surname + ", name=" + getName() + ", username=" + getUsername() 
				+ ", position=" + position + ", phoneNumber=" + phoneNumber
				+ ", ID=" + getID() + "]";
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
	
}
