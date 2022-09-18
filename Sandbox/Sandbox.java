import java.util.Scanner;

class Sandbox
{

	public static void main(String[] args)
	{
		Object obj;
		int first;
		int second;
		double r;
		Scanner stdin = new Scanner(System.in);
		System.out.print("Enter\n1)Two dimensional shape\n2)Three dimensional shape:");
		first = stdin.nextInt();
		if (first == 1)

		{
			System.out.print("Enter\n1)Circle\n2)Square\n3)Triangle:");
			second = stdin.nextInt();

		} else
		{
			System.out.print("Enter\n1)Sphere\n2)Cube\n3)Tetrahedron:");
			second = stdin.nextInt();
		}
		int choice = first * 10 + second;
		switch (choice)
		{
		case 11:
			System.out.print("Enter radius of circle:");
			r = stdin.nextDouble();
			obj = new Circle(r);
			System.out.print(obj.toString());
			break;
		case 12:
			System.out.print("Enter side of square:");
			r = stdin.nextDouble();
			obj = new Square(r);
			System.out.print(obj.toString());
			break;
		case 13:
			System.out.print("Enter side of triangle:");
			double side1 = stdin.nextDouble();
			System.out.print("Enter side of triangle:");
			double side2 = stdin.nextDouble();
			System.out.print("Enter side of triangle:");
			double side3 = stdin.nextDouble();
			obj = new Triangle(side1, side2, side3);
			System.out.print(obj.toString());
			break;
		case 21:
			System.out.print("Enter side of sphere");
			r = stdin.nextDouble();
			obj = new Sphere(r);
			System.out.print(obj.toString());
			break;
		case 22:
			System.out.print("Enter side of cube:");
			r = stdin.nextDouble();
			obj = new Cube(r);
			System.out.print(obj.toString());
			break;
		case 23:
			System.out.print("Enter side of tetrahedron:");
			r = stdin.nextDouble();
			obj = new Tetrahedron(r);
			System.out.print(obj.toString());
			break;
		default:
			System.out.print("NOPE");
			break;
		}
		stdin.close();

	}

	static abstract class Shape extends Object
	{
	}

	static abstract class TwoDimensionalShape extends Shape
	{
		public double perimeter()
		{
			return 0.0;
		}

		public double area()
		{
			return 0.0;
		}

		public String toString()
		{
			return String.format("Area: %.2f \nPerimeter: %.2f\n", area(), perimeter());
		}
	}

	static class Circle extends TwoDimensionalShape
	{
		private double r;

		public Circle(double r)
		{
			this.r = r;
		}

		public double area()
		{
			return Math.PI * this.r * this.r;
		}

		public double perimeter()
		{
			return Math.PI * 2 * this.r;
		}

	}

	static class Square extends TwoDimensionalShape
	{
		private double side;

		public Square(double side)
		{
			this.side = side;
		}

		public double area()
		{
			return this.side * this.side;
		}

		public double perimeter()
		{
			return this.side * 4;
		}
	}

	static class Triangle extends TwoDimensionalShape
	{
		private double side1;
		private double side2;
		private double side3;

		public Triangle(double side1, double side2, double side3)
		{
			this.side1 = side1;
			this.side2 = side2;
			this.side3 = side3;
		}

		public double perimeter()
		{
			return this.side1 + this.side2 + this.side3;
		}

		public double area()
		{
			double half = (this.side1 + this.side2 + this.side3) / 2;
			double calc = Math.sqrt(half * (half - this.side1) * (half - this.side2) * (half - this.side3));
			return calc;
		}
	}

	static class ThreeDimensionalShape extends Shape
	{
		public double getVolume()
		{
			return 0.0;
		}

		public double getSurfaceArea()
		{
			return 0.0;
		}

		public String toString()
		{
			return String.format("Surface area:%.2f%nVolume: %.2f%n", getSurfaceArea(), getVolume());
		}

	}

	static class Sphere extends ThreeDimensionalShape
	{
		private double r;

		public Sphere(double r)
		{
			this.r = r;
		}

		public double getVolume()
		{
			return (Math.PI * this.r * this.r * this.r * 4) / 3.0;
		}

		public double getSurfaceArea()
		{
			return 4 * Math.PI * this.r * this.r;
		}

	}

	static class Cube extends ThreeDimensionalShape
	{
		private double side;

		public Cube(double side)
		{
			this.side = side;
		}

		public double getVolume()
		{
			return this.side * this.side * this.side;
		}

		public double getSurfaceArea()
		{
			return this.side * this.side * 6;
		}

	}

	static class Tetrahedron extends ThreeDimensionalShape
	{
		private double side;

		public Tetrahedron(double side)
		{
			this.side = side;
		}

		public double getVolume()
		{
			return this.side * this.side * this.side / (Math.sqrt(2) * 6);
		}

		public double getSurfaceArea()
		{
			return this.side * this.side * Math.sqrt(3);
		}
	}
}