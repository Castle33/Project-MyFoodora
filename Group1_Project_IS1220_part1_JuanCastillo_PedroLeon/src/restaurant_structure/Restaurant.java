package restaurant_structure;

/**
 * 
 * @author Pedro León
 *
 */

public class Restaurant {
	
	public String name;
	public Coordinate loc;
	
	public ItemFactory itemFactory = new ItemFactory();
	
	Menu menu = new Menu();
	
	public static void main(String[] args){
		Restaurant r = new Restaurant();
		
		System.out.println(r.itemFactory.getItem("DESSERT", "d1", 0));
		
		/*
		r.menu.items.add(r.itemFactory.getItem("STARTER", "s1", 0));
		r.menu.items.add(r.itemFactory.getItem("STARTER", "s2", 0));
		r.menu.items.add(r.itemFactory.getItem("DESSERT", "d1", 0));
		r.menu.items.add(r.itemFactory.getItem("MAINDISH", "md1", 0));
		r.menu.items.add(r.itemFactory.getItem("MAINDISH", "md2", 0));
		*/
		//System.out.println(r.menu);
	}

}
