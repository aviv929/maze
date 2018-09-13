package Client;

import java.io.*;

/**
 * Created by ronnie on 18-May-17.
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
