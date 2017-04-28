package voigaittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketClient {
    

    static public void start() {

        try {
            System.out.println("Welcome to Client side");
            Socket fromserver = null;
            System.out.println("Connecting to... ");
            fromserver = new Socket("localhost", 4444);
            BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
            PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);
            BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));
            String fuser, fserver;
            while ((fuser = inu.readLine()) != null) {
                out.println(fuser);
                fserver = in.readLine();
                System.out.println(fserver);
                if (fuser.equalsIgnoreCase("close")) {
                    break;
                }
                if (fuser.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            out.close();
            in.close();
            inu.close();
            fromserver.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
