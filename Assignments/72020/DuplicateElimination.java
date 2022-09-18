/*

Write an application that inputs five numbers, each between 10 and 100,
inclusive. As each number is read, display it only if it’s not a duplicate
of a number already read. Provide for the “worst case,” in which all five
numbers are different. Use the smallest possible array to solve this problem.
Display the complete set of unique values input after the user enters each
new value.

*/

import java.util.Scanner;

public class DuplicateElimination
{

	public static void main(String[] args)
	{
		// CREATE INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);

		// DECLARE AND INITIALIZE ARRAY OF INTS
		int[] uniqueNumList = {0, 0, 0, 0, 0};

		// DECLARE TEMPORARY VARIABLES TO STORE USER INPUT
		int currentNum = 0;
		int previousNum = 0;
		int uniqueIndex = 0;

		// LOOP TO ASK USER FOR 5 INTEGERS
		for (int loop = 0; loop < 5; loop++)
		{
			// TAKE INPUT FROM USER AND STORE IN TEMP VARIABLE
			System.out.print("Enter an integer between 10 and 100:");
			currentNum = input.nextInt();

			// CHECK TO SEE IF USER'S NUMBER GIVEN IS ALREADY STORED
			if (loop == 0) // THE FIRST VALUE GIVEN WILL ALWAYS BE UNIQUE
			{
				// DECLARE TO USER IF THIS IS THE FIRST OCCURENCE OF THEIR NUMBER
				System.out.printf("This is the first time %d has been entered\n", currentNum);
				uniqueNumList[uniqueIndex] = currentNum;
				uniqueIndex++;
			}
			else if (currentNum == previousNum) // IF CURRENT AND PREVIOUS NUMBERS ARE THE SAME
			{
				// DECLARE BOOLEAN FLAG
				boolean inUniqueList = false;

				// COMPARE CURRENT NUMBER TO EACH VALUE STORED IN UNIQUE ARRAY
				for (int compIndex = 0; compIndex < uniqueNumList.length; compIndex++)
				{
					if (currentNum == uniqueNumList[compIndex])
						inUniqueList = true;
				}
				// IF CURRENT NUMBER NOT ALREADY IN UNIQUE LIST, ADD IT
				if (inUniqueList == false)
				{
					uniqueNumList[uniqueIndex] = currentNum;
					uniqueIndex++;
				}
			}
			else
			{
				// DECLARE TO USER IF THIS IS THE FIRST OCCURENCE OF THEIR NUMBER
				System.out.printf("This is the first time %d has been entered\n", currentNum);
				// ADD CURRENT NUMBER TO UNIQUE LIST
				uniqueNumList[uniqueIndex] = currentNum;
				uniqueIndex++;
			}
			
			// UPDATE TEMP VARIABLE VALUES FOR NEXT LOOP ITERATION
			previousNum = currentNum;

		} // END OF FOR LOOP
		
		System.out.printf("The complete set of unique values entered is:\n");

		// OUTPUT ONLY UNIQUE NUMBERS IN THE LIST
		int uvIndex = 1;
		for (int i = 0; i < uniqueNumList.length; i++)
		{
			if (uniqueNumList[i] != 0)
			{
				System.out.printf("Unique Value %d: is %d\n", uvIndex, uniqueNumList[i]);
				uvIndex++;
			}
		}
		// CLOSE INPUT STREAM OBJECT
		input.close();
	}

}