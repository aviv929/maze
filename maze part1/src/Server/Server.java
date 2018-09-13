package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ronnie on 18-May-17.
 */
public class Server {
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop; // volatile=diffrent threads will use this value so we read it from ram and not from cache
    private ExecutorService threadPool;

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy)
    {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    public void start()
    {
        threadPool.execute(()-> { run(); });
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(listeningInterval);
            System.out.println("Server started!");
            while (!stop) {
                try {
                    Socket aClient = server.accept(); // blocking call
                    threadPool.execute(()-> { handleClient(aClient); });
                } catch (SocketTimeoutException e) {
                    //System.out.println("Server Socket Timeout Exception" + e.getMessage());
                }
            }
            server.close();
        } catch (IOException e) {
            System.out.println("Server second IO Exception: " + e.getMessage());
        }
    }

    private void handleClient(Socket aClient) {
        try {
            System.out.println("Client excepted!");
            System.out.println(String.format("Handling client with socket: %s", aClient.toString()));
            serverStrategy.serverStrategy(aClient.getInputStream(), aClient.getOutputStream());
            aClient.getOutputStream().close();
            //aClient.getInputStream().close(); // throes exception
            aClient.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void stop() {
        System.out.println("Stopping server..");
        stop = true;
        threadPool.shutdown();
    }
}
