package ir.moke.dandelion.logger;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoggerProducer {

    public static Logger produceLogger() {
        //TODO Fix logManager reset
        LogManager.getLogManager().reset();
        Handler handler = new DandelionConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new DandelionLogFormatter());
        Logger logger = Logger.getGlobal();
        logger.setLevel(Level.ALL);
        logger.addHandler(handler);
        return logger;
    }
}