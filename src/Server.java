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
///test


    try {
        serverSideSocket = new ServerSocket(port);
        serverSideSocket.setSoTimeout(500);
        try {
            serverSideSocket.accept();
            if (serverSideSocket != null) {
                Connection detected = new Connection(serverSideSocket);
                conList.add(detected);
                //checking to see if we got a connection.
                //store a new Connection.
            }
            else {
                //I don't know.
            }
        }
        catch (SocketTimeoutException e) {
        //stuff
        }
    }
    catch(IOException ioe)
    {  //stuff here.
    }

}

}