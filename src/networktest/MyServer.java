/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package networktest;
import gpjl.network.*;

/**
 *
 * @author pardella
 */
public class MyServer extends Server {

    /**
     * Handles an incoming {@code String}.
     * 
     * @param string A {@code String} that has been sent by the other {@code Endpoint}
     */
    protected void readString(String string)
    {
        sendString("echo: "+string);
    }

    /**
     * Handles an incoming {@code Object} that implements {@code Serializable}.
     * 
     * @param object A {@code Object} that has been sent by the other {@code Endpoint}
     */
    protected void readObject(Object object)
    {
        if (object instanceof String) readString( (String) object);
        else 
        {
            sendString("ERR");
        }
    }

    /**
     * Is called when the other {@code Endpoint} sent {@code DISCONNECTED}.
     */
    protected void onDisconnect()
    {
        sendString("Verbindung beendet.");
    }
    
}
