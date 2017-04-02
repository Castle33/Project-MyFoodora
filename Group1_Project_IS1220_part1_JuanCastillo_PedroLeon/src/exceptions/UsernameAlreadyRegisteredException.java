package exceptions;

@SuppressWarnings("serial")
public class UsernameAlreadyRegisteredException extends Exception{

	public UsernameAlreadyRegisteredException(){
		super("This username already exists: try a different username");
	}
}
