package system;

public interface ITargetProfitPolicy {
	double computeProfitStrategyBased(double orderPrice, double markupPercentage, double serviceFee, double deliveryCost);

}
