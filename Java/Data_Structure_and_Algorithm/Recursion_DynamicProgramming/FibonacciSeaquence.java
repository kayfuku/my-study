// Cumputes the Fibonacci number in two ways. 
// One is recursive solution and the other is iterative solution, 
// which is more efficient. 
// (By CS111C)

package cs111c;

public class FibonacciSeaquence
{

    public static void main(String[] args)
    {
        int result = recursiveFibonacci(10);
        System.out.println(result);
        
        result = iterativeFibonacci(10);
        System.out.println(result);
        

    }
    
    // This method is inefficient.
    // Pre: n > 0
    public static int recursiveFibonacci(int n)
    {
        if (n <= 2)
        {
            return 1;            
        }
        else
        {
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
        }
    }
    
    // This method is more efficient.
    // Pre: n > 0
    public static int iterativeFibonacci(int n)
    {
        int secondTolast = 1;
        int last = 1;
        int next = 0;
        for (int i = 3; i <= n; i++)
        {
            next = secondTolast + last;
            secondTolast = last;
            last = next;           
        }
        
        return next;        
    }    
}
