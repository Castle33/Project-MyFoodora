package users;

import system.FidelityCard;
import system.FidelityCardBasic;
import system.FidelityCardPoint;
import system.FidelityCardLottery;

public class Customer extends User{
	
	private String surname;
	private Address address;
	private String email;
	private String phoneNumber;
	private FidelityCard fidelityCard;
	
	public Customer(String name, String username, String surname, Address address, String email, 
			String phoneNumber, String password) {
		super(name, username, password);
		this.surname = surname;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.fidelityCard = new FidelityCardBasic();
	}
	
	@Override
	public String toString() {
		return "Customers [name=" + getName() + "surname=" + surname + ", username=" + getUsername() + 
				", ID=" + getID() + ", address=" + address + ", email=" + email + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	/***************************************************************************************************/
	/*
	 * Fidelity Card: methods to get and set Fidelity Card Plan and change points of FidelityCardPoint
	 */
	
	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	public void setFidelityCard(FidelityCard fidelityCard) {
		if(!(fidelityCard.getClass().equals(this.fidelityCard.getClass()))){
			this.fidelityCard = fidelityCard;
		}	
	}
	
	public void setFidelityCardtoBasic(){
		FidelityCardBasic basic = new FidelityCardBasic();
		setFidelityCard(basic);
	}
	
	public void setFidelityCardtoPoints(){
		FidelityCardPoint points = new FidelityCardPoint();
		setFidelityCard(points);
	}
	
	public void setFidelityCardtoLottery(){
		FidelityCardLottery lottery = new FidelityCardLottery();
		setFidelityCard(lottery);
	}
	
	public int getNumberFidelityPoints(){
		if(this.fidelityCard instanceof FidelityCardPoint){
			return ((FidelityCardPoint) this.fidelityCard).getPoints();
		}
		return 0;
	}
	
	public void addFidelityPoints(int n){
		if(this.fidelityCard instanceof FidelityCardPoint){
			((FidelityCardPoint) this.fidelityCard).setPoints(((FidelityCardPoint) this.fidelityCard).getPoints()+n);
		} else {
			System.out.println("Fidelity Card is not of type Points");
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