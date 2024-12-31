package vacationPlanner;

import java.util.ArrayList;
import java.util.Collections;

public class Calculator {

	public static String calculate(ArrayList<Friend> friends) {
		// Loop through ArrayList

		final double totalCost = calculatePie(friends); // Calculate total spent
		final double requiredContribution = Math.floor((totalCost / friends.size()) * 100.0) / 100.0; // Amount each
																										// friend "owes"

		ArrayList<Friend> underpaid = new ArrayList<Friend>();// create ArrayList to hold friends who overpaid
		ArrayList<Friend> overpaid = new ArrayList<Friend>();// create ArrayList to hold friends who underpaid
		splitFriends(friends, underpaid, overpaid, requiredContribution);// call splitFriends to figure out which
																			// friends belong where

		if (underpaid.isEmpty()) {
			return "Nothing is owed";
		}

		ArrayList<Payment> payments = new ArrayList<Payment>();// create ArrayList of payments due

		double owes;// create owes
		double paid;// creates paid

		// Set to the first friend that overpaid
		// Find the amount the friend needs to be paid back
		int friendBeingPaid = 0;// create and initialize friendBeingPaid to 0
		double deserves = Math.round(((overpaid.get(friendBeingPaid).getTotalSpent()) - requiredContribution) * 100.0)
				/ 100.0;// create and initialize deserves to the amount the friend being paid deserved

		for (int friend = 0; friend < underpaid.size(); friend++) {// loop through the friends who need to pay
			paid = 0;// set paid to 0
			owes = Math.round((requiredContribution - underpaid.get(friend).getTotalSpent()) * 100.0) / 100.0;// set owes to the amount friend owes

			while (paid < owes) {// While friend in underpaid still owes money
				if (owes <= deserves) { // If friend in underpaid owes less than friend in overpaid still deserves
					payments.add(new Payment(underpaid.get(friend), overpaid.get(friendBeingPaid), owes));// create new Payment with friend being
																											// paid and owes as amount and add it 
																											// friend's payments field
					deserves -= owes; // subtract amount paid(owes) from deserves
					paid += owes; // add owes to paid(affectively breaking out of the while loop)
				}

				else { // If friend in underpaid owes more than friend in overpaid still deserves
					payments.add(new Payment(underpaid.get(friend), overpaid.get(friendBeingPaid), deserves));// create new Payment with friend
																												// being  paid and deserves as
																												// amount and add it to friend's
																												// payments field
					owes -= deserves;// subtract amount friend who overpaid deserves from owes
					deserves = 0;// set deserves to 0
					friendBeingPaid++;// increment friend being payed
					deserves = (overpaid.get(friendBeingPaid).getTotalSpent()) - requiredContribution;// set deserves to what next friend in
																										// overpaid deserves
				}
			}
		}
		return printPayments(payments);// call printPayments to build string of who owes who what and then returns it
	}

	/**
	 * This method receives an ArrayList of friends, and calculates the total amount
	 * spent.
	 * 
	 * @param friends ArrayList<Friend> to do the calculations on
	 * @return double as the amount of money spent
	 */
	private static double calculatePie(ArrayList<Friend> friends) {
		double pie = 0;
		for (int friend = 0; friend < friends.size(); friend++) {
			pie += friends.get(friend).getTotalSpent();// add on money spent
		}
		return pie;
	}

	/**
	 * This method receives an ArrayList filled with friends, and 2 empty ArrayLists
	 * of Friends, and the double amount of how much each is supposed to pay. It
	 * then puts the friends who paid less onto the underpaid ArrayList and the
	 * friends who paid more onto the overpaid ArrayList.
	 * 
	 * @param friends   ArrayList<Friend> to do the calculations on
	 * @param underpaid ArrayList<Friend> to hold friends who underpaid
	 * @param overpaid  ArrayList<Friend> to hold friends who overpaid
	 */
	private static void splitFriends(ArrayList<Friend> friends, ArrayList<Friend> underpaid, ArrayList<Friend> overpaid,
			double slice) {
		for (int friend = 0; friend < friends.size(); friend++) {// loop through the friends
			if (friends.get(friend).getTotalSpent() < slice) {// check if friend spent less than what he owes
				underpaid.add(friends.get(friend));// add friend to underpaid
			} else if (friends.get(friend).getTotalSpent() > slice) {// check if friend spent more than what he owes
				overpaid.add(friends.get(friend));// add friend to overpaid
			}
		}
		Collections.reverse(underpaid);// sort underpaid so that the it goes from those who owe the most
		Collections.sort(overpaid);// sort overpaid so that it goes from those who are owed the most
	}

	/**
	 * This method receives an ArrayList ofPayments and displays it to the console.
	 * 
	 * @param payments ArrayList<Payment> to display
	 */
	private static String printPayments(ArrayList<Payment> payments) {
		StringBuilder str = new StringBuilder("Payments Calculated:");// create StringBuilder object with heading
		for (int payment = 0; payment < payments.size(); payment++) {// loop through payments ArrayList
			str.append("\n\t" + (payment + 1) + ") " + payments.get(payment));// append next line with payment number,
																				// and relevant info
		}
		return str.toString();// returns str
	}

}
