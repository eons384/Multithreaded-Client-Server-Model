/*****************************************************************************
/*                         Michael Talbott
/*                        Prototype Project
/*
/* This program introduces threading and to handle multiple clients. The idea 
/* is that the server spins a thread for each client. Then sends input to the 
/* Service Broker to call whichever service the user wants. Dictionary.java is 
/* the only actual working service. The others just return a simple message.
/*****************************************************************************
/* Input: A letter 1,2,3 or a string q. The numbers denote which service the q quits.
/*        if Service 3 is selected the program will ask for an english word to translate
/* Output: Output is the English word then the Spanish Translation. Or a message
/*         printing out the service that was called.
/*****************************************************************************
/*                             Maintenance Log
/*****************************************************************************
/* 1/28/2014 -- added PrintWriter to write to an output file
/* 1/24/2014 -- successfully implemented the communication between files using 
/*              Process, getRuntime().exec, and using BufferedInputStream.
/* 1/22/2014 -- constructed the basic while loop taking in input from user in
/*              Translator.java code.
/* 1/22/2014 -- constructed the switch statement that will handle the translation
/*              in the file Dictionary.java.
/* 2/23/2014 -- constructed the client server model you see in this particular example
/* 2/23/2014 -- constructed the printwriters to make it easier to pass along 
/*              information through the sockets. This may be a bad way to handle
/*              socket programming but for this example it made it fairly simple.
/* 2/23/2014 -- removed the getRuntime().exec command to switch to using sockets.
                Also implemented the client server model required for project 2.
/* 3/29/2014 -- added Service1, Service2, Server, and ServiceBroker classes to broaden 
                the versatility of the program
/* 3/30/2014 -- added threading to Server to handle multiple clients
/* 3/30/2014 -- added exception handling for the first prompt if you enter anything
                besides 1,2,3, or q exception is thrown and prompt will print again
/* 3/30/2014 -- prompting has been changed to accomodate for new services
/* 3/30/2014 -- Service broker implements a new getRunTime().exec command to run
                whichever service is called
/*****************************************************************************
*/

/*****************************************************************************
/*                             Client.java
/*
/* Description of Class: This class asks for a number input from 1 to 3. These
/* numbers correspond with which service is called. If 3 is called it is similar
/* to the translation program where you will be prompted for an english word. It 
/* takes these inputs and sends them to the Server.java class where threading is started.
/*
/*****************************************************************************
/* Input: A letter 1,2,3 or a string q. The numbers denote which service the q quits.
/* Output: The string that whichever service chosen returns.
/*****************************************************************************
*/

import java.io.*; // Importing tools such as Scanner, Process, and InputStream in these two statements
import java.util.*;
import java.net.*; // Importings tools such as Sockets

public class Client{
  
  public static void main(String[] args)
  { 
    String word = null; // defined here for scope
    String translatedWord = null; // holder for translated word
    Scanner in = null; // defined here for scope
    boolean valid = false; // valid boolean for exception handling
    String englishWord = null; // defined here for scope
    Socket connection1 = null; // socket initialized here for global scope
    PrintWriter pw = null; // This assignment is here because I need the scope of the variable to be throughout the program
    File f = new File("AssignmentOutput.txt"); // creating a file to send output to
    try // try for any exceptions with PrintWriter
    {
      pw = new PrintWriter(f); // using this to be able to actually write to the output file
    }
    catch(Exception e) // if exception is caught the stack will be printed
    {
      e.printStackTrace();
    }
    boolean quit = false; // validation variable to exit the loop when user wants to quit
    
    /************************************************************************************
    /* Document While code: While the validation variable is false. Until the user enters
    /* q the while loop will not exit.
    /*
    /************************************************************************************
    */
    while(!quit)
    {
      /************************************************************************************
      /* Document While code: While the validation variable is false. Until the user enters
      /* q or 1,2,3 the while loop will not exit. If anything else is entered and exception 
      /* is thrown and you will be prompted for input again.
      /************************************************************************************
      */
      while(!valid)
      {
        try
        {
          System.out.println("Please Enter 1 For Service 1. Enter 2 For Service 2. Enter 3 For Translation Program. Enter q To Quit."); 
          in = new Scanner(System.in); // To read user input
          word = in.next(); // whatever user enters is stored in the word variable here
          if(word.matches("[q123]")) // using a regular expression to make sure that these are the only values that can be taken as input
          {
            valid = true; // loop exits here if input is valid
          }
          else
          {
            throw new Exception(); // throws exception if input is not valid
          }
        }
        catch(Exception e)
        {
        }
      }
      valid = false; // valid boolean is flagged false here because the outer loop will execute more than once
      /***********************************************************************************
      /* Document IF code: If user enters q. The validation variable quit is set to true.
      /* The while loop will exit and a socket will be initialized to shut down the 
      /* Dictionary server.
      /*
      /***********************************************************************************
      */
      if(word.equals("q"))
      {
        quit = true; // variable is true now so we break the while loop and move on
        pw.close(); // closing the fileWriter so all input and output will be written to the output text file
        try // you have to try any sockets for exceptions
        {
        connection1.close(); // socket is shut down here 
        }
        catch(Exception e)
        {
        }
      }
      
      /***********************************************************************************
      /* Document ELSE IF code: If user enters 3. This Service requires extra prompts for 
      /* an english word so we can run the translation program. It communicates with the 
      /* server the same as the others though except and english word is passed with the socket.
      /* The translated word will be passed back once the the process is done in the thread.
      /*
      /***********************************************************************************
      */
      else if(word.equals("3")) // if service three is selected we need to run the translation service so it is handled differently than the other 2
      {
        try // trying this code for any exceptions
        {
          System.out.println("Please Enter An English Word To Translate To Spanish.");
          englishWord = in.next(); // Scanner for the english word user input
          connection1 = new Socket("127.0.0.1",30001); // connection1 socket is initialized here with a local ip and port of 30000
          PrintWriter toServer = new PrintWriter(connection1.getOutputStream(), true); // PrintWriter to send to Dictionary server
          toServer.println(englishWord); // passing the english word to the Dictionary server for translation
          BufferedReader fromServer = new BufferedReader(new InputStreamReader(connection1.getInputStream())); // PrintWriter to recieve from Dictionary server
          translatedWord = fromServer.readLine(); // reading what is passed back and saving it as a string
          System.out.println("English Word: " + englishWord); // printing english word to be translated
          System.out.println("Translation: " + translatedWord); // printing the translated word
          System.out.println();
        } 
        catch(Exception e) // Catching other Exceptions and printing the stack
        {
          e.printStackTrace();
        }
        pw.println(englishWord + "\t\t\t\t" + translatedWord); // printing output to the text document
      }
      /***********************************************************************************
      /* Document ELSE code: If user enters 1 or 2. Then a simple connection to the server is
      /* made using sockets. The 1 or 2 is passed to the thread to continue processing. Then a message
      /* will be passed back here for the client to print out
      /***********************************************************************************
      */
      else 
      {
        try // trying this code for any exceptions
        {
          connection1 = new Socket("127.0.0.1",30001); // connection1 socket is initialized here with a local ip and port of 30001
          PrintWriter toServer = new PrintWriter(connection1.getOutputStream(), true); // PrintWriter to send to Dictionary server
          toServer.println(word); // passing the english word to the Dictionary server for translation
          BufferedReader fromServer = new BufferedReader(new InputStreamReader(connection1.getInputStream())); // PrintWriter to recieve from Dictionary server
          translatedWord = fromServer.readLine(); // reading what is passed back and saving it as a string
          System.out.println(translatedWord); // printing the translated word
          System.out.println();
        } 
        catch(Exception e) // Catching other Exceptions and printing the stack
        {
          e.printStackTrace();
        }
        pw.println(translatedWord); // printing output to the text document
      }
    }
  }
}
    
      
