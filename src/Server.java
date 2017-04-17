/**
 * Created by Peter on 3/27/2017.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {


    private ServerSocket serverSideSocket;
    private LinkedList<Connection> conList;


public Server(int port) throws IOException {
	conList = new LinkedList<Connection>();
	System.out.println("I set the Socket");
        serverSideSocket = new ServerSocket(port);
	System.out.println("Hi");
        serverSideSocket.setSoTimeout(500);
        while (true) {


                try {
                    while (true) {
                        Socket clientSocket;
                        clientSocket = serverSideSocket.accept();
                        if (clientSocket != null) {
                            Connection detected = new Connection(clientSocket);
                            conList.add(detected);
                            for (int i = 0; i < conList.size(); i++) {
                                //if a client has a message.
                                if (conList.get(i).getAvailable()) {
                                    //get the message and send it on.
                                    String myText = conList.get(i).getMessage();    
                                    String senderID = conList.get(i).getID();
                                    //send the data to the other connections.
                                    for (int j = 0; j < conList.size(); j++) {
                                        if (!senderID.equals(conList.get(j).getID())) { 
                                            //write the outgoing message to other users.
                                            conList.get(j).sendMessage(myText);
                                        }
                                    }
                                }
                            }
                            for (int k = 0; k < conList.size(); k++) {
                                if (conList.get(k).getStatus() == true) {
                                    conList.remove(k);
                                }
                            }
                            //checking to see if we got a connection.
                        } else {

                        }
                    }
                } catch (IOException ioe) {  //stuff here.
                }

            }
    }
    public static void main( String[] args ) throws IOException {
        int listeningPort=Integer.parseInt(args[0]);
        Server s = new Server(listeningPort);
        System.out.println("Hello World!");
    }

}
