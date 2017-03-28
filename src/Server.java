/**
 * Created by Peter on 3/27/2017.
 */

import java.io.*;
import java.net.*;


public class Server {

    private Socket socket;
    private ServerSocket server;
    private DataInputStream userInput;



public Server(int port) {


    try {
        server = new ServerSocket(port);
        server.setSoTimeout(500);
        try {
            server.accept();
            if (server != null) {
                //checking to see if we got a connection.
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