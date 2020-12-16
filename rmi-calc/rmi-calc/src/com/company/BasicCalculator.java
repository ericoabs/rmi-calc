package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface BasicCalculator extends Remote{
    double addition(double firstNumber, double secondNumber) throws RemoteException;
    double subtraction(double firstNumber, double secondNumber) throws RemoteException;
    double multiplication(double firstNumber, double secondNumber) throws RemoteException;
    double division(double firstNumber, double secondNumber) throws RemoteException;
}
