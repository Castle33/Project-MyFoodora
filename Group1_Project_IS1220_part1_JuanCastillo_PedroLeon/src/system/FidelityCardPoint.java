package system;

import java.io.Serializable;

/**
 * This fidelity card plan has a coefficient of 1 or 1-discount: this is determined by the discounts points that are
 * obtained each time the customer places an order. When the customer reaches a concrete amount of points (by default 100)
 * a determined discount is applied to the next order (by default 10%) and the points are then removed from the customer.
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 *
 */
public class FidelityCardPoint implements FidelityCard ,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5182054242756211941L;
	private int points;
	private double discount;
	private int discPoints;
	/**
	 * tested: YES
	 */
	public FidelityCardPoint() {
		super();
		this.points = 0;
		this.discount = 0.1;
		this.discPoints = 100;
	}
	/**
	 * tested: YES
	 */
	@Override
	public double applyFidelityPlan() {
		if(points >= discPoints){
			points -= discPoints;
			return (1 - discount);
		}
		return 1;
	}

	/***************************************************************************************************/
	/*
	 * Getters and Setters
	 */
	
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the discPoints
	 */
	public int getDiscPoints() {
		return discPoints;
	}

	/**
	 * @param discPoints the discPoints to set
	 */
	public void setDiscPoints(int discPoints) {
		this.discPoints = discPoints;
	}
	
}
