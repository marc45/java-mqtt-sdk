package ir.moke.dandelion.logger;

import java.time.LocalDateTime;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class DandelionLogFormatter extends SimpleFormatter {
    @Override
    public String format(LogRecord record) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return String.valueOf(localDateTime) + " " +
                record.getLevel().getName() + " " +
                record.getSourceClassName() + " " +
                "[" + record.getSourceMethodName() + "] - " +
                record.getMessage() + "\n";
    }
}
