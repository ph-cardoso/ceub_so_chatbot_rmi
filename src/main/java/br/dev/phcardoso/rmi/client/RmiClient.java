package br.dev.phcardoso.rmi.client;

import br.dev.phcardoso.rmi.services.ChatService;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;
import java.util.UUID;

// TODO: Gerar um uuid dinâmico para identificar o cliente e enviar em cada requisição
public class RmiClient {
    public static void main(String[] args) throws IOException, NotBoundException, ServerNotActiveException {
        String objName = "rmi://localhost/ChatService";
        UUID clientUuid = UUID.randomUUID();
        Scanner sc = new Scanner(System.in);
        ChatService chatService = (ChatService) Naming.lookup(objName);

        String clientMessage;
        while(true) {
            System.out.println("\nEnter your message: ");
            clientMessage = sc.nextLine();

            if (clientMessage.equalsIgnoreCase("SAIR")) {
                System.out.println("\n" + chatService.sendServerReport(clientUuid));
                chatService.quit(clientUuid);
                break;
            }

            System.out.println(chatService.sendMessage(clientMessage, clientUuid));
        }
    }
}
