package com.sparta.ab.control;

import com.sparta.ab.model.EmployeeCollection;
import com.sparta.ab.model.EmployeeDTO;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesWriterTest {

//    @BeforeAll
//    public static void  beforeall() {
//        String file = "src/main/resources/EmployeeRecordsSecond.csv";
//        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
//        EmployeeCollection.setOriginalEmployees(arrayToFilter);
//    }

    @Test
    @DisplayName("check for the Duplicate id")
    void checkforduplicateid(){
        String file = "src/main/resources/EmployeeRecordsSecond.csv";
        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkForDuplicateIDs(EmployeeCollection.getOriginalEmployees());
        Assertions.assertEquals(114,EmployeeCollection.getCorruptList().size());
    }

    @Test
    @DisplayName("check for the futuredate")
    void checkforfuturedate(){
        String file = "src/main/resources/EmployeeRecordsSecond.csv";
        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkForFutureDates(EmployeeCollection.getOriginalEmployees());
        Assertions.assertEquals(1,EmployeeCollection.getCorruptList().size());
    }

    @Test
    @DisplayName("check for the gender")
    void checkforgender(){
        String file = "src/main/resources/EmployeeRecordsSecond.csv";
        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkGender(EmployeeCollection.getOriginalEmployees());
        Assertions.assertEquals(51,EmployeeCollection.getCorruptList().size());
    }

    @Test
    @DisplayName("check for the Initials")
    void checkforInitials(){
        String file = "src/main/resources/EmployeeRecordsSecond.csv";
        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkInitials(EmployeeCollection.getOriginalEmployees());
        Assertions.assertEquals(14,EmployeeCollection.getCorruptList().size());
    }

//    @Test
//    @DisplayName("check for the valid date of birth")
//    void checkDobvalid(){
//        String file = "src/main/resources/EmployeeRecordsSecond.csv";
//        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
//        EmployeeCollection.setOriginalEmployees(arrayToFilter);
//        EmployeeCollection.checkDobvalid(EmployeeCollection.getOriginalEmployees());
//        Assertions.assertEquals(4514,EmployeeCollection.getCorruptList().size());
//    }

//    @Test
//    @DisplayName("check for the valid date of joining")
//    void checkDojvalid(){
//        String file = "src/main/resources/EmployeeRecordsSecond.csv";
//        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
//        EmployeeCollection.setOriginalEmployees(arrayToFilter);
//        EmployeeCollection.checkDojvalid(EmployeeCollection.getOriginalEmployees());
//        Assertions.assertEquals(4514,EmployeeCollection.getCorruptList().size());
//    }

//    @Test
//    @DisplayName("check for the valid date of birth")
//    void checkDobDojNowvalid(){
//        String file = "src/main/resources/EmployeeRecordsSecond.csv";
//        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
//        EmployeeCollection.setOriginalEmployees(arrayToFilter);
//        EmployeeCollection.checkdatecomparison(EmployeeCollection.getOriginalEmployees());
//        Assertions.assertEquals(4515,EmployeeCollection.getCorruptList().size());
//    }

    @Test
    @DisplayName("check for the valid Salary")
    void checkSalvalid(){
        String file = "src/main/resources/EmployeeRecordsSecond.csv";
        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkSalaryInvalid(EmployeeCollection.getOriginalEmployees());
        Assertions.assertEquals(9,EmployeeCollection.getCorruptList().size());
    }

    @Test
    @DisplayName("Performance Test")
    void checkPerformance() {
        String file = "src/main/resources/EmployeeRecordsSecond.csv";
        long stTime = EmployeeCollection.getTime();
        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);
        EmployeeCollection.setOriginalEmployees(arrayToFilter);
        EmployeeCollection.checkAllCorruptions();
        EmployeeCollection.createCleanList();
        EmployeeCollection.insertEmptodb();
        EmployeesWriter employeesWriter = new EmployeesWriter("src/main/resources/corruptemployee.csv");
        employeesWriter.writeEmployeesToCSV(EmployeeCollection.getCorruptList());
        long enTime = EmployeeCollection.getTime();
        long elapsedTime = enTime - stTime;
        System.out.println("Time taken to process the input file, filter and load the clean rec to db : "+ elapsedTime / 1000000000 + " sec" );
        int dbCleanRecCnt = EmployeeCollection.getEmpRecCntfromDB();
        Assertions.assertEquals(9772,dbCleanRecCnt);
    }


}