// NAME:     Mark Darling
// DATE:     10/02/20
// CLASS:    COP 3330-02Z  --  Object Oriented Programming
// PROGRAM:  Eclipse Project 2  --  TreeSetUse.java

////////////////////////////////////////////////////////////////////////////////

/*
DIRECTIONS:

Write a program that reads a line of text input by the user
and places each word in a TreeSet. Print the elements of the
TreeSet to the screen. This will cause the elements to be
printed in ascending order.

Include your name at the top of your source file.

Use proper indentation, like you see in the book.

Your class must be in a package named mypackage, as explained
in last week's videos and be named TreeSetUse.

Upload TreeSetUse.java.
*/

////////////////////////////////////////////////////////////////////////////////

// CREATE PACKAGE SPECIFIED IN DIRECTIONS
package mypackage;

// IMPORT REQUIRED DEPENDENCIES
import java.util.Arrays;	// LISTS
import java.util.TreeSet;	// TREE SETS
import java.util.Scanner;	// USER INPUT STREAM


// BEGIN TreeSetUse() CLASS
public class TreeSetUse
{
	// BEGIN main(){}
	public static void main(String[] args)
	{
		// CREATE SCANNER OBJECT FOR USER INPUT
		Scanner input = new Scanner(System.in);

		// PROMPT USER TO ENTER A SINGLE LINE OF WORDS
		System.out.print("ENTER TEXT: ");

		// STORE USER'S INPUT IN String VARIABLE
		String userInput = input.nextLine();

		// SPLIT WORDS BY THE SPACE " " BETWEEN THEM
		// USING String.split() AND STORE IN A STRING ARRAY
		String[] wordList = userInput.split(" ");

		// CREATE NEW TreeSet OBJECT THAT WILL STORE String DATA TYPE
		TreeSet<String> words = new TreeSet<>();

		// USE TreeSet.addAll() TO PUSH STRING ARRAY
		// TO TreeSet AS A LIST USING Arrays.asList()
		words.addAll(Arrays.asList(wordList));

		// USING ENHANCED FOR LOOP, STEP THROUGH EACH WORD IN THE TreeSet
		for (String word : words)
		{
			// PRINT EACH WORD TO SCREEN
			System.out.println(word);
		} // END for()

		// CLOSE INPUT STREAM TO AVOID RESOURCE LEAKS
		input.close();

	} // END main(){}
} // END TreeSetUse{}
