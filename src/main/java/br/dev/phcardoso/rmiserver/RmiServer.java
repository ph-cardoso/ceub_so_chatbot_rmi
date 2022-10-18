package br.dev.phcardoso.rmiserver;

import br.dev.phcardoso.rmiinterface.ChatService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements ChatService {
    private final static String objName = "rmi://localhost/ChatService";
    protected RmiServer() throws RemoteException {
    }

    @Override
    public String sendMessage(String clientMessage) throws RemoteException, ServerNotActiveException {
        return "Server says hello to " + clientMessage + "!\n" + RemoteServer.getClientHost();
    }

    @Override
    public void quit() throws RemoteException {
        System.out.println("quit");
        UnicastRemoteObject.unexportObject(this, false);

        new Thread(() -> {
            System.out.print("Shutting down...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("done");
            System.exit(0);
        }).start();
    }

    public static void main(String[] args) {
        try {
            ChatService chatService = new RmiServer();

            LocateRegistry.createRegistry(1099);

            Naming.rebind(RmiServer.objName, chatService);
            System.out.println("Server is ready");
            System.out.println("Waiting for invocations from clients...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
