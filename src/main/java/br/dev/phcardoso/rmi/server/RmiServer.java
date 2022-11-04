package br.dev.phcardoso.rmi.server;

import br.dev.phcardoso.rmi.server.database.PostgresConnection;
import br.dev.phcardoso.rmi.server.database.dao.DatabaseOperations;
import br.dev.phcardoso.rmi.server.model.ResponseGenerated;
import br.dev.phcardoso.rmi.server.model.UserData;
import br.dev.phcardoso.rmi.services.ChatService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
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
        if (!connectedClients.containsKey(clientUuid)) {
            connectedClients.put(clientUuid, new UserData(clientUuid, RemoteServer.getClientHost()));
        }

        ResponseGenerated responseRow = ResponseGenerator.getResponse(clientMessage);

        if (responseRow != null) {
            connectedClients.get(clientUuid).incrementSuccessfulRequests();
            System.out.println("\nClient " + clientUuid + " sent a message: " + clientMessage);
            System.out.print("Response generated: " + responseRow.getAnswer());
            System.out.printf("Similarity: %.2f%%\n\n", responseRow.getSimilarity() * 100);
            return responseRow.getAnswer();
        } else {
            connectedClients.get(clientUuid).incrementUnsuccessfulRequests();
            connectedClients.get(clientUuid).addNotFoundMessage(clientMessage);
            return "Ainda não sei falar sobre isso... Tente perguntar de outra maneira";
        }
    }

    @Override
    public void quit(UUID clientUuid) throws RemoteException {
        connectedClients.remove(clientUuid);

        if (connectedClients.isEmpty()) {
            UnicastRemoteObject.unexportObject(this, false);

            new Thread(() -> {
                System.out.print("\nShutting down...\n");
                PostgresConnection.closeConnection();
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
        DatabaseOperations.insertConnection(connectedClients.get(clientUuid));
        DatabaseOperations.insertNotFoundQuestions(
                clientUuid,
                connectedClients.get(clientUuid).getNotFoundMessagesList()
        );

        return connectedClients.get(clientUuid).getReport();
    }

    public static void main(String[] args) {
        try {
            Connection postgresConnection = PostgresConnection.getConnection();
            if (postgresConnection != null) {
                ChatService chatService = new RmiServer();
                LocateRegistry.createRegistry(1099);
                Naming.rebind(RmiServer.objName, chatService);
                System.out.println("Server is running...");
            } else {
                System.out.println("Error connecting to database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
