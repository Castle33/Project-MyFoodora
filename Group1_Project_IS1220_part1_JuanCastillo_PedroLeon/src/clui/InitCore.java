package clui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import exceptions.NumberOfArgumentsException;
import system.Core;

/**
 * initCore "coreFile.ser"
 * temporary command to create Core.ser
 * 
 * @author Pedro León
 *
 */

public class InitCore implements CommandProcessor {
	final int nArgs = 1;
	
	@Override
	public String process(String[] args) {
		try{
			if (args.length == nArgs){
				Core c = MyFoodora.getCore();
				FileOutputStream fileOut = new FileOutputStream(args[0].toString().toLowerCase());
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(c);
				out.close();
				fileOut.close();
				return "Core serialized";
			}else{
				throw new NumberOfArgumentsException();
			}
		}catch(NumberOfArgumentsException e){
			return e.getMessage();
		}catch(FileNotFoundException e){
			return e.getMessage();
		}catch(IOException e){
			return e.getMessage();
		}
	}
}
