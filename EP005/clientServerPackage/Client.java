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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

// BEGIN Client CLASS
public class Client
{
	// INSTANCE VARIABLES
	private Socket connection; // CONNECTION TO SERVER
	private ObjectInputStream in; // INPUT STREAM FROM SERVER
	private ObjectOutputStream out; // OUTPUT STREAM TO SERVER
	private String message;
	private String fileName;

	// GLOBAL CONSTANTS
	static final int CLIENT_PORT = 4444;
	static final String SERVER_IP_ADDRESS = "127.0.0.1";

	// FUNCTIONAL METHODS
	public void startClient(String ip, int port)
	{
		try
		{
			// STEP 1: Create a Socket to Connect to the Server
			connectToServer(ip, port);

			// STEP 2: Get the Socket's I/O Sreams
			getStreams();

			// STEP 3: Perform the Processing
			processConnection();

		} catch (EOFException e) // HANDLE EXCEPTIONS
		{
			System.out.println("CLIENT ERROR: EOFException");
			System.out.println("CLIENT ERROR: CLIENT TERMINATED CONNECTION");
			e.printStackTrace();

		} catch (IOException e)
		{
			System.out.println("CLIENT ERROR: IOException!");
			e.printStackTrace();

		} finally
		{
			stopClient();

		} // END try{}catch(){}finally{}

	} // END startClient(){} METHOD

	private void connectToServer(String ip, int port) throws IOException
	{
		System.out.println("STARTING CLIENT...");
		connection = new Socket(ip, port);
		System.out.println("CLIENT CONNECTED TO " +
							connection.getInetAddress().getHostName() +
							" ON PORT: " +
							port
						  );
	} // END connectToServer(){} METHOD

	private void getStreams() throws IOException
	{
		// STEP 3: Get the Socket's I/O Streams
		System.out.println("AQUIRING I/O STREAMS...");
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush(); // FLUSH OUTPUT BUFFER TO SEND HEADER INFORMATION
		in = new ObjectInputStream(connection.getInputStream());
		System.out.println("I/O STREAMS AQUIRED!");

	} // END getStreams(){} METHOD

	public void stopClient()
	{
		try
		{
			// STEP 4: Close the Connection
			// TERMINATE ALL CLIENT IOSTREAMS & CONNECTIONS
			System.out.println("TERMINATING CLIENT...");
			out.close();
			in.close();
			connection.close();
			System.out.println("CLIENT TERMINATION COMPLETE!");

		} catch (IOException e) // HANDLE EXCEPTIONS
		{
			System.out.println("CLIENT ERROR: IOException!");
			e.printStackTrace();

		} // END try{}catch(){}

	} // END stopClient(){} METHOD

	private void processConnection() throws IOException
	{
		System.out.println("CLIENT IS NOW PROCESSING CONNECTION...\n\n");

/*
		// COMMUNICATION CHECK - TEST MESSAGES
		try
		{
			// TEST MESSAGE
			sendData("TEST MESSAGE FROM CLIENT TO SERVER");

			// READ NEXT message OBJECT FROM SERVER
			message = (String) in.readObject();

			// DISPLAY MESSAGES RECEIVED FROM SERVER
			System.out.println("\n" + message);
		} catch (ClassNotFoundException e)
		{
			System.out.println("CLIENT ERROR: ClassNotFoundException!");
			e.printStackTrace();
		}
*/

		// PRIMARY PROCESSING
		try
		{
			// READ NEXT message OBJECT FROM SERVER
			message = (String) in.readObject();
			// DISPLAY MESSAGES RECEIVED FROM SERVER
			System.out.print("\n" + message);

			// TAKE INPUT FROM USER
			Scanner input = new Scanner(System.in);
			fileName = input.nextLine();
//			input.close();

			// SEND USER INPUT BACK TO SERVER
			out.writeObject(fileName);
			out.flush();
			System.out.println("CLIENT >>> " + fileName);
			
			message = (String) in.readObject();
			if (message.equals("SERVER >>> ERROR! - FILE DOES NOT EXIST!\n"))
			{
				System.out.println("ERROR! - FILE NOT FOUND! RESTARTING PROCESSING");
				processConnection();
				input.close();
				return;
			}
				

			// READ NEXT message OBJECT FROM SERVER
			message = (String) in.readObject();

			// DISPLAY MESSAGES RECEIVED FROM SERVER
			System.out.println("\n" + message);

			do
			{
				// READ NEXT message OBJECT FROM SERVER
				message = (String) in.readObject();

				// DISPLAY MESSAGES RECEIVED FROM SERVER
				System.out.print(message + "\n");

			}while(!message.equals("SERVER >>> END OF FILE!\n\n\n\n"));

			// CLOSE SCANNER OBJECT BEFORE EXITING PROCESSING METHOD
			// TO PREVENT RESOURCE LEAKS
			input.close();

		} catch (ClassNotFoundException e)
		{
			System.out.print("CLIENT ERROR! : UNKNOWN OBJECT TYPE RECEIVED!");

		} // END try{}catch(){}

	} // END processConnection(){} METHOD

	private void sendData(String message) // FOR SENDING MESSAGES TO SERVER
	{
		// SEND OBJECT TO SERVER
		try
		{
			out.writeObject("CLIENT >>> " + message);
			out.flush(); // FLUSH OUTPUT TO SERVER
			// DISPLAY MESSAGE SENT TO SERVER ON CLIENT'S CONSOLE OUTPUT
			System.out.println("\nCLIENT >>> " + message);

		} catch (IOException e)
		{
			System.out.println("CLIENT ERROR: IOException!");
			e.printStackTrace();

		} // END try{}catch(){}

	} // END sendData(){} METHOD

	// MAIN PROGRAM
	public static void main(String[] args)
	{
		System.out.println("START: public class Client { main() }");
		Client client = new Client();
		client.startClient(SERVER_IP_ADDRESS, CLIENT_PORT);
		System.out.println("END: public class Client { main() }");

	} // END main(){} PROGRAM

} // END Client CLASS