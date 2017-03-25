package restaurant_structure;

import java.util.List;
import java.util.ArrayList;



/**
 * 
 * @author Pedro León
 *
 */
public class Menu {
	
	List<Starter> starters = new ArrayList<Starter>();
	List<MainDish> mainDishes= new ArrayList<MainDish>();
	List<Dessert> desserts = new ArrayList<Dessert>();
	
	public Menu(List<Starter> starters, List<MainDish> mainDishes, List<Dessert> desserts){
		this.starters = starters;
		this.mainDishes = mainDishes;
		this.desserts = desserts;
	}
	
	public void addStarter(Starter s){
		starters.add(s);
	}
	
	public void removeStarter(Starter s){
		starters.remove(s);
	}
	
	public void addMainDish(MainDish md){
		mainDishes.add(md);
	}
	
	public void removeMainDish(MainDish md){
		mainDishes.remove(md);
	}
	
	public void addDessert(Dessert d){
		desserts.add(d);
	}
	
	public void removeDessert(Dessert d){
		desserts.remove(d);
	}
	
	@Override
	public String toString(){
		String res = "";
		for(Starter s : starters){
			res = res + (s.toString());
		}
		for(MainDish md : mainDishes){
			res = res + (md.toString());
		}
		for(Dessert d : desserts){
			res = res + (d.toString());
		}
		return res;
	}
	
}
