package system;

import users.Observer;
import users.Restaurant;
import restaurant_structure.Meal;

public interface Observable {
	public void registerObserver(Observer observer);
	
	public void removeObserver(Observer observer);
	
	public void notifyObservers(Restaurant r, Meal m);
	
}
