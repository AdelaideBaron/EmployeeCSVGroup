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
        long start = EmployeeCollection.getTime();
        String file = "src/main/resources/EmployeeRecordsSecond.csv";

        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);

        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkAllCorruptions();
        EmployeeCollection.createCleanList();
        EmployeeCollection.getEmpRecCntfromDB();
        logger.log(Level.INFO, "Adding corrupt employees to CSV");
        EmployeesWriter employeesWriter = new EmployeesWriter("src/main/resources/corruptemployee.csv");
        employeesWriter.writeEmployeesToCSV(EmployeeCollection.getCorruptList());
        long end = EmployeeCollection.getTime();
        long totaltime = (end-start)/1000000000;
        System.out.println("Total time:" + totaltime +"sec");

    }

    private static void loggingLevelControl() {
        LoggingLevelInOut loggingLevelInOut = new LoggingLevelInOut();
        Level levelChoice = loggingLevelInOut.getlevelChoice();
        LogConfig.setLogConfig();
        logger.setLevel(levelChoice);
    }

}
