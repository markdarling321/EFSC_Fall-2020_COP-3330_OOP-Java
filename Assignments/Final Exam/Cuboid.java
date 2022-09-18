// NAME:     Mark Darling
// DATE:     11/30/20
// CLASS:    COP 3330-02Z  --  Object Oriented Programming
//
// PROGRAM:  Final Exam Programming Exercises (Question 50)
//
////////////////////////////////////////////////////////////////////////////////

/*
PROGRAM INSTRUCTIONS:			Final Exam Programming Exercises (Question 50)

(15 points)

A Cuboid can be thought of as a cube that may or may not have equal length edges.
Write a class, "Cuboid" that implements the "Comparable" interface.
The class contains height, width and depth attributes (type "double"),
a constructor, and accessor and mutator methods for each of the attributes.

Add a "main" method that performs the following:

	--	Add 3 objects of the "Cuboid" type to a Collection (e.g., List). 
	
	--	Sort the list.  (i.e., will order the elements  based on the method
		implemented to satisfy the "Comparable" interface) 
	
	--	Use an iterative structure to display the elements.

The "compareTo" method will be based on the "Cuboid" object’s volume.

For example:

Cuboid cuboid1(1,1,1);
Cuboid cuboid2(5,6,7);

The following expression will return a negative value
( i.e., volumecuboid1 – volumecuboid2 )

cuboid1.compareTo(cuboid2)

Save and upload the file as "Cuboid.java".
Do not upload anything but a .java file, no class files.
Uploading a class file will result in 0 points.

If you run into a technical issue, zip your .java file and email the .zip file
to me immediately. Not following these instructions will result in 0 points.

*/
////////////////////////////////////////////////////////////////////////////////

// IMPORT REQUIRED DEPENDENCIES
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cuboid implements Comparable< Cuboid >
{
	// PRIVATE CLASS MEMBER VARIABLES
	private double height;
	private double width;
	private double depth;

	// DEFAULT (0 ARGUMENTS) CONSTRUCTOR
	public Cuboid()
	{
		this.height = 0.00;
		this.width = 0.00;
		this.depth = 0.00;
	}

	// 3 ARGUMENTS CONSTRUCTOR
	public Cuboid(double height, double width, double depth)
	{
		this.height = height;
		this.width = width;
		this.depth = depth;
	}

	// ACCESSOR (GET) METHODS
	public double getHeight()
	{
		return this.height;
	}

	public double getWidth()
	{
		return this.width;
	}

	public double getDepth()
	{
		return this.depth;
	}

	public Double getVolume()
	{
		return this.height * this.width * this.depth;
	}

	// MUTATOR (SET) METHODS
	public void setHeight(double height)
	{
		this.height = height;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public void setDepth(double depth)
	{
		this.depth = depth;
	}

	// ADDITIONAL METHODS
	public void printObj()
	{
		System.out.printf("CUBOID: %d, %d, %d\n", this.height, this.width, this.depth);
	}

	public void printObj(int i)
	{
		System.out.printf("CUBOID [%d] : %d, %d, %d\n", i, this.height, this.width, this.depth);
	}

	// OVERRIDDEN METHODS
	@Override
	public String toString()
	{
		return "CUBOID: " + this.height + ", " + this.width + ", " + this.depth + "\n";
	}
	
	public String toString(int i)
	{
		return "CUBOID [" + i + "] : " + this.height + ", " + this.width + ", " + this.depth + "\n";
	}

	// IMPLEMENT compareTo METHOD FOR java.lang.Comparable
	@Override
	public int compareTo(Cuboid that)
	{
		return this.getVolume().compareTo(that.getVolume());
	}

	// MAIN PROGRAM
	public static void main(String[] args)
	{
		// CREATE LIST OF "Cuboid"S
		List<Cuboid> list = new ArrayList<Cuboid>();

		// CREATE 3 CUBOID OBJECTS
		Cuboid cuboid1 = new Cuboid(1, 1, 1);
		Cuboid cuboid2 = new Cuboid(5, 6, 7);
		Cuboid cuboid3 = new Cuboid(2, 2, 2);

		// 1.) ADD 3 OBJECTS OF THE "Cuboid" TYPE TO A Collection (E.G. List)
		list.add(cuboid1);
		list.add(cuboid2);
		list.add(cuboid3);

		// 1.5.) USE AN ITERATIVE STRUCTURE TO DISPLAY THE ELEMENTS (BEFORE SORTING)
		for (Cuboid cuboid : list)
		{
			System.out.print(cuboid.toString());
			System.out.println(cuboid.getVolume());
		}

		// 2.) SORT THE LIST. (I.E. WILL ORDER THE ELEMENTS BASED ON THE METHOD
		// 	   IMPLEMENTED TO SATISFY THE "Comparable" interface.)
		Collections.sort(list);

		// 3.) USE AN ITERATIVE STRUCTURE TO DISPLAY THE ELEMENTS (AFTER SORTING)
		for (Cuboid cuboid:list)
		{
			System.out.print(cuboid.toString());
			System.out.println(cuboid.getVolume());
		}
			

	} // END main(){}
} // END CLASS