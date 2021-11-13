package gpjl.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

/**
 * The {@code Endpoint} class represents an Endpoint of a Socket-Connection.
 * 
 * @author  Gereon
 * @see     gpjl.network.DisconnectedException
 * @see     gpjl.network.Client
 * @see     gpjl.network.Server
 * @since   0.1
 * @version 1.1
 */
public abstract class Endpoint {
    
    /**
     * Sending the Value {@value} indicates that nothing will be sent anymore.
     */
    public final static byte DISCONNECT = -1;

    /**
     * Sending the Value {@value} indicates that a String will be sent.
     */
    public final static byte STRING = 0;

    /**
     * Sending the Value {@value} indicates that a serializable Object will be sent.
     */
    public final static byte OBJECT = 1;


    /**
     * The {@code Socket} that's beeing used to connect and transfer data.
     */
    private Socket socket;

    /**
     * The {@code ObjectOutputStream} that's beeing written to.
     */
    private ObjectOutputStream out;


    /**
     * Connects a Socket to some other Socket.
     * 
     * @param port The port {@code int}
     * @return A connected {@code Socket}
     * @throws IOException If building the Connection failed
     */
    protected abstract Socket connectSocket(int port) throws IOException;

    /**
     * Handles an incoming {@code String}.
     * 
     * @param string A {@code String} that has been sent by the other {@code Endpoint}
     */
    protected abstract void readString(String string);

    /**
     * Handles an incoming {@code Object} that implements {@code Serializable}.
     * 
     * @param object A {@code Object} that has been sent by the other {@code Endpoint}
     */
    protected abstract void readObject(Object object);

    /**
     * Is called when the other {@code Endpoint} sent {@code DISCONNECTED}.
     */
    protected abstract void onDisconnect();


    /**
     * Connects using {@code connectSocket()} and sets up IOStreams.
     * 
     * @param port The port {@code int}
     * @return {@code true} if building the Connection succeeded, {@code false} if it failed
     */
    public boolean connect(int port) {
        disconnect();
        try {
            socket = connectSocket(port);
            out = new ObjectOutputStream(socket.getOutputStream());
            startReadThread();
            return true;
        } catch(IOException e) {
            return false;
        }
    }

    /**
     * Disconnects the socket.
     */
    public void disconnect() {
        if(socket == null || socket.isClosed())
            return;
        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends {@code DISCONNECT} to notify the other {@code Endpoint} and calls {@code disconnect()}.
     */
    public void sendEnd() {
        send(DISCONNECT, null);
        disconnect();
    }
    
    /**
     * Sends a {@code String} to the other {@code Endpoint}.
     * 
     * @param string A {@code String} that will be sent.
     */
    public void sendString(String string) {
        send(STRING, string);
    }

    /**
     * Sends a {@code Object} to the other {@code Endpoint}.
     * 
     * @param <T> The class of the Object which has to implement {@code Serializable}
     * @param obj A {@code Object} that will be sent.
     */
    public <T extends Serializable> void sendObject(T obj) {
        send(OBJECT, obj);
    }

    /**
     * Sends data to the other {@code Endpoint}.
     * 
     * @param <T> The class of the Object which has to implement {@code Serializable}
     * @param type The type-info {@code byte} that indicates the following data
     * @param obj A {@code Object} that will be sent, if {@code type} is not {@code DISCONNECT}
     */
    private <T extends Serializable> void send(byte type, T obj) {
        if(!isConnected())
            return;
        try {
            out.writeByte(type);
            switch(type) {
                case STRING -> out.writeUTF((String) obj);
                case OBJECT -> out.writeObject(obj);
            }
            out.flush();
        } catch(SocketException e) {    //Unexpected Error (e.g. crash)
            disconnect();
            onDisconnect();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a {@code Thread} that handles incoming data.
     */
    private void startReadThread() {
        new Thread(() -> {
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                while(true) {
                    byte type = in.readByte();
                    switch (type) {
                        case STRING -> readString(in.readUTF());
                        case OBJECT -> readObject(in.readObject());
                        default -> throw new DisconnectedException();
                    }
                }
            } catch(DisconnectedException e) {
                disconnect();
                onDisconnect();
            } catch(SocketException e) {    //get's thrown after {@code sendEnd()} has been called
            } catch(IOException | ClassNotFoundException e) {   //Unexpected Error (e.g. crash)
                disconnect();
                onDisconnect();
            }
        }).start();
    }

    /**
     * Checkst the Connection-State using {@code !Socket.isClosed()}.
     * 
     * @return {@code true} if {@code Socket} isn't closed, {@code false} if it is
     */
    public boolean isConnected() {
        return !(socket == null || socket.isClosed());
    }

}
