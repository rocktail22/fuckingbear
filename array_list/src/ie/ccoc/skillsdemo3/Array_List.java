package ie.ccoc.skillsdemo3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class Array_List {

	static String bike, time;
	static int rawr = 0;
	static Scanner sc = new Scanner(System.in);
	static ArrayList<String> batman = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		String answer = null;
		do {

			time = SkillsDemo3.theTime();
			arraylist();
			returning();

			System.out.println("Do you wish to rent another bike    Yes/no?");
			answer = sc.nextLine();
		} while ((answer.equalsIgnoreCase("yes")));
	}

	static void arraylist() {

		String answer;
		boolean rental;

		do {
			do {
				System.out.println("Time " + time);

				System.out.println("Please enter the number of which bike you would like to rent ");
				System.out.println("1 - 15");
				bike = sc.nextLine();
				int batman1;
				batman1 = Integer.parseInt(bike);
				if (batman1 == 4 || batman1 == 7 || batman1 == 15)
					System.out.println("Sorry that bike isn't available. ");
				rental = false;
				if (rental = true && (!batman.contains(bike)) && batman1 != 4 && batman1 != 7 && batman1 != 15)
					batman.add(bike);

			} while (rental == false);

			System.out.println("Would you like to rent another  yes/no?");

			answer = sc.nextLine();

		} while (answer.equalsIgnoreCase("yes"));

	}

	static void returning() {
		String answer1;

		do {
			System.out.println("\nWould you like to return a bike  yes/no?");
			answer1 = sc.nextLine();
		} while (!(answer1.equalsIgnoreCase("yes")));
		do {
			System.out.println("Which Bike would you like to return?");
			bike = sc.nextLine();

			if (!(batman.contains(bike)))
				System.out.println("please choose another bike, that bike is currently not out on rental.");

		} while (!(batman.contains(bike)));
		int batman1;
		batman1 = Integer.parseInt(bike);
		if ((batman.contains(bike)) && batman1 != 4 && batman1 != 7 && batman1 != 15)
			batman.remove(bike);
		byte i = 1;
		i++;
		String rawr = bike + i;
		System.out.println("Bike " + bike);

		System.out.println("Thank you for returning bike " + bike
				+ " hope to see you again soon\nplease check the basket to see if you left any belongings");

	}

}
