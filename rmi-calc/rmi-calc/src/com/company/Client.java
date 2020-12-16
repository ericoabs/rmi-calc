package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket basicSocket;
    private OutputStream basicOutputStream;
    private Writer basicOutputStreamWriter;
    private BufferedWriter basicBufferedWriter;
    private final Scanner scanner;

    public Client() {
        System.out.println("Como utilizar as operações:");
        System.out.println("Soma: n1 + n2");
        System.out.println("Subtração: n1 - n2");
        System.out.println("Multiplicação: n1 * n2");
        System.out.println("Divisão: n1 / n2");
        System.out.println("Exponenciação: n1 ^ n2");
        System.out.println("Raiz: n1 _ n2");
        System.out.println("Porcentagem: n1 % n2");
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException{

        Client client = new Client();
        client.connect();
        client.reader();
        client.listener();
        client.quit();
    }

    private void reader() throws IOException {
        System.out.println("Digite a expressão: ");
        String expression = scanner.nextLine();
        send(expression);
    }

    public void connect() throws IOException{

        basicSocket = new Socket("localhost", 3315);
        basicOutputStream = basicSocket.getOutputStream();
        basicOutputStreamWriter = new OutputStreamWriter(basicOutputStream);
        basicBufferedWriter = new BufferedWriter(basicOutputStreamWriter);
        basicBufferedWriter.flush();

    }

    public void send(String mensagem) throws IOException {

        basicBufferedWriter.write(mensagem + "\r\n");
        basicBufferedWriter.flush();

    }

    public void listener() throws IOException {

        InputStream basicInputStream = basicSocket.getInputStream();
        InputStreamReader basicInputStreamReader = new InputStreamReader(basicInputStream);
        BufferedReader basicBufferedReader = new BufferedReader(basicInputStreamReader);

        String message = "";

        while (!message.equalsIgnoreCase("sair")){

            if (basicBufferedReader.ready()) {

                message = basicBufferedReader.readLine();

                if (message.equalsIgnoreCase("sair")) {

                    System.out.println("Servidor caiu\r\n");

                } else {

                    System.out.println(message + "\r\n");

                }

                this.reader();
            }

        }

    }

    public void quit() throws IOException {
        System.out.println("Desconectado");
        basicBufferedWriter.close();
        basicOutputStreamWriter.close();
        basicOutputStream.close();
        basicSocket.close();
        System.exit(0);

    }
}
