package system;

public class FidelityCardLottery implements FidelityCard{

	private double lotteryProba;
	
	public FidelityCardLottery() {
		super();
		this.lotteryProba = 0.1;
	}

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
