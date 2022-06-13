package com.sparta.ab;

import java.util.ArrayList;
import java.util.HashSet;

public class EmployeeCollection {


    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();

    private static HashSet<EmployeeDTO> cleanSet = new HashSet<>();

    public static ArrayList<EmployeeDTO> getDirtyList() {
        return dirtyList;
    }

    private static ArrayList<EmployeeDTO> dirtyList = new ArrayList<>();



    public static ArrayList<EmployeeDTO> getEmployees() {
        return employees;
    }

    public static void checkForDuplicateIDs() {
        for (EmployeeDTO employee : employees) {
            for (EmployeeDTO emp : employees ) {
                if (employee != emp && employee.getEmpId().equals(emp.getEmpId()))
                    dirtyList.add(employee);
            }
        }
    }


    public static void setEmployees(ArrayList<EmployeeDTO> employees) {
        EmployeeCollection.employees = employees;
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
