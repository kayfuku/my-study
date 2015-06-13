// CS211S Library.
// Version 1.3


package library;

import java.util.Scanner;


public class CS211S_Lib 
{
	
	
	
	
	
	//-------------myPrintln()-----------------------------------------
	// Print a line. CS211S.
	public static void myPrintln(Object... o)
	{
		for (int i = 0; i < o.length; i++) 
		{
			System.out.print(o[i] + " ");
		}
		System.out.println();
	}
	
	//-------------sleep()-----------------------------------------
	// To simplify Thread.sleep(). CS211S.
	public static void sleep(long msec)
	{
		try
		{
			Thread.sleep(msec);
		} catch (InterruptedException e){}		
	}
	
	//-------------die()-------------------------------------------
	// display error message. CS211S.
	public static void die(String... msg)
	{
		if (msg.length == 0) System.exit(1);
		System.err.println(msg[0]);
		System.exit(1);
	}
	
	//-------------askYN()--------------------------------------
    // Ask user if the user want to continue. 
	@SuppressWarnings("resource")
	public static boolean askYN()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Continue(Y/N)?");
		while (true)
		{
			char ans = Character.toUpperCase(scanner.nextLine().charAt(0));
			if (ans == 'Y' || ans == 'N') return (ans == 'Y');
			System.err.println("Error: y or n only.");
		}
	}
	
	//------------- randomInRange() ----------------------------
	// Returns the random whole number between low and high inclusive.
	public int randomInRange(int low, int high)
	{
	   return ((int) (Math.random() * (high - low + 1)) + low);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
