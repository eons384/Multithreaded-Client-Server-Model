/*****************************************************************************
/*                             Michael Talbott
/*                               Server.java
/*
/* Description of Class: This class acts a server and threads each connection 
/* that is made to it to be able to handle multiple clients. Runs infinitely 
/* use control+c simultaneously to break it from the command prompt. 
/*
/*****************************************************************************
/* Input: Is a string passed from the Client.java
/* Output: No output
/*****************************************************************************
*/
import java.io.*; // enabling input and output
import java.net.*; // enabling for using the server object
import java.util.*;

public class Server
{
  public static void main(String[] args)
  {
    ServerSocket server = null; // initialized here for scope
    Socket connection; // also for scope
    boolean valid = false; // used to continously run the server
    try // trying for exceptions
    {
      server = new ServerSocket(30001); // initializing the server to port 30001
    }
    catch(Exception e) // catching and printing stack with any exception thrown
    {
      e.printStackTrace();
    }
    /************************************************************************************
    /* Document While code: This while loop will run continously it will never stop. 
    /* This is how a server is supposed to be. Its listening for sockets and once it 
    /* recieves one that connection is passed to the ClientThread class and the thread is 
    /* ran.
    /************************************************************************************
    */
    while(!valid) //infinite loop I know this and was told this was ok
    {
      try
      {
        connection = server.accept(); // accept any sockets trying to communicate on the port
        ClientThread recieve = new ClientThread(connection); // instantiating a ClientThread object and passing the connection to the constructor
        Thread thread = new Thread(recieve); // Thread instantiated and recieve object which implements runnable is passed to it
        thread.start(); // sending flow of control to the run() block in ClientThread class
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}
/*****************************************************************************
/*                             Michael Talbott
/*                            ClientThread.java
/*
/* Description of Class: This is where threads are handled. This class is to 
/* enable the server to handle multiple clients. Run method is where the logic
/* is and each thread uses this method for the exact same purpose
/*
/*****************************************************************************
/* Input: A socket connection from the Server.java
/* Output: a string or sentence that is passed back to the Client.java
/*****************************************************************************
*/
class ClientThread implements Runnable // runnable is implemented to manage threads
{
  Socket clientSocket = null; // used for scope 
  BufferedReader fromClient = null; // used for scope 
  BufferedReader fromServiceBroker = null; // used for scope 
  PrintWriter toClient = null; // used for scope 
  
  /************************************************************************************
  /* Document Constructor Code: This overriden constructor recieves as socket and 
  /* assigns the clientSocket to the socket passed into the contructor
  /************************************************************************************
  */
  public ClientThread(Socket socket)
  {
    this.clientSocket = socket;
  }
  /************************************************************************************
  /* Document run() code: This is where all the logic for each thread is handled
  /* we read from the client then send that message to the ServiceBroker.java to 
  /* do even more work. ServiceBroker then sends back pertinent information to this 
  /* run() method. Which in turn is passed back to the client for displaying the data.
  /************************************************************************************
  */
  public void run()
  {
    try
    {
      fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // reader to read client output
      String message = fromClient.readLine(); // this output is assigned to this string
      Process p = Runtime.getRuntime().exec("java -jar ServiceBroker.jar " + message); // using runtime to start the service broker and passing the message to it from command line args
      fromServiceBroker = new BufferedReader(new InputStreamReader(p.getInputStream())); // to read what Service Broker returns
      String returnMessage = fromServiceBroker.readLine(); // assigning it to a string
      toClient = new PrintWriter(clientSocket.getOutputStream(),true); // initialize our printWriter to send information back to the client
      toClient.println(returnMessage); // sending our output to the client here
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}


    
