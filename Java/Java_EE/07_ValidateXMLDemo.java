//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : ValidateXMLDemo.java
//  Date      : May 15, 2015
//  Objective : This program demonstrates how to validate a XML 
//              file. 
//  Java version : 1.8.0_20
//**************************************************************

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ValidateXMLDemo
{
    // -------------- main() ------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        String fileName = "";
        try
        {        
            if (args.length != 0)
            {
                fileName = args[0];
            }
            else
            {
                System.err.println("Please enter a file name" + "\n" + 
                    "Usage: java ValidateXMLDemo <xml_filename>" + "\n" + 
                    "e.g. java ValidateXMLDemo java_properties.xml\n");                
            }
            File inputFile = new File("data/" + fileName);
            DocumentBuilderFactory f = 
			        DocumentBuilderFactory.newInstance();
            DocumentBuilder db = f.newDocumentBuilder();
            Document doc = db.parse(inputFile);
            
            System.out.println("File name: " + fileName + "\n" +  
                               "This XML is valid."); 
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            System.err.println("File name: " + fileName + "\n" + 
                               "This XML is invalid.");
        }
    } // end of main() 
}
