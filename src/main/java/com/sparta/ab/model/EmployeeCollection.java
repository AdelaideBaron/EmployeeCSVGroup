package com.sparta.ab.model;

import com.sparta.ab.jdbc.ConnectionManager;
import com.sparta.ab.jdbc.EmployeeDAO;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeCollection {

    private static ArrayList<EmployeeDTO> originalEmployees = new ArrayList<>();
    private static HashSet<EmployeeDTO> corruptList = new HashSet<>();

    private static HashSet<EmployeeDTO> cleanSet = new HashSet<>();

    public static ArrayList<EmployeeDTO> getOriginalEmployees() { //gets the array of employees
        logger.log(Level.INFO, "Obtain array of employees");
        return originalEmployees;
    }

    public static void setOriginalEmployees(ArrayList<EmployeeDTO> originalEmployees) {
        logger.log(Level.FINE, "Set employee");
        EmployeeCollection.originalEmployees = originalEmployees;
    }

    public static HashSet<EmployeeDTO> getCleanSet() {
        return cleanSet;
    }

    public static HashSet<EmployeeDTO> getCorruptList() {
        logger.log(Level.INFO, "Obtain array of corrupt employee entries");
        return corruptList;
    }

    public static void checkForDuplicateIDs(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for duplicated IDs");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            for (EmployeeDTO employeeTwo : employeeListToCheckForCorruptions) {
                logger.log(Level.FINE, "comparing each employee ID against each other");
                if (employee != employeeTwo && employee.getEmpId().equals(employeeTwo.getEmpId())) {
                    corruptCount++;
                    corruptList.add(employee);
                }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate empId, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkForDuplicateEmails(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for duplicated emails");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            for (EmployeeDTO employeeTwo : employeeListToCheckForCorruptions) {
                logger.log(Level.FINE, "comparing each employee email against each other");
                if (employee != employeeTwo && employee.getEmail().equals(employeeTwo.getEmail())) {
                    corruptCount++;
                    corruptList.add(employee);
                }
            }
        }
        logger.log(Level.INFO, "All records checked for duplicate email, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkForFutureDates(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for dob in future");
        //for now, it seems safe to say that anyone with DOB >=2022 is 'corrupt' - check range/dates with customer
        int corruptCount = 0;
        int yearCurrents = 2022;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            logger.log(Level.FINE, "checking individual employeeID");
            String dob = employee.getDob();
            String yearOB = dob.substring(6);
            if (Integer.parseInt(yearOB) >= yearCurrents) {
                corruptList.add(employee);
                corruptCount++;

            }
        }

        logger.log(Level.INFO, "All records checked for dob in future, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkGender(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for invalid gender");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            if (!employee.getGender().equals("F") && !employee.getGender().equals("M")) {
                logger.log(Level.FINE, "checking individual employee gender");
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for invalid gender, " + corruptCount + " moved to dirty data list.");
    }


    public static void checkInitials(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for invalid intials");
        int corruptCount = 0;
        for (EmployeeDTO employee : employeeListToCheckForCorruptions) {
            if (employee.getMiddleInitial().length() != 1) {
                logger.log(Level.FINE, "checking individual employee initial");
                corruptCount++;
                corruptList.add(employee);

            }
        }
        logger.log(Level.INFO, "All records checked for 'FALSE' initials, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkDobvalid(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        int corruptCount = 0;
        logger.log(Level.INFO, "Checking for invalid dob");
        for(EmployeeDTO employee : employeeListToCheckForCorruptions) {
            String[] date = employee.getDoj().split("/");
            if (date[0].length() != 2) {
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for valid dateof birth, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkDojvalid(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        int corruptCount = 0;
        logger.log(Level.INFO, "Checking for invalid doj");
        for(EmployeeDTO employee : employeeListToCheckForCorruptions) {
            String[] date = employee.getDoj().split("/");
            if (date[0].length() != 2) {
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for valid dateof birth, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkdatecomparison(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions){
        logger.log(Level.INFO, "Checking for invalid dob with respect to doj and current date");
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        int corruptCount = 0;
        for(EmployeeDTO employee : employeeListToCheckForCorruptions) {
            String[] dobd = employee.getDob().split("/");
            String[] dojd = employee.getDoj().split("/");
            if (dobd[0].length() == 2 && dojd[0].length() == 2) {
                Date dob = null;
                Date doj = null;
                try {
                    dob = sdf.parse(employee.getDob());
                    doj = sdf.parse(employee.getDoj());
                    Date now = new Date();
                    if ((dob.after(doj)) || dob.after(now)) {
                        corruptCount++;
                        corruptList.add(employee);
                    }
                } catch (Exception e) {
                    corruptCount++;
                    corruptList.add(employee);
                }
            }
        }
        logger.log(Level.INFO, "All records checked for dob before doj and current date, " + corruptCount + " moved to dirty data list.");
    }

    public static void checkSalaryInvalid(ArrayList<EmployeeDTO> employeeListToCheckForCorruptions) {
        logger.log(Level.INFO, "Checking for invalid Salary Amount");
        int corruptCount = 0;
        for(EmployeeDTO employee : employeeListToCheckForCorruptions) {
            int sal = Integer.parseInt(employee.getSalary());
            if (sal <= 0) {
                corruptCount++;
                corruptList.add(employee);
            }
        }
        logger.log(Level.INFO, "All records checked for valid salary, " + corruptCount + " moved to dirty data list.");
    }

    public static int getSize(ArrayList<EmployeeDTO> employeeListToSize) {
        logger.log(Level.INFO, "Obtain amount of employees");
        return employeeListToSize.size();
    }

    public static void checkAllCorruptions() {
        checkInitials(originalEmployees);
        checkGender(originalEmployees);
        checkForDuplicateEmails(originalEmployees);
        checkForDuplicateIDs(originalEmployees);
        checkForFutureDates(originalEmployees);
        checkDojvalid(originalEmployees);
        checkDobvalid(originalEmployees);
        checkdatecomparison(originalEmployees);
        checkSalaryInvalid(originalEmployees);
        logger.log(Level.INFO, " " + getCorruptList().size() + " corruptions located.");
    }

    public static void createCleanList() {
        logger.log(Level.INFO, "Creating clean list");
        for(EmployeeDTO originalEmployee: originalEmployees){ //works
            String originalEmpID = originalEmployee.getEmpId();
            cleanSet.add(originalEmployee);
            for(EmployeeDTO corruptEmployee : corruptList){
                String corruptEmpId = corruptEmployee.getEmpId();
               if(originalEmpID.equals(corruptEmpId)){
                   cleanSet.remove(originalEmployee);
               }
            }
        }
        logger.log(Level.INFO, "Clean list created: " + cleanSet.size() + " employees added.");
    }

    public static void insertEmptodb() {
        int recCnt = 0;

        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());

        //Clear the table before inserting new records from the input file
        employeeDAO.truncateTable();

        //Loop through the cleanset of Arraylist and insert it into employees table
        for (EmployeeDTO emp : cleanSet) {

            employeeDAO.insertEmployee(emp.getEmpId(),
                    emp.getEmpId(),
                    emp.getFirstName(),
                    emp.getMiddleInitial(),
                    emp.getLastName(),
                    emp.getGender(),
                    emp.getEmail(),
                    emp.getDob(),
                    emp.getDoj(),
                    emp.getSalary());
            recCnt++;
        }
        logger.log(Level.INFO, "All records in the input file loaded into database, Total records :  " + recCnt);
    }

    public static int getEmpRecCntfromDB() {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());
        return employeeDAO.getdbEmpRec();
    }

    public static long getTime() {
        return System.nanoTime();
    }

}

