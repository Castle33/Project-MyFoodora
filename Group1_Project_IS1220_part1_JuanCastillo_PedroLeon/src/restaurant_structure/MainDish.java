package restaurant_structure;

import java.io.Serializable;

/**
 * 
 * @author Pedro León (programer)
 * @author Juan Castillo (tester)
 * tested: YES
 */

public class MainDish extends Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1485968544810908024L;

	/*
	 * constructor indicating type and not indicating type
	 */
	public MainDish(String name, double price, String type){
		super(name,price,type);
	}
	public MainDish(String name, double price){
		super(name,price);
		this.setFoodType("standard");
	}
	
	@Override
	public String toString() {
		return "MainDish [toString()=" + super.toString() + "]";
	}
	
	

}