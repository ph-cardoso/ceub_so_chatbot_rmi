package br.dev.phcardoso.rmi.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

public class ResponseGenerator {
    public static JSONObject generateResponse(String clientMessage) {
        JSONParser parser = new JSONParser();

        JSONObject jsonObject;
        try (Reader reader = new FileReader("./src/main/resources/responses.json")) {
            jsonObject = (JSONObject) parser.parse(reader);
            //noinspection rawtypes
            Set keySet = jsonObject.keySet();

            for (int i = 0; i < keySet.size(); i++) {
                JSONObject response = (JSONObject) jsonObject.get(keySet.toArray()[i]);
                if (((String) response.get("pergunta")).equalsIgnoreCase(clientMessage)) {
                    return response;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return new JSONObject(Map.of(
                "notFound",
                "Desculpe, ainda não sei falar sobre isso... Tente perguntar de outra forma"
        ));
    }
}
