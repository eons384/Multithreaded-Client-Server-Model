/*****************************************************************************
/*                             Michael Talbott
/*                            ServiceBroker.java
/*
/* Description of class: Serves as an intermediary between the ClientThread and
/* the actual Service that the client wants to call
/*****************************************************************************
/* Input: Is a string passed from the ClientThread run() method
/* Output: A string sent to the Service that is called by the client
/*****************************************************************************
*/
import java.io.*;
import java.util.*;

public class ServiceBroker {
  
  public static void main(String[] args)
  {
  Process p = null; // initialized here for scope
  String message = args[0]; // message from thread is received from command line
  String word = null; // initialized here for scope
  String backToServer = null; // initialized here for scope
  BufferedReader fromService = null; // initialized here for scope
  /***********************************************************************************
  /* Document IF code: If message equals 1 we use getRunTime().exec to call service 
  /* 1. Then wait for that code to run and recieve the output it produces then we send
  /* that output back to the thread
  /***********************************************************************************
  */
  if(message.equals("1"))
  {
    try
    {
      p = Runtime.getRuntime().exec("java -jar Service1.jar"); // sending flow of control to Service 1
      p.waitFor(); // waiting for Service1 to run
      fromService = new BufferedReader(new InputStreamReader(p.getInputStream())); // recieving output from Service 1
      backToServer = fromService.readLine(); // saving output as string
      System.out.println(backToServer); // sending the string back to the thread
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /***********************************************************************************
  /* Document ELSE IF code: If message equals 2 we use getRunTime().exec to call service 
  /* 2. Then wait for that code to run and recieve the output it produces then we send
  /* that output back to the thread
  /***********************************************************************************
  */
  else if(message.equals("2"))
  {
    try
    {
      p = Runtime.getRuntime().exec("java -jar Service2.jar");// sending flow of control to Service 2
      p.waitFor(); // waiting for Service 2 to run
      fromService = new BufferedReader(new InputStreamReader(p.getInputStream())); // receiving output from Service 2
      backToServer = fromService.readLine(); // saving output as string
      System.out.println(backToServer); // sending the string back to the thread
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  /***********************************************************************************
  /* Document ELSE code: If only reaches here if client is trying to tranlate a word.
  /* we use getRunTime().exec to call Dictionary.jar. Then wait for that code to run and 
  /* we send that output back to the thread 
  /***********************************************************************************
  */
  else
  {
    try
    {
      p = Runtime.getRuntime().exec("java -jar Dictionary.jar " + message); // sending flow of control to Dictionary
      p.waitFor(); // waiting for Dictionary to run
      fromService = new BufferedReader(new InputStreamReader(p.getInputStream())); // receiving output from Dictionary
      backToServer = fromService.readLine(); // saving output as string
      System.out.println(backToServer); // sending the string back to the thread
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  }
}
