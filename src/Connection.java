/**
 * Created by Peter on 3/28/2017.
 */
import java.io.*;
import java.net.*;

public class Connection {
    private String userName;
    private ServerSocket clientSocket;
    private boolean errored;
    private InputStream inText;
    private OutputStream outText;


    public Connection(ServerSocket inSocket) {

        clientSocket = inSocket;
        errored = false;
        //do stuff to maintain the connection?
        maintainConnection();
        //terminate.
    }

    public void maintainConnection() {
        //stuff to run the connection.
        for (;;) {
            int inputWaiting;
            try {
                inputWaiting = inText.available();
            }
            catch (IOException ioe) {
                //exception handling.
                errored = true;
                break;
            }
            if (inputWaiting > 0) {
                readMessage();
                //invoke read message.
            }
            else {
                continue; //nothing.
            }
            //should I check for write?

            //stuff running the connection here.
            //when available, call read message.
            //when I get input, send to write.
        }
    }
    public void readMessage() {
        //stuff for receiving messages.
    }
    public void writeMessage() {
        //stuff for sending a message.
    }
}

//the read and write functions will involve passing data to and from the server from the client.
