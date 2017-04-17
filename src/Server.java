/**
 * Created by Peter on 3/27/2017.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {


    private ServerSocket serverSideSocket;
    private LinkedList<Connection> conList;


public Server(int port) {

	   conList = new LinkedList<Connection>();
           try {
               serverSideSocket = new ServerSocket(port); //this all needs to be in the loop.
           }
            catch(IOException ioe) {  //stuff here.
            }
            while (true) {
                try {
                    serverSideSocket.setSoTimeout(500);
                } catch (SocketException se) {
		System.out.println("Nothing found");
                }

                try {
                    while (true) {
			Socket clientSocket;
			clientSocket = serverSideSocket.accept();
                        if (clientSocket != null) {
				System.out.println("Socket found.");
                                                   
                            Connection detected = new Connection(clientSocket);
                            conList.add(detected);
				System.out.println("Connection Added.");

                            for (int k = 0; k < conList.size(); k++) {
                                if (conList.get(k).getStatus() == true) {
                                    conList.remove(k);
                                }
                            }
                            //checking to see if we got a connection.
                        } else {
                            System.out.println("Nothing");
                            //I don't know.
                        }
			        for (int i = 0; i < conList.size(); i++) {
				System.out.println("Checking my connections");
                                //if a client has a message.
                                if (conList.get(i).getAvailable()) {
					System.out.println("I have a message!");
                                    //get the message and send it on.
                                    String myText = conList.get(i).getMessage();    //I should really obey encapsulation and interact using methods.
                                    String senderID = conList.get(i).getID();
                                    //send the data to the other connections.
                                    for (int j = 0; j < conList.size(); j++) {
                                        if (!senderID.equals(conList.get(j).getID())) {  //replace with equals()?  if (conList.get(j).userID != senderID)
                                            //write the outgoing message to other users.
                                            conList.get(j).sendMessage(myText);
                                        }
                                    }
                                }
                            }
                    }
                } catch (IOException ioe) {  //stuff here.
                }

            }
    }
    public static void main( String[] args ) {
        int listeningPort=Integer.parseInt(args[0]); //for debugging.
        // int listeningPort = 1245;  //this overflows in debugging.
        Server s = new Server(listeningPort);
        System.out.println("Hello World!");
    }

}
