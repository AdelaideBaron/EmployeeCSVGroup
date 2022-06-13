package com.sparta.lh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    static ArrayList<String> employeeId = new ArrayList<String>();


    public static void main(String[] args) {
        readFromFile("src/main/resources/EmployeeRecords.csv");
    }

        private static void readFromFile(String fileName){
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
                String[] employeeData = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(employeeData);

                employeeId.add(employeeData[0]);

                //ArrayList of Employees
                //Class Employee
            }
            System.out.println(employeeId);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

