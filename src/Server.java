/**
 * Created by Peter on 3/27/2017.
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {

    private ServerSocket serverSideSocket;
    private DataInputStream userInput;
    private List<Connection> conList;


public Server(int port) {

    try {
        serverSideSocket = new ServerSocket(port);
        serverSideSocket.setSoTimeout(500);
        for (; ; ) {  //keeps repeating until we get some shutdown signal.
            try {
                serverSideSocket.accept();
                if (serverSideSocket != null) {
                    Connection detected = new Connection(serverSideSocket);
                    conList.add(detected);
                    //checking to see if we got a connection.
                }
                else {
                    continue;
                    //I don't know.
                }
            } catch (SocketTimeoutException e) {
                continue; //keep trying for a connection.
                //stuff
            }
        }
    }
    catch(IOException ioe)
    {  //stuff here.
    }

}

}