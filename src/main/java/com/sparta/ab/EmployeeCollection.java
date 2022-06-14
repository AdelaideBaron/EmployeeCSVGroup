package com.sparta.ab;

import java.util.ArrayList;
import java.util.HashSet;

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
        checkForDuplicateIDs();
        return corruptList;
    }

    public static int getCorruptedByDup() {
        checkForDuplicateIDs();
        return corruptList.size();
    }

    public static void checkForDuplicateIDs() {
        for (EmployeeDTO employee : employees) {
            for (EmployeeDTO employeeTwo : employees) {
                if (employee != employeeTwo && employee.getEmpId().equals(employeeTwo.getEmpId()))
                    corruptList.add(employee);
            }
        }
    }

    public static int getCorruptedByFutureDates() {
        checkForFutureDates();
        return corruptList.size();
    }

    public static void checkForFutureDates() {
        //for now, it seems safe to say that anyone with DOB >=2022 is 'corrupt' - check range/dates with customer
        String yearCurrent = new String("2022");
        int yearCurrents = 2022;
        for (EmployeeDTO employee : employees) {
            String dob = employee.getDob();
            String yearOB = dob.substring(6); //will matter if / in there
            if (Integer.parseInt(yearOB) >= yearCurrents) {
                corruptList.add(employee);
            }
        }
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
