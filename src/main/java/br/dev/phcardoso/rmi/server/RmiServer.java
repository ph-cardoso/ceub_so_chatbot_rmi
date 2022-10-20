package br.dev.phcardoso.rmi.server;

import br.dev.phcardoso.rmi.server.model.UserData;
import br.dev.phcardoso.rmi.services.ChatService;
import org.json.simple.JSONObject;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

public class RmiServer extends UnicastRemoteObject implements ChatService {
    private static HashMap<UUID, UserData> connectedClients;
    private final static String objName = "rmi://localhost/ChatService";
    protected RmiServer() throws RemoteException {
        super();
        connectedClients = new HashMap<>();
    }

    @Override
    public String sendMessage(String clientMessage, UUID clientUuid) throws RemoteException, ServerNotActiveException {
        JSONObject responseObject = ResponseGenerator.generateResponse(clientMessage);
        if (responseObject.containsKey("notFound")) {
            if (!connectedClients.containsKey(clientUuid)) {
                connectedClients.put(clientUuid, new UserData(clientUuid, RemoteServer.getClientHost()));
            }
            connectedClients.get(clientUuid).incrementUnsuccessfulRequests();
            connectedClients.get(clientUuid).addNotFoundMessage(clientMessage);
            return (String) responseObject.get("notFound");
        } else {
            if (!connectedClients.containsKey(clientUuid)) {
                connectedClients.put(clientUuid, new UserData(clientUuid, RemoteServer.getClientHost()));
            }
            connectedClients.get(clientUuid).incrementSuccessfulRequests();
            return (String) responseObject.get("resposta");
        }
    }

    @Override
    public void quit(UUID clientUuid) throws RemoteException {
        connectedClients.remove(clientUuid);

        if (connectedClients.isEmpty()) {
            UnicastRemoteObject.unexportObject(this, false);

            new Thread(() -> {
                System.out.print("\nShutting down...\n");
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Done");
                System.exit(0);
            }).start();
        }
    }

    @Override
    public String sendServerReport(UUID clientUuid) throws RemoteException {
        return connectedClients.get(clientUuid).getReport();
    }

    public static void main(String[] args) {
        try {
            ChatService chatService = new RmiServer();

            LocateRegistry.createRegistry(1099);

            Naming.rebind(RmiServer.objName, chatService);
            System.out.println("Server is ready");
            System.out.println("Waiting for invocations from clients...\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
