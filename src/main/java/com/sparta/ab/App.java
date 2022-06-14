package com.sparta.ab;


import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        String file = "src/main/resources/EmployeeRecords.csv";

        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);

        //each employee may be access with arrayToFilter.get(n) where n = index, e.g. 0
        //System.out.println(arrayToFilter.get(0));

        EmployeeCollection.setEmployees(arrayToFilter); //this sets the data to empDTO
//        System.out.println(EmployeeCollection.getCorruptList());
        System.out.println(EmployeeCollection.getCorruptedByDup());

        //need a way to pass the 'clean array' through to each next method

 //       EmployeeCollection.checkForDuplicateIDs();
 //       System.out.println(EmployeeCollection.getDirtyList());

    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster

}
