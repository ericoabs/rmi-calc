package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Server extends Thread {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BasicCalculator basicCalculator;
    private SpecialCalculator specialCalculator;

    public Server(Socket socket){
        this.socket = socket;
        try {
            this.basicCalculator = (BasicCalculator) Naming.lookup("rmi://localhost:3325/Basic");
            this.specialCalculator = (SpecialCalculator) Naming.lookup("rmi://localhost:3335/Special");
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException | NotBoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String []args) {

        try{
            int port = 3315;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor ativo na porta: " + port);
            while(true){
                System.out.println("Aguardando conexão...");
                Socket connection = serverSocket.accept();
                System.out.println("Cliente conectado...");
                Thread thread = new Server(connection);
                thread.start();
            }

        }catch (Exception exception) {

            exception.printStackTrace();
        }
    }

    public void run(){

        try{
            OutputStream outputStream =  this.socket.getOutputStream();
            Writer outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            String request;

            while(true)
            {
                request = bufferedReader.readLine();
                String response = distributor(request);
                bufferedWriter.write(response + "\r\n");
                bufferedWriter.flush();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String distributor(String request) throws RemoteException {
        if (request.contains("%") | request.contains("^") | request.contains("_")) {
            System.out.println("Calculadora especial...");
            return special(request);
        } else if (request.contains("+") | request.contains("-") | request.contains("/") | request.contains("*")){
            System.out.println("Calculadora básica...");
            return basic(request);
        }
        return "";
    }

    private String basic(String request) throws RemoteException {
        request = request.trim();

        String[] elements = request.split(" ");

        double numberOne = Double.parseDouble(elements[0]);

        double numberTwo = Double.parseDouble(elements[2]);

        if (elements[1].equals("+")) {
            return "=>" + this.basicCalculator.addition(numberOne, numberTwo);
        }

        if (elements[1].equals("-")) {
            return "=> " + this.basicCalculator.subtraction(numberOne, numberTwo);
        }

        if (elements[1].equals("/")) {
            return "=> " + this.basicCalculator.division(numberOne, numberTwo);
        }

        if (elements[1].equals("*")) {
            return "=> " + this.basicCalculator.multiplication(numberOne, numberTwo);
        }

        return "Nom sei";
    }

    private String special(String request) throws RemoteException {
        request = request.trim();

        String[] elements = request.split(" ");

        double numberOne = Double.parseDouble(elements[0]);

        double numberTwo = Double.parseDouble(elements[2]);

        if (elements[1].equals("%")) {
            return "=>" + this.specialCalculator.percentage(numberOne, numberTwo);
        }

        if (elements[1].equals("_")) {
            return "=> " + this.specialCalculator.root(numberOne, numberTwo);
        }

        if (elements[1].equals("^")) {
            return "=> " + this.specialCalculator.pow(numberOne, numberTwo);
        }
        return "";
    }
}