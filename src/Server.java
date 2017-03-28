/**
 * Created by Peter on 3/27/2017.
 */

import java.io.*;
import java.net.*;
import


public class Server {

    //private Socket socket;
    private ServerSocket socket;
    private DataInputStream userInput;



public Server(int port) {
///test
    try {
        socket = new ServerSocket(port);
        socket.setSoTimeout(500);
        try {
            socket.accept();
            if (socket != null) {
                //checking to see if we got a connection.
                //store a new Connection.
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