package br.dev.phcardoso.rmi.server;

import br.dev.phcardoso.rmi.server.database.dao.DatabaseOperations;
import br.dev.phcardoso.rmi.server.model.ResponseGenerated;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResponseGenerator {
    public static ResponseGenerated getResponse(String clientMessage) {
        try {
            ResultSet rs = DatabaseOperations.selectSimilarQuestion(clientMessage);
            if (rs == null) {
                return null;
            }

            if (rs.next()) {
                return new ResponseGenerated(
                        rs.getString("id"),
                        rs.getString("question"),
                        rs.getString("answer").replaceAll("%n", System.getProperty("line.separator")),
                        rs.getString("similarity")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
