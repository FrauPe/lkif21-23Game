package gpjl.network;

/**
 * The {@code DisconnectedException} should be called as a break-out of a controlled read-Thread.
 * 
 * @author  Gereon
 * @see     gpjl.network.Endpoint
 * @since   0.1
 * @version 1.0
 */
public class DisconnectedException extends Exception {
    
    /**
     * Constructs a {@code DisconnectedException}.
     */
    public DisconnectedException() {
        super();
    }

}
