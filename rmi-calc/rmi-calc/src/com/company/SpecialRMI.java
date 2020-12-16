package com.company;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class SpecialRMI {

    public static void main(String[] args) {
        try{
            Registry registry = java.rmi.registry.LocateRegistry.createRegistry(3335);
            SpecialIMPL specialIMPL = new SpecialIMPL();
            registry.rebind("Special", specialIMPL);
        }catch (RemoteException exception) {
            exception.getStackTrace();
        }
    }
}
