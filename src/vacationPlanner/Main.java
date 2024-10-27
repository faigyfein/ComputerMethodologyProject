package vacationPlanner;

import java.util.*;

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
			ArrayList<Friend> friends = getAllFriends(input);
			System.out.println(Calculator.calculate(friends));
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
	
	public static ArrayList<Friend> getAllFriends(Scanner input) {
		boolean addFriends = true;
		ArrayList<Friend> friends = new ArrayList<>();
		while(addFriends) {
			System.out.print("Friend Name: >> ");
			String name = input.nextLine();
			ArrayList<Item> items = getFriendItems(input);
			friends.add(new Friend(name, items));
			addFriends = getYesOrNoChoice(input, "Continue adding friends? ");
		}
		return friends;
		
	}
	
	public static ArrayList<Item> getFriendItems(Scanner input){
		boolean addItems = true;
		ArrayList<Item> items = new ArrayList<>();
		System.out.println("Enter Items Bought: ");
		while(addItems) {
			// Get Item Name
			System.out.print("Item Name >> ");
			String itemName = input.nextLine();
			
			// Get Price
			// TODO - Input Validation
			System.out.print("Item Price >> ");
			double price = input.nextDouble();
			input.nextLine(); // Clear Buffer
			
			// Get Quantity
			int quantity;
			do {
			System.out.print("Item Quantity >> ");
			quantity = getUserNumericChoice(input);
			}while(quantity < 1);
			
			items.add(new Item(itemName, price, quantity));
			
			addItems = getYesOrNoChoice(input, "Continue adding items?");
		}
		return items;
	}
	
	public static boolean getYesOrNoChoice(Scanner input, String message) {
		char answer = 'Y';
		do {
		System.out.print(message);
		System.out.print(" Y / N >> ");
		answer = input.nextLine().toUpperCase().charAt(0);
		}while(answer != 'Y' && answer != 'N');
		
		if(answer == 'Y') {
		return true;
		}
		
		return false;
		

	}
}
