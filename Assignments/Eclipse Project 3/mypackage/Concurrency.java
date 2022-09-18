// NAME:     Mark Darling
// DATE:     10/04/20
// CLASS:    COP 3330-02Z  --  Object Oriented Programming
// PROGRAM:  Eclipse Project 3  --  Concurrency.java

////////////////////////////////////////////////////////////////////////////////////////////////////

/*
PROGRAM INSTRUCTIONS:

If you understand the content of the Concurrency Basics Tutorial, this Project
will be easy. If not, 40 hours of working on it won't help. I suggest reading
the instructions slowly and carefully before reading the tutorial so that you
will notice what is needed as you read. Then read them again, slowly and carefully.

Using the concepts from the Concurrency Basics Tutorial I provided in Modules,
write a program that consists of two threads. The first is the main thread that
every Java application has. The main thread should create a new thread from the
Runnable object, MessageLoop, and wait for it to finish. If the MessageLoop thread
takes too long to finish, the main thread should interrupt it. Use a variable
named maxWaitTime to store the maximum number of seconds to wait. The main thread
should output a message stating that it is still waiting every half second.

The MessageLoop thread should print out a series of 4 messages. These messages
should be numbered, as in the example below. It should wait 850 milliseconds
between printing messages to create a delay.  If it is interrupted before it has
printed all its messages, the MessageLoop thread should print "Message loop
interrupted" and exit. Or you can let main print "Message loop interrupted".

Your program must demonstrate that it can both output messages and interrupt the
message output. To do this, place the body of main into a for loop using maxWaitTime
as the index. As in the following example, it should finally output all 4 messages
in the last iteration.


So in main your code will be:

for (int maxWaitTime = 1;  maxWaitTime <= 4;  maxWaitTime++)
{
	// All of main's processing goes here (Note that it does not say some, it says all).
}


Your class must be in a package named mypackage and be named Concurrency, as
explained in last week's videos. It should be contained in 1 and only 1 source file.

Include your name at the top of the source file.

Upload Concurrency.java


////////////////////////////////////////////////////////////////////////////////////////////////////

SAMPLE OUTPUT:

maxWaitTime: 1 second(s)
main : Starting MessageLoop thread
main : Waiting for MessageLoop thread to finish
main : Continuing to wait...
main : Continuing to wait...
Thread-0 : 1. All that is gold does not glitter, Not all those who wander are lost
main : MessageLoop interrupted



maxWaitTime: 2 second(s)
main : Starting MessageLoop thread
main : Waiting for MessageLoop thread to finish
main : Continuing to wait...
main : Continuing to wait...
Thread-1 : 1. All that is gold does not glitter, Not all those who wander are lost
main : Continuing to wait...
main : Continuing to wait...
Thread-1 : 2. The old that is strong does not wither, Deep roots are not reached by the frost
main : MessageLoop interrupted



maxWaitTime: 3 second(s)
main : Starting MessageLoop thread
main : Waiting for MessageLoop thread to finish
main : Continuing to wait...
main : Continuing to wait...
Thread-2 : 1. All that is gold does not glitter, Not all those who wander are lost
main : Continuing to wait...
main : Continuing to wait...
Thread-2 : 2. The old that is strong does not wither, Deep roots are not reached by the frost
main : Continuing to wait...
main : Continuing to wait...
Thread-2 : 3. From the ashes a fire shall be woken, A light from the shadows shall spring
main : MessageLoop interrupted



maxWaitTime: 4 second(s)
main : Starting MessageLoop thread
main : Waiting for MessageLoop thread to finish
main : Continuing to wait...
main : Continuing to wait...
Thread-3 : 1. All that is gold does not glitter, Not all those who wander are lost
main : Continuing to wait...
main : Continuing to wait...
Thread-3 : 2. The old that is strong does not wither, Deep roots are not reached by the frost
main : Continuing to wait...
main : Continuing to wait...
Thread-3 : 3. From the ashes a fire shall be woken, A light from the shadows shall spring
main : Continuing to wait...
Thread-3 : 4. Renewed shall be blade that was broken



main : Done!

*/

////////////////////////////////////////////////////////////////////////////////////////////////////

/*
MESSAGE LOOP CONTENTS:

1. All that is gold does not glitter, Not all those who wander are lost
2. The old that is strong does not wither, Deep roots are not reached by the frost
3. From the ashes a fire shall be woken, A light from the shadows shall spring
4. Renewed shall be blade that was broken

*/

////////////////////////////////////////////////////////////////////////////////////////////////////

// DECLARE PACKAGE AS SPECIFIED IN THE INSTRUCTIONS
package mypackage;

// IMPORT REQUIRED DEPENDENCIES
// import java.util.concurrent;

// BEGIN class Concurrency{}
public class Concurrency
{

	// BEGIN class MessageLoop{}
	public static class MessageLoop implements Runnable
	{

		// PRIVATE MEMBER VARIABLE(S)
		private String[] storyContents;

		// 1 ARGUMENT CONSTRUCTOR
		public MessageLoop(String[] storyContents)
		{
			this.storyContents = storyContents;
		} // END CONSTRUCTOR

		// RUN THREAD WITH CODE BELOW
		@Override
		public void run()
		{

			// FINAL VARIABLE(S)
			final int MSGLOOP_WAIT = 850;

			// LONG RUNNING OPERATION WITH EXCEPTION HANDLING...
			try
			{

				// storyContents[] PRINTING LOOP
				for (int i = 0; i < storyContents.length; i++)
				{

					// SLEEP FOR 850ms BEFORE ISSUING THE NEXT LINE OF storyContents
					// System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\tSLEEPING %dms!\n", MSGLOOP_WAIT);
					Thread.sleep(MSGLOOP_WAIT);

					// ECHO CURRENT THREAD BEING EXECUTED
					System.out.print("\n\n" + Thread.currentThread().getName() + " :\t");

					// ECHO CURRENT LINE OF storyContents
					System.out.println(storyContents[i]);

				} // END for(){}

				// DEBUG -- CHECK TO SEE IF PROGRAM EXECUTION MAKES IT TO THIS LINE
				// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tAFTER MessageLoop{} for(){} LOOP!");

			} // END try{}
			catch (InterruptedException iE)
			{
				// NOTIFY INTERRUPT FROM MessageLoop{} THREAD
				System.out.print("\n" + Thread.currentThread().getName() + " :\t");
				System.out.println("MessageLoop() INTERRUPTED!");

				// PROPOGATE INTERRUPT (resets interrupt flag status)
				Thread.currentThread().interrupt();
				return;

			} // END catch(){}

		} // END run(){}

	} // END MessageLoop{}

	// BEGIN main(){}
	public static void main(String[] args) throws InterruptedException
	{

		// FINAL VARIABLE(S)
		final int MAIN_WAIT = 500;

		// LOCAL VARIABLE(S)
		String[] storyContents =
		{
			"1. All that is gold does not glitter, Not all those who wander are lost",
			"2. The old that is strong does not wither, Deep roots are not reached by the frost",
			"3. From the ashes a fire shall be woken, A light from the shadows shall spring",
			"4. Renewed shall be blade that was broken"
		};

		// THREAD: main -- ALL OF main()'s PROCESSING GOES HERE
		for (int maxWaitTime = 1; maxWaitTime <= 4; maxWaitTime++)
		{

			// IMPLEMENT EXCEPTION HANDLING FOR main() THREAD
			try
			{

				// CONVERT maxWaitTime FROM SECONDS TO MILLISECONDS
				int threadTime = maxWaitTime * 1000;

				// ECHO maxWaitTime TO USER
				System.out.printf("\n\n\n\n\nmaxWaitTime:\t%d second(s)\n", maxWaitTime);

				// ECHO INTENT TO START THREAD
				System.out.print(Thread.currentThread().getName() + " :\t\t");
				System.out.println("Starting MessageLoop thread");

				// START NEW THREAD: MessageLoop() --> run()
				Thread msgLoopThread = new Thread(new MessageLoop(storyContents));
				msgLoopThread.start();

				// ECHO WAIT MESSAGE TO USER ABOUT THREAD BEING EXECUTED
				System.out.print(Thread.currentThread().getName() + " :\t\t");
				System.out.println("Waiting for MessageLoop thread to finish");

				// PRINT WAITING MESSAGE FROM main() EVERY 500ms
				// for (int count = maxWaitTime * 2; count != 0 && msgLoopThread.isAlive(); count--)
				for (int count = threadTime / MAIN_WAIT; count != 0 && msgLoopThread.isAlive(); count--)
				{

					// ECHO WAITING MESSAGE TO USER AT t = 0 AND EVERY 500ms
					System.out.print("\n" + Thread.currentThread().getName() + " :\t\t");
					System.out.print("----->    Continuing to wait...    <-----");

					// WAIT AT MOST 500ms FOR msgLoopThread TO FINISH
					// BEFORE REVERTING CONTROL BACK TO main THREAD
					// System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\tWAITING AT MOST %dms!\n", MAIN_WAIT);
					msgLoopThread.join(MAIN_WAIT);

					// INTERRUPT msgLoopThread THREAD IF IT IS STILL RUNNING AND
					// INNER for(){} LOOP HAS REACHED ITS FINAL ITERATION
					if (msgLoopThread.isAlive() && count == 1)
					{
						// NOTIFY INTERRUPT FROM main() THREAD
						// System.out.print("\n" + Thread.currentThread().getName() + " :\t\t");
						// System.out.println("MessageLoop() INTERRUPTED!");

						// INTERRUPT msgLoopThread THREAD AND YIELD CONTROL TO msgLoopThread SO
						// ITS catch(){} BLOCK CAN FINISH EXECUTING BEFORE main() THREAD CONTINUES
						msgLoopThread.interrupt();
						msgLoopThread.join(MAIN_WAIT);

					} // END if(){}

				} // END for(){}

				// DEBUG -- CHECK TO SEE IF PROGRAM EXECUTION MAKES IT TO THIS LINE
				// System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tAFTER main() INNER for(){} LOOP");

			} // END try{}
			catch (InterruptedException iE)
			{

				// NOTIFY INTERRUPT
				System.out.print(Thread.currentThread().getName() + " :\t\t");
				System.out.println("main() INTERRUPTED!");

				// PROPOGATE INTERRUPT
				Thread.currentThread().interrupt();
				return;

			} // END catch(){}

		} // END for()

		// ECHO FINAL THREAD STATUS
		System.out.printf("\n\n\n\n\n");
		System.out.print(Thread.currentThread().getName() + " :\t\t");
		System.out.println("Done!");

	} // END main(){} METHOD

} // END class Concurrency{}