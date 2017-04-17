/**
 * Created by Peter on 3/28/2017.
Class Connection
This class is the container used with the Server class
which contains multiple of these Connections in a list
or array structure. It contains the host name, input and 
output streams, connection status, and the socket.

The connection is the object which handles checking for
available messages, message writing, and message reading.



*/


import java.io.*;
import java.net.*;

public class Connection {
    private Socket client;
    private boolean errored;


    private DataInputStream inData; 
    private DataOutputStream outData;
    private InputStream rawIn;
    private OutputStream rawOut;

    private String myText = new String();
    public String userID = new String();


    //Constructor.
    public Connection(Socket in) throws IOException {

        client = new Socket();
        client = in; //receives socket.

        errored = false;
	//instantiates data streams.
        rawOut = client.getOutputStream();
        rawIn = client.getInputStream();

        outData = new DataOutputStream(rawOut);
        inData = new DataInputStream(rawIn);
	//gets identity of remote client.
        userID = client.getInetAddress().getCanonicalHostName();
    }

    //returns the user ID to server.
    public String getID() {
        return userID;
    }

    //getAvailable is used to alert the server if a message is waiting.
    public boolean getAvailable() {
        int waiting = 0; //initially, no messages are waiting.
        try {
            waiting = rawIn.available(); //checks for messages.
        } catch (IOException ioe) {
            //exception handling.
            errored = true;
        }
        if (waiting > 0) { //if a message is waiting.
            return true;
        } else {
            return false;
        }
    }
    //exists to clear out the connection's message field.
    private void clearText() {
        myText = "";
    }

    //if getAvailable showed a message waiting, we retrieve it.
    public String getMessage() {
        try {
            myText = inData.readUTF();
        }
        catch (IOException ioe) {
            errored = true;
        }
        return myText;
    }
    
    //sendMessage is used to write 
    public void sendMessage(String outgoingMessage) {
        try {
            outData.writeUTF(outgoingMessage);
        }
        catch (IOException ioe) {
            errored = true;
        }
    }
 
    //returns the connection's status to the server.
    public boolean getStatus() {
        return errored;
    }
}
