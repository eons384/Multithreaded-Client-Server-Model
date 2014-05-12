/*****************************************************************************
/*                             Michael Talbott
/*                              Service1.java
/*
/* Description of class: Dummy class to just return a message to ServiceBroker
/*****************************************************************************
/* Input: Is a string passed from the Service Broker from command line
/* Output: A string sent back to ServiceBroker
/*****************************************************************************
*/
public class Service1{
  
  public static void main(String[] args){
    System.out.println("Service 1 called..."); // sending the message back to Service Broker
  }
}
