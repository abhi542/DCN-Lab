package FTP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FTPC {
    static Scanner sc;
    static Socket socket;
    static PrintWriter pw;
    static BufferedReader bf;

    public static void main(String[] args) throws Exception {
        int ch;
        sc = new Scanner(System.in);
        socket = new Socket("127.0.0.1", 4000);
        pw = new PrintWriter(socket.getOutputStream(), true);
        bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String fileName = "";
        do {
            System.out.print("\n0-Exit 1-Send 2-Receive\nEnter yout choice: ");
            ch = sc.nextInt();
            sc.nextLine();
            if (ch != 0) {
                System.out.print("Enter file name: ");
                fileName = sc.nextLine();
            }
            if (ch == 1) {
                pw.println("receive"); // send 'receive' command to server
                pw.println(fileName); // send filename
                send(fileName);
            } else if (ch == 2) {
                pw.println("send"); // send 'send' command to server
                pw.println(fileName); // send filename
                receive(fileName);
            }
        } while (ch != 0);
    }

    private static void send(String filename) throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        String str;
        while ((str = fileReader.readLine()) != null)
            pw.println(str);
        pw.println("\u0004");
        fileReader.close();
        System.out.println("File sent to server");
    }

    private static void receive(String filename) throws Exception {
        String str;
        System.out.println("\nFile contents: ");
        while (!(str = bf.readLine()).equals("\u0004"))
            System.out.println(str);
        System.out.println("\nFile received");
    }
}