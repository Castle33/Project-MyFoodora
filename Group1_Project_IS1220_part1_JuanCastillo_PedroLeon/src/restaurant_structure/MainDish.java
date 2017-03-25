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
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	

}