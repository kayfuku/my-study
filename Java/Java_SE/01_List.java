//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : List.java
//  Date      : 9/6/14
//  Objective : This program demonstrates how to write a program
//              that accepts option(s).
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.*;
import java.util.*;
import java.io.*;

public class List 
{
    private static int linesSize;
    private static ArrayList<String> lines = 
                                new ArrayList<String>();
    private static ArrayList<String> linesTemp = 
                                new ArrayList<String>();

    public static void main(String[] args) 
    {
        int c;
        double bal;
        String name;
        boolean nShouldBeLast = false;

        // Use GetOpt class.
        GetOpt go = new GetOpt(args, "ntrh");

        // Assign the name of the file to variable 'filename'. 
        // data_file: data/my.txt 
        String filename = args[args.length - 1];  
        // When user did not input filename, show help.
        if (filename.charAt(0) == '-')
        {
            showHelp();
            System.exit(1);
        } 
        File file = new File(filename);
        if (!file.exists()) die(filename + " does not exist.");
        if (file.isDirectory()) die(filename + " is a directory.");
        if (!file.canRead()) die("You cannot read " + filename + 
                                 " .");
        
        // Put the content of the file into ArrayList 'lines'. 
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                lines.add(scanner.nextLine());
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        // Execute the statements depending on the options 
        // that user input.   
        linesSize = lines.size();
        while ((c = go.getopt()) != -1) 
        {
            switch (c) 
            {
                case 'n':
                    // Just for testing.
                    //System.out.println("n");

                    // If there is -t and/or -r option(s), 
                    // the -n option should be executed last.
                    /* for (int i = 0; i < args.length; i++)
                    {
                        if (args[i].equals("-t") || 
                            args[i].equals("-r") )
                        {
                            nShouldBeLast = true;
                        }
                    } */

                    nShouldBeLast = true;

                    break;

                case 't':
                    // Just for testing.
                    //System.out.println("t");

                    // Put the elements of the Arraylist 'lines'
                    // in the reverse order. 
                    reverseLines();

                    break;

                case 'r':
                    // Just for testing.
                    //System.out.println("r");

                    // Put the characters of each element of 
                    // 'lines' in the reverse order. 
                    reverseChars();

                    break;

                case 'h':
                    // Just for testing.
                    //System.out.println("h");

                    showHelp();

                    System.exit(1);

                    break;

                default: 
                    showHelp();

                    die("Invalid option(s) included.");

                    break;
            }; // end of switch
        } // end of while

        // After executing -t and/or -r, let's do -n.
        if (nShouldBeLast)
        {
            showLineNumbers();
        }
        else
        {
            for (int i = 0; i < linesSize; i++)
            {
                System.out.println(lines.get(i));
            }
        }

        lines.clear();
    } // end of main().

    //--------------showLineNumbers()---------------------------
    // Add a line number to the begining of each line.
    public static void showLineNumbers()
    {
        int lineNumber = 0;
        for (int i = 0; i < linesSize; i++)
        {
            lineNumber++;
            System.out.printf("%-4d" + 
                              lines.get(i) + 
                              "\n", lineNumber);
        }
    }

    //--------------reverseLines()------------------------------
    // Put the elements of the Arraylist 'lines' in the reverse 
    // order. 
    public static void reverseLines()
    {
        for (int i = 0; i < linesSize; i++)
        {                      
            linesTemp.add(lines.get(linesSize - 1 - i));
        }

        for (int i = 0; i < linesSize; i++)
        {                      
            lines.set(i, linesTemp.get(i));
        }
    }

    //--------------reverseChars()------------------------------
    // Put the characters of each element of 'lines' in the 
    // reverse order. 
    public static void reverseChars()
    {
        for (int i = 0; i < linesSize; i++)
        {   
            StringBuffer sb = new StringBuffer(lines.get(i));
            lines.set(i, sb.reverse().toString());
        }
    }

    //--------------showHelp()----------------------------------
    // Show help. 
    public static void showHelp()
    {
        System.out.println("usage: List option(s) <file> \n" +
                "where option(s) are: \n" + 
                "-h: display help. \n" + 
                "-n: display line numbers. \n" +
                "-t: display lines in the reverse order. \n" + 
                "-r: display characters in the reverse order. "
                );
    }
}
