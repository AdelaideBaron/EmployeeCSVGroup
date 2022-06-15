package com.sparta.ab.control;




import com.sparta.ab.model.EmployeeCollection;
import com.sparta.ab.model.EmployeeDTO;

import java.util.ArrayList;

public class App
{
    public static void main( String[] args )
    {
        String file = "src/main/resources/EmployeeRecordsSecond.csv";

        ArrayList<EmployeeDTO> arrayToFilter = FileIO.readFromFile(file);

        EmployeeCollection.setOriginalEmployees(arrayToFilter);

        EmployeeCollection.checkAllCorruptions();

      //  System.out.println(EmployeeCollection.getSize() ); //checking size of array


  //      EmployeeCollection.checkInitials();
//        EmployeeCollection.checkGender();
 //       EmployeeCollection.checkForFutureDates();
 //       EmployeeCollection.checkForDuplicateIDs();


        //this sets the data to empDTO
        //each employee may be access with arrayToFilter.get(n) where n = index, e.g. System.out.println(arrayToFilter.get(0));

//        System.out.println(EmployeeCollection.getCorruptList());
//        System.out.println(EmployeeCollection.getCorruptedByFutureDates());

        //need a way to pass the 'clean array' through to each next method


        //it will firstly need to run on full EmployeeCollection.setEmployees(arrayToFilter)

//        EmployeeDTO employee;
//        for(employee.getEmpId()) //not in the corrupted list
      //  System.out.println(EmployeeCollection.getCorruptListElement(0));
     //   System.out.println(EmployeeCollection.getCorruptListElement(1));

      //  System.out.println(EmployeeCollection.getCorruptedByFutureDates());


    }
    // 1. loop - one big array of Employees  (for each, remove corrupted) Easy / takes longer
    // 2. while in loop, filter and add to clean array   Harder / faster


    //obviously each check for corrupted with affect the other, so need to loop through
}
