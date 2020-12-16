package com.company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMI extends UnicastRemoteObject implements Calculator {

    public RMI() throws RemoteException{
        double firstNumber, secondNumber;
    }

    public double addition(double firstNumber, double secondNumber) throws RemoteException {
        return firstNumber + secondNumber;
    }
    public double subtraction(double firstNumber, double secondNumber) throws RemoteException {
        return firstNumber - secondNumber;
    }
    public double multiplication(double firstNumber, double secondNumber) throws RemoteException {
        return firstNumber * secondNumber;
    }
    public double division(double firstNumber, double secondNumber) throws RemoteException {
        return firstNumber / secondNumber;
    }
}
