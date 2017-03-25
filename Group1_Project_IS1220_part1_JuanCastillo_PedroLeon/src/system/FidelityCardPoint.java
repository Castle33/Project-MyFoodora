package system;

public class FidelityCardPoint implements FidelityCard{
	
	private int points;
	private double discount;
	private int discPoints;
	
	public FidelityCardPoint() {
		super();
		this.points = 0;
		this.discount = 0.1;
		this.discPoints = 100;
	}

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
