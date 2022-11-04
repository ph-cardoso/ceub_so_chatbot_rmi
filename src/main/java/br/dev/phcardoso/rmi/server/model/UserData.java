package br.dev.phcardoso.rmi.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UserData {
    private final UUID clientUuid;
    private final String ipAdress;
    private int messagesSent = 0;
    private int successfulRequests = 0;
    private int unsuccessfulRequests = 0;
    private final List<String> notFoundMessagesList;

    public UserData(UUID clientUuid, String ipAdress) {
        this.clientUuid = clientUuid;
        this.ipAdress = ipAdress;
        this.notFoundMessagesList = new ArrayList<>();
    }

    public String getReport() {
        return "Client: " + this.clientUuid + "\n" +
                "Ip Adress: " + this.ipAdress + "\n" +
                "Messages sent: " + this.messagesSent + "\n" +
                "Successful requests: " + this.successfulRequests + "\n" +
                "Unsuccessful requests: " + this.unsuccessfulRequests + "\n" +
                "\nNot found requests for messages: " + "\n" +
                String.join("\n", this.notFoundMessagesList);
    }

    public void addNotFoundMessage(String message) {
        this.notFoundMessagesList.add(message);
    }

    public void incrementUnsuccessfulRequests() {
        this.messagesSent++;
        this.unsuccessfulRequests++;
    }

    public void incrementSuccessfulRequests() {
        this.messagesSent++;
        this.successfulRequests++;
    }

    public UUID getClientUuid() {
        return clientUuid;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public int getMessagesSent() {
        return messagesSent;
    }

    public int getSuccessfulRequests() {
        return successfulRequests;
    }

    public int getUnsuccessfulRequests() {
        return unsuccessfulRequests;
    }

    public List<String> getNotFoundMessagesList() {
        return Collections.unmodifiableList(notFoundMessagesList);
    }
}
