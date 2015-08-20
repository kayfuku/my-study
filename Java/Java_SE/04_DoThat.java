//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DoThat.java
//  Date      : 10/10/14
//  Objective : This class is a sample data file to check if the 
//              program Probe.java works properly. Other than 
//              the structure, there is no meaning. 
//  Java version : 1.8.0_20
//**************************************************************

import static library.CS211S_Lib.sleep;

public class DoThat implements Runnable
{
    public double avg;
    private int x;
    public static final String LABEL_AVE = "average";

    // ------------- DoThat() -----------------------------------
    // Constructor 1.
    public DoThat()
    {
        avg = 0;
        x = 0;
    }

    // ------------- DoThat() -----------------------------------
    // Constructor 2.
    public DoThat(int a, int b, int c)
    {
        avg = (a + b + c) / 3;
    }

    // ------------- add() --------------------------------------
    // Method 1.
    public static void add(int a, double d)
    {
        double sum = 0;
        sum = a + d;
        System.out.println(sum);
    }

    // ------------- doit() -------------------------------------
    // Method 2.
    private int doit()
    {
        Thread thread = new Thread(this);
        thread.start();

        return x;
    }

    @Override
    // ------------- run() --------------------------------------
    // Method 3.
    public void run()
    {
        sleep(3000);
    }
}
