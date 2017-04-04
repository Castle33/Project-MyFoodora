package exceptions;

@SuppressWarnings("serial")
public class InputMismatchException extends Exception {
	
	public InputMismatchException(){
		super("ArgumentMismatchException - Usually with addresses and dates.\nAlso with ENUM such as itemCategory/mealCategory/DeliveryPolicyType/FidelityPolicyType/ProfitPolicyType");
	}

}
