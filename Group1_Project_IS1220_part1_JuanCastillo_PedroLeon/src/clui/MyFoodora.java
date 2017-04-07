package clui;

import system.*;
import java.util.Scanner;

import exceptions.InputMismatchException;
import exceptions.NumberOfArgumentsException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import restaurant_structure.Item;
import restaurant_structure.Meal;

public class MyFoodora {
	protected static Core core;
	protected static StringCast stringCast;
	protected static HashMap<String,ArrayList<Item>> listTempMeals;
	protected static ArrayList<Order> listTempOrders;
	private String name;
	private ArrayList<String> args;
	private static ArrayList<String> listCmds;
	private CommandProcessor cmdProcessor;
	private String commandReturn;
	
	
	public MyFoodora() {
		super();
		this.name = "";
		this.commandReturn = null;
		this.args = new ArrayList<String>();
		MyFoodora.listTempMeals = new HashMap<String,ArrayList<Item>>();
		MyFoodora.listTempOrders = new ArrayList<Order>();
		MyFoodora.core = new Core();
		MyFoodora.stringCast = new StringCast();
		MyFoodora.readCmdFile("src/texts/listCmd.txt");
	}

	public void launcherCL() {
		Scanner sc;
		String input = "";
		try {
			sc = new Scanner(System.in);
			System.out.println("¡Welcome to MyFoodora!\tCheck possible commands = type 'help'\tEnd program = type 'stop'");
			System.out.print(">");
			
			while(!input.equals("stop") && sc.hasNextLine()){
				input = sc.nextLine();
				treatCmd(input);
				System.out.print(">");
			}
			System.out.println("Leaving MyFoodora\tSee you later!");
			sc.close();
			
		} catch (Exception e) {
			//System.out.println("Unexpected error: Please try again in a few minutes");
			e.printStackTrace();
		}
	}
	
	public void treatCmd(String s) {
		
		if(s.equals("help")){
			System.out.println(displayListCmd());
			
		} else {
			String[] input = s.trim().split(" ");
			if(!checkCmdExists(input[0])){ //TO-DO boolean function that checks if name is in the list of Commands
				name = "";
				System.out.println("Error finding command: Make sure the command is correctly introduced");
			} else {
				name = input[0];
			}
			if(input.length>1){
				//Split arguments by ""
				ArrayList<String> temp_string = new ArrayList<String>();
				for(int i = 1; i < input.length; i++){
					if(input[i].charAt(0) != '"' || input[i].charAt(input[i].length()-1) !='"') {
						System.out.println("Syntax Error: Arguments must be surrounded by quotation marks");
					} else {
						//Eliminates quotation marks from argument and adds it to the temporary list
						temp_string.add(input[i].substring(1, input[i].length()-1));
					}
				}
				args = temp_string;
				System.out.println("Command name " +name+ " read: checking if valid arguments...");
				processCommand(name, args);
				
			} else if (input[0].equals("runTest")){
				readTestScenario("testScenario.txt");
				
			} else {
				System.out.println("Error reading arguments: argumetns could not be found");
			}
			
		}
	}
	
	public void readTestScenario(String filename){
		File file = new File(filename);
		Scanner sc = null;
		String input = "";
		
		try {
			try {
				sc = new Scanner(file);
				System.out.println("Processing commands from file " + filename + " ...");
				while(sc.hasNextLine()){
					input = sc.nextLine();
					if(!input.isEmpty()){
						input.replaceAll("<", "\"");
						input.replaceAll(">", "\"");
						treatCmd(input);
					}
				}
				System.out.println("All commands from file "+filename+" processed");
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found: please enter a valid file name");
			}
		} catch (Exception e) {
			System.out.println("Unexpected error: Please try again in a few minutes");
		} finally {
			sc.close();
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
		
		String[] argum;
		argum = listToArray(arguments);
		if(!command.equals("")){
			
			try{
				
				switch (command.toLowerCase()){
				case "login":
					cmdProcessor = new LogIn();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "logout":
					cmdProcessor = new LogOut();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "registerrestaurant":
					cmdProcessor = new RegisterRestaurant();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "registercustomer":
					cmdProcessor = new RegisterCustomer();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "registercourier":
					cmdProcessor = new RegisterCourier();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "adddishrestaurantmenu":
					cmdProcessor = new AddDishRestaurantMenu();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "createmeal":
					cmdProcessor = new CreateMeal();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "adddish2meal":
					cmdProcessor = new AddDish2Meal();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "removeitemfrommeal":
					cmdProcessor = new RemoveItemFromMeal();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "showmeal":
					cmdProcessor = new ShowMeal();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "savemeal":
					cmdProcessor = new SaveMeal();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "setspecialoffer":
					cmdProcessor = new SetSpecialOffer();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "removefromspecialoffer":
					cmdProcessor = new RemoveSpecialOffer();
					setCommandReturn(cmdProcessor.process(argum));
					break;
				case "createorder":
					cmdProcessor = new CreateOrder();
					cmdProcessor.process(argum);
					break;
				case "additem2order":
					cmdProcessor = new AddItem2Order();
					cmdProcessor.process(argum);
					break;
				case "endorder":
					cmdProcessor = new EndOrder();
					cmdProcessor.process(argum);
					break;
				case "onduty":
					cmdProcessor = new OnDuty();
					cmdProcessor.process(argum);
					break;
				case "offduty":
					cmdProcessor = new OffDuty();
					cmdProcessor.process(argum);
					break;
				case "finddeliverer":
					cmdProcessor = new FindDeliverer();
					cmdProcessor.process(argum);
					break;
				case "setdeliverypolicy":
					cmdProcessor = new SetDeliveryPolicy();
					cmdProcessor.process(argum);
					break;
				case "setprofitpolicy":
					cmdProcessor = new SetProfitPolicy();
					cmdProcessor.process(argum);
					break;
				case "associatecard":
					cmdProcessor = new AssociateCard();
					cmdProcessor.process(argum);
					break;
				case "showcourierdeliveries":
					cmdProcessor = new ShowCourierDeliveries();
					cmdProcessor.process(argum);
					break;
				case "showrestauranttop":
					cmdProcessor = new ShowRestaurantTop();
					cmdProcessor.process(argum);
					break;
				case "showcustomers":
					cmdProcessor = new ShowCustomers();
					cmdProcessor.process(argum);
					break;
				case "showmenuitem":
					cmdProcessor = new ShowMenuItem();
					cmdProcessor.process(argum);
					break;
				case "showtotalprofit":
					cmdProcessor = new ShowTotalProfit();
					cmdProcessor.process(argum);
					break;
				}
			}catch (NumberOfArgumentsException e){
				System.out.println("Error in arguments: Wrong number of arguments for command "+command);
			}catch (InputMismatchException e1){
				System.out.println("Error in arguments: Wrong type of arguments for command "+command);
			}finally{
				System.out.println(getCommandReturn());
				setCommandReturn("");
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
				counter = 1;
				prevValue = i;
			}
		}
		return returnValue;
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
	
	public static Item getItemByName(String itemName, String mealName){
		Item itemFound = null;
		for(String meal : listTempMeals.keySet()){
			if(meal.equals(mealName)){
				for(Item item : listTempMeals.get(mealName)){
					if(item.getName().equals(itemName)){
						itemFound = item;
					}
				}
			}
		}
		return itemFound;
	}
	
	public String getCommandReturn(){
		return commandReturn;
	}
	
	public void setCommandReturn(String cr){
		commandReturn = cr;
	}

	/**
	 * @return the core
	 */
	public static Core getCore() {
		return core;
	}

	/**
	 * @param core the core to set
	 */
	public static void setCore(Core core) {
		MyFoodora.core = core;
	}
	
	
}
