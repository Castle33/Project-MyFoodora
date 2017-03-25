package system;

public class DeliveryFastest implements IDeliveryPolicy{
	
	@Override
	public String changeDeliveryPolicy(){
		System.out.println("Choose the courier with the shortest distance to cover");
		return "Fast";
	}

}
