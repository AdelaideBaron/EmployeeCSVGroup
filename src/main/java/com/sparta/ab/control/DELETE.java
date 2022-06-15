package com.sparta.ab.control;

import com.sparta.ab.logging.LogConfig;
import com.sparta.ab.view.LoggingLevelInOut;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DELETE {

    private static Logger logger = Logger.getLogger("my logger");


    public static void main(String[] args) {

        //the below should be in any output class - I think?

        LoggingLevelInOut loggingLevelInOut = new LoggingLevelInOut();
        Level levelChoice = loggingLevelInOut.getlevelChoice();
        LogConfig.setLogConfig();
        logger.setLevel(levelChoice);

        //below is testing the above

        logger.log(Level.INFO, "This is an INFO message");
        logger.log(Level.WARNING, "This is a WARNING message");
        logger.log(Level.FINE, "This is a FINE message");
        logger.log(Level.CONFIG, "This is a CONFIG message");


    }
}


