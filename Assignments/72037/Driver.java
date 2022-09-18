/*

Define the class InvalidSideException, which inherits from the Exception class.

Also define a Square class, which has one method variable -- an int describing
the side length. The constructor of the Square class should take one argument,
an int meant to initialize the side length; however, if the argument is not
greater than 0, the constructor should throw an InvalidSideError. The Square
class should also have a method getArea(), which returns the area of the square.

Create a Driver class with a main method to test your classes.
Your program should prompt the user to enter a value for the side length,
and then create a Square object with that side length. If the side length
is valid, the program should print the area of the square. Otherwise, it
should catch the InvalidExceptionError, print "Side length must be
greater than 0.", and terminate the program.

*/

import java.util.Scanner; // FOR INPUT STREAM OBJECTS

public class Driver
{

	public static class Square
	{

		// PRIVATE INSTANCE VARIABLE(S) (ATTRIBUTES)
		private int side;

		// 0 PARAMETER (DEFAULT) CONSTRUCTOR
		// public Square()
		// {
		// System.out.println("DEFAULT CONSTRUCTOR: public Square(); side = 0");
		// side = 111;
		// }

		// 1 PARAMETER CONSTRUCTOR
		public Square(int side) throws InvalidSideException
		{
			// this(); // ALWAYS INITIALIZE FIRST USING DEFAULT CONSTRUCTOR
			// System.out.println("1 PARAMETER CONSTRUCTOR: public Square(int side); side = 0");

			// ONLY UPDATE PRIVATE INSTANCE VARIABLE WITH VALUES PASSED
			// IF THEY ARE WITHIN DESIGNATED RANGE
			setSide(side);
			
		}

		// MUTATOR FOR SIDE
		public void setSide(int side) throws InvalidSideException
		{
			// System.out.println("MUTATOR: public void setSide(int side)");
			if (side > 0)
				this.side = side;
			else
				throw new InvalidSideException("Side length must be greater than 0.");
		}

		// ACCESSOR FOR SIDE
		public int getSide()
		{
			// System.out.println("ACCESSOR: public int getSide()");
			return side;
		}

		// AREA METHOD
		public int getArea()
		{
			// System.out.println("METHOD: public int getArea()");
			int area = this.side * this.side;
			return area;
		}

	} // END OF Square() CLASS

	// https://stackoverflow.com/questions/2288937/what-does-it-mean-the-serializable-class-does-not-declare-a-static-final-serial
	@SuppressWarnings("serial")
	public static class InvalidSideException extends Exception
	{
		// DEFAULT CONSTRUCTOR (0 PARAMETERS)
		// PASSES DEFAULT ERROR MESSAGE String TO THE SUPERCLASS CONSTRUCTOR
		public InvalidSideException()
		{
			super();
		}

		// 1 PARAMETER CONSTRUCTOR
		// RECEIVES A CUSTOMIZED ERROR MESSAGE AS A String AND PASSES IT TO THE
		// SUPERCLASS CONSTRUCTOR
		public InvalidSideException(String errorMsg)
		{
			super(errorMsg);
		}

		// 2 PARAMETER CONSTRUCTOR
		// RECEIVES A CUSTOMIZED ERROR MESSAGE AS A String AND A Throwable (FOR CHAINING
		// EXCEPTIONS) AND
		// PASSES BOTH TO THE SUPERCLASS CONSTRUCTOR
		public InvalidSideException(String errorMsg, Throwable error)
		{
			super(errorMsg, error);
		}

		// 1 PARAMETER CONSTRUCTOR
		// RECEIVES A Throwable (FOR CHAINING EXCEPTIONS) AND PASSES IT TO THE
		// SUPERCLASS CONSTRUCTOR
		public InvalidSideException(Throwable error)
		{
			super(error);
		}

	} // END OF InvalidSideException() CLASS

	// MAIN DRIVER PROGRAM FOR TESTING CLASS
	public static void main(String[] args) throws InvalidSideException
	{
		// CREATE NEW INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);

		// BEGIN CLASS TEST HERE
		System.out.print("Enter side length of square:");
		int side = input.nextInt();

		// DECLARE OBJECT BUT DO NOT INITIALIZE IT YET
		Square square = null;

		// CREATE NEW CLASS OBJECT
		try
		{
			// ATTEMPT TO INITIALIZE NEW OBJECT AND CATCH ANY EXCEPTIONS
			square = new Square(side);
			
			System.out.printf("%d", square.getArea());
		}
		catch(InvalidSideException e)
		{
			// IF EXCEPTION IS CAUGHT, PRINT ITS ERROR MESSAGE FOR USER TO SEE
			System.out.print(e.getMessage());
		}
		
		// AS LONG AS square IS NOT NULL, THAT MEANS IT WAS INITIALIZED WITH GOOD INPUT
		//if (square != null)
		//	System.out.printf("%d", square.getArea());

		// END OF CLASS TEST

		// CLOSE INPUT STREAM
		input.close();

	} // END OF Main() DRIVER PROGRAM

} // END OF MAIN CLASS