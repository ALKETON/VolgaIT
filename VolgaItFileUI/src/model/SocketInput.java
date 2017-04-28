package model;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import presenter.Presenter;

interface SockIn {

    Presenter getPresenter();

    void setPresenter(Presenter presenter);

}

public class SocketInput implements Runnable, SockIn {

    private DataInputStream dInStream;
    private Presenter presenter;

    public void setSocket(Socket fromServer) {
        try {
            dInStream = new DataInputStream(fromServer.getInputStream());
            start();
        } catch (IOException ex) {
            Logger.getLogger(SocketInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    String serverOut;
                    while ((serverOut = dInStream.readUTF()) != null) {
                        //Получаем данные с сервера
                        System.out.println("Server: " + serverOut);
                        presenter.setNewComand(serverOut);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(SocketInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    @Override
    public void run() {
        try {
            String serverOut;
            while ((serverOut = dInStream.readUTF()) != null) {
                //Получаем данные с сервера
                System.out.println("Server: " + serverOut);
                presenter.setNewComand(serverOut);

            }
        } catch (IOException ex) {
            Logger.getLogger(SocketInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
