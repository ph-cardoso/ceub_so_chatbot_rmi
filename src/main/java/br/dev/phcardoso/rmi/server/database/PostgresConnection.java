package br.dev.phcardoso.rmi.server.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresConnection {
    private static final String url = "jdbc:postgresql://"
            + System.getenv("POSTGRES_HOST")
            + ":"
            + System.getenv("POSTGRES_PORT")
            + "/"
            + System.getenv("POSTGRES_DB");
    private static final String user = System.getenv("POSTGRES_USER");
    private static final String password = System.getenv("POSTGRES_PASSWORD");
    private static Connection connection;

    private PostgresConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
