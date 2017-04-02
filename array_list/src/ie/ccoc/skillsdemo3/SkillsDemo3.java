package ie.ccoc.skillsdemo3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class SkillsDemo3 {
	static Scanner sc = new Scanner (System.in);
	
	public static Boolean printFile (String fileName) throws IOException {
		// Accepts a string (filename), outputs the contents of that file to screen and returns true. 
		// Returns false if the file cannot be opened.
			
			try {
				Scanner printFile = new Scanner (new File(fileName));
				
				while (printFile.hasNextLine())
					System.out.println(printFile.nextLine ());
				printFile.close ();
				return true;
			} catch (IOException e) {
				System.err.println("\n" + fileName + " cannot be opened. The file might be missing or corrupt.\n\n");
				return false;
			}		
		}
		
	public static Boolean checkLoginCredentials (String email, String PIN) {
		// Accepts an email and PIN (strings).
		// Returns true if the email and PIN couple exist in passwords.txt, returns false otherwise.
		
		try {
			Scanner loginFile = new Scanner (new File("Passwords.txt"));
			Boolean found = false;
			String fileUsername, filePassword;
			
			while (!found && loginFile.hasNextLine()) {
				fileUsername = loginFile.nextLine ();
				filePassword = loginFile.nextLine ();
				if (fileUsername.equals (email) && (filePassword.equals (PIN)))
					found = true;
			}
			loginFile.close ();
			if (found)
				return true;
			else
				return false;
		} catch (IOException e) {
			System.err.println("\nPasswords.txt cannot be opened. The file might be missing or corrupt.\n\n");
			return false;
		}		
	}
	
	private static void appendToFile (String fileName, String toAppend) throws FileNotFoundException {
		try {
		    Files.write(Paths.get(fileName), toAppend.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    System.err.println("\n" + fileName + " may be missing or corrupt.\n");
		} 
	}
	
	public static void appendToLoginFile (String email, String PIN) throws Exception {
		// Accepts an email and PIN (strings) and appends these to passwords.txt.
		
		try {
			appendToFile ("passwords.txt", email + "\n" + PIN + "\n");
		} catch (FileNotFoundException e) {
			System.err.println("\nMaintenance Log file may be missing or corrupt.\n");
		}
		}
	
	public static void appendToMaintenanceLog (String fault) throws Exception{
		// Accepts a fault (string) and appends this to maintenanceLog.txt.
		
		try {
			try {
				appendToFile ("MaintenanceLog.txt", fault + "\n");
			} catch (FileNotFoundException e) {
				System.err.println("\nMaintenance Log file may be missing or corrupt.\n");
			}
		}
		finally {
			
		}
		}
	
	public static String theTime () {
		// Returns the current time as a string in the format HH:mm:ss.
			
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		        
	    Date now = new Date();
	    String strDate = sdf.format(now);
	    return (strDate.substring(11,  19));
		}
		
	public static Byte getAge() throws Exception{
		//	Reads in a date of birth and returns an age in years.
			
		System.out.println("Enter your date of birth details\n");
		format ("Day", (byte)10);
		byte day = sc.nextByte ();
		format ("Month", (byte)10);
		byte month = sc.nextByte ();
		format ("Year", (byte)10);
		int year = sc.nextInt ();
		
		
		LocalDate today = LocalDate.now(), birthday = LocalDate.of(year, month, day);
	    Period p = Period.between(birthday, today);
	   
	    return (byte) p.getYears ();	        
		}
	        		
	private static void format (String toFormat, byte length) {
		// Formats toFormat in a zone width of length.
		
		System.out.print (toFormat + ": ");
		byte numSpaces = (byte)(length - toFormat.length ());
		for (byte i = 1; i <= numSpaces; i++)
			System.out.print(" ");
		}
	
	private static boolean checkExpiryDate (byte month, byte year) {
		// If the entered month is > 12 or the entered date is less than today, then return false. Else return true.
		
		if (month > 12) {
			System.err.println("The month must be between 1 and 12.\n");
			return false;
		}
		else {
			System.out.println("Checking the year.");
			LocalDate today = LocalDate.now();
			byte thisYear = (byte)(Calendar.getInstance().get(Calendar.YEAR) % 1000),
			     thisMonth = (byte)(today.getMonthValue());
			System.out.println("The month is " + thisMonth + " and the year is " + thisYear);
			if (year < thisYear || (year == thisYear && month < thisMonth)) {
					System.err.println("Your credit card is out of date.\n");
					return false;
			}		
			else
				return true;
		}
		}
		
	
	public static String getContact (String contactType) {
		// Accepts the contact type as a string, prompts for that contact type, validates the pattern required for that contact and returns a valid contact detail as a string.
		
		String regEx = "", errMsg = "";
			
			
		format(contactType, (byte)20);
		String contact = sc.nextLine ( );
		switch (contactType) {
			case "First name": 			regEx = "[a-zA-z]+[ ]?[a-zA-Z]+"; 
										errMsg = " can consist of letters and a space only.";
							   			break;
			case "Surname": 			regEx = "[a-zA-z]+['|-]*[a-zA-Z]+"; 
										errMsg = " can consist of letters, a single quotation mark and a hyphen only.";
										break;
			case "Gender" : 			regEx = "[f|m|F|M]"; 
										errMsg = " can be F or M only.";
										break;
			case "Email address": 		regEx = "[a-zA-Z\\d._%-]+@[a-zA-Z0-9.-]+[a-zA-Z]{2,4}"; 
										errMsg = " must be entered in the format username@domain.";
										break;
			case "PIN":					regEx = "[\\d]{4}";
										errMsg = " must consist of exactly four digits.";
										break;
			case "Mobile number": 		regEx = "\\(?\\d{3}\\)?[ |-]\\d{5,7}";
										errMsg = " must be entered in the format ddd-ddddd or ddd-dddddd or ddd-ddddddd.";
										break;
			case "Address": 			regEx = "[A-Za-z\\d'. ]+"; 
										errMsg = " can consist of letters, digits, a space and a dot only.";
										break;
			case "Credit Card Number": 	regEx = "[\\d]{4}[ |-]?[\\d]{4}[ |-]?[\\d]{4}[ |-]?[\\d]{4}";
										errMsg = " must consist of exactly 16 digits.";
										break;
			case "Expiry Date": 		regEx = "[\\d]{2}[/ ][\\d]{2}";
										errMsg = " must be in the format mm/yy.";
		}
					
		while (!contact.matches(regEx)) {
				System.err.print(contact + " is not a valid " + contactType + ". A ");
				boolean needAnN = "AEIOUaeiou".indexOf(contactType.charAt(0)) != -1;;
				if (needAnN)
					System.err.print ("n ");
				else
					System.err.print (" ");
				System.err.println(contactType.toLowerCase() +  errMsg + " Please re-enter.\n\n");
				format (contactType, (byte)20);
				contact = sc.nextLine ();
			}
			
		if (contactType.equals ("Expiry Date")) {
				boolean validExpiryDate = false;
				while (!validExpiryDate) {
					validExpiryDate = checkExpiryDate ((byte)(Integer.parseInt (contact.substring(0, 2))), (byte)(Integer.parseInt (contact.substring(3, 5))));
					System.out.println("Month = " + contact.substring(0, 2));
					System.out.println("year = " + contact.substring(3, 5));
					if (!validExpiryDate) {
						format (contactType, (byte)20);
						contact = sc.nextLine ();
					}
				}
			}
		return contact;
		}
}

