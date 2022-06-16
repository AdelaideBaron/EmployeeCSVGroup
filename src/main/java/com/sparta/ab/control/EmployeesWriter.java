package com.sparta.ab.control;

import com.sparta.ab.model.EmployeeDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sparta.ab.logging.LogConfig.logger;

//in progress

public class EmployeesWriter {

    private static Logger logger = Logger.getLogger("my logger");
    //general methods that we can pass both corrupt&clean to

    private File file;
    private String filePath;

    public EmployeesWriter(File file, String filePath) {
        this.file = file;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void writeEmployeesToCSV(ArrayList<EmployeeDTO> employeesToAdd){
        logger.log(Level.FINE, "Adding employees to CSV"); //try to tailor to the specific list?
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write("EmpId, Name Prefix, First name, Middle Initial, Last Name, Gender, email, dob, Date of joinin");
            bufferedWriter.newLine();
            for (EmployeeDTO employee : employeesToAdd) {
                bufferedWriter.write(employee.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush(); //flushes the stream
            bufferedWriter.close(); //close the stream
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
