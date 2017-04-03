package clui;

import system.*;
import java.util.Scanner;

public class MyFoodora {
	protected static Core core = new Core();
	protected static StringCast stringCast;
	private String name;
	private String[] args;
	
	public void launcherCL() {
		Scanner sc;
		String input = "";
		try {
			sc = new Scanner(System.in);
			System.out.println("¡Welcome to MyFoodora!\tCheck possible commands = type 'help'\tEnd program = type 'stop'");
			System.out.print(">");
			
			while(!input.equals("stop")){
				input = sc.nextLine();
				
				System.out.print(">");
			}
			System.out.println("Leaving MyFoodora\tSee you later ;)");
			sc.close();
			
		} catch (Exception e) {
			System.out.println("Unexpected error: Please try again in a few minutes");
		}
	}
	
	public void readCmdName(String s) {
		
		if(s.equals("help")){
			//TO-DO : display list of commands
		} else {
			String[] input = s.trim().split(" ");
			//Split arguments by ""
			
			
		}
	}
}
