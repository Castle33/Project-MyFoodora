package system;
/**
 * This fidelity card plan has a coefficient of 1 or 0, being 0 when the user wins the lottery, which happens with
 * a determined probability, by default 10%.
 * @author Juan Castillo (programer)
 * @author Pedro León (tester)
 *
 */
public class FidelityCardLottery implements FidelityCard{

	private double lotteryProba;
	/**
	 * tested: YES
	 */
	public FidelityCardLottery() {
		super();
		this.lotteryProba = 0.1;
	}
	/**
	 * tested: YES
	 */
	@Override
	public double applyFidelityPlan() {
		if(Math.random() >= lotteryProba){
			return 1;
		}
		return 0;
	}

	/***************************************************************************************************/
	/*
	 * Getters and Setters
	 */
	
	/**
	 * @return the lotteryProba
	 */
	public double getLotteryProba() {
		return lotteryProba;
	}

	/**
	 * @param lotteryProba the lotteryProba to set
	 */
	public void setLotteryProba(double lotteryProba) {
		this.lotteryProba = lotteryProba;
	}
	
	
}
