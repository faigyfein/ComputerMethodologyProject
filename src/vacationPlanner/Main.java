package vacationPlanner;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numOfMainMenuChoices = 2;  // Number of options in the main menu - to be updated as menu is updated
		int choice = 0;                // Placeholder
		boolean exit = false;
		
		do {                // Do while choice is not to exit
		do {                // Do while choice not a choice in the menu
		displayMainMenu();  // Display the main menu
		
			/*
			 * Get User Numeric Choice will get a number from the user.
			 * If the user enters a non-numeric character, the menu will return -1, triggering the while loop to run again
			 * The while will ensure that the choice is 1 or higher and lower than the number of menu choices
			 */
			choice = getUserNumericChoice(input);
		}while(choice < 1 || choice > numOfMainMenuChoices);
		
		switch(choice) {
		case 1:    // 1. Calculator
			calculator(input);
			break;
		case 2:    // 2. Exit
			System.out.println("Exiting...");
			exit = true;
			break;
		default:
			System.out.println("Error");
		}
		}while(!exit);
		
	}

	/**
	 * This method will display the main menu
	 */
	public static void displayMainMenu() {
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Calculator");
		System.out.println("2. Exit");
	}
	
	/**
	 * @param input Scanner
	 * This method ensures that the response from the user is fully numeric
	 * @return Integer response, -1 if the String is not completely numeric
	 */
	public static int getUserNumericChoice(Scanner input) {
		   String answer = input.nextLine();
		   char[] array = answer.toCharArray();
		   for(int index = 0; index < array.length; index++) {
			  if(!Character.isDigit(array[index])) {
				  System.out.println("Not a digit.");
				  return -1; // return an integer value that would cause the do-while loop to run again, thereby getting a new user response
			  } 
		   }
		   return Integer.parseInt(answer);
		   
	   }
	
	public static void calculator(Scanner input) {
	 System.out.println("To-Do - Calculator");
	}
}
