package system;

import java.util.*;

public interface ITargetProfitPolicy {
	public double computeProfitStrategyBased(double serviceFee, double markupPercentage, double deliveryCost, double targetProfit, List<Order> listOfCompletedOrders, Calendar initDate, Calendar finDate);
	
	

}
