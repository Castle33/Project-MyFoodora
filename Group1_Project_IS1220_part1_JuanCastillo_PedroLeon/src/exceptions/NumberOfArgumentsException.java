package exceptions;

@SuppressWarnings("serial")
public class NumberOfArgumentsException extends Exception{

	public NumberOfArgumentsException(){
		super("Incorrect number of arguments - type help for further command information.");
	}

}
