package com.sparta.ab.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class EmployeeFormatter extends Formatter {


    //ISO 9601 - 2022-06-07 YYYY-MM-DD

    @Override
    public String format(LogRecord record) {
        return getDateFormat()
                + " " + record.getLevel()
                + " " + record.getMessage() + "\n";

    }
    private String getDateFormat() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }

}
