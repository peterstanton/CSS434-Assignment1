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
                serverSideSocket.accept();
                if (serverSideSocket != null) {
                    Connection detected = new Connection(serverSideSocket);
                    conList.add(detected);
                    //checking to see if we got a connection.
                } else {
                    continue;
                    //I don't know.
                }
            }
            catch(IOException ioe)
            {  //stuff here.
            }
            //once I've checked for new connections, I should cycle through my
            //connection list and check for new data to receive and send to the other
            //connections.
        }
    }
}
