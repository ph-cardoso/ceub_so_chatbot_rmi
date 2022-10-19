package br.dev.phcardoso.rmiserver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerInstance {
    private static Logger instance;

    private LoggerInstance() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            Logger logger = Logger.getLogger("responseNotFoundLogger");
            FileHandler fh;

            try {
                fh = new FileHandler("responseNotFound.log");
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();
                fh.setFormatter(formatter);
                instance = logger;
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
