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

// DECLARE PACKAGE AS SPECIFIED IN THE INSTRUCTIONS
// package mypackage;

// IMPORT REQUIRED DEPENDENCIES
// import java.util.concurrent;

// BEGIN class Concurrency{}
public class ConcurrencyTutorial
{

////////////////////////////////////////////////////////////////////////////////////////////////////

	public static class HelloRunnable implements Runnable
	{
		public void run()
		{
			System.out.println(Thread.currentThread().getName());
			System.out.println("Hello from a HelloRunnable thread!");
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////

	public static class HelloThread extends Thread
	{
		public void run()
		{
			System.out.println(Thread.currentThread().getName());
			System.out.println("Hello from a HelloThread thread!");
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////

	public static class SleepMessages implements Runnable
	{
		// PRIVATE MEMBER VARIABLE
		private String[] importantInfo;

		// 1 VARIABLE CONSTRUCTOR
		public SleepMessages(String[] importantInfo)
		{
			this.importantInfo = importantInfo;
		}

		// FUNCTIONAL METHOD  --  run()
		public void run()
		{
			for (int i = 0; i < importantInfo.length; i++)
			{
				// PAUSE FOR 4 SECONDS
				try
				{
					Thread.sleep(4000);
				}
				catch (InterruptedException e)
				{
					// WE'VE BEEN INTERRUPTED: NO MORE MESSAGES
					System.out.println(Thread.currentThread().getName());
					System.out.println("WE'VE BEEN INTERRUPTED: NO MORE MESSAGES!");
					return;
				}
				// PRINT A MESSAGE
				System.out.println(Thread.currentThread().getName());
				System.out.println(importantInfo[i]);
			} // END for()
		} // END run(){}
	} // END class SleepMessages{}

////////////////////////////////////////////////////////////////////////////////////////////////////

	public static class HeavyCrunch implements Runnable
	{
		// PRIVATE MEMBER VARIABLE
		private String[] inputs;

		// 1 VARIABLE CONSTRUCTOR
		public HeavyCrunch(String[] inputs)
		{
			this.inputs = inputs;
		}

		// FUNCTIONAL METHOD -- run()
		public void run()
		{
			for (int i = 0; i < inputs.length; i++)
			{
				System.out.println(Thread.currentThread().getName());
				System.out.println(inputs[i]);

				if (Thread.interrupted())
				{
					// WE'VE BEEN INTERRUPTED: NO MORE MESSAGES
					System.out.println(Thread.currentThread().getName());
					System.out.println("WE'VE BEEN INTERRUPTED: NO MORE MESSAGES!");
					return;
				}

			} // END for()
		} // END run(){}
	} // END class SleepMessages{}

////////////////////////////////////////////////////////////////////////////////////////////////////

	// BEGIN main(){}
	public static void main(String[] args) throws InterruptedException
	{
		try
		{
			new Thread(new HelloRunnable()).start();
			
			new HelloThread().start();

			String[] importantInfo = {
				"Mares eat oats",
				"Does eat oats",
				"Little lambs eat ivy",
				"A kid will eat ivy too"
			};

			String[] crunchInfo = {
				"HeavyCrunch 1",
				"HeavyCrunch 2",
				"HeavyCrunch 3",
				"HeavyCrunch 4",
			};

			new Thread(new SleepMessages(importantInfo)).start();

			Thread hcThread = new Thread(new HeavyCrunch(crunchInfo));
			hcThread.start();
			hcThread.interrupt();
			if (hcThread.isInterrupted())
				{
					throw new InterruptedException();
				}
			/*
			for (int i = 0; i < importantInfo.length; i++)
			{
				// PAUSE FOR 4 SECONDS
				Thread.sleep(4000);
				// PRINT A MESSAGE
				System.out.println(importantInfo[i]);
			}
			*/		
		}
		catch(InterruptedException e)
		{
			// WE'VE BEEN INTERRUPTED: NO MORE MESSAGES
			System.out.println(Thread.currentThread().getName());
			System.out.println("WE'VE BEEN INTERRUPTED: NO MORE MESSAGES!");
			return;
		}

		System.out.println("END OF main()");

	} // END main(){}

////////////////////////////////////////////////////////////////////////////////////////////////////

} // END class ConcurrencyTutorial{}