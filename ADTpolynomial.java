//**************************************************************
//  Purpose   : This program demonstrates how to implement ADT 
//              polynomial and how to use it. 
//  Author    : Fukutani, Kei
//  Date      : March 3, 2015
//  Labs      : Lab2 (CS111C)
//  File Name : Lab2.java 
//**************************************************************

import java.util.Scanner;

public class Lab2
{
    public static void main(String[] args)
    // Allows the user to input, and then add, two polynomials.
    // Pre: 0 <= degree <= MAX_DEGREE
    // Post: Displays the sum of the two polynomials.
    {
        try (Scanner keyboard = new Scanner(System.in))
        {
            System.out.println("POLYNOMIAL ADDER");
            System.out.println("This program adds two polynomials "
                    + "and displays the result.");
            System.out.println();

            // The first polynomial.
            System.out.println("Enter first polynomial.");
            System.out.println("Enter each term on a separate line; "
                    + "coefficient followed by power.");
            System.out.println("For example, 3 4 represents the term 3x^4.");
            System.out.println("Enter -1 -1 to end input for the polynomial.");

            int inputCoef = keyboard.nextInt();
            int inputPower = keyboard.nextInt();
            Polynomial polynomial = new Polynomial();

            while (inputCoef != -1 || inputPower != -1)
            {
                try
                {
                    polynomial.changeCoefficient(inputCoef, inputPower);
                    inputCoef = keyboard.nextInt();
                    inputPower = keyboard.nextInt();
                }
                catch (InvalidPowerExceptions e)
                {
                    System.out.println("Error: cannot have negative power.\n"
                            + "Please re-enter term and continue.");
                    inputCoef = keyboard.nextInt();
                    inputPower = keyboard.nextInt();
                }
            }
            System.out.println("First polynomial: " + polynomial);
            System.out.println();

            // The second polynomial.
            System.out.println("Enter second polynomial.");
            System.out.println("Enter each term on a separate line; "
                    + "coefficient followed by power.");
            System.out.println("For example, 3 4 represents the term 3x^4.");
            System.out.println("Enter -1 -1 to end input for the polynomial.");

            int inputCoef2 = keyboard.nextInt();
            int inputPower2 = keyboard.nextInt();
            Polynomial polynomial2 = new Polynomial();

            while (inputCoef2 != -1 || inputPower2 != -1)
            {
                try
                {
                    polynomial2.changeCoefficient(inputCoef2, inputPower2);
                    inputCoef2 = keyboard.nextInt();
                    inputPower2 = keyboard.nextInt();
                }
                catch (InvalidPowerExceptions e)
                {
                    System.out.println("Error: cannot have negative power.\n"
                            + "Please re-enter term and continue.");
                    inputCoef2 = keyboard.nextInt();
                    inputPower2 = keyboard.nextInt();
                }
            }
            System.out.println("Second polynomial: " + polynomial2);
            System.out.println();

            // Add the polynomials.
            System.out.println("First polynomial:   " + polynomial);
            System.out.println("Second polynomial:  " + polynomial2);
            Polynomial sum = polynomial.add(polynomial2);
            System.out.println("Sum:                " + sum);

        } // end of try (Scanner...
    } // end of main()
}

// ***************** Polynomial class ***************************
// Represents ADT(Abstract Data Type) polynomial.
class Polynomial
{
    private static final int MAX_DEGREE = 49;
    private int[] coefficients; // coefficients of this polynomial.
    private int degree; // the degree of this polynomial.

    public Polynomial()
    {
        coefficients = new int[MAX_DEGREE + 1];
        degree = 0;
    }

    public int degree()
    // Returns the degree of a polynomial.
    // Pre: The maximum degree of a polynomial is MAX_DEGREE.
    // Consider only polynomials whose exponents are nonnegative
    // integers.
    // Post: Returns the degree of a polynomial.
    {
        return degree;
    }

    public int getCoefficient(int power) throws InvalidPowerExceptions
    // Returns the coefficient of the x^power term.
    // Pre: 0 <= power <= MAX_DEGREE
    // Post: Returns the degree of a polynomial.
    {
        if (power >= 0 && power <= MAX_DEGREE)
        {
            return coefficients[power];
        }
        else
        {
            throw new InvalidPowerExceptions(
                    "InvalidPowerExceptions on getCoefficient().");
        }
    }

    public void changeCoefficient(int newCoef, int power)
            throws InvalidPowerExceptions
    // Replaces the coefficient of the x^power term
    // with newCoef.
    // Pre: 0 <= power <= MAX_DEGREE
    // Post: Produces a new polynomial with newCoef.
    {
        if (power >= 0 && power <= MAX_DEGREE)
        {
            coefficients[power] = newCoef;
        }
        else
        {
            throw new InvalidPowerExceptions(
                    "InvalidPowerExceptions on changeCoefficient().");
        }

        // Determine the degree of this polynomial.
        if (degree < power)
        {
            degree = power;
        }
    }

    public Polynomial add(Polynomial addend)
    // Computes the sum of this polynomial and addend polynomial.
    // Pre: 0 <= degree <= MAX_DEGREE
    // Post: Returns an object of class Polynomial representing
    // the sum of this polynomial and addend polynomial.
    {
        Polynomial sum = new Polynomial();
        degree = this.degree() > addend.degree() ? this.degree() : addend.degree();

        try
        {
            for (int i = 0; i <= degree; i++)
            {
                sum.changeCoefficient(
                        this.getCoefficient(i) + addend.getCoefficient(i), i);
            }
        }
        catch (InvalidPowerExceptions e)
        {
            System.out.println("InvalidPowerExceptions on add().");
        }

        return sum;
    }

    @Override
    public String toString()
    // Display the polynomial.
    // Pre: 0 <= degree <= MAX_DEGREE
    // Post: Returns the String of formatted polynomial, such as
    // "- 5x^3 + 4x - 2".
    {
        String pString = "";
        int coefficient;

        try
        {
            for (int power = degree; power >= 0; power--)
            {
                coefficient = getCoefficient(power);

                // Don't display terms if the coefficient is 0.
                if (coefficient != 0)
                {
                    // Don't display "+" if coefficient is negative.
                    // Don't display a preceding "+" at the first term.
                    if (coefficient < 0)
                    {
                        pString += " - ";
                    }
                    else if (power == degree)
                    {
                        pString += "";
                    }
                    else
                    {
                        pString += " + ";
                    }

                    // Don't display the coefficient if it's 1 or -1.
                    // Display the coefficient even if it's 1 or -1
                    // when power is 0.
                    if (Math.abs(coefficient) != 1 || power == 0)
                    {
                        pString += Math.abs(coefficient) + "";
                    }

                    // Don't display x^0 and ^1.
                    if (power > 1)
                    {
                        pString += "x^" + power;
                    }
                    else if (power == 1)
                    {
                        pString += "x";
                    }

                } // end of if (coefficient != 0)...
            } // end of for (int power = degree;...
        }
        catch (InvalidPowerExceptions e)
        {
            e.printStackTrace();
        }

        return pString;
    }
}

// ***************** InvalidPowerExceptions class ***************
// This exception is thrown when user inputs negative power.
class InvalidPowerExceptions extends Exception
{
    InvalidPowerExceptions(String msg)
    // Sets up the exception object with a particular message.
    {
        super(msg);
    }
}
