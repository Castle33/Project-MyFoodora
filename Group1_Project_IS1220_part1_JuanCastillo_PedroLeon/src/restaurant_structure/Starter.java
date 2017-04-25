package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

public class Starter extends Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5677031913610320156L;

	/*
	 * constructor indicating type and not indicating type
	 */
	public Starter(String name, double price, String type){
		super(name,price,type);
	}
	public Starter(String name, double price){
		super(name,price);
		this.setFoodType("standard");
	}

	@Override
	public String toString() {
		return "Starter [toString()=" + super.toString() + "]";
	}
	
	
}
