package com.sparta.ab;


public class App 
{
    public static void main( String[] args )
    {
        //LogConfig.setLogConfig();
        String file = "src/main/resources/EmployeeRecords.csv";
        EmployeeCollection.setEmployees(FileIO.readFromFile(file));
        //System.out.println(EmployeeCollection.getEmployees().get(1000));

        EmployeeCollection.checkForDuplicateIDs();
        System.out.println(EmployeeCollection.getDirtyList());

    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster

}
