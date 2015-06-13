//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : RmiClient.java
//  Date      : March 8, 2015
//  Objective : This is a client program and needs to run 
//              RmiServer.java in advance. The client uses RMI to 
//              call the methods in the server.
//  Java version : 1.8.0_20
//**************************************************************

import java.rmi.Naming;
import java.util.Scanner;

public class RmiClient
{
    // ------------- main() ------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        try (Scanner keyboard = new Scanner(System.in))
        {
            // args[0] = "localhost"                        
            String url = "rmi://" + args[0] + "/RmiServer";
            // Search the remote object in the registry.
            RmiServerIntf rServer = (RmiServerIntf) Naming.lookup(url);
            
            // Test for getCapital() in the server, which uses RMI.
            System.out.print("Client says: Type in a state> ");
            String state = keyboard.nextLine();
            String result = rServer.getCapital(state);
            System.out.println("Server says: ");
            System.out.println(result);
           
            // Test for getState() in the server, which uses RMI.
            System.out.print("Client says: Type in a capital> ");
            String capital = keyboard.nextLine();
            result =rServer.getState(capital);
            System.out.println("Server says: ");
            System.out.println(result);
          
            // Test for states() in the server, which uses RMI.
            System.out.print("Client says: Type in a regex for states> ");
            String regex = keyboard.nextLine();
            String[] results = rServer.states(regex);
            System.out.println("Server says: ");
            for (String s : results)
            {
                if (s != null) System.out.println(s);
            }
           
            // Test for capitals() in the server, which uses RMI.
            System.out.print("Client says: Type in a regex for capitals> ");
            regex = keyboard.nextLine();
            results = rServer.capitals(regex);
            System.out.println("Server says: ");
            for (String s : results)
            {
                if (s != null) System.out.println(s);
            }
        }
        catch (Exception e)
        {
            System.err.println("RmiClient Exception.");
            e.printStackTrace();
        }

        System.err.println("Client says: Client done.");
        return;
    } // end of main()
}
