package system;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
/**
 * Computes the Delivery Cost value required to meet a target profit, given the Service Fee and the Markup Percentage
 * @author Pedro Leon (programmer)
 * @author Juan Castillo (tester)
 *
 */
public class TargetProfitDeliveryCost implements ITargetProfitPolicy ,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4222948781711201631L;

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
			deliveryCost = (lastMonthTotalFoodIncome*markupPercentage - targetProfit)/lastMonthNumOrders + serviceFee;
		}else{
			// throw exception NO order last month
		}
		
		return deliveryCost;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TargetProfitDeliveryCost";
	}
	
	
}
