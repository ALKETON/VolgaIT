package voigaittest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServ {

    public void start() {
        try {
            System.out.println("Welcome to Server side");

            ServerSocket servers = null;
            Socket fromclient = null;

            // create server socket
            try {
                servers = new ServerSocket(4444);
            } catch (IOException e) {
                System.out.println("Couldn't listen to port 4444");
                System.exit(-1);
            }

            try {
                System.out.print("Waiting for a client...");
                fromclient = servers.accept();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println("Can't accept");
                System.exit(-1);
            }

            DataOutputStream out = new DataOutputStream(fromclient.getOutputStream());
            DataInputStream in = new DataInputStream(fromclient.getInputStream());

            String input, output;
            out.writeUTF("Connected");

            System.out.println("Wait for messages");

            while ((input = in.readUTF()) != null) {
                System.out.println("Command = " + input);
                /*Command command = getCommandFromInput(input);
                if (command.getName() == CommandName.CLOSE_CONNECTION) {
                    System.exit(1);
                }
                CommandResult executionResult = command.execute();
                 */
                if (input.equals("File")) {
                    String list[] = new File("C:\\Android").list();
                    String fillList = "FILE: ";
                    for (int i = 0; i < list.length; i++) {
                        System.out.println(list[i]);
                        fillList+= list[i] + ", ";
                    }
                    out.writeUTF(fillList); //Отправляем ответ
                }

                out.writeUTF(input); //Отправляем ответ
                out.flush();
            }

            out.close();
            in.close();
            fromclient.close();
            servers.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
