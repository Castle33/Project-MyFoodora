package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

public class Dessert extends Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3218518375820286036L;

	/*
	 * constructor indicating type and not indicating type
	 */
	public Dessert(String name, double price, String type){
		super(name,price,type);
	}
	public Dessert(String name, double price){
		super(name,price);
		this.setFoodType("standard");
	}
	
	@Override
	public String toString() {
		return "Dessert [toString()=" + super.toString() + "]";
	}
	
	
	

}