package com.company;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class BasicRMI {

    public static void main(String[] args) {
        try{
            Registry registry = java.rmi.registry.LocateRegistry.createRegistry(3325);
            BasicIMPL basicIMPL = new BasicIMPL();
            registry.rebind("Basic", basicIMPL);
        }catch (RemoteException exception) {
            exception.getStackTrace();
        }
    }
}
