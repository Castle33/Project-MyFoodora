package system;

import java.io.Serializable;

/**
 * This fidelity card plan has a coefficient of 1 as it doesn't apply any additional discount
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 *
 */
public class FidelityCardBasic implements FidelityCard ,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1155654508695225861L;

	public FidelityCardBasic() {
		super();
	}
	
	@Override
	public double applyFidelityPlan() {
		return 1;
	}
	
	
}
