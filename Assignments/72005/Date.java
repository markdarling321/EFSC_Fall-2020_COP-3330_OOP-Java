/*

3.14 (Date Class)
Create a class called Date that includes three instance variables-
a month (type int), a day (type int) and a year (type int).
Provide a constructor that initializes the three instance variables
and assumes that the values provided are correct. Provide a set and
a get method for each instance variable. Provide a method displayDate
that displays the month, day and year separated by forward slashes (/).
Add a main method to the class that demonstrates class Date's capabilities.

*/

import java.util.Scanner;

public class Date
{

	private int month;
	private int day;
	private int year;

	public Date(int month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}

	public int getYear()
	{
		return year;
	}

	public void displayDate()
	{
		System.out.printf("The Date object's state is: %d/%d/%d\n", getMonth(), getDay(), getYear());
	}

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the month, day and year in the form XX XX XXXX and hit enter:");
		int month = input.nextInt();
		int day = input.nextInt();
		int year = input.nextInt();

		Date date1 = new Date(month, day, year);

		date1.displayDate();

		System.out.print("Enter a new year:");
		year = input.nextInt();
		date1.setYear(year);

		date1.displayDate();

		System.out.print("Enter a new day:");
		day = input.nextInt();
		date1.setDay(day);

		date1.displayDate();

		System.out.print("Enter a new month:");
		month = input.nextInt();
		date1.setMonth(month);

		date1.displayDate();

		input.close();
	}

}