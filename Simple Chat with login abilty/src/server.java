import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {

    private static ArrayList<clientThread> clients;

    //thread to manage connecting clients
    public static class clientThread extends server implements Runnable {
        private Socket socket;


        public clientThread(Socket socket) {
            this.socket = socket;


        }

        //function to broad cast to all clients
        private void Broadcastmessage(Socket socket, String received) throws IOException {
            for (int i = 0; i < clients.size(); i++) {

                DataOutputStream dout1 = new DataOutputStream(clients.get(i).socket.getOutputStream());

                if (!(socket.equals(clients.get(i).socket))) {
                    dout1.writeUTF(received);
                }

            }
        }

        @Override
        public void run() {
            String received;
            while (true)
                try {
                    DataInputStream din = new DataInputStream((socket.getInputStream()));


                    received = din.readUTF();

                    System.out.println(received);


                    //try to broadcast message
                    Broadcastmessage(socket, received);


                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    //helper functions to verfiy log in or ask again for passcode
    public static void login(DataOutputStream dout1, Socket socket) throws IOException {
        dout1.writeUTF("You are connected");
        clientThread client = new clientThread(socket);
        clients.add(client);
        Thread thread = new Thread(client);
        thread.start();
    }

    public static void tryloginagain(DataOutputStream dout1) throws IOException {
        dout1.writeUTF("Incorrect access code");
        dout1.writeUTF("Enter access code:");
    }


    public static void main(String args[]) throws IOException {

        int portnumber = 4444;


        //the array to store clients
        clients = new ArrayList<clientThread>();


        ServerSocket serverSocket = new ServerSocket(portnumber);

        Socket socket;

        while (true) {
            socket = serverSocket.accept();


            System.out.println("accepts : " + socket.getRemoteSocketAddress());

            //trying to verify code
            DataInputStream din = new DataInputStream((socket.getInputStream()));

            String code = din.readUTF();
            System.out.println(code);
            code = code.substring(code.lastIndexOf(":") + 1);

            DataOutputStream dout1 = new DataOutputStream(socket.getOutputStream());

            //first time to try to log in we handle log in
            if (!code.equals("cs319spring2020")) {
                tryloginagain(dout1);

            } else {
                login(dout1, socket);
            }

            //while user is still not typing correct access code
            while (!code.equals("cs319spring2020")) {
                code = din.readUTF();
                code = code.substring(code.lastIndexOf(":") + 1);
                if (code.equals("cs319spring2020")) {
                    login(dout1, socket);
                } else {

                    tryloginagain(dout1);
                }
            }


        }
    }


}
