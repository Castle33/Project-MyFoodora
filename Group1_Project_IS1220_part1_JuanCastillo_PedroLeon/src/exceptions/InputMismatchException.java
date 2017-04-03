package exceptions;

@SuppressWarnings("serial")
public class InputMismatchException extends Exception {
	
	public InputMismatchException(){
		super("Argument mismatch exception - usually with addresses and dates.");
	}

}
