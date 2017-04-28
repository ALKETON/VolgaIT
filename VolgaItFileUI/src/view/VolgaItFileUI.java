package view;

import java.net.Socket;
import model.SocketInput;
import model.SocketOutput;
import presenter.Presenter;
import volgaitfileui.Menu;

public class VolgaItFileUI {

    public static void main(String[] args) {
        // TODO code application logic here
        
        SocketInput si = new SocketInput();
        SocketOutput so = new SocketOutput();
        Menu menu = new Menu();
        Presenter logik = new Presenter(si,so,menu);
        menu.show();
    }
    
}
