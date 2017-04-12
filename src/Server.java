/**
 * Created by Peter on 3/27/2017.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {

    private ServerSocket serverSideSocket;
    private List<Connection> conList;

public Server(int port) {

        for (; ; ) {  //keeps repeating until we get some shutdown signal.
           try {
               serverSideSocket = new ServerSocket(port); //this all needs to be in the loop.
           }
            catch(IOException ioe) {  //stuff here.
            }
            try {
                serverSideSocket.setSoTimeout(500);
            }
            catch (SocketException se) {
            }

            try {
                while (true) {
                    serverSideSocket.accept();
                    if (serverSideSocket != null) {
                        Socket clientSocket;
                        clientSocket = serverSideSocket.accept();
                        Connection detected = new Connection(clientSocket);
                        conList.add(detected);
                        for (int i = 0; i < conList.size(); i++) {
                            int inputWaiting;
                            inputWaiting = conList.get(i).rawIn.available();
                            //if a client has a message.
                            if (inputWaiting > 0) {
                                //get the message and send it on.
                                String myText = conList.get(i).inData.readUTF();
                                String senderID = conList.get(i).getID();
                                //send the data to the other connections.
                                for (int j = 0; j < conList.size(); j++) {
                                    if (conList.get(j).userID != senderID) {
                                        //write the outgoing message to other users.
                                        conList.get(j).outData.writeUTF(myText);
                                    }
                                }
                            }
                        }
                        //checking to see if we got a connection.
                    } else {
                        continue;
                        //I don't know.
                    }
                }
            }
            catch(IOException ioe)
            {  //stuff here.
            }

        }
    }
    public void propagate(String message, String ID){

    }

}
