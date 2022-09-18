/*

4.35 (Sides of a Triangle)
Write an application that reads three nonzero values entered by the user
and determines and prints whether they could represent the sides of a triangle.

*/

import java.util.Scanner;

public class TriangleYN
{

	private double a;
	private double b;
	private double c;

	public TriangleYN(double a, double b, double c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean isTriangle()
	{
		if ( (a + b > c) && (b + c > a) && (a + c > b) )
			return true;
		else
			return false;
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		System.out.print("Enter three sizes, separated by spaces(decimals values are acceptable):");
		double a = input.nextDouble();
		double b = input.nextDouble();
		double c = input.nextDouble();

		//System.out.printf("A=%.2f B=%.2f C=%.2f", a, b, c);

		TriangleYN triangle = new TriangleYN(a,b,c);

		if (triangle.isTriangle() == true)
			System.out.printf("A triangle could measure %.2f, %.2f, by %.2f.\n", a, b, c);
		else
			System.out.printf("A triangle could not measure %.2f, %.2f, by %.2f.\n", a, b, c);

		input.close();
	}

}