/**
 * Created by Peter on 3/28/2017.
 */
import java.io.*;
import java.net.*;

public class Connection {
    private Socket client;
    private boolean errored;


    private DataInputStream inData;  //do I use the readUTF function on the reg streams to put strings into the datastreams?
    private DataOutputStream outData;
    private InputStream rawIn;
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

    public boolean getAvailable() {
        int waiting = 0;
        try {
            waiting = rawIn.available();
        } catch (IOException ioe) {
            //exception handling.
            errored = true;
        }
        if (waiting > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void clearText() {
        myText = "";
    }


    public String getMessage() {
        try {
	    System.out.println("I am getting a message");
            myText = inData.readUTF();
            //System.out.println("Received: " + myText);
            //super.propagate(myText,userID);
        }
        catch (IOException ioe) {
            errored = true;
        }
	
        return myText;
    }
    //shouldn't be void, should return a boolean if writing was successful.
    public void sendMessage(String outgoingMessage) {
        try {
            outData.writeUTF(outgoingMessage);
            //System.out.println("Received: " + myText);
        }
        catch (IOException ioe) {
            errored = true;
        }
    }

    public boolean getStatus() {
        return errored;
    }
}

//If should do something with errored. If a connection becomes errored, I should delete it from conList.
