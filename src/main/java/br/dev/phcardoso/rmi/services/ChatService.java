package br.dev.phcardoso.rmi.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.UUID;

public interface ChatService extends Remote {
    String sendMessage(String clientMessage, UUID clientUuid) throws RemoteException, ServerNotActiveException;
    void quit(UUID clientUuid) throws RemoteException;
    String sendServerReport(UUID clientUuid) throws RemoteException;
}
