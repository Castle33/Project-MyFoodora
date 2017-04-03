package clui;

import system.*;
import java.util.Scanner;
import java.util.ArrayList;
import restaurant_structure.Meal;

public class MyFoodora {
	protected static Core core = new Core();
	protected static StringCast stringCast;
	private String name;
	private ArrayList<String> args;
	protected static ArrayList<Meal> listTempMeals;
	
	
	public MyFoodora() {
		super();
		this.name = "";
		this.args = new ArrayList<String>();
		MyFoodora.listTempMeals = new ArrayList<Meal>();
	}

	public void launcherCL() {
		Scanner sc;
		String input = "", 
			cmdNameOutput;
		try {
			sc = new Scanner(System.in);
			System.out.println("¡Welcome to MyFoodora!\tCheck possible commands = type 'help'\tEnd program = type 'stop'");
			System.out.print(">");
			
			while(!input.equals("stop")){
				input = sc.nextLine();
				cmdNameOutput = readCmdNameArgs(input);
				System.out.print(">");
			}
			System.out.println("Leaving MyFoodora\tSee you later ;)");
			sc.close();
			
		} catch (Exception e) {
			System.out.println("Unexpected error: Please try again in a few minutes");
		}
	}
	
	public String readCmdNameArgs(String s) {
		
		if(s.equals("help")){
			//TO-DO : display list of commands
		} else {
			String[] input = s.trim().split(" ");
			if(!input[0]){ //TO-DO boolean function that checks if name is in the list of Commands
				name = "";
				return "Error finding command: Make sure the command is correctly introduced";
			} else {
				name = input[0];
			}
			if(input.length>1){
				//Split arguments by ""
				ArrayList<String> temp_string = new ArrayList<String>();
				for(int i = 1; i < input.length; i++){
					if(input[i].charAt(0) != '"' || input[i].charAt(input[i].length()-1) !='"') {
						return "Sintax Error: Arguments must be surrounded by quotation marks";
					} else {
						//Eliminates quotation marks from argument and adds it to the temporary list
						temp_string.add(input[i].substring(1, input[i].length()-1));
					}
				}
				args = temp_string;
				return "Command name " +name+ " read: checking if valid arguments";
			} else {
				return "Error reading arguments";
			}
			
		}
	}
	
	public boolean checkCmdExists(String name){
		
	}
	
	public void processCommand(String name, ArrayList<String> args){
		if(!name.equals("")){
			
		}
	}
	
	
	public String[] listToArray(ArrayList<String> args) {
		String[] array = new String[args.size()];
		for(int i=0; i<array.length; i++){
			array[i] = args.get(i);
		}
		return array;
	}
}
