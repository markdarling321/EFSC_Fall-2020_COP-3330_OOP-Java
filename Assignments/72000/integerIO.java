/*

2.15 (Arithmetic)

Write an application that asks the user to enter two integers, obtains them
from the user and prints their sum, product, difference and quotient (division).

Use the techniques shown in Fig. 2.7. (i.e. Scanner, nextInt and printf)

*/

import java.util.Scanner;

public class integerIO
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter first integer: ");
		int num1 = input.nextInt();

		System.out.print("Enter second integer: ");
		int num2 = input.nextInt();

		System.out.printf("%d+%d=%d\n", num1, num2, num1 + num2);
		System.out.printf("%d*%d=%d\n", num1, num2, num1 * num2);
		System.out.printf("%d-%d=%d\n", num1, num2, num1 - num2);
		System.out.printf("%d/%d=%d\n", num1, num2, num1 / num2);

		input.close();
	}
}