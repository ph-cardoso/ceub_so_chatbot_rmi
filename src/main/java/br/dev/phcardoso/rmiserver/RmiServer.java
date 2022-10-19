package br.dev.phcardoso.rmiserver;

import br.dev.phcardoso.rmiinterface.ChatService;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.logging.Logger;

public class RmiServer extends UnicastRemoteObject implements ChatService {
    private static long successfulResponses = 0;
    private final Logger logger = LoggerInstance.getInstance();
    private final static String objName = "rmi://localhost/ChatService";
    protected RmiServer() throws RemoteException {
    }

    @Override
    public String sendMessage(String clientMessage) throws RemoteException, ServerNotActiveException {
        Map<Integer, String> response = ResponseGenerator.generateResponse(clientMessage);
        if (!response.containsKey(0)) {
            successfulResponses++;
        } else {
            logger.info("Client " + RemoteServer.getClientHost() + " sent a message that could " +
                    "not be understood: \"" + clientMessage + "\"");
        }
        return response.get(response.keySet().iterator().next());
    }

    @Override
    public void quit() throws RemoteException {
        UnicastRemoteObject.unexportObject(this, false);

        new Thread(() -> {
            System.out.print("\nShutting down...\n");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Done");
            System.exit(0);
        }).start();
    }

    @Override
    public String sendServerReport() throws IOException, ServerNotActiveException {
        BufferedReader reader = new BufferedReader(new FileReader("responseNotFound.log"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return  "Connection with ip (" + RemoteServer.getClientHost() + ")\n" + content + "\n" +
                "\nSuccessful responses: " + successfulResponses + "\n";
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
