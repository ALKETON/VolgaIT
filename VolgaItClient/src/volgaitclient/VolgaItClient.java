package volgaitclient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VolgaItClient {

    SocketOutput out;
    SocketInput in;

    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            //SocketOutput out = new SocketOutput();
            //SocketInput in = new SocketInput();
            start();
        } catch (IOException ex) {
            Logger.getLogger(VolgaItClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    static public void start() throws IOException {

        System.out.println("Welcome to Client side");
        Socket fromServer = null;
        System.out.println("Connecting to... ");
        fromServer = new Socket("localhost", 4444);

        DataInputStream dInStream = new DataInputStream(fromServer.getInputStream());
        DataOutputStream dOutStream = new DataOutputStream(fromServer.getOutputStream());

        Scanner systemIn = new Scanner(System.in);

        String commandIn = null, serverOut = null;

        while (true) {
            
            //Получаем данные с сервера
             if ((serverOut = dInStream.readUTF()) != null) {
                System.out.println("Server: " + serverOut);
                // Что то выводим или обрабатываем
            }
            
            // Считываем с клавитатуры.
            if ((commandIn = systemIn.nextLine()) != null) {
                System.out.println("Client: " + commandIn);
                //Command command = new Command(commandInput); // Можно создать класс с командами. Туда отправлять команду и она будет выдовать обратно ответ который нужно отпраивт на сервер
                dOutStream.writeUTF(commandIn.toString());
                commandIn = null;
            }

           

        }
    }
}
