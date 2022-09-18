/*

Create a class Rectangle with double attributes length and width.
The default constructor should set these attributes to 1.
Provide methods that calculate the rectangle's perimeter and area,
as well as accessors and mutators for both data fields.
The mutator methods for length and width should verify that the number
being passed in is larger than 0.0 and less than 20.0 -- if it doesn't
fit those criteria, the value of the field should not be changed.

Write a Driver class in the same file to test your Rectangle class.
It should prompt the user to enter a length and width of a rectangle,
and then print out the area and perimeter of the rectangle.
(Use the mutators to set the length and width of the rectangle, not the constructor.)

*/

import java.util.Locale; // FOR DECIMAL OUTPUT FORMATTING
import java.util.Scanner; // FOR INPUT STREAM OBJECTS
import java.text.DecimalFormat; // FOR DECIMAL OUTPUT FORMATTING
import java.text.DecimalFormatSymbols; // FOR DECIMAL OUTPUT FORMATTING

public class Driver
{
	// PRIVATE INSTANCE VARIABLES (ATTRIBUTES)
	private double length;
	private double width;

	// 0 PARAMETER (DEFAULT) CONSTRUCTOR
	public Driver()
	{
		length = 1.0;
		width = 1.0;
	}

	// 2 PARAMETER CONSTRUCTOR
	public Driver(double length, double width)
	{
		this(); // ALWAYS INITIALIZE FIRST USING DEFAULT CONSTRUCTOR

		// ONLY UPDATE PRIVATE INSTANCE VARIABLE WITH VALUES PASSED
		// IF THEY ARE WITHIN DESIGNATED RANGE
		if (length > 0.0 && length < 20.0)
			this.length = length;
		if (width > 0.0 && width < 20.0)
			this.width = width;
	}

	// ACCESSOR FOR LENGTH
	public double getLength()
	{
		return length;
	}

	// ACCESSOR FOR WIDTH
	public double getWidth()
	{
		return width;
	}

	// MUTATOR FOR LENGTH
	private void setLength(double length)
	{
		if (length > 0.0 && length < 20.0)
			this.length = length;
	}

	// MUTATOR FOR WIDTH
	private void setWidth(double width)
	{
		if (width > 0.0 && width < 20.0)
			this.width = width;
	}
	
	// PERIMETER METHOD
	public double getPerimeter()
	{
		double perimeter = (this.length * 2) + (this.width * 2);
		return perimeter;
	}

	// AREA METHOD
	public double getArea()
	{
		double area = this.length * this.width;
		return area;
	}

	// MAIN DRIVER PROGRAM FOR TESTING CLASS
	public static void main(String[] args)
	{
		// CREATE NEW INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);

		// CREATE NEW CLASS OBJECT
		Driver rect = new Driver();

		// BEGIN CLASS TEST HERE
		System.out.print("Enter length of rectangle:");
		double length = input.nextDouble();
		rect.setLength(length);

		System.out.print("Enter width of rectangle:");
		double width = input.nextDouble();
		rect.setWidth(width);

		// CREATE DECIMAL FORMATTER TO REMOVE TRAILING ZEROES
		// I FOUND THIS SOLUTION AT:
		// https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0
		DecimalFormat df = new DecimalFormat("0.0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
		df.setMaximumFractionDigits(340); // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

		// MY ORIGINAL ATTEMPT:
		// System.out.printf("Area: %.2f, Perimeter: %.2f", rect.getArea(), rect.getPerimeter());
		System.out.print("Area: ");
		System.out.print(df.format(rect.getArea()));
		System.out.print(", Perimeter: ");
		System.out.print(df.format(rect.getPerimeter()));
		// END OF CLASS TEST

		// CLOSE INPUT STREAM
		input.close();

	} // END OF MAIN DRIVER PROGRAM

} // END OF CLASS DEFINITION