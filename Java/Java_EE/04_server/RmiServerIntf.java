//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : RmiServerIntf.java
//  Date      : March 5, 2015
//  Objective : This is a remote interface. The client uses RMI
//              to call the methods in this interface which is 
//              implemented in the remote object in the server.
//  Java version : 1.8.0_20
//**************************************************************

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerIntf extends Remote
{
    String[] states(String regex) throws RemoteException;
    String[] capitals(String regex) throws RemoteException;
    String getCapital(String state) throws RemoteException;
    String getState(String capital) throws RemoteException;
}
