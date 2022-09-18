
/*
Write a program that uses String method regionMatches to compare two strings
input by the user. The program should prompt the user to enter two strings,
the starting index in the first string, the starting index in the second string,
and the number of characters to be compared.

The program should print whether or not the strings are equal.
(Ignore the case of the characters during comparison.)

SYNTAX:

public boolean regionMatches(int toffset, String other, int ooffset, int len)
public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len)

ignoreCase	-	if true, ignore case when comparing characters
toffset		-	the starting offset of the subregion in this string
other		-	the string argument
ooffset		-	the starting offset of the subregion in the string
len			-	the number of characters to compare

*/

import java.util.Scanner;	// FOR INPUT STREAM OBJECTS

public class StringCompare
{

	public static void main(String[] args)
	{

		// CREATE NEW INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);

		// BEGIN main TEST PROGRAM HERE

		// TAKE USER INPUT
		System.out.print("Enter first string:");
		String string1 = input.nextLine();

		System.out.print("Enter second string:");
		String string2 = input.nextLine();

		System.out.print("Enter starting index for first string:");
		int startIndexStr1 = input.nextInt();

		System.out.print("Enter starting index for second string:");
		int startIndexStr2 = input.nextInt();

		System.out.print("Enter number of characters to be compared:");
		int numChars = input.nextInt();

		System.out.print(string1.regionMatches(true, startIndexStr1, string2, startIndexStr2, numChars));

		// CLOSE INPUT STREAM
		input.close();
	
	} // END main()

} // END StringCompare CLASS