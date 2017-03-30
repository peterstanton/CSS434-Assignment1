/**
 * Created by Peter on 3/28/2017.
 */
import java.io.*;
import java.net.*;

public class Connection {
    private String userName;

    private ServerSocket clientSocket;
    private Socket test;

    private boolean errored;


    private DataInputStream inData;  //do I use the readUTF function on the reg streams to put strings into the datastreams?
    private DataOutputStream outData;
    private InputStream inText;
    private OutputStream outText;

    private String text;


    public Connection(ServerSocket inSocket) throws IOException {

        clientSocket = inSocket;
        errored = false;
        outText = test.getOutputStream();
        outText = clientSocket.getOutputStream();
        maintainConnection(); //the stuff this currently does should be invoked by the server.
        //terminate.
    }

    public void maintainConnection() {
        //stuff to run the connection.
        int inputWaiting;
        for (;;) {
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
            if (errored == true) {
                break;
            }
        }
        //time to end connection.
    }
    //I should replace all this stuff. Instead of infinite loop,
    //the looping is handled by the server.
    //Will have to signal an error state for connection termination
    //by the server.

    public void readMessage() {
        try {
            text = inText.readUTF();
        }
        catch (IOException ioe) {
            errored = true;
        }
    }
    public void writeMessage(String outgoingMessage) {
        //stuff for sending a message.
    }
}



//the read and write functions will involve passing data to and from the server from the client.
