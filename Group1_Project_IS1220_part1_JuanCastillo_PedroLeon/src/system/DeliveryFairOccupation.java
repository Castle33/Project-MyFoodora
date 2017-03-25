package system;

public class DeliveryFairOccupation implements IDeliveryPolicy {
	
	@Override
	public int changeDeliveryPolicy(){
		System.out.println("Choose the courier with the least number comleted delivery");
		return 0;
	}

}
