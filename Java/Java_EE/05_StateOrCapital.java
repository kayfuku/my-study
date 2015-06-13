//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : StateOrCapital.java
//  Date      : April 2, 2015
//  Objective : This is a servlet program and returns a state or 
//              capital city string corresponding to the user 
//              input by making HTML. This project is a dynamic 
//              web project and the path to Tomcat is set. 
//              Also, this program uses JDBC accessing to MySQL.
//  Java version : 1.8.0_20
//**************************************************************

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StateOrCapital")
public class StateOrCapital extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    private static HashMap<String, String> statesCapitalsMap = 
                                                 new HashMap<>();

    private static final String DB_TABLE = "states";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_STATE = "state";
    private static final String DB_COLUMN_CAPITAL = "capital";
    private Connection connection;

    @Override
    // ------------ init() -------------------------------------
    // Prepare for MySQL.
    public void init(ServletConfig sc) throws ServletException
    {
        super.init(sc);

        try
        {
            connection = getConnection();

            // Create a table.
            createTable(connection);
            // Get data from the file and parse it.
            File file = readFile();
            parseUsStates(file);
            // Populate the data to the table If not yet.
            populate(connection);

        } // end of try (Connection ...)
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } // end of init()

    // ------------- getConnection() ----------------------------
    // Use Properties file for security.
    private Connection getConnection() throws SQLException, IOException,
                                              ClassNotFoundException
    {
        Properties properties = new Properties();
        // If you want to set the file path, do like this when using servlet.
        String realPath = this.getServletContext().getRealPath(
                "data/database.properties");
        FileInputStream fileInputStream = new FileInputStream(realPath);
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
        // By the way, MySQL connector is added by external jar in the lib
        // folder.
        Class.forName("com.mysql.jdbc.Driver");

        return (DriverManager.getConnection(url + database, username, 
                                            password));
    }

    // -------------- readFile() --------------------------------
    // Read the file.
    private File readFile()
    {
        String realPath = this.getServletContext().getRealPath(
                                             "data/US_states");
        File file = new File(realPath);

        if (!file.exists())
            die(realPath + " does not exist.");
        if (file.isDirectory())
            die(realPath + " is a directory.");
        if (!file.canRead())
            die("You cannot read " + realPath + " .");

        return file;
    } // end of readFile().

    // ------------- die() --------------------------------------
    // Display error message.
    private static void die(String... msg)
    {
        if (msg.length == 0)
            System.exit(1);
        System.err.println(msg[0]);
        System.exit(1);
    }

    // ------------ parseUsStates() ----------------------------
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

    // ------------ createTable() -------------------------------
    // Create a table.
    private static void createTable(Connection connection) 
                                    throws SQLException
    {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + 
                DB_TABLE + " (" + DB_COLUMN_ID + 
                " int  not null auto_increment, " + DB_COLUMN_STATE + 
                " varchar(32)  not null, " + DB_COLUMN_CAPITAL + 
                " varchar(32)  not null, " + "primary key(" + 
                DB_COLUMN_ID + "))";

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(createTableSQL))
        {
            // Create a table.
            preparedStatement.executeUpdate();
        }
    } // end of createTable().

    // ------------ populate() ------------------------------------
    // Populate data if not yet.
    private static void populate(Connection connection) throws SQLException
    {
        int rowCount = getRowCount(connection);

        // If data is already populated, do nothing.
        if (rowCount == 0)
        {
            String insertSQL = "INSERT INTO " + DB_TABLE + " (" + DB_COLUMN_STATE
                    + ", " + DB_COLUMN_CAPITAL + ") " + "VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(insertSQL))
            {
                // Populate the data.
                for (Map.Entry<String, String> entry : statesCapitalsMap.entrySet())
                {
                    preparedStatement.setString(1, entry.getKey());
                    preparedStatement.setString(2, entry.getValue());

                    preparedStatement.executeUpdate();
                }
            } // end of try (PreparedStatement ...)
        } // end of if ()
    } // end of populate()

    // ------------- getRowCount() -----------------------------------
    // Return the number of rows.
    private static int getRowCount(Connection connection) throws SQLException
    {
        String selectSQL = "SELECT count(*) from " + DB_TABLE;
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(selectSQL))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                resultSet.next();
                int rowCount = resultSet.getInt(1);
                return rowCount;
            }
        } // end of try (PreparedStatement ...)
    } // end of getRowCount()

    // ------------- select() -----------------------------------
    // Execute a SQL select statement and display the result.
    private static String[][] select(Connection connection, String selectSQL,
            int numOfField) throws SQLException
    {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(selectSQL))
        {
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                String[][] result = new String[20][10];
                int row = 0;
                while (resultSet.next())
                {
                    row++;
                    for (int column = 1; column <= numOfField; column++)
                    {
                        result[row][column] = resultSet.getString(column);
                    }
                }

                return result;
            } // end of try (ResultSet ...)
        } // end of try (PreparedStatement ...)
    } // end of select()

    // ------------- stateOrCapital() -----------------------
    // Return String "state" if the userInput is state.
    // Vice versa.
    private String stateOrCapital(String userInput)
    {
        Matcher matcher;
        Pattern pattern = null;

        try
        {
            pattern = Pattern.compile(userInput, Pattern.CASE_INSENSITIVE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> e : statesCapitalsMap.entrySet())
        {
            // Check if the state's name matches the input.
            matcher = pattern.matcher(e.getKey());
            if (matcher.matches())
            {
                return "state";
            }

            // Check if the capital's name matches the input.
            matcher = pattern.matcher(e.getValue());
            if (matcher.matches())
            {
                return "capital";
            }
        }

        return "neither";
    } // end of stateOrCapital()

    @Override
    // ------------ destroy() ----------------------------------
    // Close the connection.
    public void destroy()
    {
        try
        {
            connection.close();
        }
        catch (Exception e)
        {
            // Do nothing.
        }
    };

    // ------------ doGet() ------------------------------------
    // Get information from the browser.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException
    {
        // Receive info from the browser.
        String msg = request.getParameter("state_or_capital");

        try
        {
            // Check if the user input is state or capital.
            String stateOrCapital = stateOrCapital(msg);

            if (stateOrCapital.equals("state"))
            {
                String selectSQL = "SELECT " + DB_COLUMN_CAPITAL + " FROM "
                        + DB_TABLE + " WHERE " + DB_COLUMN_STATE + " = \'" + msg
                        + "\'";
                String[][] result = select(connection, selectSQL, 1);
                // Send info back to the browser.
                sendHtml(response, result, "capital", msg);
            }
            else if (stateOrCapital.equals("capital"))
            {
                String selectSQL = "SELECT " + DB_COLUMN_STATE + " FROM " + DB_TABLE
                        + " WHERE " + DB_COLUMN_CAPITAL + " = \'" + msg + "\'";
                String[][] result = select(connection, selectSQL, 1);
                // Send info back to the browser.
                sendHtml(response, result, "state", msg);
            }
            else
            {
                String[][] result = {{"Please type state or capital."}};
                // Send info back to the browser.
                sendHtml(response, result, "neither", msg);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } // end of doGet()
    
    @Override
    // ------------ doPost() ------------------------------------
    // Get information from the browser.
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
                       throws ServletException, IOException
    {
        doGet(req, res);
    }

    // ------------ sendHtml() ---------------------------------
    // Send info back to the browser.
    public void sendHtml(HttpServletResponse response, String[][] result,
            String stateOrCapital, String msg) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head><title>State or Capital</title></head>");

        if (stateOrCapital != "neither")
        // If user input state or capital.
        {
            pw.println("<body><h1><strong>The " + stateOrCapital + " of " + 
                       msg + " is ");
            pw.println(result[1][1] + ".</strong></h1></body>");
        }
        else
        // If user input other words. 
        {
            pw.println("<body><h1><strong>" + result[0][0] + 
                       "</strong></h1></body>");
        }

        pw.println("</html>");
        pw.close();
    }
}
