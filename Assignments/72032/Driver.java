/*
Implement the Shape hierarchy -- create an abstract class called Shape,
which will be the parent class to TwoDimensionalShape and ThreeDimensionalShape.
The classes Circle, Square, and Triangle should inherit from TwoDimensionalShape,
while Sphere, Cube, and Tetrahedron should inherit from ThreeDimensionalShape.

Each TwoDimensionalShape should have the methods getArea() and getPerimeter(),
which calculate the area and perimeter of the shape, respectively.
Every ThreeDimensionalShape should have the methods getArea() and getVolume(),
which respectively calculate the surface area and volume of the shape.
Every class should have a member variable containing its dimensions --
for example, the Circle class should have a member variable describing
its radius, while the Triangle class should have three member variables
describing the length of each side.

Note that the Tetrahedron cass should describe a regular tetrahedron,
and as such, should only have one member variable.

Create a Driver class with a main method to test your Shape hierarchy.
The program should prompt the user to enter the type of shape they'd like
to create, and then the shape's dimensions. If the shape is two dimensional,
the program should print its area and its perimeter, and if it's a three
dimensional shape, its surface area and volume.
*/

import java.util.Scanner; // FOR INPUT STREAM OBJECTS

public class Driver
{
	// SUPER CLASS
	public static abstract class Shape
	{

	} // END Shape CLASS



	// SUB CLASS
	public static abstract class TwoDimensionalShape extends Shape
	{

	} // END TwoDimensionalShape CLASS



	// SUB CLASS
	public static abstract class ThreeDimensionalShape extends Shape
	{

	} // END ThreeDimensionalShape CLASS



	// SUB SUB CLASS
	public static class Circle extends TwoDimensionalShape
	{

		// 1 PARAMETER CONSTRUCTOR
		public Circle(double radius)
		{
			super();
			this.radius = radius;
		}

		// PRIVATE INSTANCE VARIABLE(S)
		private double radius;

		// AREA ACCESSOR METHOD
		public double getArea()
		{
			// AREA OF CIRCLE = (pi) * (r^2)
			double area = Math.PI * Math.pow(this.radius, 2);
			return area;
		}

		// PERIMETER ACCESSOR METHOD
		public double getPerimeter()
		{
			// PERIMETER OF CIRCLE = (2) * (pi) * (r)
			double perimeter = 2 * Math.PI * this.radius;
			return perimeter;
		}

	} // END Circle CLASS
	


	// SUB SUB CLASS
	public static class Square extends TwoDimensionalShape
	{

		// 1 PARAMETER CONSTRUCTOR
		public Square(double side)
		{
			super();
			this.side = side;
		}

		// PRIVATE INSTANCE VARIABLE(S)
		private double side;

		// AREA ACCESSOR METHOD
		public double getArea()
		{
			// AREA OF SQUARE = (s^2)
			double area = Math.pow(this.side, 2);
			return area;
		}

		// PERIMETER ACCESSOR METHOD
		public double getPerimeter()
		{
			// PERIMETER OF SQUARE = (4) * (s)
			double perimeter = this.side * 4;
			return perimeter;
		}

	} // END Square CLASS


	// SUB SUB CLASS
	public static class Triangle extends TwoDimensionalShape
	{

		// 3 PARAMETER CONSTRUCTOR
		public Triangle(double side1, double side2, double side3)
		{
			super();
			this.side1 = side1;
			this.side2 = side2;
			this.side3 = side3;
		}

		// PRIVATE INSTANCE VARIABLE(S)
		private double side1;
		private double side2;
		private double side3;

		// AREA ACCESSOR METHOD
		public double getArea()
		{
			// HERON'S FORMULA: Area = SqRt( p * (p-a) * (p-b) * (p-c) )
			double p = getPerimeter() / 2;
			double a = this.side1;
			double b = this.side2;
			double c = this.side3;

			double area = Math.sqrt(p * (p-a) * (p-b) * (p-c));
			return area;
		}

		// PERIMETER ACCESSOR METHOD
		public double getPerimeter()
		{
			// PERIMETER OF TRIANGLE = (s1) + (s2) + (s3)
			double perimeter = this.side1 + this.side2 + this.side3;
			return perimeter;
		}

	} // END Triangle CLASS



	// SUB SUB CLASS
	public static class Sphere extends ThreeDimensionalShape
	{

		// 1 PARAMETER CONSTRUCTOR
		public Sphere(double radius)
		{
			super();
			this.radius = radius;
		}

		// PRIVATE INSTANCE VARIABLE(S)
		private double radius;

		// SURFACE AREA ACCESSOR METHOD
		public double getArea()
		{
			// SURFACE AREA OF SPHERE = (4) * (pi) * (r^2)
			double area = 4 * Math.PI * Math.pow(this.radius, 2);
			return area;
		}

		// VOLUME ACCESSOR METHOD
		public double getVolume()
		{
			// VOLUME OF SPHERE = (4/3) * (pi) * (r^3)
			double volume = ((4/3D) * Math.PI * (Math.pow(this.radius, 3)));
			return volume;
		}

	} // END Sphere CLASS



	// SUB SUB CLASS
	public static class Cube extends ThreeDimensionalShape
	{

		// 1 PARAMETER CONSTRUCTOR
		public Cube(double side)
		{
			super();
			this.side = side;
		}

		// PRIVATE INSTANCE VARIABLE(S)
		private double side;

		// SURFACE AREA ACCESSOR METHOD
		public double getArea()
		{
			// SURFACE AREA OF CUBE = (6) * (s^2)
			double area = 6 * Math.pow(this.side, 2);
			return area;
		}

		// VOLUME ACCESSOR METHOD
		public double getVolume()
		{
			// VOLUME OF CUBE = (s^3)
			double volume = Math.pow(this.side, 3);
			return volume;
		}

	} // END Cube CLASS



	// SUB SUB CLASS
	public static class Tetrahedron extends ThreeDimensionalShape
	{

		// 1 PARAMETER CONSTRUCTOR
		public Tetrahedron(double side)
		{
			super();
			this.side = side;
		}

		// PRIVATE INSTANCE VARIABLE(S)
		private double side;

		// SURFACE AREA ACCESSOR METHOD
		public double getArea()
		{
			// SURFACE AREA OF (REFULAR) TETRAHEDRON = (s^2) * SqRt(3)
			double area = Math.pow(this.side, 2) * Math.sqrt(3);
			return area;
		}

		// VOLUME ACCESSOR METHOD
		public double getVolume()
		{
			// VOLUME OF (REGULAR) TETRAHEDRON = ( (s^3) * SqRt(2) ) / (12)
			double volume = (Math.pow(this.side, 3) * Math.sqrt(2)) / (12);
			return volume;
		}

	} // END Tetrahedron CLASS



	// main() PROGRAM
	public static void main(String[] args)
	{
		// OPEN STANDARD INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);

		System.out.println("Enter");
		System.out.println("1)Two dimensional shape");
		System.out.print("2)Three dimensional shape:");
		
		int userChoice1 = input.nextInt();

		if (userChoice1 == 1)
		{
			System.out.println("Enter");
			System.out.println("1)Circle");
			System.out.println("2)Square");
			System.out.print("3)Triangle:");
			
			int userChoice2 = input.nextInt();

			if (userChoice2 == 1)
			{
				System.out.print("Enter radius of circle:");
				double radius = input.nextDouble();
				
				Circle circle = new Circle(radius);
				
				System.out.printf("Area: %.2f \n", circle.getArea());
				System.out.printf("Perimeter: %.2f", circle.getPerimeter());

			}
			else if (userChoice2 == 2)
			{
				System.out.print("Enter side of square:");
				double side = input.nextDouble();
				
				Square square = new Square(side);
				
				System.out.printf("Area: %.2f \n", square.getArea());
				System.out.printf("Perimeter: %.2f", square.getPerimeter());
			}
			else if (userChoice2 == 3)
			{
				System.out.print("Enter side of triangle:");
				double side1 = input.nextDouble();
				System.out.print("Enter side of triangle:");
				double side2 = input.nextDouble();
				System.out.print("Enter side of triangle:");
				double side3 = input.nextDouble();

				Triangle triangle = new Triangle(side1, side2, side3);
				
				System.out.printf("Area: %.2f \n", triangle.getArea());
				System.out.printf("Perimeter: %.2f", triangle.getPerimeter());
			}
			else
			{
				System.out.printf("ERROR: invalid input\n");	
			}
		}
		else if (userChoice1 == 2)
		{
			System.out.println("Enter");
			System.out.println("1)Sphere");
			System.out.println("2)Cube");
			System.out.print("3)Tetrahedron:");

			int userChoice2 = input.nextInt();

			if (userChoice2 == 1)
			{
				System.out.print("Enter radius of sphere:");
				double radius = input.nextDouble();

				Sphere sphere = new Sphere(radius);

				System.out.printf("Surface area: %.2f \n", sphere.getArea());
				System.out.printf("Volume: %.2f", sphere.getVolume());
			}
			else if (userChoice2 == 2)
			{
				System.out.print("Enter side of cube:");
				double side = input.nextDouble();

				Cube cube = new Cube(side);

				System.out.printf("Surface area: %.2f \n", cube.getArea());
				System.out.printf("Volume: %.2f", cube.getVolume());
			}
			else if (userChoice2 == 3)
			{
				System.out.print("Enter side of tetrahedron:");
				double side = input.nextDouble();

				Tetrahedron tetrahedron = new Tetrahedron(side);
				
				System.out.printf("Surface area: %.2f \n", tetrahedron.getArea());
				System.out.printf("Volume: %.2f", tetrahedron.getVolume());
			}
			else
			{
				System.out.printf("ERROR: invalid input\n");
			}
		}
		else
		{
			System.out.printf("ERROR: invalid input\n");
		}
		
		// CLOSE INPUT STREAM OBJECT
		input.close();

	} // END main() PROGRAM

} // END Driver CLASS