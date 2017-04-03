package clui;

import java.util.Scanner;

public class testReadFrom {
	
	public static void main (String[] args){
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("¡Welcome to MyFoodora!\tCheck possible commands = type 'help'\tEnd program = type 'stop'");
		System.out.print(">");
		String message = sc.nextLine();
		while(!message.equals("stop")){
			System.out.println(message);
			System.out.print(">");
			message = sc.nextLine();
		}
		System.out.println("Leaving MyFoodora\tSee you later ;)");
		*/
		MyFoodora mf = new MyFoodora();
		mf.launcherCL();
	}

}
