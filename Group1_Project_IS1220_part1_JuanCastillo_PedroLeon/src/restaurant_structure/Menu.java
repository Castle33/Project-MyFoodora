package restaurant_structure;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;



/**
 * 
 * @author Pedro León
 *
 */
public class Menu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8100353078966721305L;
	private List<Starter> starters;
	private List<MainDish> mainDishes;
	private List<Dessert> desserts;
	
	
	public Menu() {
		super();
		this.starters = new ArrayList<Starter>();
		this.mainDishes = new ArrayList<MainDish>();
		this.desserts = new ArrayList<Dessert>();
	}

	public Menu(List<Starter> starters, List<MainDish> mainDishes, List<Dessert> desserts){
		this.starters = starters;
		this.mainDishes = mainDishes;
		this.desserts = desserts;
	}
	
	/***************************************************************************************************/
	/*
	 * Add and remove methods
	 */
	
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
	
	/***************************************************************************************************/
	/*
	 * Getters and Setters
	 */
	/*
	@Override
	public String toString() {
		return "Menu [starters=" + starters + ", mainDishes=" + mainDishes + ", desserts=" + desserts + "]";
	}
	*/

	/**
	 * @return the starters
	 */
	public List<Starter> getStarters() {
		return starters;
	}

	/**
	 * @param starters the starters to set
	 */
	public void setStarters(List<Starter> starters) {
		this.starters = starters;
	}

	/**
	 * @return the mainDishes
	 */
	public List<MainDish> getMainDishes() {
		return mainDishes;
	}

	/**
	 * @param mainDishes the mainDishes to set
	 */
	public void setMainDishes(List<MainDish> mainDishes) {
		this.mainDishes = mainDishes;
	}

	/**
	 * @return the desserts
	 */
	public List<Dessert> getDesserts() {
		return desserts;
	}

	/**
	 * @param desserts the desserts to set
	 */
	public void setDesserts(List<Dessert> desserts) {
		this.desserts = desserts;
	}
	
	public static void main (String [] args){
		Menu m = new Menu();
		
		Starter s = new Starter("S", 1);
		
		m.addStarter(s);
		
		System.out.println(m);
	}
	
	
}
