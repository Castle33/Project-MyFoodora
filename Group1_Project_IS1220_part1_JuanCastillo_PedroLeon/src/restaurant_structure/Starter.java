package restaurant_structure;

/**
 * 
 * @author Pedro Le�n
 *
 */

public class Starter extends Item {
	
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
