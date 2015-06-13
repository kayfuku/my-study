//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : ServerDemo.java
//  Date      : 2/5/15
//  Objective : This is a server program and needs to run 
//              ClientDemo.java after. The server returns a 
//              capital or state that corresponds to the string
//              sent by the client. 
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.die;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerDemo
{
    private static final int PORT_NUM = 10005;
    private static HashMap<String, String> statesCapitalsMap = 
                                                   new HashMap<>();
    private static String[] temp;
    private static File f;

    // ------------- readFile() --------------------------------
    // Read the file.
    private static void readFile()
    {
        // The file path is from the root directory of the project.
        String file = new String("data/US_states");

        f = new File(file);
        if (!f.exists())
            die(file + " does not exist.");
        if (f.isDirectory())
            die(file + " is a directory.");
        if (!f.canRead())
            die("You cannot read " + file + " .");
    } // end of readFile().

    // -------------parseUsStates()-----------------------------
    // Parse the data in the file 'f'.
    private static void parseUsStates()
    {
        try
        {
            String line;
            int ig = 0;

            Scanner sc = new Scanner(f);
            while (sc.hasNext())
            {
                line = sc.nextLine();
                // Ignore the first two lines.
                if (ig > 1)
                {
                    temp = line.split("\\s\\s+");
                    statesCapitalsMap.put(temp[0], temp[1]);
                }
                ig++;
            }

            sc.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    } // end of parseUSState().

    // ------------- main() ------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        // Get the states and capitals data from a file and
        // put them into the Map.
        readFile();
        parseUsStates();

        // Create a server socket.
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUM))
        {
            System.out.println("Server started.");

            int i = 1;
            while (true)
            {
                // Wait for connection with a client.
                // Get a socket from the client.
                System.out.println("Waiting for msg from client...");
                Socket clienSocket = serverSocket.accept();
                System.out.println("Connection established");

                // Create another thread.
                System.out.println("Create thread " + i);
                Runnable runnable = 
                        new ReturnCapitalOrState(clienSocket,
                                                 statesCapitalsMap);
                Thread thread = new Thread(runnable);
                thread.start();
                i++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.err.println("Server done.");
        return;
    } // end of main().
} // end of ServerDemo class.

// ***************** ReturnCapitalOrState class ****************
// Receive a state or capital from a client and return a corresponding
// capital or state to the client.
class ReturnCapitalOrState implements Runnable
{
    private Socket clientSocket;
    private HashMap<String, String> statesCapitalsMap = new HashMap<>();

    // ------ ReturnCapitalOrState() ---------------------------
    // Constructor.
    public ReturnCapitalOrState(Socket s, HashMap<String, String> m)
    {
        clientSocket = s;
        statesCapitalsMap = m;
    }

    @Override
    // ------ run() ---------------------------------------------
    // Another thread calls this method.
    public void run()
    {
        System.out.println("run() start");
        // Get input stream from the client.
        try (InputStream inputStream = clientSocket.getInputStream())
        {
            // Get output stream from the client.
            try (OutputStream outputStream = clientSocket.getOutputStream())
            {
                try (Scanner netIn = new Scanner(inputStream))
                {
                    PrintWriter netOut = new PrintWriter(
                            outputStream, true /* auto flash */);
                    netOut.println("Enter BYE to exit.");

                    while (true)
                    {
                        String msgReceived = netIn.nextLine();
                        System.out.println("Client says: " + msgReceived);

                        if (msgReceived.trim().equalsIgnoreCase("BYE"))
                            break;

                        // Send back a capital or state to the client.
                        String msgResponse = findCapitalOrState(msgReceived);
                        netOut.println(msgResponse);
                    }
                } // No need of 'catch' for Scanner.
                System.out.println("normal termination");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Connection terminated.");
    } // end of run().

    // ------------- findCapitalOrState() -----------------------
    // Return capital or state that corresponding to the input.
    private String findCapitalOrState(String userInput)
    {
        Matcher matcher;
        Pattern pattern = null;

        try
        {
            pattern = Pattern.compile(userInput, Pattern.CASE_INSENSITIVE);
        }
        catch (Exception e)
        {
            return "Type in a state or capital.";
        }

        for (Map.Entry<String, String> e : statesCapitalsMap.entrySet())
        {
            // Check if the state's name matches the input.
            matcher = pattern.matcher(e.getKey());
            if (matcher.matches())
            {
                return e.getValue();
            }

            // Check if the capital's name matches the input.
            matcher = pattern.matcher(e.getValue());
            if (matcher.matches())
            {
                return e.getKey();
            }
        }

        return "Type in a state or capital.";
    }
}
