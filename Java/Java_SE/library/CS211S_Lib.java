// CS211S Library.


package library;

public class CS211S_Lib {
	
	
	
	
	
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
	// display error message.
	public static void die(String... msg)
	{
		if (msg.length == 0) System.exit(1);
		System.err.println(msg[0]);
		System.exit(1);
	}
	
	
	
	

}
