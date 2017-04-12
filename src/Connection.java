/**
 * Created by Peter on 3/28/2017.
 */
import java.io.*;
import java.net.*;

public class Connection {
    private Socket client;
    private boolean errored;


    public DataInputStream inData;  //do I use the readUTF function on the reg streams to put strings into the datastreams?
    public DataOutputStream outData;
    public InputStream rawIn;
    private OutputStream rawOut;

    private String myText = new String();
    public String userID = new String();


    public Connection(Socket in) throws IOException {

        client = new Socket();
        client = in;


        errored = false;

        rawOut = client.getOutputStream();
        rawIn = client.getInputStream();

        outData = new DataOutputStream(rawOut);
        inData = new DataInputStream(rawIn);

        userID = client.getInetAddress().getCanonicalHostName();
    }

    public String getID() {
        return userID;
    }

    public void clearText() {
        myText = "";
    }

    public void maintainConnection() {  //this should be server stuff, that loops through the vector of connections checking available.
        //stuff to run the connection.
        int inputWaiting;
        for (;;) {
            try {
                inputWaiting = rawIn.available();
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
            myText = inData.readUTF();
            //System.out.println("Received: " + myText);
            //super.propagate(myText,userID);
        }
        catch (IOException ioe) {
            errored = true;
        }
    }
    //shouldn't be void, should return a boolean if writing was successful.
    public void writeMessage(String outgoingMessage) {
        try {
            outData.writeUTF(outgoingMessage);
            //System.out.println("Received: " + myText);
        }
        catch (IOException ioe) {
            errored = true;
        }
    }
}



//the read and write functions will involve passing data to and from the server from the client.


//Because connection will exist on an independent thread, I should be fine with my running function in
//Connection.