//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : SystemAccess.java
//  Date      : 9/23/14
//  Objective : This program demonstrates how to access to OS.
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.println;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SystemAccess
{

    // ------------- system() -------------------------------------
    // Program execution starts with this method.
    public static void system(String str)
    {
        try
        {
            StringTokenizer st = new StringTokenizer(str);
            ArrayList<String> strings = new ArrayList<String>();

            while (st.hasMoreTokens())
            {
                strings.add(st.nextToken());
            }

            Process process = (new ProcessBuilder(strings))
                    .redirectErrorStream(true).start();

            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNext())
                println(scanner.nextLine());
            scanner.close();

            // Wait until the process finished.
            int exitCode = process.waitFor();
            println("exit code: " + exitCode);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
    }

    // ------------- main() -------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        system("java -version");
        system("pwd");
        system("ls");
        system("ls -l");
        system("ls -l SystemAccess.java");
        system("ls -l \\*.java");

    }
}
