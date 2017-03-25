package restaurant_structure;

public class ItemFactory {

	public Item getItem(String itemType, String name, double price, String foodType) {
		if(itemType.equalsIgnoreCase("STARTER")){
			return new Starter(name,price,foodType);
		}else if(itemType.equalsIgnoreCase("MAINDISH")){
			return new MainDish(name,price,foodType);
		}else if(itemType.equalsIgnoreCase("DESSERT")){
			return new Dessert(name,price,foodType);
		}
		return null;
	}
	
	public Item getItem(String itemType, String name, double price) {
		if(itemType.equalsIgnoreCase("STARTER")){
			return new Starter(name,price);
		}else if(itemType.equalsIgnoreCase("MAINDISH")){
			return new MainDish(name,price);
		}else if(itemType.equalsIgnoreCase("DESSERT")){
			return new Dessert(name,price);
		}
		return null;
	}

	
	

}
