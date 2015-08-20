//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : ShowColors.java
//  Date      : 11/3/14
//  Objective : This program shows how to use graphics, layout,
//              canvas, radio buttons, sliders, and labels and 
//              also shows how to convert a number to a string 
//              representing that number in a binary, octal or 
//              hexadecimal digits. 
//  Java version : 1.8.0_20
//**************************************************************

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ShowColors
{
    // ************* Panel0 class *******************************
    // This panel provides a border layout that contains panel1,
    // panel2, and canvas.
    public class Panel0 extends JPanel
    {
        private RgbCanvas rgbCanvas;
        private int rValue = 0;
        private int gValue = 0;
        private int bValue = 0;
        private JLabel rLabel, gLabel, bLabel;
        private int radixFlag = 10;

        // ------------- Panel0() -------------------------------
        // Constructor.
        public Panel0()
        {
            setLayout(new BorderLayout());
            setBackground(Color.CYAN);

            // Set up RgbCanvas.
            rgbCanvas = new RgbCanvas();
            add(rgbCanvas, BorderLayout.CENTER);

            // Set up Panel1.
            Panel1 panel1 = new Panel1();
            panel1.setPreferredSize(new Dimension(100, 450));
            panel1.setBackground(Color.GREEN);
            add(panel1, BorderLayout.EAST);

            // Set up Panel2.
            Panel2 panel2 = new Panel2();
            panel2.setPreferredSize(new Dimension(600, 150));
            panel2.setBackground(Color.YELLOW);
            add(panel2, BorderLayout.SOUTH);
        }

        // ************* RgbCanvas class ************************
        // This canvas is placed in CENTER of Panel0.
        public class RgbCanvas extends Canvas
        {
            @Override
            // --------- paint() --------------------------------
            // Draw red, green, and/or blue bar(s) and an oval.
            public void paint(Graphics g)
            {
                final int BAR_GAP = 70;
                final int BAR_WIDTH = 100;
                final int BAR_BOTTOM = 330;
                final int BAR_ZERO_HEIGHT = 5;
                int rHeight = rValue + BAR_ZERO_HEIGHT;
                int gHeight = gValue + BAR_ZERO_HEIGHT;
                int bHeight = bValue + BAR_ZERO_HEIGHT;

                setBackground(Color.LIGHT_GRAY);

                // Red bar.
                g.setColor(Color.RED);
                g.fillRect(20, BAR_BOTTOM - rHeight, BAR_WIDTH, rHeight);
                // Green bar.
                g.setColor(Color.GREEN);
                g.fillRect(20 + BAR_WIDTH + BAR_GAP, BAR_BOTTOM - gHeight,
                        BAR_WIDTH, gHeight);
                // Blue bar.
                g.setColor(Color.BLUE);
                g.fillRect(20 + BAR_WIDTH * 2 + BAR_GAP * 2, BAR_BOTTOM - bHeight,
                        BAR_WIDTH, bHeight);
                // Color Oval.
                g.setColor(new Color(rValue, gValue, bValue));
                g.fillOval(140, 10, 200, 50);
                // In case that the color inside of the oval is
                // the same as that of background.
                g.setColor(Color.BLACK);
                g.drawOval(140, 10, 200, 50);
            } // end of paint().
        } // end of RgbCanvas class.

        // ************* Panel1 class ***************************
        // This panel is placed in EAST of Panel0.
        public class Panel1 extends JPanel
        {
            private JRadioButton decimalButton, binaryButton, hexButton,
                    octalButton;

            // ------------- Panel1() ---------------------------
            // Constructor.
            public Panel1()
            {
                setLayout(new GridLayout(4, 1));

                // Radio Buttons.
                decimalButton = new JRadioButton("Decimal", true);
                binaryButton = new JRadioButton("Binary");
                hexButton = new JRadioButton("Hex");
                octalButton = new JRadioButton("Octal");

                // Group the radio buttons.
                ButtonGroup bGroup = new ButtonGroup();
                bGroup.add(decimalButton);
                bGroup.add(binaryButton);
                bGroup.add(hexButton);
                bGroup.add(octalButton);

                // Set the listener to the radio buttons.
                RadioListener listener = new RadioListener();
                decimalButton.addChangeListener(listener);
                binaryButton.addChangeListener(listener);
                hexButton.addChangeListener(listener);
                octalButton.addChangeListener(listener);

                // Add the radio buttons to this panel1.
                add(decimalButton);
                add(binaryButton);
                add(hexButton);
                add(octalButton);
            } // end of Panel1().

            // ************* RadioListener class ****************
            // Represents the listener for all radio buttons.
            private class RadioListener implements ChangeListener
            {
                // --------- actionPerformed() ------------------
                // Change the base depending on the button pressed.
                public void stateChanged(ChangeEvent event)
                {
                    if (decimalButton.isSelected())
                    {
                        rLabel.setText("Red:  " + rValue);
                        gLabel.setText("Green:  " + gValue);
                        bLabel.setText("Blue:  " + bValue);
                        radixFlag = 10;
                    }
                    if (binaryButton.isSelected())
                    {
                        rLabel.setText("Red:  " + Integer.toBinaryString(rValue));
                        gLabel.setText("Green:  " + Integer.toBinaryString(gValue));
                        bLabel.setText("Blue:  " + Integer.toBinaryString(bValue));
                        radixFlag = 2;
                    }
                    if (hexButton.isSelected())
                    {
                        rLabel.setText("Red:  " + Integer.toHexString(rValue));
                        gLabel.setText("Green:  " + Integer.toHexString(gValue));
                        bLabel.setText("Blue:  " + Integer.toHexString(bValue));
                        radixFlag = 16;
                    }
                    if (octalButton.isSelected())
                    {
                        rLabel.setText("Red:  " + Integer.toOctalString(rValue));
                        gLabel.setText("Green:  " + Integer.toOctalString(gValue));
                        bLabel.setText("Blue:  " + Integer.toOctalString(bValue));
                        radixFlag = 8;
                    }
                }
            } // end of RadioListener class.
        } // end of Panel1 class.

        // ********* Panel2 class *******************************
        // This panel is placed in SOUTH of Panel0.
        public class Panel2 extends JPanel
        {
            private JSlider rSlider, gSlider, bSlider;

            // ----- Panel2() -----------------------------------
            // Constructor.
            public Panel2()
            {
                setLayout(new GridLayout(2, 3, 5, 0));

                // Create labels and sliders.
                rLabel = new JLabel("Red:  0");
                gLabel = new JLabel("Green:  0");
                bLabel = new JLabel("Blue:  0");
                rSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
                rSlider.setMajorTickSpacing(50);
                rSlider.setPaintTicks(true);
                rSlider.setPaintLabels(true);
                gSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
                gSlider.setMajorTickSpacing(50);
                gSlider.setPaintTicks(true);
                gSlider.setPaintLabels(true);
                bSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
                bSlider.setMajorTickSpacing(50);
                bSlider.setPaintTicks(true);
                bSlider.setPaintLabels(true);

                // Set the listener to the sliders.
                SliderListener sliderListener = new SliderListener();
                sliderListener.canvas = rgbCanvas;
                rSlider.addChangeListener(sliderListener);
                gSlider.addChangeListener(sliderListener);
                bSlider.addChangeListener(sliderListener);

                // Add the labels and the sliders to this panel2.
                add(rLabel);
                add(gLabel);
                add(bLabel);
                add(rSlider);
                add(gSlider);
                add(bSlider);
            } // end of Panel2().

            // ************* SliderListener class ***************
            // Represent the listener for all three sliders.
            private class SliderListener implements ChangeListener
            {
                RgbCanvas canvas;

                // --------- stateChanged() ---------------------
                // Get the value of each slider, then updates the
                // labels and RgbCanvas.
                public void stateChanged(ChangeEvent event)
                {
                    rValue = rSlider.getValue();
                    gValue = gSlider.getValue();
                    bValue = bSlider.getValue();

                    switch (radixFlag)
                    {
                        case 10:
                            rLabel.setText("Red:  " + rValue);
                            gLabel.setText("Green:  " + gValue);
                            bLabel.setText("Blue:  " + bValue);
                            break;
                        case 2:
                            rLabel.setText("Red:  " + Integer.toBinaryString(rValue));
                            gLabel.setText("Green:  "
                                    + Integer.toBinaryString(gValue));
                            bLabel.setText("Blue:  "
                                    + Integer.toBinaryString(bValue));
                            break;
                        case 16:
                            rLabel.setText("Red:  " + Integer.toHexString(rValue));
                            gLabel.setText("Green:  " + Integer.toHexString(gValue));
                            bLabel.setText("Blue:  " + Integer.toHexString(bValue));
                            break;
                        case 8:
                            rLabel.setText("Red:  " + Integer.toOctalString(rValue));
                            gLabel.setText("Green:  "
                                    + Integer.toOctalString(gValue));
                            bLabel.setText("Blue:  " + Integer.toOctalString(bValue));
                            break;
                        default:
                            break;
                    }

                    // Execute paint() every time the value of
                    // each color changes.
                    canvas.repaint();
                }
            }
        } // end of Panel2 class.
    } // end of Panel0 class.

    // ------------- main() -------------------------------------
    // Program execution starts with this method.
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            // ------------- run() ------------------------------
            // Another thread runs this method.
            public void run()
            {
                JFrame jframe = new JFrame("Show Colors");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Set up Panel0.
                ShowColors showColors = new ShowColors();
                ShowColors.Panel0 panel0 = showColors.new Panel0();
                panel0.setPreferredSize(new Dimension(600, 500));

                jframe.getContentPane().add(panel0);
                jframe.pack();
                jframe.setVisible(true);
            }
        });
    } // end of main().
}
