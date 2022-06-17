package com.sparta.ab.model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeCollection {

    private static ArrayList<EmployeeDTO> originalEmployees = new ArrayList<>();
    private static HashSet<EmployeeDTO> corruptList = new HashSet<>();

    private static ArrayList<EmployeeDTO> cleanSet = new ArrayList<>();

    public static ArrayList<EmployeeDTO> getOriginalEmployees() { //gets the array of employees
        logger.log(Level.INFO, "Obtain array of employees");
        return originalEmployees;
    }

    public static void setOriginalEmployees(ArrayList<EmployeeDTO> originalEmployees) {
        logger.log(Level.FINE, "Set employee");
        EmployeeCollection.originalEmployees = originalEmployees;
    }

    public static ArrayList<EmployeeDTO> getCleanSet() {
        return cleanSet;
    }

    public static HashSet<EmployeeDTO> getCorruptList() {
        logger.log(Level.INFO, "Obtain array of corrupt employee entries");
        return corruptList;
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
                }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate employee Ids, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkForDuplicateEmails(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for duplicated emails");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            for (EmployeeDTO employeeTwo : employeeListToCheckForCorruptions) {
                logger.log(Level.FINE, "comparing each employee email against each other");
                if (employee != employeeTwo && employee.getEmail().equals(employeeTwo.getEmail())) {
                    corruptCount++;
                    corruptList.add(employee);
                }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate email, " + corruptCount + " moved to dirty data list.");
    }

    public static int getCorruptedByFutureDates(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.FINE, "Obtain amount of employees with dob in future");
        checkForFutureDates(employeeListToCheckForCorruptions);
        return corruptList.size();
    }

    public static void checkForFutureDates(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for dob in future");
        //for now, it seems safe to say that anyone with DOB >=2022 is 'corrupt' - check range/dates with customer
        String yearCurrent = new String("2022");
        int corruptCount = 0;
        LocalDate date = LocalDate.now();
        System.out.println(date);
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            logger.log(Level.FINE, "checking individual employeeID");
            LocalDate dob = employee.getDob();
            if (employee.getDob().isAfter(date)) {
                corruptList.add(employee);
                corruptCount++;

            }
        }

        logger.log(Level.INFO, "All records checked for dob in future, " + corruptCount + " moved to dirty data list.");
    }

    public static int getAmountCorruptedByGender(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.FINE, "Obtain amount of employees with invalid gender");
        checkGender(employeeListToCheckForCorruptions);
        return corruptList.size();
    }

    public static void checkGender(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for invalid gender");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            if (!employee.getGender().equals("F") && !employee.getGender().equals("M") && !employee.getGender().equals("X")) {
                logger.log(Level.FINE, "checking individual employee gender");
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for invalid gender, " + corruptCount + " moved to dirty data list.");
    }

public static void checkNegativeSalaries(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions){
    logger.log(Level.INFO, "Checking for negative salaries");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            if (employee.getSalary() < 0) {
                corruptCount++;
                corruptList.add(employee);
            }
        }
    logger.log(Level.INFO, "All records checked for negative salaries, " + corruptCount + " moved to dirty data list.");
}
    public static void checkInitials(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for invalid initials");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            if (employee.getMiddleInitial().length() != 1) {
                logger.log(Level.FINE, "checking individual employee initial");
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for 'FALSE' initials, " + corruptCount + " moved to dirty data list.");
    }

    private static void removeFromClean(EmployeeDTO employee) {
        logger.log(Level.FINE, "Removing corrupted data from original list, different corruption found");
        cleanSet.remove(cleanSet.indexOf(employee));

    }

    public static int getSize(ArrayList<EmployeeDTO> employeeListToSize) {
        logger.log(Level.INFO, "Obtain amount of employees");
        return employeeListToSize.size();
    }

    public static void checkAllCorruptions() {

        checkInitials(originalEmployees);
        checkGender(originalEmployees);
        checkForDuplicateEmails(originalEmployees);
        checkForDuplicateIDs(originalEmployees);
        checkForFutureDates(originalEmployees);
        checkNegativeSalaries(originalEmployees);
        System.out.println(getCorruptList().size());

        //the below is me trying to sort the list bois
        ArrayList<EmployeeDTO> cleanestEmployees = originalEmployees; //yet to be cleaned

        ArrayList<String> corruptId = new ArrayList<String>();
        ArrayList<String> originalID = new ArrayList<String>();

        //get the employee IDs from corrupt list
        for (EmployeeDTO corruptEmployee : corruptList) {
            corruptId.add(corruptEmployee.getEmpId());
        }

        for(EmployeeDTO originalEmployee : originalEmployees){
            originalID.add(originalEmployee.getEmpId());
        //    String ogID = originalEmployee.getEmpId();
        }


        //for(int j = 0; j < originalID.size(); j++) { //this works
         //  for (int i = 0; i < corruptId.size(); i++) { //this works
          //      if (corruptId.get(i).equals(originalID.get(j))){
           //         String corruptIdToRemove = corruptId.get(i);
           //         for (EmployeeDTO employee : cleanestEmployees){
             //           if (corruptIdToRemove.equals(employee.getEmpId())){
                     //       cleanestEmployees.remove();
             //           }
             //           employee.getEmpId();
            //        }
           //         System.out.println(corruptId.get(i));

            //        cleanestEmployees.remove()
           //         String employeeToRemove = corruptId.get(i);
           //         cleanestEmployees.remove(cleanestEmployees.indexOf(employeeToRemove));
                 }
            }
  //      }



        // check them against cleanest

        //  for(EmployeeDTO)






