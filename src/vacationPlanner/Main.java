package vacationPlanner;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please choose one of the following options:");
		System.out.println("1. Calculator");
		System.out.println("2. Exit");
		int choice = input.nextInt();
		
		while (choice < 1 || choice > 2) {
			System.out.println("Invalid. Please enter a number from 1-2.");
			choice = input.nextInt();
		}
	}
}
