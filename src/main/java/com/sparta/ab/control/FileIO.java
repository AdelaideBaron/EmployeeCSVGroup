package com.sparta.ab.control;

import com.sparta.ab.model.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {
    private static Logger logger = Logger.getLogger("my logger");

    public static ArrayList<EmployeeDTO> readFromFile(String filename) {
        logger.log(Level.INFO, "Reading from CSV file");

        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        try {
            // Decorator pattern:
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); //read first line column headers
            logger.log(Level.INFO, "Extracting data");

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                logger.log(Level.FINER, "Splitting the CSV by comma entries, creating employeeDTO");
                String[] args = line.split(",");
                EmployeeDTO currentEmployee = new EmployeeDTO(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
            employees.add(currentEmployee);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return employees;

    }
}
