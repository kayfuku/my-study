//**************************************************************
//  Purpose   : This program has iterative and recursive methods
//              and compares their efficiencies. 
//  Author    : Fukutani, Kei
//  Date      : February 10, 2015
//  File Name : RecursionEfficiency.java 
//**************************************************************

public class RecursionEfficiency
{
    private static int numOfMultiply = 0;
    private static int numOfRecursion = 0;

    public static void main(String[] args)
    {
        // Test for the method power1.
        System.out.println("power1: ");
        System.out.println("3^32 = " + power1(3, 32));
        System.out.println("Number of multiplications(3^32): " + numOfMultiply);
        numOfMultiply = 0;
        System.out.println("3^19 = " + power1(3, 19));
        System.out.println("Number of multiplications(3^19): " + numOfMultiply);
        System.out.println();

        // Test for the method power2.
        System.out.println("power2: ");
        numOfMultiply = 0;
        System.out.println("3^32 = " + power2(3, 32));
        System.out.println("Number of multiplications(3^32): " + numOfMultiply);
        System.out.println("Number of recursive calls(3^32): " + numOfRecursion);
        numOfMultiply = 0;
        numOfRecursion = 0;
        System.out.println("3^19 = " + power2(3, 19));
        System.out.println("Number of multiplications(3^19): " + numOfMultiply);
        System.out.println("Number of recursive calls(3^19): " + numOfRecursion);
        System.out.println();

        // Test for the method power3.
        System.out.println("power3: ");
        numOfMultiply = 0;
        numOfRecursion = 0;
        System.out.println("3^32 = " + power3(3, 32));
        System.out.println("Number of multiplications(3^32): " + numOfMultiply);
        System.out.println("Number of recursive calls(3^32): " + numOfRecursion);
        numOfMultiply = 0;
        numOfRecursion = 0;
        System.out.println("3^19 = " + power3(3, 19));
        System.out.println("Number of multiplications(3^19): " + numOfMultiply);
        System.out.println("Number of recursive calls(3^19): " + numOfRecursion);
        System.out.println();

        // Test for the method power4.
        System.out.println("power4: ");
        numOfMultiply = 0;
        numOfRecursion = 0;
        System.out.println("3^32 = " + power4(3, 32));
        System.out.println("Number of multiplications(3^32): " + numOfMultiply);
        System.out.println("Number of recursive calls(3^32): " + numOfRecursion);
        numOfMultiply = 0;
        numOfRecursion = 0;
        System.out.println("3^19 = " + power4(3, 19));
        System.out.println("Number of multiplications(3^19): " + numOfMultiply);
        System.out.println("Number of recursive calls(3^19): " + numOfRecursion);
        System.out.println();
    }

    private static long power1(int x, int n)
    // Computes x^n in an iterative way.
    // Pre: n >= 0
    // Post: Returns the result of x^n.
    {
        long result = 1;
        for (int i = 0; i < n; i++)
        {
            result *= x;
            numOfMultiply++;
        }

        return result;
    }

    private static long power2(int x, int n)
    // Computes x^n in a recursive way.
    // Pre: n >= 0
    // Post: Returns the result of x^n.
    {
        if (n <= 0)
        {
            return 1;
        }
        else
        {
            numOfMultiply++;
            numOfRecursion++;
            return x * power2(x, n - 1);
        }
    }

    private static long power3(int x, int n)
    // Computes x^n in a recursive way using different ways
    // depending on whether n is even or odd.
    // Pre: n >= 0
    // Post: Returns the result of x^n.
    {
        if (n <= 0)
        {
            return 1;
        }
        else if (n % 2 == 0)
        {
            numOfMultiply++;
            numOfRecursion += 2;
            return power3(x, n / 2) * power3(x, n / 2);
        }
        else
        {
            numOfMultiply += 2;
            numOfRecursion += 2;
            return x * power3(x, (n - 1) / 2) * power3(x, (n - 1) / 2);
        }
    }

    private static long power4(int x, int n)
    // Computes x^n in a recursive way using different ways
    // depending on whether n is even or odd.
    // The most efficient way!
    // Pre: n >= 0
    // Post: Returns the result of x^n.
    {
        if (n <= 0)
        {
            return 1;
        }
        else if (n % 2 == 0)
        {
            numOfMultiply++;
            numOfRecursion++;
            long partialResult = power4(x, n / 2);
            return partialResult * partialResult;
        }
        else
        {
            numOfMultiply += 2;
            numOfRecursion++;
            long partialResult = power4(x, (n - 1) / 2);
            return x * partialResult * partialResult;
        }
    }
}
