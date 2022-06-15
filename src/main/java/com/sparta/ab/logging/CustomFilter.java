package com.sparta.ab.logging;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CustomFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
        //when the user inputs a logging level, will need to set
        //this as parameter
        /*
        if (record.getMessage().contains("hello")){
            return true;
        } else {
            return false;
        }
        */
    }
}


