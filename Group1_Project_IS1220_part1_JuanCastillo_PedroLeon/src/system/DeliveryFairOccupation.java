package system;

public class DeliveryFairOccupation implements IDeliveryPolicy {
	
	@Override
	public String changeDeliveryPolicy(){
		System.out.println("Choose the courier with the least number comleted delivery");
		return "Fair";
	}

}
