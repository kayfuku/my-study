//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : ClientDemo.java
//  Date      : 2/5/15
//  Objective : This is a client program and needs to run 
//              ServerDemo.java first. The client sends a state
//              or capital and receive a corresponding capital 
//              or state from the server. 
//  Java version : 1.8.0_20
//**************************************************************

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo
{
    private static final String HOST_NAME = "localhost";
    private static final int PORT_NUM = 10005;

    // ------------- main() -------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        System.out.println("Connecting to " + HOST_NAME + ":" + PORT_NUM);
        // Create a socket with destination server.
        try (Socket socket = new Socket(HOST_NAME, PORT_NUM))
        {
            System.out.println("Connection established");

            Scanner userIn = new Scanner(System.in);

            // For sending data to a server.
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter netOut = new PrintWriter(
                             outputStream, true /* auto flush */);

            // For receiving data from a server.
            InputStream inputStream = socket.getInputStream();
            Scanner netIn = new Scanner(inputStream);

            String response, userMsg;
            while (true)
            {
                // Receive data from the server.
                response = netIn.nextLine();
                System.out.println("Server says: " + response);

                System.out.print("Client Input> ");
                userMsg = userIn.nextLine();
                // Send data to the server.
                netOut.println(userMsg);
            }
        }
        catch (Exception e)
        {
            // Do nothing. 
        }
        System.out.println("Connection terminated.");

        System.err.println("Client done.");
        return;
    }
}
