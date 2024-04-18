package FTP;

import java.io.*;
import java.net.*;

public class FTPS {
    static ServerSocket serverSocket;
    static Socket socket;
    static PrintWriter pw;
    static BufferedReader bf;

    public static void main(String[] args) throws Exception {
        String ch;
        serverSocket = new ServerSocket(4000);
        System.out.println("Server is ready");
        socket = serverSocket.accept();
        System.out.println("Server is connected to a client");
        pw = new PrintWriter(socket.getOutputStream(), true);
        bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String fileName;
        while (true) {
            ch = bf.readLine();
            if (ch.equals("send")) {
                System.out.println("Send command received");
                fileName = bf.readLine();
                send(fileName);
            } else if (ch.equals("receive")) {
                System.out.println("Receive command received");
                fileName = bf.readLine();
                receive(fileName);
            }
        }
    }

    private static void send(String filename) throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        String str;
        while ((str = fileReader.readLine()) != null)
            pw.println(str);
        pw.println("\u0004");
        fileReader.close();
        System.out.println("\nFile sent to client");
    }

    private static void receive(String filename) throws Exception {
        String str;
        System.out.println("\nFile contents: ");
        while (!(str = bf.readLine()).equals("\u0004"))
            System.out.println(str);
        System.out.println("\nFile received");
    }
}
