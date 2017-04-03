package clui;

import system.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import restaurant_structure.Meal;

public class MyFoodora {
	protected static Core core;
	protected static StringCast stringCast;
	protected static ArrayList<Meal> listTempMeals;
	protected static ArrayList<Order> listTempOrders;
	private String name;
	private ArrayList<String> args;
	private static ArrayList<String> listCmds;
	
	
	public MyFoodora() {
		super();
		this.name = "";
		this.args = new ArrayList<String>();
		MyFoodora.listTempMeals = new ArrayList<Meal>();
		MyFoodora.core = new Core();
		MyFoodora.readCmdFile("listCmd.txt");
	}

	public void launcherCL() {
		Scanner sc;
		String input = "", 
			cmdNameOutput;
		try {
			sc = new Scanner(System.in);
			System.out.println("�Welcome to MyFoodora!\tCheck possible commands = type 'help'\tEnd program = type 'stop'");
			System.out.print(">");
			
			while(!input.equals("stop")){
				input = sc.nextLine();
				cmdNameOutput = readCmdNameArgs(input);
				System.out.println(cmdNameOutput);
				
				System.out.print(">");
			}
			System.out.println("Leaving MyFoodora\tSee you later!");
			sc.close();
			
		} catch (Exception e) {
			System.out.println("Unexpected error: Please try again in a few minutes");
		}
	}
	
	public String readCmdNameArgs(String s) {
		
		if(s.equals("help")){
			return displayListCmd();
			
		} else {
			String[] input = s.trim().split(" ");
			if(!checkCmdExists(input[0])){ //TO-DO boolean function that checks if name is in the list of Commands
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
				return "Command name " +name+ " read: checking if valid arguments...";
			} else if (input[0].equals("runTest")){
				//TO-DO: Read testScenario file and execute the commands
				return "";
			} else {
				return "Error reading arguments";
			}
			
		}
	}
	
	public boolean checkCmdExists(String name){
		if(listCmds.contains(name)){
			return true;
		} else {
			return false;
		}
	}
	
	public void processCommand(String command, ArrayList<String> arguments){
		if(!command.equals("")){
			switch (command.toLowerCase()){
			case "login":
				break;
			case "logout":
				break;
			case "registerrestaurant":
				break;
			case "registercustomer":
				break;
			case "registercourier":
				break;
			case "adddishrestaurantmenu":
				break;
			case "createmeal":
				break;
			case "adddish2meal":
				break;
			case "showmeal":
				break;
			case "savemeal":
				break;
			case "setspecialoffer":
				break;
			case "removefromspecialoffer":
				break;
			case "createorder":
				break;
			case "additem2order":
				break;
			case "endorder":
				break;
			case "onduty":
				break;
			case "offduty":
				break;
			case "finddeliver":
				break;
			case "setdeliverypolicy":
				break;
			case "setprofitpolicy":
				break;
			case "associatecard":
				break;
			case "showcouriersdeliveries":
				break;
			case "showrestauranttop":
				break;
			case "showcustomers":
				break;
			case "showmenuitem":
				break;
			case "showtotalprofit":
				break;
			}
		}
	}
	
	public String displayListCmd(){
		int counter = 0;
		int prevValue = 0;
		String returnValue = "";
		for(int i=0; i<listCmds.size(); i++){
			if(listCmds.get(i).charAt(0)!='<'){
				counter++;
			}
			if(counter==2){
				for(int j=prevValue; j<i;j++){
					returnValue += listCmds.get(j);
					if(j!=i-1){
						returnValue += " ";
					}
				}
				returnValue += "\n";
				counter = 0;
				prevValue = i;
			}
		}
		return returnValue;
	}
	public void readTestScenario(String filename){
		File file = new File(filename);
		Scanner sc = null;
		String input = "", 
				cmdNameOutput;
		
		try {
			try {
				sc = new Scanner(file);
				System.out.println("Processing commands from file " + filename);
				while(sc.hasNextLine()){
					input = sc.nextLine();
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found: please enter a valid file name");
			}
		} catch (Exception e) {
			System.out.println("Unexpected error: Please try again in a few minutes");
		} finally {
			sc.close();
		}
		
		
	}
	
	/*
	 * Reads the file with the list of commands (name and arguments) and safes it to a static variable
	 * Example: login <username> <password>
	 */
	public static void readCmdFile(String fileName) {
		  
		  String stringCmds = "";
		  FileReader file = null;
		  BufferedReader reader = null;
		  
		  try {
			  // open input stream pointing at fileName
			  file = new FileReader(fileName);
			  
			  // open input buffered reader to read file line by line
			  reader = new BufferedReader(file);
			  String line = "";
			  
			  // reading input file line by line
			  while ((line = reader.readLine()) != null) {
				  stringCmds += line + " ";
			  }
		  } catch (Exception e) {
		      throw new RuntimeException(e);
		  } finally {
		    if (file != null) {
		      try {
		        file.close();
		        reader.close();
		       
		      } catch (IOException e) {
		    	  System.out.println("File not found: " + fileName);
		        // Ignore issues during closing 
		      }
		    }
		  }
		  String[] tempListCmd = stringCmds.split(" ");
		  listCmds = arrayToList(tempListCmd);
		} 
	
	public static String[] listToArray(ArrayList<String> args) {
		String[] array = new String[args.size()];
		for(int i=0; i<array.length; i++){
			array[i] = args.get(i);
		}
		return array;
	}
	
	public static ArrayList<String> arrayToList(String[] args) {
		ArrayList<String> array = new ArrayList<String>();
		for(int i=0; i<args.length; i++){
			array.add(args[i]) ;
		}
		return array;
	}
}
