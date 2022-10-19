package br.dev.phcardoso.rmiclient;

import br.dev.phcardoso.rmiinterface.ChatService;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

public class RmiClient {
    public static void main(String[] args) throws IOException, NotBoundException, ServerNotActiveException {
        String objName = "rmi://localhost/ChatService";
        Scanner sc = new Scanner(System.in);
        ChatService chatService = (ChatService) Naming.lookup(objName);

        String clientMessage;
        while(true) {
            System.out.println("Enter your message: ");
            clientMessage = sc.nextLine();

            if (clientMessage.equalsIgnoreCase("SAIR")) {
                System.out.println(chatService.sendServerReport());
                chatService.quit();
                break;
            }

            System.out.println(chatService.sendMessage(clientMessage));
        }
    }
}
