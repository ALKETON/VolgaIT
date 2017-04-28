package volgaitclient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketInput implements Runnable {

    Socket sClient;

    public SocketInput(Socket sClient) {
        this.sClient = sClient;
    }

    @Override
    public void run() {
        try {
            DataOutputStream dOutStream = new DataOutputStream(sClient.getOutputStream());

            /*while (true) {
                // Считываем с клавитатуры.
                if ((commandIn = systemIn.nextLine()) != null) {
                    System.out.println("Client: " + commandIn);
                    //Command command = new Command(commandInput); // Можно создать класс с командами. Туда отправлять команду и она будет выдовать обратно ответ который нужно отпраивт на сервер
                    dOutStream.writeUTF(commandIn.toString());
                    commandIn = null;
                }

            }*/

        } catch (IOException ex) {
            Logger.getLogger(SocketInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
