package vacationPlanner;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int numOfMainMenuChoices = 2;  // Number of options in the main menu - to be updated as menu is updated
		int choice = 0;                // Placeholder
		boolean exit = false;
		
		do {                // Do while choice is not to exit
			displayMainMenu();
			choice = getIntegerInput(input);			
		
			switch(choice) {
			case 1:    // 1. Calculator
				System.out.println(" **** Calculator **** ");
				System.out.println("Important Notification: Due to the Jewish laws of Ribbis, calculator rounds cents down. By following these calculations, users may be underpaid by a fraction of a cent");
				ArrayList<Friend> friends = getAllFriends(input);
				System.out.println(Calculator.calculate(friends));
				break;
			case 2:    // 2. Exit
				System.out.println("Exiting...");
				exit = true;
				break;
			default:
				System.out.println("This is not a valid choice. Please reenter a number between 1 and " + numOfMainMenuChoices + ".");
			}
		}while(!exit);
		
	}

	/**
	 * This method will display the main menu.
	 */
	public static void displayMainMenu() {
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Calculator");
		System.out.println("2. Exit");
	}
	/**
	 * This method validates that the response from the user is an integer, if it is not an integer it continues to reprompt the user until the 
	 * response is an integer.
	 * @param input Scanner for reading user input.
	 * @return The integer value from the user.
	 */
	public static int getIntegerInput(Scanner input) {
		int answer = 0;
		boolean validInput = false;
		while(!validInput) {
			try {
				answer = input.nextInt();
				validInput = true;
			}catch (InputMismatchException e) {
				System.out.println("That is not an integer. Please enter an integer.");
				input.nextLine(); //clear invalid input
			}
		}
		input.nextLine(); //clear buffer
		return answer;
	}
	/**
	 * This method validates that the response from the user is a double, if it not a double it continues to reprompt the user until the response
	 * is a double.
	 * @param input Scanner for reading user input.
	 * @return The double value from the user.
	 */
	public static double getDoubleInput(Scanner input) {
		double answer = 0.0;
		boolean validInput = false;
		while(!validInput) {
			try {
				answer = input.nextDouble();
				validInput = true;
			}catch (InputMismatchException e) {
				System.out.println("Invalid input. Please try again: ");
				input.nextLine(); //clear invalid input
			}
		}
		input.nextLine(); //clear buffer 
		return answer;
		
	}
	/**
	 * This method collects a list of friends and their purchased items.
	 * @param input Scanner for reading user input.
	 * @return An ArrayList of Friend objects.
	 */
	public static ArrayList<Friend> getAllFriends(Scanner input) {
		ArrayList<Friend> friends = new ArrayList<>();
		
		boolean addFriends = true;
		while(addFriends) {
			System.out.print("Friend Name: >> ");
			String name = input.nextLine();
			ArrayList<Item> items = getFriendItems(input);
			friends.add(new Friend(name, items));
			addFriends = getYesOrNoChoice(input, "Continue adding friends? ");
		}
		return friends;
	}
	/**
	 * This method collects items purchased by a Friend.
	 * @param input Scanner for reading user input.
	 * @return An ArrayList of Item objects.
	 */
	public static ArrayList<Item> getFriendItems(Scanner input){
		ArrayList<Item> items = new ArrayList<>();
		System.out.println("Enter Items Bought: ");
		
		boolean addItems = true;
		while(addItems) {
			System.out.print("Item Name >> ");
			String itemName = input.nextLine();
			
			System.out.print("Item Price >> ");
			double price = verifyPrice(input);
			
			System.out.print("Item Quantity >> ");
			int quantity = verifyQuantity(input);
			
			
			items.add(new Item(itemName, price, quantity));
			addItems = getYesOrNoChoice(input, "Continue adding items?");
		}
		return items;
	}
	/**
	 * This method verifies that the price is a valid positive double value (greater than 0).
	 * @param input Scanner for reading user input.
	 * @return The validated price (greater than 0).
	 */
	public static double verifyPrice(Scanner input) {
		double price = 0;
		boolean validInput = false;
		
		while(!validInput) {
			price = getDoubleInput(input);
			if(price > 0) {
				validInput = true;
			}else {
				System.out.println("Invalid price. Must be greater than 0.");
			}
		}
		return price;
	}
	/**
	 * This method verifies that the quantity is a valid positive integer (1 or greater).
	 * @param input Scanner for reading user input.
	 * @return The validated quantity (greater than or equal to 1).
	 */
	public static int verifyQuantity(Scanner input) {
		int quantity = 0;
		boolean validInput = false;
		
		while(!validInput) {
			quantity = getIntegerInput(input);
			if(quantity >= 1) {
				validInput = true;
			}else {
				System.out.println("Invalid quantity. Must be 1 or greater.");
			}
		}
		return quantity;
	}
	/**
	 * This method prompts the user for a yes('Y') or no('N') choice.
	 * @param input Scanner for reading user input.
	 * @param message The passed in message to display.
	 * @return If this user chooses 'Y' then true, false otherwise.
	 */
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
