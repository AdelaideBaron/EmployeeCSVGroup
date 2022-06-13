package com.sparta.ab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

    public static ArrayList<EmployeeDTO> readFromFile(String filename) {

        ArrayList<EmployeeDTO> arrayToSort = new ArrayList<>();
        try {
            // Decorator pattern:
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); //read first line column headers

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] args = line.split(",");
                arrayToSort.add(new EmployeeDTO(args));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return arrayToSort;

    }
}
