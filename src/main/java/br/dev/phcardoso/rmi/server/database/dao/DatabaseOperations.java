package br.dev.phcardoso.rmi.server.database.dao;

import br.dev.phcardoso.rmi.server.database.PostgresConnection;
import br.dev.phcardoso.rmi.server.model.UserData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

public abstract class DatabaseOperations {
    private static final Connection connection = PostgresConnection.getConnection();

    public static ResultSet selectSimilarQuestion(String clientMessage) {
        String QUERY = "SELECT *, SIMILARITY(question, "
                + "'" + clientMessage + "'"
                + ") FROM answers WHERE SIMILARITY(question, "
                + "'" + clientMessage + "'"
                + ") > "
                + System.getenv("SIMILARITY_THRESHOLD")
                + " ORDER BY SIMILARITY(question, "
                + "'" + clientMessage + "'"
                + ") DESC LIMIT 1;";

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                return statement.executeQuery(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Connection is null");
        return null;
    }

    public static void insertConnection(UserData userData) {
        String QUERY = "INSERT INTO connections (" +
                "id," +
                "ip_adress," +
                "messages_sent," +
                "succesful_requests," +
                "unsuccessful_requests" +
                ") VALUES ("
                + "'" + userData.getClientUuid() + "', "
                + "'" + userData.getIpAdress() + "', "
                + userData.getMessagesSent() + ", "
                + userData.getSuccessfulRequests() + ", "
                + userData.getUnsuccessfulRequests()
                + ");";

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertNotFoundQuestions(UUID clientUuid, List<String> notFoundMessages) {
        StringBuilder QUERY = new StringBuilder("INSERT INTO not_found_questions (" +
                "connection_id," +
                "question" +
                ") VALUES ");

        for (String message : notFoundMessages) {
            QUERY.append("(")
                    .append("'").append(clientUuid).append("', ")
                    .append("'").append(message).append("'")
                    .append("),");
        }

        QUERY.deleteCharAt(QUERY.length() - 1);
        QUERY.append(";");

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(QUERY.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
