package com.sparta.ab.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeCollection {



    private static ArrayList<EmployeeDTO> originalEmployees = new ArrayList<>();
    private static ArrayList<EmployeeDTO> corruptList = new ArrayList<>();

    private static ArrayList<EmployeeDTO> cleanSet = new ArrayList<>();

    public static ArrayList<EmployeeDTO> getOriginalEmployees() { //gets the array of employees
        logger.log(Level.INFO, "Obtain array of employees");
        return originalEmployees;
    }

    public static void setOriginalEmployees(ArrayList<EmployeeDTO> originalEmployees) {
        logger.log(Level.FINE, "Set employee");
        EmployeeCollection.originalEmployees = originalEmployees;
    }

 //   private static HashSet<EmployeeDTO> cleanSet = new HashSet<>();
    //hashsets contain unique values

    public static ArrayList<EmployeeDTO> getCorruptList() {
        logger.log(Level.INFO, "Obtain array of corrupt employee entries");
        return corruptList;
    }

    public static EmployeeDTO getCorruptListElement(int element) {
        logger.log(Level.INFO, "Obtain element from corrupt list");
        return corruptList.get(element);
    }

    public static ArrayList<EmployeeDTO> getCorruptListByDup(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Obtain list of employees corrupted by duplicate ID");
        checkForDuplicateIDs(employeeListToCheckForCorruptions);
        return corruptList;
    }

    public static int getCorruptedByDup(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.FINE, "Obtain amount of employees corrupted by duplicate ID");
        checkForDuplicateIDs(employeeListToCheckForCorruptions);
        return corruptList.size();
    }

    public static void checkForDuplicateIDs(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for duplicated IDs");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            for (EmployeeDTO employeeTwo : employeeListToCheckForCorruptions) {
                logger.log(Level.FINE, "comparing each employee ID against each other");
                if (employee != employeeTwo && employee.getEmpId().equals(employeeTwo.getEmpId())) {
                    corruptCount++;
                    corruptList.add(employee);
                    removeFromClean(employee);
                } else {
                    logger.log(Level.FINE, "adding clean data");
                    cleanSet.add(employee);
                }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate empId, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkForDuplicateEmails(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for duplicated Emails");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            for (EmployeeDTO employeeTwo : employeeListToCheckForCorruptions) {
                logger.log(Level.FINE, "comparing each employee ID against each other");
                if (employee != employeeTwo && employee.getEmail().equals(employeeTwo.getEmail())) {
                    corruptCount++;
                    corruptList.add(employee);
                    removeFromClean(employee);
                } else {
                    logger.log(Level.FINE, "adding clean data");
                    cleanSet.add(employee);
                }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate Emails, " + corruptCount + " moved to dirty data list.");
    }





    public static void checkForFutureDates(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for date of joining in future");
        //for now, it seems safe to say that anyone with DOB >=2022 is 'corrupt' - check range/dates with customer
        String yearCurrent = new String("2022");
        int corruptCount = 0;
        LocalDate date = LocalDate.now();
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            logger.log(Level.FINE, "checking individual employeeID");
            LocalDate dob = employee.getDob();
            if (employee.getDateOfJoining().isAfter(date)) {
                corruptList.add(employee);
                corruptCount++;
                removeFromClean(employee);
            } else {
                logger.log(Level.FINE, "adding clean data");
                cleanSet.add(employee);
            }
        }

        logger.log(Level.INFO, "All records checked for dob in future, " + corruptCount + " moved to dirty data list.");
    }

    public static int getAmountCorruptedByGender(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions){
        logger.log(Level.FINE, "Obtain amount of employees with invalid gender");


        checkGender(employeeListToCheckForCorruptions);
        return corruptList.size();


    }

    public static void checkGender(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for invalid gender");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            if (employee.getGender().equals("F") || employee.getGender().equals("M")) {
                logger.log(Level.FINE, "adding clean data");
                cleanSet.add(employee);
            } else {
                logger.log(Level.FINE, "checking individual employee gender");
                corruptCount++;
                corruptList.add(employee);
                removeFromClean(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for invalid gender, " + corruptCount + " moved to dirty data list.");
    }

    public static int checkInitials(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions){
        logger.log(Level.INFO, "Checking for invalid initials");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions){
            if (employee.getMiddleInitial().length() == 1){
                logger.log(Level.FINE, "adding clean data");
                cleanSet.add(employee);
            } else {
                logger.log(Level.FINE, "checking middle initial");
                corruptCount++;
                corruptList.add(employee);
                removeFromClean(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for 'FALSE' initials, " + corruptCount + " moved to dirty data list.");
        checkInitials(employeeListToCheckForCorruptions);
        return corruptList.size();
    }



    private static void removeFromClean(EmployeeDTO employee) {
        logger.log(Level.FINE, "Removing corrupted data from clean list, different corruption found");
        if(cleanSet.contains(employee)){
            cleanSet.remove(cleanSet.indexOf(employee));
        }
    }


    public static int getSize(ArrayList<EmployeeDTO> employeeListToSize) {
        logger.log(Level.INFO, "Obtain amount of employees");
        return employeeListToSize.size();
    }

    public static void checkAllCorruptions(){
        checkInitials(originalEmployees);
        checkGender(cleanSet);
  //      checkForDuplicateIDs(cleanSet);
   //     checkForFutureDates(cleanSet);
   //     System.out.println(corruptList);
    }


    // methods for interacting with employees
    // EmployeeCollection.addToCollection()
    //Employee.addToCorruptedCollection()
    // get size
    //remove duplicates



}
