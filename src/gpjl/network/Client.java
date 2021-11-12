package gpjl.network;

import java.io.IOException;
import java.net.Socket;

/**
 * The {@code Client} class represents a Client, that can connect to an open {@code ServerSocket}.
 * 
 * @author  Gereon
 * @see     gpjl.network.Endpoint
 * @since   0.1
 * @version 1.0
 */
public abstract class Client extends Endpoint {
    
    /**
     * The hostname that the {@code Client} tries to connect to.
     */
    private final String hostname;

    /**
     * Initializes the hostname.
     * 
     * @param hostname The hostname {@code String}
     */
    public Client(String hostname) {
        this.hostname = hostname;
    }

    @Override
    protected Socket connectSocket(int port) throws IOException {
        return new Socket(hostname, port);
    }

}
