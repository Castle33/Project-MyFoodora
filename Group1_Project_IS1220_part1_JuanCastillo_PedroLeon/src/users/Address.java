package users;
/**
 * Class that represents the address as a 2 dimensional coordinates
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 * tested: YES
 */
public class Address {
	
	private int x;
	private int y;
	/**
	 * tested: YES
	 * @param x
	 * @param y
	 */
	public Address(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	/**
	 * Calculates the distance between two addresses 
	 * tested: YES
	 * -before: 		return Math.hypot(this.x-address.x, this.y-address.y);
	 * -after: 		return Math.hypot(x-address.getX(),y-address.getY());
	 * @param address with which calculate the distance of the current instance of address
	 * @return the distance as a double number
	 */
	public double calcDistance(Address address){
		return Math.hypot(x-address.getX(),y-address.getY());
	}
	
	/***************************************************************************************************/
	/*
	 * Getters and Setters
	 */
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/***************************************************************************************************/
	/*
	 * Overriding methods: toString, hashCode, equal
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
	
	
}
