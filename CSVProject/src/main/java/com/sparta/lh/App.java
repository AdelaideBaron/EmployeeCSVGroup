package com.sparta.lh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        readFromFile("src/main/resources/EmployeeRecords.csv");
    }

        private static void readFromFile(String fileName){
        try {
            int count = 0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
                String[] employeeData = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(employeeData);

                System.out.println(employeeDTO);
                count ++;
                //ArrayList of Employees
                //Class Employee
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

