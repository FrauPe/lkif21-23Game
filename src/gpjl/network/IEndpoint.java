package gpjl.network;

public interface IEndpoint {

    /**
     * Handles an incoming {@code String}.
     * 
     * @param string A {@code String} that has been sent by the other {@code Endpoint}
     */
    void readString(String string);

    /**
     * Handles an incoming {@code Object} that implements {@code Serializable}.
     * 
     * @param object A {@code Object} that has been sent by the other {@code Endpoint}
     */
    void readObject(Object object);

    /**
     * Is called when the other {@code Endpoint} sent {@code DISCONNECTED}.
     */
    void onDisconnect();
    
}
