package com.sparta.ab.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.logging.*;

public class LogConfig {
        public static Logger logger = Logger.getLogger("Employee CSV logger");

        public static void setLogConfig() {
            try {
                //fill path - content root in below parentheses
                Handler fileHandler = new FileHandler("src/main/java/com/sparta/ab/logging/log.log", true);
              //  logger.setFilter(new CustomFormatter());
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(new CustomFormatter());
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }
           // logger.setLevel(Level.INFO);
            //perhaps integrate the above with user input filter
        }
    }

