//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoJDBC.java
//  Date      : February 13, 2015
//  Objective : This program demonstrates how to connect a 
//              database and populate data and retrieve data. 
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.die;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DemoJDBC
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "kfukutan";
    private static final String DB_USER_NAME = "kfukutan";
    private static final String DB_PASSWORD = "mysql56";
    
    private static final String FILE_NAME = "data/US_states";
    private static HashMap<String, String> statesCapitalsMap = 
                                                 new HashMap<>();

    private static final String DB_TABLE = "states";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_STATE = "state";
    private static final String DB_COLUMN_CAPITAL = "capital";

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

    // -------------parseUsStates()-----------------------------
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
    
    //-------------createTable()--------------------------------
    // Create a table.
    private static void createTable(Statement statement) 
	                                         throws SQLException
    {
        String sqlcmd = "";
        
        sqlcmd += "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " (";
        sqlcmd += DB_COLUMN_ID + " int  not null auto_increment, ";
        sqlcmd += DB_COLUMN_STATE + " varchar(32)  not null, ";
        sqlcmd += DB_COLUMN_CAPITAL + " varchar(32)  not null, ";
        sqlcmd += "primary key(" + DB_COLUMN_ID + "))";
        
        statement.execute(sqlcmd);
    } // end of createTable().

    //-------------insert()-------------------------------------
    // Insert data.
    private static void insert(Statement statement) 
                                             throws SQLException
    {
        String sqlcmd = "";
        
        for (Map.Entry<String, String> entry : statesCapitalsMap.entrySet())
        {
            sqlcmd = "";
            sqlcmd += "INSERT INTO " + DB_TABLE + " (";
            sqlcmd += DB_COLUMN_STATE + ", " + DB_COLUMN_CAPITAL + ") ";
            sqlcmd += "VALUES (\'" + entry.getKey() + "\', \'" + 
			          entry.getValue() + "\')";
            statement.execute(sqlcmd);
        }         
    } // end of insert().
    
    //-------------- main() ------------------------------------
    // Program execution starts with this method.    
    public static void main(String[] args) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        
        try 
        {
            // Always begin with connection when using database!
            connection = DriverManager.getConnection(
                    DB_URL + DB_NAME, DB_USER_NAME, DB_PASSWORD);
            System.out.println("Connected to the database.");
            
            // PreparedStatement might be better 
			// for preventing SQL Injection. 
            statement = connection.createStatement();
            // Create a table.
            createTable(statement);
            System.out.println("Table created.");
            // Get data from the file and parse it. 
            File file = readFile();
            parseUsStates(file);
            // Insert the data to the table If not yet. 
            ResultSet resultSet = statement.executeQuery(
                            "SELECT count(*) FROM " + DB_TABLE);
            resultSet.next();
            if (resultSet.getInt(1) < 50)
            {
                insert(statement);
            }
            System.out.println("Data inserted.");

            // Queries.
            // 1. The number of states.
            resultSet = statement.executeQuery(
                            "SELECT count(*) FROM " + DB_TABLE);
            resultSet.next();
            System.out.println("1. Number of states: ");
            System.out.println(resultSet.getInt(1));

            // 2. Capital of Florida.
            resultSet = statement.executeQuery(
                    "SELECT " + DB_COLUMN_CAPITAL + " FROM " + DB_TABLE +
                    " WHERE " + DB_COLUMN_STATE + " = \'Florida\'");
            resultSet.next();
            System.out.println("2. Capital of Florida: ");
            System.out.println(resultSet.getString(1));

            // 3. The states that begin with letter 'M'.
            resultSet = statement.executeQuery(
                    "SELECT " + DB_COLUMN_STATE + " FROM " + DB_TABLE +
                    " WHERE " + DB_COLUMN_STATE + " regexp \'^M\'");
            System.out.println(
			        "3. The states that begin with letter 'M': ");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1));
            }
            
            // 4. The states that consists of 5 characters.
            resultSet = statement.executeQuery(
                    "SELECT " + DB_COLUMN_STATE + " FROM " + DB_TABLE +
                    " WHERE " + DB_COLUMN_STATE + " regexp \'^.{5}$\'");
            System.out.println(
			        "4. The states that consist of 5 characters: ");
            while (resultSet.next())
            {
                System.out.println(resultSet.getString(1));
            }
            
            if (!resultSet.isClosed()) resultSet.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (!statement.isClosed()) statement.close();
            if (!connection.isClosed()) connection.close();
        }
    } // end of main().
}
