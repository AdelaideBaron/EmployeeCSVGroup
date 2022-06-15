package com.sparta.ab.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeCollection {



    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();
    private static ArrayList<EmployeeDTO> corruptList = new ArrayList<>();

    public static ArrayList<EmployeeDTO> getEmployees() { //gets the array of employees
        return employees;
    }

    public static void setEmployees(ArrayList<EmployeeDTO> employees) {
        EmployeeCollection.employees = employees;
    }

    private static HashSet<EmployeeDTO> cleanSet = new HashSet<>();
    //hashsets contain unique values

    public static ArrayList<EmployeeDTO> getCorruptList() {
        return corruptList;
    }

    public static EmployeeDTO getCorruptListElement(int element) {
        return corruptList.get(element);
    }

    public static ArrayList<EmployeeDTO> getCorruptListByDup() {
        checkForDuplicateIDs();
        return corruptList;
    }

    public static int getCorruptedByDup() {
        checkForDuplicateIDs();
        return corruptList.size();
    }

    public static void checkForDuplicateIDs() {
        int corruptCount = 0;
        for (EmployeeDTO employee : employees) {
            for (EmployeeDTO employeeTwo : employees) {
                if (employee != employeeTwo && employee.getEmpId().equals(employeeTwo.getEmpId()))
                    corruptCount++;
                    corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate empId, " + corruptCount + " moved to dirty data list.");
    }

    public static int getCorruptedByFutureDates() {
        checkForFutureDates();
        return corruptList.size();
    }

    public static void checkForFutureDates() {
        //for now, it seems safe to say that anyone with DOB >=2022 is 'corrupt' - check range/dates with customer
        String yearCurrent = new String("2022");
        int corruptCount = 0;
        int yearCurrents = 2022;
        for (EmployeeDTO employee : employees) {
            String dob = employee.getDob();
            String yearOB = dob.substring(6); //will matter if / in there
            if (Integer.parseInt(yearOB) >= yearCurrents) {
                corruptList.add(employee);
            }
        }

        logger.log(Level.INFO, "All records checked for dob in future, " + corruptCount + " moved to dirty data list.");
    }

    public static int getAmountCorruptedByGender(){
        checkGender();
        return corruptList.size();
    }

    public static void checkGender() {
        int corruptCount = 0;
        for (EmployeeDTO employee : employees) {
            if (!employee.getGender().equals("F") && employee.getGender().equals("M")) {
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for invalid gender, " + corruptCount + " moved to dirty data list.");
    }

    public static int getAmountCorruptedByInitials(){
        checkInitials();
        return corruptList.size();
    }

    public static void checkInitials(){
        int corruptCount = 0;
        for(EmployeeDTO employee : employees){
            if(employee.getMiddleInitial().equals("FALSE")){
                corruptCount ++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for 'FALSE' initials, " + corruptCount + " moved to dirty data list.");
    }


    public static int getSize() {
        return employees.size();
    }


    // methods for interacting with employees
    // EmployeeCollection.addToCollection()
    //Employee.addToCorruptedCollection()
    // get size
    //remove duplicates



}
