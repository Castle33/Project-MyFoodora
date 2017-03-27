package system;

import java.util.Calendar;
import java.util.List;

public class TargetProfitMarkup implements ITargetProfitPolicy {
	
	@Override
	public double computeProfitStrategyBased(double serviceFee, double markupPercentage, double deliveryCost, double targetProfit, List<Order> listOfCompletedOrders, Calendar initDate, Calendar finDate){
		double lastMonthTotalFoodIncome = 0;
		int lastMonthNumOrders = 0;
		
		for(Order order : listOfCompletedOrders){
			if(order.getDate().after(initDate) && order.getDate().before(finDate)){
				lastMonthTotalFoodIncome += order.calcPrice();
				lastMonthNumOrders += 1;
			}
		}
		if(lastMonthNumOrders != 0){
			markupPercentage = (targetProfit - serviceFee + deliveryCost)/(lastMonthTotalFoodIncome/lastMonthNumOrders);
		}else{
			// throw exception NO order last month
		}
		
		return markupPercentage;
	}
		
	

}
