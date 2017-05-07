package restaurant_structure;

import java.io.Serializable;

public class FactoryProducer implements Serializable { 
	
	private static final long serialVersionUID = -4502471626609094784L;
	/**
	 * Returns an AbstractFatory object of the specified type
	 * 
	 * @param choice: a String specifying the AbstractFacorty type "ITEM" or "MEAL"
	 * @return AbstractFactory
	 */
	public static AbstractFactory getFactory(String choice){ 
		if(choice.equalsIgnoreCase("ITEM")){ 
			return new ItemFactory();
		}else if(choice.equalsIgnoreCase("MEAL")){
			return new MealFactory();
		}return null;
	}
}
