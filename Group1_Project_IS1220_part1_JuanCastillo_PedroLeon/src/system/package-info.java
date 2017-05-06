/**
 * This package contains the Core class of the system.
 * It also contains policy related classes
 * it implements IDeliveryPolicy interface required for Strategy pattern and delivery policy of the system classes
 * (ie the <code>DeliveryFairOccupation</code>, <code>DeliveryFastest</code>
 * it implements FidelityCard interface required for Strategy pattern and fidelity card of the Customer class
 * (ie the <code>FidelityCardBasic</code>, <code>FidelityCardLottery</code>, <code>FidelityCardPoint</code> classes)
 * it implements ITargetProfitPolicy interface required for Strategy pattern and target profit policy classes
 * (ie <code>TargetProfitDeliveryCost</code>, <code>TargetProfitMarkup</code>, <code>TargetProfitServiceFee</code> classes)
 * It also implements <code>Order</code> class
 * required for order treatment of the system.
 * It also implements enumerations for all policy types
 * (ie. the <code>DeliveryPolicyType</code>, <code>FidelityCardType</code>, <code>ProfitPolicyType</code> classes)
 * required for correctly reading inputs in CLUI.
 * 
 * @author Pedro León
 * @author Juan Castillo
 */
package system;