package users;

public class Customer extends User{
	
	private String surname;
	private Address address;
	private String email;
	private String phoneNumber;
	
	public Customer(String name, String username, String surname, Address address, String email, 
			String phoneNumber, String password) {
		super(name, username, password);
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "Customers [name=" + getName() + "surname=" + surname + ", username=" + getUsername() + 
				", ID=" + getID() + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
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
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
}
