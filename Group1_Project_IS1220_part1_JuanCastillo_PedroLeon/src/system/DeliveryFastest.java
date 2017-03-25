package system;

public class DeliveryFastest implements IDeliveryPolicy{
	
	@Override
	public int changeDeliveryPolicy(){
		System.out.println("Choose the courier with the shortest distance to cover");
		return 0;
	}

}
