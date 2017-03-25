package restaurant_structure;

/**
 * 
 * @author Pedro León
 *
 */

public class MainDish extends Item {
	
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