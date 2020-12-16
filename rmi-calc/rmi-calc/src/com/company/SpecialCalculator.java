package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpecialCalculator extends Remote{
    double pow(double firstNumber, double secondNumber) throws RemoteException;
    double root(double firstNumber, double secondNumber) throws RemoteException;
    double percentage(double firstNumber, double secondNumber) throws RemoteException;
}
