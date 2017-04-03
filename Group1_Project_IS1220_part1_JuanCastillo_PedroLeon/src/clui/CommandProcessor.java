package clui;

import exceptions.NumberOfArgumentsException;
import exceptions.InputMismatchException;

public interface CommandProcessor {
	String process(String[] args) throws NumberOfArgumentsException, InputMismatchException;

}
