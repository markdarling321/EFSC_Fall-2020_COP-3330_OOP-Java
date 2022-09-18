/*
Create an HourlyEmployee class that inherits from Employee and has two new
instance variables: hours, which represents the hours worked, and wage, which
represents the employee's pay per hour. (Both are doubles.)

Create a constructor that takes the arguments first name, last name,
social security number, hourly wage, and the number of hours worked.

Also create accessors, mutators, an earnings method that returns
the money earned by the employee this week, and a toString method
that returns information about the employee in the form of a String.

The setWage method should ensure that the wage is nonnegative,
and the setHours method should ensure that the value of hours
is between 0 and 168 (the number of hours in a week).

Create a Driver class with a main method that prompts the user to enter a
first name, last name, social security number, hours, and wage for an employee.
Then, the program should create an HourlyEmployee object and use its toString
method to print information about it.
*/

import java.util.Scanner; // FOR INPUT STREAM OBJECTS

public class Driver
{

	public static class Employee
	{
		// PRIVATE INSTANCE VARIABLES
		String firstName;
		String lastName;
		String socialSecurityNumber;

		// DEFAULT CONSTRUCTOR
		public Employee()
		{
			this.firstName = "";
			this.lastName = "";
			this.socialSecurityNumber = "";
		}

		// 3 PARAMETER CONSTRUCTOR
		public Employee(String firstName,
						String lastName,
						String socialSecurityNumber)
		{
			this(); // CALL DEFAULT CONSTRUCTOR TO INITIALIZE PRIVATE
					// INSTANCE VARIABLES TO BLANK VALUES
			this.firstName = firstName;
			this.lastName = lastName;
			this.socialSecurityNumber = socialSecurityNumber;
		}

		// ACCESSOR METHODS
		public String getFirstName()
		{
			return this.firstName;
		}

		public String getLastName()
		{
			return this.lastName;
		}

		public String getSocialSecurityNumber()
		{
			return this.socialSecurityNumber;
		}

		// MUTATOR METHODS
		public void setFirstName(String firstName)
		{
			this.firstName = firstName;
		}
		
		public void setLastName(String lastName)
		{
			this.lastName = lastName;
		}

		public void setSocialSecurityNumber(String socialSecurityNumber)
		{
			this.socialSecurityNumber = socialSecurityNumber;
		}

		// toString METHOD
		@Override
		public String toString()
		{
			return "hourly employee: " + firstName + " " + lastName + "\n" +
				   "social security number: " + socialSecurityNumber + "\n";
		}
	} // END OF Employee CLASS

	public static class HourlyEmployee extends Employee
	{
		// PRIVATE INSTANCE VARIABLES
		private double hours;
		private double wage;

		// DEFAULT CONSTRUCTOR
		public HourlyEmployee()
		{
			super(); // CALL SUPER CLASS DEFAULT CONSTRUCTOR FIRST TO INITIALIZE
					 // INHERITED PRIVATE INSTANCE VARIABLES PRIOR TO INITIALIZING
					 // CURRENT CLASS PRIVATE INSTANCE VARIABLES
			this.hours = 0;
			this.wage = 0;
		}

		// 5 PARAMETER CONSTRUCTOR
		public HourlyEmployee(String firstName,
							  String lastName,
							  String socialSecurityNumber,
							  double hourlyWage,
							  double numHoursWorked)
		{
			super(firstName, lastName, socialSecurityNumber); // INITIALIZE SUPER CLASS (INHERITED) VARIABLES
															  // USING SUPER CLASS 3 PARAMETER CONSTRUCTOR
			//this.wage = hourlyWage;
			setHourlyWage(hourlyWage);		// USE MUTATOR METHODS TO PREVENT BAD DATA BEING INPUT
			//this.hours = numHoursWorked;
			setHoursWorked(numHoursWorked);	// USE MUTATOR METHODS TO PREVENT BAD DATA BEING INPUT
		}

		// ACCESSOR METHODS
		public double getHoursWorked()
		{
			return this.hours;
		}

		public double getHourlyWage()
		{
			return this.wage;
		}

		// MUTATOR METHODS
		public void setHoursWorked(double hours)
		{
			if (hours >= 0 && hours <= 168) // CONFIRM HOURS BILLED FALL WITHIN
				this.hours = hours;			// TOTAL NUMBER OF HOURS IN A WEEK
			else
				System.out.println("ERROR: hours MUST BE BETWEEN 0 AND 168 (INCLUSIVE)");
		}

		public void setHourlyWage(double wage)
		{
			if (wage >= 0)					// CONFIRM NON-NEGATIVE WAGE HAS
				this.wage = wage;			// BEEN ENTERED
			else
				System.out.println("ERROR: wage MUST BE GREATER THAN OR EQUAL TO 0");
		}

		// EARNINGS METHOD
		public double weeklyEarnings()
		{
			return (this.hours * this.wage);
		}

		// toString METHOD
		@Override
		public String toString()
		{
			return super.toString() +	// CALL SUPER CLASS toString() METHOD FIRST
				   "hours: " + String.format("%.1f", hours) + " \n" +
				   "wage: " + String.format("%.2f", wage) + " \n" +
				   "earnings: " + String.format("%.2f", weeklyEarnings());
		}

	} // END OF HourlyEmployee CLASS

	public static void main(String[] args)
	{
		// CREATE NEW INPUT STREAM OBJECT
		Scanner input = new Scanner(System.in);

		// TAKE USER INPUT AND STORE IN APPROPRIATE VARIABLES
		System.out.print("Enter first name:");
		String firstName = input.nextLine();

		System.out.print("Enter last name:");
		String lastName = input.nextLine();

		System.out.print("Enter social security number:");
		String socialSecurityNumber = input.nextLine();

		System.out.print("Enter hours worked:");
		double hoursWorked = input.nextDouble();

		System.out.print("Enter wage:");
		double hourlyWage = input.nextDouble();

		// CREATE NEW HourlyEmployee OBJECT USING INPUT OBTAINED FROM USER
		HourlyEmployee hourlyEmployee = new HourlyEmployee(
			firstName, lastName, socialSecurityNumber, hourlyWage, hoursWorked);

		// CALL toString() METHOD FOR OUR NEW HourlyEmployee OBJECT CREATED ABOVE
		System.out.print(hourlyEmployee.toString());

		// CLOSE INPUT STREAM TO AVOID RESOURCE LEAKS
		input.close();

	} // END OF main()

} // END OF Driver CLASS