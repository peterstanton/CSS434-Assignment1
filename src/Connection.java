/**
 * Created by Peter on 3/28/2017.
 */
import java.io.*;
import java.net.*;

public class Connection {
    private String userName;
    private ServerSocket clientSocket;


    public Connection(ServerSocket inSocket) {
        clientSocket = inSocket;
    }
}
