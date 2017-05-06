package restaurant_structure;

import java.io.Serializable;

public class FactoryProducer implements Serializable { 
	/**
	 * Returns an AbstractFatory object of the specified type
	 * 
	 * @param a String specifying the AbstractFacorty type "ITEM" or "MEAL"
	 * @return an Abstract Factory object
	 */
	private static final long serialVersionUID = -4502471626609094784L;

	public static AbstractFactory getFactory(String choice){ 
		if(choice.equalsIgnoreCase("ITEM")){ 
			return new ItemFactory();
		}else if(choice.equalsIgnoreCase("MEAL")){
			return new MealFactory();
		}return null;
	}
}
