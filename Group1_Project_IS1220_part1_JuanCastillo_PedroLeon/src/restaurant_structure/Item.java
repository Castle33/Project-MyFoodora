package restaurant_structure;

import java.io.Serializable;

/**
 * class that will be extended by Starter, MainDish and Dessert
 * @author Pedro León
 *
 */

public abstract class Item implements Serializable {
	
	/*
	 * each item or dish will specified by a name
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399999657982554198L;
	private String name;
	private double price;
	private String foodType;
	
	public Item(String name, double price, String foodType){
		this.name = name;
		this.price = price;
		this.foodType = foodType;
		
	}
	
	public Item(String name, double price){
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + ", type=" + foodType + "]";
	}
	
	/***************************************************************************************************/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (foodType == null) {
			if (other.foodType != null)
				return false;
		} else if (!foodType.equals(other.foodType))
			return false;
		return true;
	}
	
	/***************************************************************************************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	
	
	

}
