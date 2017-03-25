package restaurant_structure;

import java.util.List;
import java.util.ArrayList;



/**
 * 
 * @author Pedro Le�n
 *
 */
public class Menu {
	
	List<Item> items = new ArrayList<Item>();
	
	@Override
	public String toString(){
		String res = "";
		for(Item i : items){
			res = res + (i.toString());
		}
		return res;
	}
	
}
