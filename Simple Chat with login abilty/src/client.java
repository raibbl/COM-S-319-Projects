import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class client {


    //function to initialize client and ask for name and access code
    private static String start() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name;
        name = s.next();
        System.out.println("Enter access code:");
        return name;
    }


    public static void main(String args[]) throws IOException {
        String name = null;


        int portnumber = 4444;

        //start scanner
        Scanner scan = new Scanner(System.in);

        //get name from initialization
        name = start();

        Socket socket = new Socket("localhost", portnumber);


        DataInputStream din = new DataInputStream((socket.getInputStream()));
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());


        //thread to handle sending message to server
        String finalName = name;

        Thread sendMsg = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // read the message to deliver.


                    String msg = scan.nextLine();

                    try {
                        // write on the output stream
                        dout.writeUTF(finalName + ":" + msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // thread to handle reading message from server
        Thread readServerMsg = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client
                        String msg = din.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });


        //start these threads
        sendMsg.start();
        readServerMsg.start();


    }
}
