package users;

import java.io.Serializable;

/**
 * 
 * This class allows to implement different types of users
 * 
 * @author Juan Castillo (programmer)
 * @author Pedro Leon (tester) 
 *
 */

public abstract class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3632256084770939081L;
	private String name;
	private String username;
	private String password;
	private int ID;
	private static int counter;

	/**
	 * Constructor with name and username
	 * @param name
	 * @param username
	 */
	public User(String name, String username) {
		super();
		this.name = name;
		this.username = username;
		this.ID= ++counter;
	}

	/**
	 * Constructor with name, username and password
	 * @param name
	 * @param username
	 * @param password
	 */
	public User(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.ID= ++counter;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (ID != other.ID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	/***************************************************************************************************/
	/*
	 * Getters and Setters: no setters for ID and Counter
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public static int getCounter() {
		return counter;
	}
	
	
}
