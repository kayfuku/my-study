//**************************************************************
//  Author    : Kei Fukutani
//  File Name : HappyGuy.java
//  Date      : 5/17/14
//  Homework assignment : Q2
//  Objective : This represents personal information, such as 
//              name, phone number, email address, and birth date.  
//**************************************************************

package com.example.sendbirthdaywish;

public class HappyGuy 
{
	private String name;
	private String sms;
	private String email;
	private String birthDate;
	
	public HappyGuy()
	{
		name = new String("");
		sms = new String("");
		email = new String("");
		birthDate = new String("");		
	}
	
	public HappyGuy(String n, String s, String e, String b)
	{
		name = new String(n);
		sms = new String(s);
		email = new String(e);
		birthDate = new String(b);		
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSMS()
	{
		return sms;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getBirthDate()
	{
		return birthDate;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setSMS(String s)
	{
		sms = s;
	}
	
	public void setEmail(String e)
	{
		email = e;
	}
	
	public void setBirthDate(String b)
	{
		birthDate = b;
	}
}
