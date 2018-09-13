package Client;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by ronnie on 18-May-17.
 */
public class Client {
    private InetAddress IP;
    private int port;
    private IClientStrategy clientStrategy;

    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.IP = IP;
        this.port = port;
        this.clientStrategy = clientStrategy;
    }

    public void start(){
        try {
            Socket theServer = new Socket(IP, port);
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(theServer.getInputStream(),theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}