package exceptions;

@SuppressWarnings("serial")
public class AccessDeniedException extends Exception {

	public AccessDeniedException(){
		super("Access Denied: Unauthorized user");
	}
}
