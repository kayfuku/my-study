//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : MakeXMLDemo.java
//  Date      : May 15, 2015
//  Objective : This program demonstrates how to make a XML file. 
//  Java version : 1.8.0_20
//**************************************************************

import java.io.File;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MakeXMLDemo
{
    private static final String OUTPUT_FILE_PATH = 
                                  "data/java_properties.xml";
    private static TreeMap<String, String> javaProperties = 
	                                             new TreeMap<>();
    private static Document doc;

    // ------------ getJavaProperty() --------------------------
    // Get properties whose name start with "java.".
    public static void getJavaProperty()
    {
        Pattern pattern = Pattern.compile("^java\\.");
        Matcher matcher; 
        
        Properties properties = System.getProperties();
        Enumeration<Object> keysEnumeration = properties.keys();
        
        while (keysEnumeration.hasMoreElements())
        {
            String keyString = (String) keysEnumeration.nextElement();
            
            matcher = pattern.matcher(keyString);                  
            if (matcher.find())
            {
                String valueString = (String) properties.get(keyString);
                javaProperties.put(keyString, valueString);
            }
        }
    } // end of getJavaProperty()
    
    // ------------ createXMLTags() -----------------------------
    // Split the name of the properties and create XML tags 
    // with parent-child relationship.     
    // Preconditions: The elements of javaProperties are sorted. 
    public static void createXMLTags()
    {
        String javaPropertyKey = "";
        String tagName = "";
        
        try
        {
            Element[] nodes = new Element[5];
            Element node = null;
            StringTokenizer stringTokenizer;
            for (Map.Entry<String, String> entry : 
			                          javaProperties.entrySet())
            {
                // The example of javaPropertyKey is "java.class.path".
                javaPropertyKey = entry.getKey();               
                stringTokenizer = 
				        new StringTokenizer(javaPropertyKey, ".");
                
                int i = 0;
                while (stringTokenizer.hasMoreElements()) 
                {
                    // The example of tagName is "java"(i=0), 
                    // "class"(i=1), or "path"(i=2).
                    tagName = stringTokenizer.nextToken();
                    node = doc.createElement(tagName);                        
                    
                    if (i == 0)
                    {
                        // Do not create a root node 
                        // from the second for-loop. 
                        if (nodes[i] != null &&
                            tagName.equals(nodes[i].getTagName()))
                        {
                            i++;
                            continue;
                        }
                        
                        // Create a root node.
                        // This should be occurred only in the first 
						// for-loop. 
                        doc.appendChild(node);
                        nodes[i] = node;                        
                    }
                    else
                    {
                        // Do not create a child node
                        // if the node exists already.
                        if (nodes[i] != null &&
                            tagName.equals(nodes[i].getTagName()))
                        {
                            i++;
                            continue;                            
                        }
                        
                        // Create a child node.
                        nodes[i - 1].appendChild(node);
                        nodes[i] = node;                        
                    }
                    
                    i++;                    
                } // end of while (stringTokenizer...)
                
                // Set text content in between XML tags.
                node.setTextContent(entry.getValue());
            } // end of for (Map.Entry<String, String>...)
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("property: " + javaPropertyKey + "\n" + 
                    "tag name: " + tagName);
        }
    } // end of createXMLTags()    
    
    // ------------ createXMLFile() -----------------------------
    // Create a XML file from the DOM object. 
    public static void createXMLFile()
    {
        try
        {
            TransformerFactory tf = TransformerFactory.newInstance(); 
            Transformer transformer = tf.newTransformer(); 
            // New line setting.
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xalan}indent-amount", "4");
            File outfile = new File(OUTPUT_FILE_PATH);
            transformer.transform(new DOMSource(doc), 
			                      new StreamResult(outfile));            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }    
    } // end of createXMLFile()
    
    // -------------- main() ------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        try
        {
            // Create a new DOM object. 
            DocumentBuilderFactory f = 
			        DocumentBuilderFactory.newInstance();
            DocumentBuilder db = f.newDocumentBuilder();
            doc = db.newDocument();
            
            // Get system properties that start with "java.".
            getJavaProperty();
            
            // Split the name of the properties and create XML tags 
            // with parent-child relationship. 
            createXMLTags();
            
            // Create a XML file from the DOM object. 
            createXMLFile();
            System.out.println("XML file, " +  OUTPUT_FILE_PATH + 
                               ", is created.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.err.println("done.");
        return;
    } // end of main()
}
