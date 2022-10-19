package br.dev.phcardoso.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface ChatService extends Remote {
    String sendMessage(String clientMessage) throws RemoteException, ServerNotActiveException;
    void quit() throws RemoteException;
    String sendServerReport() throws RemoteException, ServerNotActiveException;
}
