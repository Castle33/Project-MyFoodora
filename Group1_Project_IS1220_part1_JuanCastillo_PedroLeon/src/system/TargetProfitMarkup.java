package system;

import java.util.Calendar;
import java.util.List;

public class TargetProfitMarkup implements ITargetProfitPolicy {
	
	/**
	 * firstly, based on last month performance and the indicated target profit we calculate the desired markup percentage
	 * secondly, based on this month performance we calculate this month profit
	 */
	
	@Override
	public double computeProfitStrategyBased(double serviceFee, double markupPercentage, double deliveryCost, double targetProfit, List<Order> listOfOrders, Calendar initDate, Calendar finDate){
		Calendar newInitDate = Calendar.getInstance();
		Calendar newFinDate = Calendar.getInstance();
		double lastMonthTotalFoodIncome = 0;
		double thisMonthTotalFoodIncome = 0;
		int lastMonthNumOrders = 0;
		int thisMonthNumOrders = 0;
		double profit = 0;
		
		System.out.println("Computing markupPercentage based on last month performance and targetProfit for this month");
		
		newInitDate.set(initDate.get(Calendar.YEAR),initDate.get(Calendar.MONTH)-1,initDate.get(Calendar.DAY_OF_MONTH));
		newInitDate.set(finDate.get(Calendar.YEAR),finDate.get(Calendar.MONTH)-1,finDate.get(Calendar.DAY_OF_MONTH));
		
		for(Order order : listOfOrders){
			if(order.getDate().after(newInitDate) && order.getDate().before(newFinDate)){
				lastMonthTotalFoodIncome += order.calcPrice();
				lastMonthNumOrders += 1;
			}
		}
		if(lastMonthNumOrders != 0){
			markupPercentage = (targetProfit - serviceFee + deliveryCost)/(lastMonthTotalFoodIncome/lastMonthNumOrders);
		}else{
			// throw exception NO order last month
		}
		
		System.out.println("Computing this month profit based on markupPercentage computation");
		
		for(Order order : listOfOrders){
			if(order.getDate().after(initDate) && order.getDate().before(finDate)){
				thisMonthTotalFoodIncome += order.calcPrice();
				thisMonthNumOrders += 1;
			}
		}
		if(thisMonthNumOrders != 0){
			profit = (thisMonthTotalFoodIncome*markupPercentage) + (serviceFee - deliveryCost)*thisMonthNumOrders;
		}else{
			// throw exception NO order this month
		}
		
		return profit;
	}

}
