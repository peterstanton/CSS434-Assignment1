/**
 * Created by Peter on 3/27/2017.
 */
public class Main {

    public static void main(String args[]) {
        int listeningPort = 4004; //for debugging.
        //int listeningPort = Integer.parseInt(args[0]);  //this overflows in debugging.
        Server s = new Server(listeningPort);
        System.out.println("Hello World!");
    }
}


