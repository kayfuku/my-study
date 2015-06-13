//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoMetaData.java
//  Date      : February 20, 2015
//  Objective : This program demonstrates how to get meta data. 
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.die;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class DemoMetaData
{   
    private static final String FILE_NAME = "data/US_states";
    private static HashMap<String, String> statesCapitalsMap = 
                                                 new HashMap<>();

    private static final String DB_TABLE = "states";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_STATE = "state";
    private static final String DB_COLUMN_CAPITAL = "capital";
    
    //------------- getConnection() ----------------------------
    // Use Properties file for security. 
    private static Connection getConnection()
            throws SQLException, IOException, ClassNotFoundException
    {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(
		        "data/database.properties");
        properties.load(fileInputStream);
        fileInputStream.close();
        
        @SuppressWarnings("unused")
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String database = properties.getProperty("jdbc.database");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        
        // Register the driver of MySQL. 
        // In case of before Java 6.0 or JDBC4.0 not supported.
        Class.forName("com.mysql.jdbc.Driver");

        return (DriverManager.getConnection(
		        url + database, username, password));        
    }

    //-------------- readFile() --------------------------------
    // Read the file.
    private static File readFile()
    {
        // The file path is from the root directory of the project.
        File file = new File(FILE_NAME);
        
        if (!file.exists())
            die(FILE_NAME + " does not exist.");
        if (file.isDirectory())
            die(FILE_NAME + " is a directory.");
        if (!file.canRead())
            die("You cannot read " + FILE_NAME + " .");
        
        return file;
    } // end of readFile().

    //------------- parseUsStates() ----------------------------
    // Parse the data in the file 'file'.
    private static void parseUsStates(File file)
    {
        String[] temp;

        try
        {
            String line;
            int ignore = 0;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                line = scanner.nextLine();
                // Ignore the first two lines.
                if (ignore > 1)
                {
                    temp = line.split("\\s\\s+");
                    statesCapitalsMap.put(temp[0], temp[1]);
                }
                ignore++;
            }

            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    } // end of parseUSState().
    
    //------------ createTable() -------------------------------
    // Create a table.
    private static void createTable(Connection connection) 
                   throws SQLException
    {                
        String createTableSQL = 
		        "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (" + 
                DB_COLUMN_ID + " int  not null auto_increment, " + 
                DB_COLUMN_STATE + " varchar(32)  not null, " + 
                DB_COLUMN_CAPITAL + " varchar(32)  not null, " + 
                "primary key(" + DB_COLUMN_ID + "))";
        
        try (PreparedStatement preparedStatement = 
		             connection.prepareStatement(createTableSQL))
        {
            // Create a table. 
            preparedStatement.executeUpdate();
        }
    } // end of createTable().

    //------------ populate() ----------------------------------
    // Populate data if not yet. 
    private static void populate(Connection connection) 
                   throws SQLException
    {
        int rowCount = getRowCount(connection);
               
        // If data is already populated, do nothing.
        if (rowCount == 0)
        {
            String insertSQL = "INSERT INTO " + DB_TABLE + " (" + 
                    DB_COLUMN_STATE + ", " + DB_COLUMN_CAPITAL + ") " + 
                    "VALUES (?, ?)";
            
            try (PreparedStatement preparedStatement = 
			         connection.prepareStatement(insertSQL))
            {
                // Populate the data.
                for (Map.Entry<String, String> entry : 
				                        statesCapitalsMap.entrySet())
                {
                    preparedStatement.setString(1, entry.getKey());
                    preparedStatement.setString(2, entry.getValue());
                    
                    preparedStatement.executeUpdate();
                }   
            } // end of try (PreparedStatement ...)
        } // end of if ()
    } // end of insert()
    
    //------------- getRowCount() -----------------------------------
    // Return the number of rows. 
    private static int getRowCount(Connection connection)
                   throws SQLException
    {   
        String selectSQL = "SELECT count(*) from " + DB_TABLE;
        try (PreparedStatement preparedStatement = 
		         connection.prepareStatement(selectSQL))
        {            
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                resultSet.next();
                int rowCount = resultSet.getInt(1);
                return rowCount;
            } 
        } // end of try (PreparedStatement ...)
    } // end of select()
       
    //------------- query() -----------------------------------
    // Execute a SQL select statement and display the result. 
    private static void query(Connection connection, String query, 
	                          int numOfField)
                   throws SQLException
    {   
        try (PreparedStatement preparedStatement = 
		         connection.prepareStatement(query))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    for (int i = 1; i <= numOfField ; i++)
                    {
                        System.out.print(resultSet.getString(i) + 
                                         ((i == numOfField) ? "\n" : ", "));
                    }
                }
            } // end of try (ResultSet ...)
        } // end of try (PreparedStatement ...)
    } // end of query()

    //-------------- main() ------------------------------------
    // Program execution starts with this method.    
    public static void main(String[] args) throws SQLException
    {
        // Connect to the database.
        try (Connection connection = getConnection())
        {
            System.out.println("Connected to the database.");            
            
            // Create a table.
            createTable(connection);
            System.out.println("Table created.");
            // Get data from the file and parse it. 
            File file = readFile();
            parseUsStates(file);                        
            // Populate the data to the table If not yet. 
            populate(connection);
            System.out.println("Data populated.");
                       
            // Get meta data of the database. 
            String[] tables = {"TABLE"};
            DatabaseMetaData dmd = connection.getMetaData();
            
            // Name of tables.
            ResultSet resultSet = dmd.getTables(null, null, "%", tables);
            String tableName = args[0];
            System.out.println("Name of the table: " + tableName);
            // Check to see if there's the table. 
            boolean isFound = false;
            while (resultSet.next())
            {
                if (resultSet.getString("TABLE_NAME").equals(tableName))
                {
                    System.out.println("The table, \'" + tableName + "\' exists.");  
                    isFound = true;
                    break;
                }               
            }
            if (!isFound)
            {
                System.out.println("The table, \'" + tableName + 
				                   "\' doesn't exist.");                  
            }
            
            String columnName, columnType;
            int columnSize;
            
            PreparedStatement ps = connection.prepareStatement(
			                       "SELECT * from " + tableName);
            resultSet = ps.executeQuery();
            // Get meta data of the ResultSet. 
            ResultSetMetaData rsmd = resultSet.getMetaData();

            // Number of the fields.
            int numOfFields = rsmd.getColumnCount();
            System.out.println("Number of the fields: " + numOfFields);
            // Name of the field.
            System.out.print("Name of the fields: ");
            for (int i = 1; i <= numOfFields; i++)
            {
                columnName = rsmd.getColumnLabel(i);
                System.out.print(columnName + 
				                 ((i == numOfFields) ? "\n" : ", "));              
            }
            // Type of the field.
            System.out.print("Type of the fields: ");
            for (int i = 1; i <= numOfFields; i++)
            {
                columnType = rsmd.getColumnTypeName(i);
                System.out.print(columnType + 
				                 ((i == numOfFields) ? "\n" : ", "));              
            }
            // Size of the field.
            System.out.print("Size of the fields: ");
            for (int i = 1; i <= numOfFields; i++)
            {
                columnSize = rsmd.getColumnDisplaySize(i);
                System.out.print(columnSize + 
				                 ((i == numOfFields) ? "\n" : ", "));              
            }
            // Number of the records. 
            System.out.print("Number of the records: " + 
			                 getRowCount(connection));
            
            resultSet.close();
            ps.close();
        } // end of try (Connection ...)
        catch (Exception e)
        {
            e.printStackTrace();
        } 
    } // end of main()
}
