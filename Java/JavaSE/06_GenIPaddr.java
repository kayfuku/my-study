//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : GenIPaddr.java
//  Date      : 11/24/14
//  Objective : This program generates 1000 unique random IP 
//              addresses and sorts them and displays the first 
//              and the last 10 IP addresses. Those numbers 
//              above are changeable.
//  Java version : 1.8.0_20
//**************************************************************

import java.net.InetAddress;
import java.util.Comparator;
import java.util.TreeSet;

public class GenIPaddr
{
	//------------- genRandAddr() ------------------------------
	// Generate random IP addresses. 
	private static String genRandAddr()
	{
		int[] n = new int[4];
	
		for (int i = 0; i < n.length; i++) 
		{
			n[i] = randomInRange(0, 255);
		}
		
		return (n[0] + "." + n[1] + "." + n[2] + "." + n[3]);
	}
	
	//------------- randomInRange() ----------------------------
	// Returns the random whole number between low and high inclusive.
	public static int randomInRange(int low, int high)
	{
	   return ((int)(Math.random() * (high - low + 1)) + low);
	}

	//------------- main() -------------------------------------
    // Program execution starts with this method. 
	public static void main(String[] args) throws Exception
	{
		String ipAddr;
		int FIRST_SET_NUM = 10;
		int LAST_SET_NUM = 10;
		int IPADDRESS_NUM = 1000;
		
		// Get the TreeSet instance which 
		// controls the order of IP addresses. 
		TreeSet<InetAddress> tSet = new TreeSet<>(new MyComparator());
		
		// Get 'IPADDRESS_NUM' unique random IP addresses. 
		while (tSet.size() != IPADDRESS_NUM) 
		{
			String adrString = genRandAddr();
			InetAddress adr = InetAddress.getByName(adrString);
			tSet.add(adr);
		} 

		System.out.println("----");
		
		// Display the first 'FIRST_SET_NUM' of the IP addresses. 
		System.out.println("First " + FIRST_SET_NUM + " IP addresses " + 
				           "out of " + IPADDRESS_NUM + ":");
		TreeSet<InetAddress> tSetCopy = new TreeSet<>(new MyComparator());
		if (FIRST_SET_NUM + LAST_SET_NUM > IPADDRESS_NUM)
		{
		    // Make a deep copy of 'tSet' and use it. 
			// Because otherwise pollFirst() can also remove the 
			// IP addresses that are close to the last. 
			tSetCopy.addAll(tSet);
		}
		for (int i = 0; i < FIRST_SET_NUM ; i++) 
		{
		    // Remove the first character '/'.
			if (FIRST_SET_NUM + LAST_SET_NUM > IPADDRESS_NUM)
			{
			    ipAddr = tSetCopy.pollFirst().toString().substring(1);
			}
			else
			{
			    ipAddr = tSet.pollFirst().toString().substring(1);
			}
			System.out.println(ipAddr);
		}
		
		System.out.println("----");
		
		// Display the last 'LAST_SET_NUM' of the IP addresses. 
		System.out.println("Last " + LAST_SET_NUM + " IP addresses " + 
				           "out of " + IPADDRESS_NUM + ":");
		for (int i = 0; i < LAST_SET_NUM ; i++) 
		{
		    // Remove the first character '/'.
			ipAddr = tSet.pollLast().toString().substring(1);
			System.out.println(ipAddr);
		}

		System.out.println("");
	} // end of main().
} // end of GenIPAddr class. 

//***************** MyComparator class *************************
// Use this class to define the order of IP addresses in TreeSet. 
class MyComparator implements Comparator<InetAddress>
{
	@Override
	//----- compare() ------------------------------------------
    public int compare(InetAddress adr1, InetAddress adr2) 
	{
        byte[] ba1 = adr1.getAddress();
        byte[] ba2 = adr2.getAddress();

        // General ordering: ipv4 before ipv6.
        if(ba1.length < ba2.length) return -1;
        if(ba1.length > ba2.length) return 1;

        // We have 2 IPs of the same type, so we have to 
		// compare each byte.
        for(int i = 0; i < ba1.length; i++) 
        {
            int b1 = unsignedByteToInt(ba1[i]);
            int b2 = unsignedByteToInt(ba2[i]);
            if(b1 == b2)
                continue;
            if(b1 < b2)
                return -1;
            else
                return 1;
        }
        return 0;
    }
	
	//----- unsignedByteToInt() --------------------------------
    private int unsignedByteToInt(byte b) 
    {
        return (int) b & 0xFF;
    }	
}
