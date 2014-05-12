Multithreaded-Client-Server-Model
=================================

Client Server Model in CMSC 355 

Directions to run:
1. The Server, Service1, Service2, Dictionary, and ServiceBroker need to be in the same directory. It doesn't matter where the Client is but for convenience of this ReadMe we will assume Client is in the same directory.
2. Open command line and change directories until you reach the folder with the source code files. Using 
   cd "Whatever the directory the source code files are in" on the command line.
3. type the command javac *.java
4. type the command: jar cvfe Client.jar Client Client.class
5. type the command: jar cvfe Server.jar Server Server.class ClientThread.class
6. type the command: jar cvfe Service1.jar Service1 Service1.class
7. type the command: jar cvfe Service2.jar Service2 Service2.class
8. type the command: jar cvfe Dictionary.jar Dictionary Dictionary.class
9. type the command: jar cvfe ServiceBroker.jar ServiceBroker ServiceBroker.class
10. Now you are ready to run the jar files. THIS IS VERY IMPORTANT. To run the server to make this program work 
   you need to either double click Server.jar or in the command prompt navigate to the directory of the Server.jar
   use the command cd "Whichever directory the Server.jar is in". Once in the directory on the command prompt
   type java -jar Server.jar. If you would rather not do this just simply double click the Server.jar icon.
   The Server.jar file needs to be running before you start the Client.jar. END OF IMPORTANT STATEMENT.
   This Server, Service1, Service2, Dictionary, and ServiceBroker jar file only simply needs to be on the same computer as the the Client jar not necessarily the same 
   directory. The Server, Service1, Service2, Dictionary, and ServiceBroker jar file absolutely have to be in the same directory.
11. If you decide to use the command prompt to run the Server.jar you will need to open up a seperate command prompt and 
   navigate to the directory the Client.jar file is in using cd "Whatever the directory of Client.jar". If you choose
   to double click the Server.jar file to execute then simply navigate to the directory using cd "Whatever the directory of Client.jar".
   Then in the command prompt type java -jar Client.jar. REMEMBER THAT SERVER.JAR NEEDS TO BE EXECUTED BEFORE THIS STEP. 
12. You will be prompted to choose a service 1, 2, 3, or q. Choose a service service 1 and 2 only simply return a message they print. Service 3 is our regular translation program.



Instructions to run the program if you have the zip file I submitted.

First find the zip file from the location you downloaded it too. Unzip the file by right clicking on it and selecting extract all... option.
Extract to your desired directory. It doesn't matter where the Client.jar file is stored as long as it is on the same machine as the 
Server.jar. The Server, Service1, Service2, Dictionary, and ServiceBroker need to be in the same directory.

Basically you need to look above and start at instruction 10 at this point.
