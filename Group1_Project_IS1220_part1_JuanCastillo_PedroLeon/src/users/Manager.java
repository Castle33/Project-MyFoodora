package users;
/**
 * 
 * @author Juan Castillo (programmer)
 * @author Pedro León (tester)
 * tested: YES
 */
public class Manager extends User {
	
	private String surname;
	/**
	 * tested: YES
	 * @param name
	 * @param username
	 * @param password
	 * @param surname
	 */
	public Manager(String name, String username, String password, String surname) {
		super(name, username, password);
		this.surname = surname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Manager [surname=" + surname + ", name=" + getName() + ", username=" + getUsername()
				+ ", ID=" + getID() + "]";
	}
	
	/***************************************************************************************************/
	/*
	 * Getters and Setters: no setters for ID and Counter
	 */
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	
}
