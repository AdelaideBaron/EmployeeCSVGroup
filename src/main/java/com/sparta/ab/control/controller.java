package com.sparta.ab.control;

import com.sparta.ab.logging.LogConfig;
import com.sparta.ab.model.EmployeeCollection;
import com.sparta.ab.model.EmployeeDTO;
import com.sparta.ab.view.LoggingLevelInOut;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controller
{

    private static Logger logger = Logger.getLogger("my logger");
    public static void main( String[] args )
    {
        loggingLevelControl(); //it is working, but doesn't seem to be filtering correctly?
        //perhaps only filtering in this class... maybe need to parse to others

        String file = "src/main/resources/EmployeeRecordsSecond.csv";

        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);

        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkAllCorruptions();
        EmployeeCollection.createCleanList();

    }

    private static void loggingLevelControl() {
        LoggingLevelInOut loggingLevelInOut = new LoggingLevelInOut();
        Level levelChoice = loggingLevelInOut.getlevelChoice();
        LogConfig.setLogConfig();
        logger.setLevel(levelChoice);
    }

}
