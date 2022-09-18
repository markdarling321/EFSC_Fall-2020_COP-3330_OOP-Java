// NAME:     Mark Darling
// DATE:     11/30/20
// CLASS:    COP 3330-02Z  --  Object Oriented Programming
//
// PROGRAM:  Final Exam Programming Exercises (Question 49)
//
////////////////////////////////////////////////////////////////////////////////

/*
PROGRAM INSTRUCTIONS:			Final Exam Programming Exercises (Question 49)

(10 points)

Write an application class (ArrayListApplication) that contains a main(...) method.

The method must perform the following:

Prompt user for 10 names and store each in an "ArrayList" object
Shuffle the names using "shuffle"" method of the "Collections" class
Display the names based on the order of the previous step
Save and upload the file as "ArrayListApplication.java"

Do not upload anything but a .java file, no class files.
Uploading a class file will result in 0 points.

If you run into a technical issue, zip your .java file and email the .zip file
to me immediately. Not following these instructions will result in 0 points.

*/
////////////////////////////////////////////////////////////////////////////////

// IMPORT REQUIRED DEPENDENCIES
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// BEGIN CLASS DEFINITION
public class ArrayListApplication
{
	public static void main(String[] args)
	{
		// CREATE ArrayList
		ArrayList<String> names = new ArrayList<String>();

		// CREATE NEW INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);
		
		// TAKE INPUT FROM USER AND ADD IT TO LIST
		for (int i = 0; i < 10; i++)
		{
			System.out.printf("[%d] Enter a name: ", i);
			String name = input.nextLine();
			names.add(name);
		}

		// PRINT LIST TO SCREEN TO VERIFY CONTENTS
		System.out.print("\n\n\nBEFORE SHUFFLING:\n\n");
		for (int i = 0; i < 10; i++)
		{
			System.out.printf("[%d] %s\n", i, names.get(i));
		}

		// SHUFFLE LIST USING "SHUFFLE" METHOD OF THE "COLLECTIONS" CLASS
		Collections.shuffle(names);

		// PRINT LIST TO SCREEN TO VERIFY CONTENTS
		System.out.print("\n\n\nAFTER SHUFFLING:\n\n");
		for (int i = 0; i < 10; i++)
		{
			System.out.printf("[%d] %s\n", i, names.get(i));
		}

		// FOR CLEAN FINISH
		System.out.printf("\n\n\n");
		// CLOSE INPUT STREAM OBJECT TO AVOID RESOURCE LEAKS
		input.close();

	} // END main(){}
} // END CLASS