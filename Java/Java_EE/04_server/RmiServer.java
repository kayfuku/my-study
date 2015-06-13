//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : RmiServer.java
//  Date      : March 8, 2015
//  Objective : This is a server program and needs to run 
//              RmiClient.java after. The client uses RMI to 
//              call the methods in this server.
//  Java version : 1.8.0_20
//**************************************************************

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RmiServer extends UnicastRemoteObject
                       implements RmiServerIntf
{
    //private static final int PORT_NUM = 10005;
    private static HashMap<String, String> statesCapitalsMap = 
                                                   new HashMap<>();
    private static String[] temp;
    private static File f;
    private Matcher matcher;
    private Pattern pattern;
    
    // ------------ RmiServer() --------------------------------
    // Constructor.
    public RmiServer() throws RemoteException
    {
        super();
    }

    @Override
    // ------------ getState() ---------------------------------
    // Return a corresponding state when the server receiving
    // a capital from the client.  
    public String getState(String capital) throws RemoteException
    {
        try
        {
            pattern = Pattern.compile(capital, Pattern.CASE_INSENSITIVE);
        }
        catch (Exception e)
        {
            return "Nothing matches.";
        }

        for (Map.Entry<String, String> e : statesCapitalsMap.entrySet())
        {
            // Check if the capital's name matches the input.
            matcher = pattern.matcher(e.getValue());
            if (matcher.matches())
            {
                return e.getKey();
            }
        }

        return "Nothing matches.";
    } // end of getState()

    @Override
    // ------------ getCapital() -------------------------------
    // Return a corresponding capital when the server receiving
    // a state from the client.  
    public String getCapital(String state) throws RemoteException
    {
        try
        {
            pattern = Pattern.compile(state, Pattern.CASE_INSENSITIVE);
        }
        catch (Exception e)
        {
            return "Nothing matches.";
        }

        for (Map.Entry<String, String> e : statesCapitalsMap.entrySet())
        {
            // Check if the state's name matches the input.
            matcher = pattern.matcher(e.getKey());
            if (matcher.matches())
            {
                return e.getValue();
            }
        }

        return "Nothing matches.";
    } // end of getCapital()

    @Override
    // ------------ states() -----------------------------------
    // Return states that match the regex.
    public String[] states(String regex) throws RemoteException
    {
        String[] states = new String[50];

        try
        {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            states[0] = "Invalid regex.";
            return states;
        }
                        
        // Search.
        boolean isFound = false;         
        int i = 0;
        for (Map.Entry<String, String> e : statesCapitalsMap.entrySet()) 
        {
            // Check if the state's name matches the input.
            matcher = pattern.matcher(e.getKey());                  
            if (matcher.find())
            {
                states[i] = e.getKey();
                i++;
                isFound = true;
            }
        }
        
        // Return the result.
        if (isFound) 
        {
            return states;
        }
        else
        {
            states[0] = "Nothing matches.";
            return states;
        }        
    } // end of states()

    @Override
    // ------------ capitals() ---------------------------------
    // Return capitals that match the regex.
    public String[] capitals(String regex) throws RemoteException
    {
        String[] capitals = new String[50];

        try
        {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            capitals[0] = "Invalid regex.";
            return capitals;
        }
                        
        // Search.
        boolean isFound = false;         
        int i = 0;
        for (Map.Entry<String, String> e : statesCapitalsMap.entrySet()) 
        {
            // Check if the state's name matches the input.
            matcher = pattern.matcher(e.getValue());                  
            if (matcher.find())
            {
                capitals[i] = e.getValue();
                i++;
                isFound = true;
            }
        }
        
        // Return the result.
        if (isFound) 
        {
            return capitals;
        }
        else
        {
            capitals[0] = "Nothing matches.";
            return capitals;
        }        
    } // end of capitals()

    // ------------- readFile() --------------------------------
    // Read the file.
    private static void readFile()
    {
        // The file path is from the root directory of the project.
        String file = new String("data/US_states");

        f = new File(file);
        if (!f.exists())
        {
            System.err.println(file + " does not exist.");
            System.exit(1);           
        }        
        if (f.isDirectory())
        {
            System.err.println(file + " is a directory.");
            System.exit(1);                       
        }
        if (!f.canRead())
        {
            System.err.println("You cannot read " + file + " .");
            System.exit(1);                                   
        }
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
        
        if (System.getSecurityManager() == null)
        {
            System.setSecurityManager(null);
        }
        
        try
        {
            // Get the remote object. 
            RmiServer rServer = new RmiServer();
            // Register the remote object with the registry. 
            Naming.rebind("RmiServer", rServer);
            
            System.out.println("RmiServer ready.");
        }
        catch (RemoteException e)
        {
            System.err.println("RmiServer RemoteException.");
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            System.err.println("RmiServer MalformedURLException.");
            e.printStackTrace();
        }                
    } // end of main()
}

