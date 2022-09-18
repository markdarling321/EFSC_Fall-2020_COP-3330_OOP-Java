// NAME:	Mark Darling
// CLASS:	COP-3330-02Z
// DATE:	09/13/20
// PROGRAM:	Eclipse Project 1  --  FileCopy.java

////////////////////////////////////////////////////////////////////////////////

/*
PROGRAM DESCRIPTION & INSTRUCTIONS:

You may NOT write this program as a GUI (Graphical User Interface).
That would earn no points.

Write a program that reads a file named input.txt and writes a file
that contains the same contents, but is named output.txt.
The input file will contain more than one line when I test this
and so should your output file.

Do not use a path name when opening these files.
This means the files should be located in the top level folder of the project.
Do not use a copy method that is supplied by Java.
Your program must read the file line by line and write the file itself.

DO NOT WRITE TO input.txt! That will cost a lot of points. Double check this.
There is no sin worse than wiping out the client's input file due to carelessness.

Your class name must be FileCopy. Capitalization counts.
It should be in a package named mypackage, as explained in this week's videos.
Put your name at the top of the source file.
Your program must work in Eclipse, even if you use a different IDE to develop it.
It is best to use Eclipse.
I am starting you with a simple Eclipse project to get you ready for more complex projects.

Upload FileCopy.java. Not Project1.java or FileCopyUpload.java.
A large part of your grade will be how well you follow these directions.
*/

////////////////////////////////////////////////////////////////////////////////

// DECLARE PACKAGE FIRST BEFORE IMPORTS
package mypackage;

// IMPORT NECESSARY PACKAGES
import java.io.IOException;
import java.util.Scanner;
import java.util.Formatter;

import java.io.File;

// BEGIN FileCopy CLASS
public class FileCopy
{
	public static final String IN_FILENAME = "input.txt";
	public static final String OUT_FILENAME = "output.txt";

	public static void main(String[] args) throws IOException
	{
		try
		{
			// DEFINE FILE OBJECT FOR INPUT FILE USING ONLY ITS FILENAME AND
			// ECHO ITS PATH TO SCREEN

			// SYNTAX:
			// File(String pathname)
			// Creates a new File instance by converting the given pathname string
			// into an abstract pathname
			File inFileAbs = new File(IN_FILENAME);
			String inFilePath = inFileAbs.getAbsolutePath();

			// SYNTAX:
			// String getAbsolutePath()
			// Returns the absolute pathname string of this abstract pathname.
			System.out.printf("INPUT FILE PATH:\n%s\n\n", inFilePath);

			// OPEN NEW INPUT SCANNER FOR READING INPUT FILE USING PATH OBJECT

			// SYNTAX:
			// Scanner (Path source)
			// Constructs a new Scanner that produces values scanned from the
			// specified file.
			Scanner inFileSc = new Scanner(inFileAbs);

			// SYNTAX:
			// Formatter(String fileName)
			// Constructs a new formatter with the specified file name.
			Formatter outFile = new Formatter(OUT_FILENAME);
			File outFileAbs = new File(OUT_FILENAME);
			String outFilePath = outFileAbs.getAbsolutePath();
			System.out.printf("OUTPUT FILE CREATED AT:\n%s\n\n", outFilePath);

			System.out.printf("ECHO FILE TO SCREEN BEFORE WRITING TO OUTPUT FILE:\n");
			int lineNum = 1;

			// CONTINUE TO READ FILE SO LONG AS IT HAS ANOTHER LINE TO BE READ
			// UNTIL END OF FILE IS REACHED & ECHO EACH LINE TO SCREEN BEFORE
			// WRITING IT TO THE OUTPUT FILE. ALSO CHECK TO MAKE SURE WE ARE NOT
			// WRITING TO OUR INPUT FILE AS WELL.
			while (inFileSc.hasNextLine() && inFileAbs != outFileAbs && inFilePath != outFilePath)
			{
				// GET CURRENT LINE AND STORE IT AS A STRING
				String currentLine = inFileSc.nextLine();

				// PRINT CURRENT LINE TO SCREEN BEFORE WRITING IT TO OUTPUT FILE
				if (lineNum >= 0 && lineNum <= 99)
					System.out.printf("LINE %d\t  |%s\n", lineNum, currentLine);
				else if (lineNum >= 100 && lineNum <= 999)
					System.out.printf("LINE %d  |%s\n", lineNum, currentLine);
				else
					System.out.printf("LINE %d |%s\n", lineNum, currentLine);

				// WRITE CURRENT LINE TO OUTPUT FILE

				// SYNTAX:
				// format(String format, Object... args)
				// Writes a formatted string to this object's destination using
				// the specified format string and arguments.
				if (inFileSc.hasNextLine())
					// WRITE CURRENT LINE TO OUTPUT FILE
					outFile.format("%s\n", currentLine);
				else if (!inFileSc.hasNextLine() && currentLine.isEmpty())
				{
					// PRINT CURRENT LINE TO SCREEN BEFORE WRITING IT TO OUTPUT FILE
					System.out.printf("LINE %d\t  |%s\n", lineNum + 1, currentLine);
					// WRITE CURRENT LINE TO OUTPUT FILE
					outFile.format("%s\n", currentLine);
				} else
				{
					// PRINT CURRENT LINE TO SCREEN BEFORE WRITING IT TO OUTPUT FILE
					if (lineNum + 1 >= 0 && lineNum + 1 <= 99)
						System.out.printf("LINE %d\t  |\n", lineNum + 1);
					else if (lineNum + 1 >= 100 && lineNum + 1 <= 999)
						System.out.printf("LINE %d  |\n", lineNum + 1);
					else
						System.out.printf("LINE %d |\n", lineNum + 1);
					// WRITE CURRENT LINE TO OUTPUT FILE
					outFile.format("%s", currentLine);
				}

				// INCREMENT LINE NUMBER COUNTER BY 1 EACH LOOP
				lineNum++;

			}

			// NOTIFY USER END OF FILE HAS BEEN REACHED
			System.out.printf("END OF FILE!\n");

			// CLOSE SCANNER OBJECT AND OUPUT FILE OBJECT
			inFileSc.close();
			outFile.close();
		} catch (IOException error)
		{
			System.out.println("IOException ERROR!  --  PRINTING STACK TRACE: \n");
			error.printStackTrace();
		}

	} // END main()

} // END FileCopy CLASS