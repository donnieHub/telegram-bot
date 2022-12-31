package telegram.bot;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    public static void create() throws IOException {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("log.txt", true);
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
        logger.info("start");
    }
}
