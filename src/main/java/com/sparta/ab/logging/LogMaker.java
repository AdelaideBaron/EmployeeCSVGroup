package com.sparta.ab.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogConfig {
        public static Logger logger = Logger.getLogger("Employee CSV logger");
        public static void setLogConfig() {
            try {

                Handler fileHandler = new FileHandler("src/main/java/com/sparta/ah/logging/EmployeeCSV.log", true);
                logger.addHandler(fileHandler);
                //logger.setFilter(new CustomF
                // ilter());
                fileHandler.setFormatter(new CustomFormatter());
            } catch (
                    IOException e) {
                throw new RuntimeException(e);
            }

            logger.setLevel(Level.INFO);
        }
    }

