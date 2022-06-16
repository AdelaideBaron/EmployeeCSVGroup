package com.sparta.ab.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeCollection {



    private static ArrayList<EmployeeDTO> originalEmployees = new ArrayList<>();
    private static HashSet<EmployeeDTO> corruptList = new HashSet<>();

 //   static ArrayList<EmployeeDTO> cleanByDob = new ArrayList<EmployeeDTO>();
 //   static ArrayList<EmployeeDTO> cleanByGender = new ArrayList<EmployeeDTO>();
  //  static ArrayList<EmployeeDTO> cleanByInitial = new ArrayList<EmployeeDTO>();
 //   static ArrayList<EmployeeDTO> cleanByDupId = new ArrayList<EmployeeDTO>();

 //   public static ArrayList<EmployeeDTO> getCleanByDob() {
   //     return cleanByDob;
 //   }

 //   public static ArrayList<EmployeeDTO> getCleanByGender() {
 //       return cleanByGender;
 //   }

 //   public static ArrayList<EmployeeDTO> getCleanByInitial() {
 //       return cleanByInitial;
 //   }

 //   public static ArrayList<EmployeeDTO> getCleanByDupId() {
 //        return cleanByDupId;
  //  }

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

    public static ArrayList<EmployeeDTO> getCleanSet() {
        return cleanSet;
    }

    public static HashSet<EmployeeDTO> getCorruptList() {
        logger.log(Level.INFO, "Obtain array of corrupt employee entries");
        return corruptList;
    }

 //   public static EmployeeDTO getCorruptListElement(int element) {
  //      logger.log(Level.INFO, "Obtain element from corrupt list");
    //    return corruptList.get(element);
    //}

 //   public static ArrayList<EmployeeDTO> getCorruptListByDup(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
 //       logger.log(Level.INFO, "Obtain list of employees corrupted by duplicate ID");
   //     checkForDuplicateIDs(employeeListToCheckForCorruptions);
     //   return corruptList;
   // }

 //   public static int getCorruptedByDup(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
 //       logger.log(Level.FINE, "Obtain amount of employees corrupted by duplicate ID");
   //     checkForDuplicateIDs(employeeListToCheckForCorruptions);
 //       return corruptList.size();
  //  }

    public static void checkForDuplicateIDs(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for duplicated IDs");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            for (EmployeeDTO employeeTwo : employeeListToCheckForCorruptions) {
                logger.log(Level.FINE, "comparing each employee ID against each other");
                if (employee != employeeTwo && employee.getEmpId().equals(employeeTwo.getEmpId())) {
                    corruptCount++;
                    corruptList.add(employee);
               //     removeFromClean(employee);
                } //else {
                    logger.log(Level.FINE, "adding clean data");
                 //   ArrayList<EmployeeDTO> cleanByDupId = new ArrayList<EmployeeDTO>();
               //     cleanByDupId.add(employee);
                //    cleanSet.add(employee);

              //  }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate empId, " + corruptCount + " moved to dirty data list.");
    }

    public static int getCorruptedByFutureDates(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.FINE, "Obtain amount of employees with dob in future");
        checkForFutureDates(employeeListToCheckForCorruptions);
        return corruptList.size();
    }

    public static void checkForFutureDates(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for dob in future");
        //for now, it seems safe to say that anyone with DOB >=2022 is 'corrupt' - check range/dates with customer
       // date now = new date();
        String yearCurrent = new String("2022");
        int corruptCount = 0;
        int yearCurrents = 2022;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            logger.log(Level.FINE, "checking individual employeeID");
            String dob = employee.getDob();
            String yearOB = dob.substring(6); //will matter if / in there
            if (Integer.parseInt(yearOB) >= yearCurrents) {
                corruptList.add(employee);
                corruptCount++;
             //   removeFromClean(employee);
            } //else {
                logger.log(Level.FINE, "adding clean data");
          //      ArrayList<EmployeeDTO> cleanByDob = new ArrayList<EmployeeDTO>();
           //     cleanByDob.add(employee);
                //  cleanSet.add(employee);
           // }
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
            if (!employee.getGender().equals("F") || !employee.getGender().equals("M")) {
                logger.log(Level.FINE, "checking individual employee gender");
                corruptCount++;
                corruptList.add(employee);
              //  removeFromClean(employee);
            }// else {
                logger.log(Level.FINE, "adding clean data");
            //    ArrayList<EmployeeDTO> cleanByGender = new ArrayList<EmployeeDTO>();
            //    cleanByGender.add(employee);
                //  cleanSet.add(employee);
           // }
        }
        logger.log(Level.INFO, "All records checked for invalid gender, " + corruptCount + " moved to dirty data list.");
    }

    public static int getAmountCorruptedByInitials(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions){
        logger.log(Level.FINE, "Obtain amount of employees with invalid intiials");
        checkInitials(employeeListToCheckForCorruptions);
        return corruptList.size();
    }

    public static void checkInitials(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions){
        logger.log(Level.INFO, "Checking for invalid intials");
        int corruptCount = 0;
        for(EmployeeDTO employee : employeeListToCheckForCorruptions){
            if(employee.getMiddleInitial().equals("FALSE")){
                logger.log(Level.FINE, "checking individual employee initial");
                corruptCount ++;
                //check if in corrupt list - empID
                corruptList.add(employee);
            //    removeFromClean(employee);
            } // else {
                logger.log(Level.FINE, "adding clean data");
              //  ArrayList<EmployeeDTO> cleanByInitial = new ArrayList<EmployeeDTO>();
            //    cleanByInitial.add(employee);
            //    cleanSet.add(employee);
        //   }
        }
        logger.log(Level.INFO, "All records checked for 'FALSE' initials, " + corruptCount + " moved to dirty data list.");
    }

    private static void removeFromClean(EmployeeDTO employee) {
        logger.log(Level.FINE, "Removing corrupted data from original list, different corruption found");
        cleanSet.remove(cleanSet.indexOf(employee));

    }

    //need to pass the next one the clean set
    //then create a new clean set in the else statement


    public static int getSize(ArrayList<EmployeeDTO> employeeListToSize) {
        logger.log(Level.INFO, "Obtain amount of employees");
        return employeeListToSize.size();
    }

    public static void checkAllCorruptions(){

        checkInitials(originalEmployees);
    //    checkGender(originalEmployees); - not currently working, just adds everyone
        checkForDuplicateIDs(originalEmployees);
        checkForFutureDates(originalEmployees);
     //   ArrayList<EmployeeDTO> cleanestEmployees = new ArrayList<>();
       // cleanestEmployees = originalEmployees;

      //  for(EmployeeDTO)

        for(EmployeeDTO originalEmployee : originalEmployees) {
            for (EmployeeDTO corruptEmployee : corruptList) {
                if (originalEmployee.getEmpId().equals(corruptEmployee.getEmpId())) {
                    logger.log(Level.FINE, "Removing corrupted data from original list, different corruption found");
                    cleanestEmployees.remove(originalEmployee); //works
                    //  cleanSet.remove(cleanSet.indexOf(employee));
                    //    removeFromClean(corruptEmployee);
                }
            }
        }


        System.out.println(originalEmployees.size());

    }

}
