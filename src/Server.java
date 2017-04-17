/**
 * Created by Peter on 3/27/2017.
// Class Server
// This class provides a server for basic TCP
// text chat using strings between 2 or more clients
// Text input output is handled by a provided ChatClient.
// This class contains a server socket to listen to connections
// and a list to contain active connections.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {


    private ServerSocket serverSideSocket;
    private LinkedList<Connection> conList;


public Server(int port) throws IOException {
	conList = new LinkedList<Connection>();
        serverSideSocket = new ServerSocket(port);
        serverSideSocket.setSoTimeout(500);
        while (true) {
                try {
                    while (true) {
                        Socket clientSocket;
                        clientSocket = serverSideSocket.accept(); //set client socket.
                        if (clientSocket != null) {
                            Connection detected = new Connection(clientSocket); //instantiate connection
                            conList.add(detected); //add client to list.
                            //checking to see if we got a connection.
                            runConnections(); //process the connections.
                        } else {
                            runConnections(); //we have no new connections, but run the ones we have
                        }
                    }
                } catch (IOException ioe) {
                    //run connections
                    runConnections();
                }

            }
    }
//Run connections moves through our current list of connections and checks them for messages
//and if any are detected, propagates them to the other clients. It also checks for errored
//clients and removes them from the connection list.
    public void runConnections() {
        for (int i = 0; i < conList.size(); i++) {
            //if a client has a message.
            if (conList.get(i).getAvailable()) {
                //get the message and send it on.
                String myText = conList.get(i).getMessage();
                String senderID = conList.get(i).getID();
                //send the data to the other connections.
                for (int j = 0; j < conList.size(); j++) {
                    if (!senderID.equals(conList.get(j).getID())) { //do not send message to myself.
                        //write the outgoing message to other users.
                        conList.get(j).sendMessage(myText);
                    }
                }
            }
        } //checks for lapsed user connections.
        for (int k = 0; k < conList.size(); k++) {
            if (conList.get(k).getStatus() == true) {
                conList.remove(k); //remove them.
            }
        }

    }
    public static void main( String[] args ) throws IOException {
        int listeningPort=Integer.parseInt(args[0]);
        Server s = new Server(listeningPort);
    }

}
