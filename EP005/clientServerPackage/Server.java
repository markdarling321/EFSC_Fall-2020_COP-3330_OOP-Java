// NAME:     Mark Darling
// DATE:     11/04/20
// CLASS:    COP 3330-02Z  --  Object Oriented Programming
//
//								   *****************************************
// PROGRAM:  Eclipse Project 5:    **   PACKAGE:    clientServerPackage   **
//								   *****************************************
//								   *                Client.java            *
//								   *                Server.java            *
//								   *****************************************
////////////////////////////////////////////////////////////////////////////////

/*
PROGRAM INSTRUCTIONS:			Eclipse Project 5  --  Client/Server

Write a client program and server program. The server uses a socket connection
to allow a client to supply a filename and the server will send the file contents
to the client or an error message if the file does not exist. The client will
display the contents to the screen (not create a new file with the contents).

Do not accept arguments from the command line.

Do not use a path name when opening the file.
This means the file should be located in the top level folder of the project.

Do not zip your project, just send the 2 (and only 2) .java files.

You are not permitted to make this program use a GUI.
If it uses a GUI, you will receive a 0.

Both programs must be part of the same Eclipse project.
One class named Server and one named Client. These must be the only 2 classes.
They must be in a package named clientServerPackage.
These names are not negotiable and are worth points. Not using these names will
cause your program to not compile on my side without me changing them.

Your name must appear at the top of all source files. This is worth points.

Part of your grade includes indentation and meaningful variable names.
See the item in Modules named Java Programming Style Guide.

Submissions that do not meet these requirements will receive a 0.
So don't bother sending 2 separate projects, one for the server and one for the
client. It will receive a 0.

*/
////////////////////////////////////////////////////////////////////////////////

// DEFINE PACKAGE
package clientServerPackage;

// IMPORT REQUIRED DEPENDENCIES
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// BEGIN Server CLASS
public class Server
{
	// INSTANCE VARIABLES
	private ServerSocket server; // SERVER SOCKET
	private Socket connection; // CONNECTION TO CLIENT
	private ObjectInputStream in; // INPUT STREAM FROM CLIENT
	private ObjectOutputStream out; // OUTPUT STREAM TO CLIENT
	private String message;

	// GLOBAL CONSTANTS
	static final int SERVER_PORT = 4444;
	static final int QUEUE_LENGTH = 4;

	// FUNCTIONAL METHODS
	public void startServer(int port, int queueLength)
	{
		// SET UP SERVER TO RECEIVE & PROCESS CONNECTIONS
		try
		{
			// STEP 1: Create a ServerSocket
			System.out.println("STARTING SERVER...");
			server = new ServerSocket(port, queueLength); // CREATE ServerSocket
			System.out.println("SERVER SOCKET CREATED AT PORT: " + port);

			while (true) // CONTINUE TO WAIT FOR NEW CONNECTIONS
			{ // AFTER CLIENT HAS DISCONNECTED
				try
				{
					// STEP 2: Wait for a Connection
					waitForConnection();

					// STEP 3: Get the Scoket's I/O Streams
					getStreams();

					// STEP 4: Perform the Processing
					processConnection();

				} catch (EOFException e)
				{
					System.out.println("SERVER ERROR: EOFException!");
					e.printStackTrace();

				}
				finally
				{
					stopServer();

				} // END try{}catch(){}finally{}

			} // END while(){}

		} catch (IOException e) // HANDLE EXCEPTIONS
		{
			System.out.println("SERVER ERROR: IOException!");
			e.printStackTrace();

		} // END try{}catch(){}

	} // END startServer(){} METHOD

	private void waitForConnection() throws IOException
	{
		// STEP 2: Wait for a Connection
		System.out.println("WAITING FOR CONNECTION...");
		connection = server.accept(); // ALLOW SERVER TO ACCEPT CONNECTION
		System.out.println("CONNECTION RECEIVED FROM: " +
							connection.getInetAddress().getHostName()
						  );

	} // END waitForConnection(){} METHOD

	private void getStreams() throws IOException
	{
		// STEP 3: Get the Socket's I/O Streams
		System.out.println("AQUIRING I/O STREAMS...");
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush(); // FLUSH OUTPUT BUFFER TO SEND HEADER INFORMATION
		in = new ObjectInputStream(connection.getInputStream());
		System.out.println("I/O STREAMS AQUIRED!");

	} // END getStreams(){} METHOD

	public void stopServer()
	{
		try
		{
			// STEP 5: Close the Connection
			// TERMINATE ALL SERVER IOSTREAMS & CONNECTIONS
			System.out.println("TERMINATING SERVER CONNECTION...");
			out.close();
			in.close();
			connection.close();
			// server.close();
			System.out.println("SERVER TERMINATION COMPLETE!");

		} catch (IOException e) // HANDLE EXCEPTIONS
		{
			System.out.println("SERVER ERROR: IOException!");
			e.printStackTrace();

		} // END try{}catch(){}

	} // END stopServer(){} METHOD

	private void processConnection() throws IOException
	{
		System.out.println("SERVER IS NOW PROCESSING CONNECTION...\n\n");

/*
		// COMMUNICATION CHECK - TEST MESSAGES
		try
		{
			// TEST MESSAGE
			sendData("TEST MESSAGE FROM SERVER TO CLIENT");

			// READ NEXT message OBJECT FROM CLIENT
			message = (String) in.readObject();

			// DISPLAY message RECEIVED FROM CLIENT
			System.out.println("\n" + message);

		} catch (ClassNotFoundException e)
		{
			System.out.println("SERVER ERROR: ClassNotFoundException!");
			e.printStackTrace();
		}
*/

		// PRIMARY PROCESSING
		try
		{
			// PROMPT CLIENT TO ENTER FILENAME TO BE SENT FROM SERVER TO CLIENT
			sendData("ENTER A FILENAME: ");

			// READ OBJECT RECEIVED FROM CLIENT AND STORE AS String IN fileName
			String fileName = (String) in.readObject();
			System.out.println("CLIENT >>> " + fileName);
			
			// CREATE NEW ABSTRACT FILE PATH OBJECT FROM FILENAME GIVEN BY CLIENT
			File fileAbstractPath = new File(fileName);
			
			// CHECK IF FILE EXISTS
			if (!fileAbstractPath.exists())
			{
				sendData("ERROR! - FILE DOES NOT EXIST!\n");
				// RESTART PROCESSING IF FILE DOES NOT EXIST
				processConnection();
				return;
			}
			
			// STORE ABSOLUTE FILE PATH OF ABSTRACT FILE OBJECT
			String filePath = fileAbstractPath.getAbsolutePath();
			// ECHO ABSOLUTE FILE PATH TO ALL CONSOLES
			sendData("FILE FOUND AT: " + filePath + "\n");
			// CREATE NEW FILE SCANNER FOR PARSING FILE USING ABSTRACT FILE PATH
			Scanner fileScanner = new Scanner(fileAbstractPath);
			// INITIALIZE LINE NUMBER COUNTER
			int lineNum = 1;

			// PARSE FILE CONTENTS AND OUTPUT TO CLIENT'S CONSOLE
			// UNTIL END OF FILE IS REACHED
			while (fileScanner.hasNextLine())
			{
				// GET CURRENT LINE AND STORE IT AS A STRING
				String currentLine = fileScanner.nextLine();

				// OUPUT CURRENT LINE TO BOTH SERVER AND CLIENT CONSOLES
				if (lineNum >= 0 && lineNum <= 99)
					sendData("LINE " + lineNum + "\t  |" + currentLine);
				else if (lineNum >= 100 && lineNum <= 999)
					sendData("LINE " + lineNum + "  |" + currentLine);
				else
					sendData("LINE " + lineNum + " |" + currentLine);

				// HANDLE SPECIAL END OF FILE CASE(S)
				if (!fileScanner.hasNextLine() && currentLine.isEmpty())
				{
					// PRINT CURRENT LINE TO SCREEN BEFORE WRITING IT TO OUTPUT FILE
					sendData("LINE " + (lineNum + 1) + "\t  |" + currentLine + "\n");
				}

				// INCREMENT LINE NUMBER COUNTER BY 1 EACH LOOP
				lineNum++;

			} // END while(){}

			// NOTIFY USER END OF FILE HAS BEEN REACHED
			sendData("END OF FILE!\n\n\n\n");

			// CLOSE SCANNER OBJECT AND OUPUT FILE OBJECT
			fileScanner.close();

		} catch (ClassNotFoundException e)
		{
			System.out.print("SERVER ERROR! : UNKNOWN OBJECT TYPE RECEIVED!");

		} // END try{}catch(){}

	} // END processConnection(){} METHOD

	private void sendData(String message) // FOR SENDING MESSAGES TO CLIENT
	{
		// SEND OBJECT TO CLIENT
		try
		{
			out.writeObject("SERVER >>> " + message);
			out.flush(); // FLUSH OUTPUT TO CLIENT
			// DISPLAY MESSAGE SENT TO CLIENT ON SERVER'S CONSOLE OUTPUT
			System.out.println("SERVER >>> " + message);

		} catch (IOException e)
		{
			System.out.println("SERVER ERROR: IOException!");
			e.printStackTrace();

		} // END try{}catch(){}

	} // END sendData(){} METHOD

	// MAIN PROGRAM
	public static void main(String[] args)
	{
		System.out.println("START: public class Server { main() }");
		Server server = new Server();
		server.startServer(SERVER_PORT, QUEUE_LENGTH);
		System.out.println("END: public class Server { main() }");

	} // END main() PROGRAM

} // END Server CLASS