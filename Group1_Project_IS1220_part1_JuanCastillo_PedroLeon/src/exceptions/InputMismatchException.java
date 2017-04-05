package exceptions;

@SuppressWarnings("serial")
public class InputMismatchException extends Exception {
	
	public InputMismatchException(){
		super("ArgumentMismatchException\nUsually with Integer/Double/Address/Date\nAlso with itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType\n\titemCategory: 'Starter'/'MainDish'/'Dessert'\n\tmealCategory: 'HalfMeal'/'FullMeal'\n\tDeliveryPolicyType: 'Fastest'/'FairOccupation'\n\tFidelityPolicyType: 'Basic'/'Point'/'Lottery'\n\tProfitPolicyType: 'DeliveryCost'/'Markup'/'ServiceFee'\n");
	}

}
