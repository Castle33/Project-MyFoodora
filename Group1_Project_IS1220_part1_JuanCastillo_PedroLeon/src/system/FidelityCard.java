package system;
/**
 * Interface of the Fidelity Card Plans
 * @author Juan Castillo
 *
 */
public interface FidelityCard {
	/**
	 * Applies the fidelity card plan by establishing a coefficient that will be multiplied to the order's price to
	 * obtain the final price
	 * @return returns a value between 0 and 1 depending on the reduction to the price that the fidelity 
	 * card plan applies
	 */
	public double applyFidelityPlan();
}
