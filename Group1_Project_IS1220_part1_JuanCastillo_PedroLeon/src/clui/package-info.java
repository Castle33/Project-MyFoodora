/**
 * This package contains the clui command classes of the system 
 * (ie the <code>LogIn</code>, <code>LogOut</code>, <code>RegisterRestaurant</code>, <code>RegisterCustomer</code>,
 *  <code>RegisterCourier</code>, <code>AddDishRestaurantMenu</code>, <code>ReateMeal</code>, <code>LogaddDish2MealOut</code>,
 *  <code>RemoveItemFromMeal</code>, <code>ShowMeal</code>, <code>SaveMeal</code>, <code>SetSpecialOffer</code>,
 *  <code>RemoveSepcialOffer</code>, <code>CreateOrder</code>, <code>AddItem2Order</code>, <code>AddMeal2Order</code>, 
 *  <code>EndOrder</code>, <code>OnDuty</code>, <code>OffDuty</code>, <code>FindDeliverer</code>, 
 *  <code>SetDeliveryPolicy</code>, <code>SetProfitPolicy</code>, <code>AssociatedCard</code>, <code>ShowListOfOrdersCompleted</code>, 
 *  <code>ShowCourierDeliveries</code>, <code>ShowCouriersRegistered</code>, <code>ShowRestaurantsRegistered</code>,
 *  <code>ShowRestaurantTop</code>, <code>ShowCustomers</code>, <code>ShowMenuItem</code>, <code>ShowTotalProfit</code>,
 *  <code>ShowTotalProfit</code>, <code>GetParameterToTargetProfit</code>, <code>InitCore</code> classes),
 * responsible for processing user's commands of the MyFoodora system.
 * It also implements <code>CommandProcessor</code> interface
 * required for the Strategy pattern.
 * It also implements the <code>CluiLauncher</code> class to be used by a future system tester.
 * required for the Observer pattern.
 * It also implements <code>StringCast</code> class
 * to cast input strings to command's argument type requirements.
 * 
 * @author Pedro León
 * @author Juan Castillo
 */
package clui;