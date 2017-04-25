package system;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
/**
 * Computes the Service Fee value required to meet a target profit, given the Markup Percentage and the Delivery Cost
 * @author Pedro Leon (programmer)
 * @author Juan Castillo (tester)
 *
 */
public class TargetProfitServiceFee implements ITargetProfitPolicy ,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9187604569872864719L;

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
			serviceFee = (targetProfit - lastMonthTotalFoodIncome*markupPercentage)/lastMonthNumOrders + deliveryCost ;
		}else{
			// throw exception NO order last month
		}
		
		return serviceFee;
	}

}
