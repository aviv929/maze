package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ronnie on 18-May-17.
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient);
}
