package clui;

import system.*;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import restaurant_structure.Item;

/**
 * This class is the core of the CLUI. Is able to treat inputs from the console or from a text file,
 * parsing them and then processing the command.
 * 
 * @author Juan Castillo
 *
 */
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
	
	/**
	 * MyFoodora constructor, reads the initial setup from serialized core if file exists
	 * and gets the list of all commands that the user will later be able to introduce
	 */
	public MyFoodora() {
		super();
		this.name = "";
		this.commandReturn = null;
		this.args = new ArrayList<String>();
		MyFoodora.listTempMeals = new HashMap<String,ArrayList<Item>>();
		MyFoodora.listTempOrders = new ArrayList<Order>();
		MyFoodora.stringCast = new StringCast();
		MyFoodora.readCmdFile("src/texts/listCmd.txt");
		
		try{
			FileInputStream fileIn = new FileInputStream("./ser_file/core.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			core = (Core) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException e){
			System.out.println("Core file .ser not found Exception.");
			return;
		}catch(ClassNotFoundException e){
			System.out.println("Class Core not found Exception.");
			return;
		}
	}

	/**
	 * This method launches the command line interface, is able to stop the program
	 * when the user types <b>stop</b> and passes the input to <code>treatCmd</code>
	 */
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
	
	/**
	 * Treats an input string, separating the command name and its arguments
	 * @param s : the input string to read.
	 */
	public void treatCmd(String s) {
		
		if(s.equals("help")){
			System.out.println(displayListCmd());
		} else {
			String[] input = s.trim().split(" ");
			if(!checkCmdExists(input[0])){
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
	
	/**
	 * Reads and treats in order the commands from a text file and passes them to <code>treatCmd</code>
	 * @param filename : the name of the text file to read from
	 */
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
	
	/**
	 * Checks if a string is in <code>listCmds</code>
	 * @param name : string that wants to be checked
	 * @return <b>TRUE</b>, if <code>name</code> is in <code>listCmds</code>, <b>FALSE</b> otherwise
	 */
	public boolean checkCmdExists(String name){
		if(listCmds.contains(name)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Process a command with its given arguments, calling the appropriate function.
	 * @param command : string with the command name
	 * @param arguments : an ArrayList of the arguments of the command
	 */
	public void processCommand(String command, ArrayList<String> arguments){
		
		String[] argum;
		argum = listToArray(arguments);
		if(!command.equals("")){
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
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "additem2order":
				cmdProcessor = new AddItem2Order();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "addmeal2order":
				cmdProcessor = new AddMeal2Order();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "endorder":
				cmdProcessor = new EndOrder();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "onduty":
				cmdProcessor = new OnDuty();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "offduty":
				cmdProcessor = new OffDuty();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "finddeliverer":
				cmdProcessor = new FindDeliverer();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "setdeliverypolicy":
				cmdProcessor = new SetDeliveryPolicy();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "setprofitpolicy":
				cmdProcessor = new SetProfitPolicy();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "associatecard":
				cmdProcessor = new AssociateCard();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showcourierdeliveries":
				cmdProcessor = new ShowCourierDeliveries();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showrestauranttop":
				cmdProcessor = new ShowRestaurantTop();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showcustomers":
				cmdProcessor = new ShowCustomers();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showmenuitem":
				cmdProcessor = new ShowMenuItem();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showtotalprofit":
				cmdProcessor = new ShowTotalProfit();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showrestaurantsregistered":
				cmdProcessor = new ShowRestaurantsRegistered();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "showcouriersregistered":
				cmdProcessor = new ShowCouriersRegistered();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			case "initcore":
				cmdProcessor = new InitCore();
				setCommandReturn(cmdProcessor.process(argum));
				break;
			}
			System.out.println(getCommandReturn());
			setCommandReturn("");
		}
	}
	
	/**
	 * Converts the ArrayList listCmds to a string ready to be displayed in Console
	 * @return an adapted string to be displayed in Console
	 */
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
	
	/**
	 * Reads the file with the list of commands (name and arguments) and safes it to a static variable
	 * @param fileName : name of the text file to be read
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
	
	/**
	 * Converts an ArrayList to an array of strings
	 * @param args : the ArrayList to convert
	 * @return an array of strings with the converted values
	 */
	public static String[] listToArray(ArrayList<String> args) {
		String[] array = new String[args.size()];
		for(int i=0; i<array.length; i++){
			array[i] = args.get(i);
		}
		return array;
	}
	
	/**
	 * Converts an array of strings to an ArrayList
	 * @param args : the array of strings to convert
	 * @return an ArrayList with the converted values
	 */
	public static ArrayList<String> arrayToList(String[] args) {
		ArrayList<String> array = new ArrayList<String>();
		for(int i=0; i<args.length; i++){
			array.add(args[i]) ;
		}
		return array;
	}
	
	/**
	 * Search an item with a specific name in a specific meal and returns it if it is in the list
	 * @param itemName : name of the item to be found
	 * @param mealName : name of the meal where the item is
	 * @return the item with the name <code>itemName</code> or <b>NULL</b> otherwise
	 */
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
