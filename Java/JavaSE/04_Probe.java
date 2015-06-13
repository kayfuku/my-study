//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : Probe.java
//  Date      : 10/10/14
//  Objective : This program shows all/each of the constructors, 
//              methods, fields, constants and/or interfaces of 
//              the class that user input. 
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Probe 
{
    private static Class<?> classType;
    private static Class<?>[] interfaceClasses;
    private static Constructor<?>[] constructors;
    private static Method[] methods;
    private static Field[] fields;

    //------------- showConstructors() -------------------------
    // Show all the constructors. 
    private static void showConstructors()
    {
        constructors = classType.getConstructors();
        System.out.println("---- Constructors ----");
        for (Constructor<?> constructor : constructors) 
        {
            System.out.println(constructor);
        }
        System.out.println();
    }

    //------------- showMethods() ------------------------------
    // Show all the methods. 
    private static void showMethods()
    {
        methods = classType.getDeclaredMethods();
        System.out.println("---- Methods ----");
        for (Method method : methods) 
        {
            System.out.println(method);
        }
        System.out.println();
    }

    //------------- showFields() -------------------------------
    // Show all the fields. 
    private static void showFields()
    {
        fields = classType.getDeclaredFields();
        System.out.println("---- Fields ----");
        for (Field field : fields) 
        {
            System.out.println(field);
        }
        System.out.println();
    }

    //------------- showConstants() ----------------------------
    // Show all the constants. 
    private static void showConstants()
    {
        fields = classType.getDeclaredFields();
        System.out.println("---- Constants ----");
        for (Field field : fields) 
        {
            if (Modifier.isFinal(field.getModifiers()))
            {
                System.out.println(field);
            }
        }
        System.out.println();
    }

    //------------- showInterfaces() ---------------------------
    // Show all the interfaces. 
    private static void showInterfaces()
    {
        interfaceClasses = classType.getInterfaces();
        System.out.println("---- Interfaces ----");
        for (Class<?> interfaceClass : interfaceClasses) 
        {
            System.out.println(interfaceClass);
        }
        System.out.println();          
    }

    //------------- showHelp() ---------------------------------
    // Show help. 
    private static void showHelp()
    {
        System.out.println("usage: Probe <option(s)>" +
                " <class_name> \n" +
                "where option(s) are: \n" + 
                "-h: display help. \n" + 
                "-c: display all the constructors. \n" +
                "-m: display all the methods. \n" + 
                "-v: display all the fields. \n" + 
                "-C: display all the constants. \n" + 
                "-i: display all the interfaces. \n" + 
                "-a: display all the above. "
                );
        // Exit code is not 1 or 0 for Probe wrapper to work 
        // properly. 
        System.exit(2);
    }

    //------------- main() -------------------------------------
    // Program execution starts with this method. 
    public static void main(String[] args) 
    {
        int c;
        boolean showAll = false;
        GetOpt go;
        String typeName;
        boolean[] optionFlags = new boolean[5];
        
        // Use GetOpt class.
        go = new GetOpt(args, "cmvCiah");

        // Assign the class name to variable 'typeName'.  
        typeName = args[args.length - 1];  
        // If user do not input class name, show help. 
        if (typeName.charAt(0) == '-')
        {
            System.out.println("Please input class name properly.");
            showHelp();
        } 
        // If user do not input any option, show help. 
        if (args.length < 3 && args[0].charAt(0) != '-') 
        {
            System.out.println("Please input option(s) properly.");
            showHelp();
        }

        // Get the class type.
        try
        {
            classType = Class.forName(args[args.length - 1]);
        }
        catch (ClassNotFoundException e)
        {
            // Do nothing in order not to display the exception.
            // Refer to Probe wrapper. 
        }
        
        // Execute the statements depending on the options 
        // that user input.   
        while ((c = go.getopt()) != -1) 
        {
            switch (c) 
            {
                case 'c':
                    optionFlags[0] = true;
                    break;

                case 'm':
                    optionFlags[1] = true;
                    break;

                case 'v':
                    optionFlags[2] = true;
                    break;

                case 'C':
                    optionFlags[3] = true;
                    break;

                case 'i':
                    optionFlags[4] = true;
                    break;

                case 'a':
                    showConstructors();
                    showMethods();
                    showFields();
                    showConstants();
                    showInterfaces();
                    System.exit(0);
                    break;

                case 'h':
                    showHelp();
                    break;

                default: 
                    System.out.println("Invalid option(s) " + 
                                       "included");
                    showHelp();
                    break;

            }; // end of switch
        } // end of while
        
        // Do the job that user requested. 
        if (optionFlags[0]) 
        {
            showConstructors();
        }
        if (optionFlags[1])
        {
            showMethods();
        }
        if (optionFlags[2])
        {
            showFields();
        }
        if (optionFlags[3])
        {
            showConstants();
        }
        if (optionFlags[4])
        {
            showInterfaces();
        }
    } // end of main().
}
