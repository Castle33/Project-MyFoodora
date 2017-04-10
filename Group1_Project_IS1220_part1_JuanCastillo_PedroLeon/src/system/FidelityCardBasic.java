package system;
/**
 * This fidelity card plan has a coefficient of 1 as it doesn't apply any additional discount
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 *
 */
public class FidelityCardBasic implements FidelityCard{

	public FidelityCardBasic() {
		super();
	}
	
	@Override
	public double applyFidelityPlan() {
		return 1;
	}
	
	
}
