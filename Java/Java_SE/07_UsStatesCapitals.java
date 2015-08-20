//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : UsStatesCapitals.java
//  Date      : 12/8/14
//  Objective : This program demonstrates how to use Map and 
//              regular expressions. 
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.die;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class UsStatesCapitals
{
    private static HashMap<String, String> statesCapitalsMap = new HashMap<>();
    private static String[] temp;
    private static File f;
    private static Pattern pattern;
    private static Matcher matcher;

    // ************* PanelPrimary class *************************
    // This panel defines the UI.
    public class PanelPrimary extends JPanel
    {
        private JLabel inputLabel, outputLabel, resultLabel, inputRegexLabel,
                outputRegexLabel;
        private JTextPane resultRegexLabel;
        private JTextField statesOrCapitalField, regexField;

        // ------------- PanelPrimary() -------------------------
        // Constructor.
        public PanelPrimary()
        {
            inputLabel = new JLabel("Type a US state or capital, "
                    + "then press Enter key.");
            outputLabel = new JLabel("The capital or state "
                    + "that matches the input is displayed here. >");
            resultLabel = new JLabel("---");
            inputRegexLabel = new JLabel("Type a regular expression, "
                    + "then press Enter key.");
            outputRegexLabel = new JLabel("The candidates "
                    + "that matches the input are displayed here. >");
            resultRegexLabel = new JTextPane();
            resultRegexLabel.setText("---");

            statesOrCapitalField = new JTextField(15);
            statesOrCapitalField.addActionListener(new MyActionListener());
            regexField = new JTextField(15);
            regexField.addActionListener(new MyActionListenerRegex());

            add(inputLabel);
            add(statesOrCapitalField);
            add(outputLabel);
            add(resultLabel);
            add(inputRegexLabel);
            add(regexField);
            add(outputRegexLabel);
            add(resultRegexLabel);

            setPreferredSize(new Dimension(300, 200));
            setBackground(Color.WHITE);
        }

        // ********* MyActionListener class *********************
        // An action listener for 'statesOrCapitalField' text field.
        private class MyActionListener implements ActionListener
        {
            // ----- actionPerformed() --------------------------
            // When the Enter key pressed, this method is called.
            public void actionPerformed(ActionEvent event)
            {
                String text = statesOrCapitalField.getText();
                pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);

                boolean isFound = false;
                for (Map.Entry<String, String> e : statesCapitalsMap.entrySet())
                {
                    // Check if the state's name matches the input.
                    matcher = pattern.matcher(e.getKey());
                    if (matcher.matches())
                    {
                        resultLabel.setText(e.getValue());
                        isFound = true;
                        break;
                    }

                    // Check if the capital's name matches the input.
                    matcher = pattern.matcher(e.getValue());
                    if (matcher.matches())
                    {
                        resultLabel.setText(e.getKey());
                        isFound = true;
                        break;
                    }
                }

                if (!isFound)
                {
                    resultLabel.setText("Type a state or capital.");
                }
            } // end of actionPerformed().
        } // end of MyActionListener class.

        // ********* MyActionListenerRegex class ****************
        // An action listener for 'regexField' text field.
        private class MyActionListenerRegex implements ActionListener
        {
            // ----- actionPerformed() --------------------------
            // When the Enter key pressed, this method is called.
            public void actionPerformed(ActionEvent event)
            {
                StringBuilder sBuilder = new StringBuilder("");

                String regex = regexField.getText();
                try
                {
                    pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                }
                catch (PatternSyntaxException e)
                {
                    System.out.println(e);
                    resultRegexLabel.setText("Regex syntax might be wrong.");
                }

                // Search.
                boolean isFound = false;
                for (Map.Entry<String, String> e : statesCapitalsMap.entrySet())
                {
                    // Check if the state's name matches the input.
                    matcher = pattern.matcher(e.getKey());
                    if (matcher.find())
                    {
                        sBuilder.append(" " + e.getKey() + " \n");
                        isFound = true;
                    }
                }

                // Display.
                if (isFound)
                {
                    resultRegexLabel.setText(sBuilder.toString());
                }
                else
                {
                    resultRegexLabel.setText(" Nothing matches.");
                }
            } // end of actionPerformed().
        } // end of MyActionListenerRegex class.
    } // end of PanelPrimary class.

    // ------------- readFile() ---------------------------------
    // Read the file.
    private static void readFile()
    {
        // The file path is from the project directory.
        String file = new String("data/states_and_capitals");

        f = new File(file);
        if (!f.exists())
            die(file + " does not exist.");
        if (f.isDirectory())
            die(file + " is a directory.");
        if (!f.canRead())
            die("You cannot read " + file + " .");
    } // end of readFile().

    // -------------parseUsStates()------------------------------
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

    // ------------- main() -------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        // Get the states and capitals data from a file and
        // put them into the Map.
        readFile();
        parseUsStates();

        // Another thread is created.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            // ----- run() --------------------------------------
            // Another thread calls this method and sets up the UI.
            public void run()
            {
                JFrame jframe = new JFrame("US States Capitals");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Set up PanelPrimary.
                UsStatesCapitals usStatesCapitals = new UsStatesCapitals();
                UsStatesCapitals.PanelPrimary panelPrimary = usStatesCapitals.new PanelPrimary();
                panelPrimary.setPreferredSize(new Dimension(600, 500));

                jframe.getContentPane().add(panelPrimary);
                jframe.pack();
                jframe.setVisible(true);
            }
        });
    } // end of main().
}
