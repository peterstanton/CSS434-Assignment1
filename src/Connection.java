/**
 * Created by Peter on 3/28/2017.
 */
import java.io.*;
import java.net.*;

public class Connection {
    private String userName;
    private Socket client;
    private boolean errored;


    private DataInputStream inData;  //do I use the readUTF function on the reg streams to put strings into the datastreams?
    private DataOutputStream outData;
    private InputStream inText;
    private OutputStream outText;

    byte[] buf = new byte[1000];


    public Connection(ServerSocket inSocket) throws IOException {

        client = new Socket();
        client = inSocket.accept();
        errored = false;
        outText = client.getOutputStream();
        maintainConnection(); //the stuff this currently does should be invoked by the server. I should be returning.
        //terminate.
    }

    public void maintainConnection() {  //this should be server stuff, that loops through the vector of connections checking available.
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

    //read message shouldn't be void, it should return an output string to the server.
    public void readMessage() {
        try {
            inText.read(buf);
            String received = new String(buf); //change return type and hand this to the server.

        }
        catch (IOException ioe) {
            errored = true;
        }
    }
    //shouldn't be void, should return a boolean if writing was successful.
    public void writeMessage(String outgoingMessage) {
        //stuff for sending a message.
    }
}



//the read and write functions will involve passing data to and from the server from the client.
