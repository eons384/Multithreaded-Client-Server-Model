/*****************************************************************************
/*                             Michael Talbott
/*                             Dictionary.java
/*
/* Description of Class: This class receives a string passed from a runtime().exec 
/* command in the service broker class. Dictionary takes the String and uses the 
/* switch statement to find a translation. If there is none it send a message saying so.
/* Then sends this information back to the service broker
/*
/*****************************************************************************
/* Input: Is an English word. It is taken in as a string from Service Broker class
/* Output: Output is sent to Service Broker to then hand back to the Server. It is a Spanish word.
/*****************************************************************************
*/

public class Dictionary{
  
  public static void main(String[] args){
      String englishWord = args[0]; // word to translate passed from command line arguments
      String translatedWord = null; // global variable for scope
      /************************************************************************************
      /* Document Switch Statement: The switch statement will compare different cases here
      /* if the string that is passed into it matches one of the cases the translatedWord variable
      /* will be assigned. 
      /*
      /************************************************************************************
      */
        switch (englishWord) 
        {
          case "Cat": translatedWord = "Gato";
          break;
          case "cat": translatedWord = "Gato";
          break;
          case "Dog": translatedWord = "Perro";
          break;
          case "dog": translatedWord = "Perro";
          break;
          case "Girl": translatedWord = "Nina";
          break;
          case "girl": translatedWord = "Nina";
          break;
          case "Boy": translatedWord = "Nino";
          break;
          case "boy": translatedWord = "Nino";
          break;
          case "Program": translatedWord = "Programa";
          break;
          case "program": translatedWord = "Programa";
          break;
          case "Programmer": translatedWord = "Programador";
          break;
          case "programmer": translatedWord = "Programador";
          break;
          case "Football": translatedWord = "Futbol Americano";
          break;
          case "football": translatedWord = "Futbol Americano";
          break;
          case "Soccer": translatedWord = "Futbol";
          break;
          case "soccer": translatedWord = "Futbol";
          break;
          case "Church": translatedWord = "Iglesia";
          break;
          case "church": translatedWord = "Iglesia";
          break;
          case "Basketball": translatedWord = "Baloncesto";
          break;
          case "basketball": translatedWord = "Baloncesto";
          break;
          case "Girlfriend": translatedWord = "Novia";
          break;
          case "girlfriend": translatedWord = "Novia";
          break;
          case "Bathroom": translatedWord = "Bano";
          break;
          case "bathroom": translatedWord = "Bano";
          break;
          case "Fireman": translatedWord = "Bombero";
          break;
          case "fireman": translatedWord = "Bombero";
          break;
          case "Computer": translatedWord = "Computadora";
          break;
          case "computer": translatedWord = "Computadora";
          break;
          case "Year": translatedWord = "Ano";
          break;
          case "year": translatedWord = "Ano";
          break;
          default: translatedWord = "Word Not Found In This Dictionary";
          break;
        }
        System.out.println(translatedWord); // passing the translated word back to ServiceBroker.java to handle
  }
}
