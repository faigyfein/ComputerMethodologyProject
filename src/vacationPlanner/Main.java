package vacationPlanner;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		ArrayList<Friend> friends = new ArrayList<>();// ArrayList of friends to be added to and calculated
		Scanner input = new Scanner(System.in);
		int numOfMainMenuChoices = 4; // Number of options in the main menu - to be updated as menu is updated
		int numOfCalculatorMenuChoices = 5; // Number of options in the Calculator menu - to be updated as Calculator is
											// updated
		int choice = 0; // Placeholder
		boolean exit = false;

		do { // Do while choice is not to exit
			displayMainMenu();
			choice = getIntegerInput(input);
			switch (choice) {
			case 1:// Costs Calculator
				executeCalculatorMenu(friends, input, numOfCalculatorMenuChoices);
				break;
			case 2:// Weather
				weather(input);
				break;
			case 3: // Itinerary
				itinerary(input, friends);
				break;
			case 4:// Exit
				System.out.println("Exiting...");
				exit = true;
				break;
			default:
				System.out.println("This is not a valid choice. Please reenter a number between 1 and "
						+ numOfMainMenuChoices + ".");
			}
		} while (!exit);

	}

	private static void itinerary(Scanner input, ArrayList<Friend> friends) {
		boolean exit = false;
		do {

			displayItineraryMenu();
			int choice = getIntegerInput(input);
			switch (choice) {
			case 1:
				getFriendActivities(friends, findFriend(friends, input), input);
				break;
			case 2:
				displaySortedItinerary(friends);
				break;
			case 3:
				exit = true;
				break;
			default:
				System.out.println("Invalid Choice");
			}
		} while (!exit);

	}

	private static void displaySortedItinerary(ArrayList<Friend> friends) {
		ArrayList<Activity> allActivities = new ArrayList<>();

		for (Friend friend : friends) {
			allActivities.addAll(friend.getActivities());
		}

		Collections.sort(allActivities);

		for (Activity activity : allActivities) {
			System.out.println(activity);
		}

	}

	private static void displayItineraryMenu() {
		System.out.println("ITINERARY MENU");
		System.out.println("1) Add Activity (Paying Friend must already exist)");
		System.out.println("2) Display Itinerary ");
		System.out.println("3) Exit");

	}

	/**
	 * @param input Scanner for user input This method organizes the method calls to
	 *              execute the weather functionality
	 */
	public static void weather(Scanner input) {
		String city = getCityChoiceFromUser(input);
		char temp = getMeasurementTypeFromUser(input);
		Weather.getWeather(city, temp);
		System.out.println();
	}

	/**
	 * 
	 * @param input Scanner for user input Gets and validates user choice for
	 *              degrees in C or F
	 * @return C or F
	 */
	public static char getMeasurementTypeFromUser(Scanner input) {
		System.out.print("Would you like the temperature in °C or °F? ");
		char temp = input.nextLine().toUpperCase().charAt(0);
		while (temp != 'C' && temp != 'F') {
			System.out.print("Invalid. Please enter either a " + "C for celsius or a F for fahrenheit. ");
			temp = input.nextLine().toUpperCase().charAt(0);
		}
		return temp;
	}

	/**
	 * 
	 * @param input Scanner for User Input Gets the city choice from the user
	 * @return City Name
	 */
	public static String getCityChoiceFromUser(Scanner input) {
		System.out.print("Which city are you located at? ");
		String city = input.nextLine();
		return city;
	}

	/**
	 * 
	 * @param friends ArrayList of Friends Calculate which friend pays for what,
	 *                print results
	 */
	public static void calculateFriendPayments(ArrayList<Friend> friends) {
		System.out.println(" **** Calculate All Payments **** ");
		System.out.println("Important Notification: Due to the Jewish laws of Ribbis, calculator rounds cents down.");
		System.out.println("By following these calculations, users may be underpaid by a fraction of a cent.");
		System.out.println(Calculator.calculate(friends));
	}

	/**
	 * This method will display the main menu.
	 */
	public static void displayMainMenu() {
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Payments Calculator");
		System.out.println("2. Find out the weather in your location");
		System.out.println("3. Display your sorted itinerary");
		System.out.println("4. Exit");
	}

	/**
	 * This method will display the cost calculator menu.
	 */
	public static void displayPaymentsCalculatorMenu() {
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Add Friends, their items, and their activites");
		System.out.println("2. Add Items to a Friend");
		System.out.println("3. Add Activities to a Friend");
		System.out.println("4. Display friends, all their items, and all their activities");
		System.out.println("5. Calculate All Payments");
		System.out.println("6. Exit");
	}

	/**
	 * This method will display the cost calculator menu and then handle option
	 * choice, calling the necessary methods.
	 */
	public static void executeCalculatorMenu(ArrayList<Friend> friends, Scanner input, int numOfCalculatorMenuChoices) {
		int numOfMenuChoices = numOfCalculatorMenuChoices;
		int choice;
		boolean exit = false;
		do {
			displayPaymentsCalculatorMenu();
			choice = getIntegerInput(input);

			switch (choice) {
			case 1: // 2. Add Friends
				getFriends(friends, input);
				break;
			case 2: // 3. Add Items
				getFriendItems(friends, findFriend(friends, input), input);
				break;
			case 3:
				getFriendActivities(friends, findFriend(friends, input), input);
				break;
			case 4:
				displayAllFriends(friends);
				break;
			case 5:
				displayTripTotal(friends);
				calculateFriendPayments(friends);
				break;
			case 6:
				System.out.println("Exiting...");
				exit = true;
				break;
			default:
				System.out.println(
						"This is not a valid choice. Please reenter a number between 1 and " + numOfMenuChoices + ".");
			}
		} while (!exit);
	}

	/**
	 * This method calculates the trip total.
	 * 
	 * @param friends The ArrayList of Friends.
	 * @return The total cost of the trip.
	 */
	public static double calculateTripTotal(ArrayList<Friend> friends) {
		double total = 0.0;
		for (Friend friend : friends) {
			total += friend.getTotalSpent();
		}
		return total;
	}

	/**
	 * This method displays the trip total.
	 * 
	 * @param friends The ArrayList of Friends.
	 */
	public static void displayTripTotal(ArrayList<Friend> friends) {
		double total = calculateTripTotal(friends);
		System.out.println("The total spend on the trip so far is: $" + String.format("%.2f", total));
	}

	/**
	 * This method validates that the response from the user is an integer, if it is
	 * not an integer it continues to reprompt the user until the response is an
	 * integer.
	 * 
	 * @param input Scanner for reading user input.
	 * @return The integer value from the user.
	 */
	public static int getIntegerInput(Scanner input) {
		int answer = 0;
		boolean validInput = false;
		while (!validInput) {
			try {
				answer = input.nextInt();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("That is not an integer. Please enter an integer.");
				input.nextLine(); // clear invalid input
			}
		}
		input.nextLine(); // clear buffer
		return answer;
	}

	/**
	 * This method validates that the response from the user is a double, if it not
	 * a double it continues to reprompt the user until the response is a double.
	 * 
	 * @param input Scanner for reading user input.
	 * @return The double value from the user.
	 */
	public static double getDoubleInput(Scanner input) {
		double answer = 0.0;
		boolean validInput = false;
		while (!validInput) {
			try {
				answer = input.nextDouble();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please try again: ");
				input.nextLine(); // clear invalid input
			}
		}
		input.nextLine(); // clear buffer
		return answer;

	}

	/**
	 * This method collects a list of friends (validating that a all friends names
	 * and zelle combination are unique), their purchased items and activities.
	 * 
	 * @param input Scanner for reading user input.
	 * @return An ArrayList of Friend objects.
	 */
	public static void getFriends(ArrayList<Friend> friends, Scanner input) {
		boolean addFriends = true;
		boolean addItems;
		boolean addActivities;
		while (addFriends) {
			System.out.print("Friend Name: >> ");
			String name = input.nextLine();
			System.out.print("Zelle Information: >> ");
			String zelleInfo = input.nextLine();
			if (duplicateFriend(friends, name, zelleInfo)) {
				System.out.println("Friend already exists");
			} 
			else {
				friends.add(new Friend(name, zelleInfo));
				addItems = getYesOrNoChoice(input, "Add Items to Friend? ");
				if (addItems) {
					getFriendItems(friends, friends.size() - 1, input);
				}

				addActivities = getYesOrNoChoice(input, "Add Activities to Friend? ");
				if (addActivities) {
					getFriendActivities(friends, friends.size() - 1, input);
				}				
			}
			addFriends = getYesOrNoChoice(input, "Continue adding friends? ");
		}
	}

	/**
	 * This method checks if a friend with the specified name and zelle informaation
	 * already exists in the friends arrayList. If a duplicate name and zelle
	 * information is found, it returns true. If not both, returns false.
	 * 
	 * @param friends An ArrayList of Friend objects.
	 * @param name    The specified name
	 * @param zelle   Zelle contact
	 */
	public static boolean duplicateFriend(ArrayList<Friend> friends, String name, String zelle) {
		for (Friend friend : friends) {
			if (friend.getFriendName().equalsIgnoreCase(name)) {
				if (friend.getZelleInfo().equalsIgnoreCase(zelle)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method collects items purchased by a Friend.
	 * 
	 * @param friends The ArrayList of Friends.
	 * @param index   The index in the friends arrayList of the friends to add to.
	 * @param input   Scanner for reading user input.
	 */
	public static void getFriendItems(ArrayList<Friend> friends, int index, Scanner input) {
		boolean addItems;
		do {
			if (index == -1)
				break;
			System.out.println("Enter Item Bought: ");

			System.out.print("Item Name >> ");
			String itemName = input.nextLine();

			System.out.print("Item Price >> ");
			double price = verifyPrice(input);

			System.out.print("Item Quantity >> ");
			int quantity = verifyQuantity(input);

			friends.get(index).addItem(new Item(itemName, price, quantity));
			addItems = getYesOrNoChoice(input, "Continue adding items?");
		} while (addItems);
	}

	/**
	 * This method collects activities purchased by a Friend.
	 * 
	 * @param friends The ArrayList of Friends.
	 * @param index   The index in the friends arrayList of the friends to add to.
	 * @param input   Scanner for reading user input.
	 */
	public static void getFriendActivities(ArrayList<Friend> friends, int index, Scanner input) {
		boolean addActivities;
		do {
			if (index == -1)
				break;
			System.out.println("Enter Activity Name: ");

			System.out.println("Activity Name >> ");
			String activityName = input.nextLine();

			System.out.println("Activity Price >> ");
			double price = verifyPrice(input);

			int year, month, day, hour, minute;
			do {
				System.out.println("Activity Date (Year) >> ");
				year = getIntegerInput(input);
			} while (year < LocalDateTime.now().getYear()); // Can't be in the past

			do {
				System.out.println("Activity Date (Month) >> ");
				month = getIntegerInput(input);
			} while (month < 1 || month > 12);

			do {
				System.out.println("Activity Date (Day) >> ");
				day = getIntegerInput(input);
			} while (day < 1 || day > 31); // TODO - Provide better input validation here!!

			do {
				System.out.println("Activity Time (Hour) >> ");
				hour = getIntegerInput(input);
			} while (hour < 1 || hour > 24);

			do {
				System.out.println("Activity Time (Minute) >> ");
				minute = getIntegerInput(input);
			} while (minute < 1 || minute > 60);

			friends.get(index).addActivity(new Activity(activityName, price, year, month, day, hour, minute));
			addActivities = getYesOrNoChoice(input, "Continue adding activities paid from this friend?");
		} while (addActivities);
	}

	/**
	 * Asks user for Friend to add to, and returns the index of the chosen friend
	 * 
	 * @param friends ArrayList of Friend objects
	 * @param input   Scanner for user input
	 * @return index of chosen friend
	 */
	public static int findFriend(ArrayList<Friend> friends, Scanner input) {
		boolean friendFound = false;
		String friend;
		int tryCount = 0;
		while (!friendFound) {
			System.out.print("Friend to add to >> ");
			friend = input.nextLine();
			for (int i = 0; i < friends.size(); i++) {
				if (friend.equalsIgnoreCase(friends.get(i).getFriendName())) {
					return i;
				}
			}
			System.out.println("Friend not found, please try again:");
			tryCount++;
			if (tryCount == 3) {
				System.out.println("Exiting. Too many attempts were tried\n");
				break;
			}
		}
		return -1; // extraneous, but allows it to compile
	}

	/**
	 * This method verifies that the price is a valid positive double value (greater
	 * than 0).
	 * 
	 * @param input Scanner for reading user input.
	 * @return The validated price (greater than 0).
	 */
	public static double verifyPrice(Scanner input) {
		double price = 0;
		boolean validInput = false;

		while (!validInput) {
			price = getDoubleInput(input);
			if (price >= 0) {
				validInput = true;
			} else {
				System.out.println("Invalid price. Must be greater or equal to 0.");
			}
		}
		return price;
	}

	/**
	 * This method verifies that the quantity is a valid positive integer (1 or
	 * greater).
	 * 
	 * @param input Scanner for reading user input.
	 * @return The validated quantity (greater than or equal to 1).
	 */
	public static int verifyQuantity(Scanner input) {
		int quantity = 0;
		boolean validInput = false;

		while (!validInput) {
			quantity = getIntegerInput(input);
			if (quantity >= 1) {
				validInput = true;
			} else {
				System.out.println("Invalid quantity. Must be 1 or greater.");
			}
		}
		return quantity;
	}

	/**
	 * This method prompts the user for a yes('Y') or no('N') choice.
	 * 
	 * @param input   Scanner for reading user input.
	 * @param message The passed in message to display.
	 * @return If this user chooses 'Y' then true, false otherwise.
	 */
	public static boolean getYesOrNoChoice(Scanner input, String message) {
		char answer = 'Y';
		do {
			System.out.print(message);
			System.out.print(" Y / N >> ");
			answer = input.nextLine().toUpperCase().charAt(0);
		} while (answer != 'Y' && answer != 'N');

		if (answer == 'Y') {
			return true;
		}
		return false;
	}

	/**
	 * This method displays all the Friends, their Items name and price and
	 * quantity, and their Activities name and price.
	 * 
	 * @param friends The ArrayList of Friend objects.
	 */

	public static void displayAllFriends(ArrayList<Friend> friends) {
		if (friends.isEmpty()) {
			System.out.println("No friends added yet.");
			return;
		} else {
			System.out.println("***Friends, their items, and their activities***");
			for (Friend friend : friends) {
				System.out.println("Friend: " + friend.getFriendName());

				if (friend.getItems().isEmpty())
					System.out.println("No items added.");
				else {
					for (Item item : friend.getItems())
						System.out.println(item.toString());
				}
				if (friend.getActivities().isEmpty())
					System.out.println("No activities added.");
				else {
					for (Activity activity : friend.getActivities())
						System.out.println(activity.toString());
				}
			}
		}

	}

}
