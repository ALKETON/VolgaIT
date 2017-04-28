package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketOutput {

    
    DataOutputStream out;
            
            
    public void setSOcket(Socket fromServer){
        try {
            out = new DataOutputStream(fromServer.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SocketOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void send(String command){
        if(out != null){
            try {
                out.writeUTF(command);
            } catch (IOException ex) {
                Logger.getLogger(SocketOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
