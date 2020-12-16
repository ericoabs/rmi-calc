package com.company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SpecialIMPL extends UnicastRemoteObject implements SpecialCalculator {

    public SpecialIMPL() throws RemoteException{
        double firstNumber, secondNumber;
    }

    @Override
    public double pow(double firstNumber, double secondNumber) throws RemoteException {
        return Math.pow(firstNumber, secondNumber);
    }

    @Override
    public double root(double firstNumber, double secondNumber) throws RemoteException {
        return Math.pow(firstNumber, 1/secondNumber);
    }

    @Override
    public double percentage(double firstNumber, double secondNumber) throws RemoteException {
        return secondNumber * (firstNumber / 100);
    }
}
