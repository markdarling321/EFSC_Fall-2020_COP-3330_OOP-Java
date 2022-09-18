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

// DECLARE PACKAGE:
package clientServerPackage;

// IMPORT DEPENDENCIES:
import java.net.*;			// LOW-LEVEL COMMUNICATION DETAILS
import java.io.*;			// INPUT & OUTPUT STREAMS TO WRITE & READ FROM

// BEGIN Server CLASS:
public class GreetServer
{
	// INSTANCE VARIABLES
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	// FUNCTIONAL METHODS
	public void start (int port)
	{
		try
		{
			serverSocket = new ServerSocket(port);
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String greeting = in.readLine();
			if ("hello server".equals(greeting))
			{
				out.println("hello client");
			}
			else
			{
				out.println("unrecognised greeting");
			}
		}
		catch (IOException ioe)
		{
			ioe.toString();
		}
	
	} // END start METHOD

	public void stop()
	{
		try
		{
			in.close();
			out.close();
			clientSocket.close();
			serverSocket.close();
		}
		catch (IOException ioe)
		{
			ioe.toString();
		}

	} // END stop METHOD

	// BEGIN MAIN PROGRAM CODE
	public static void main(String[] args)
	{
		GreetServer server = new GreetServer();
		server.start(6666);
	} // END main(){}

} // END Server CLASS