package com.sparta.ab.model;

import java.util.logging.Level;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeDTO {


    // DTO - Data Transfer Object
    private String empId;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private String dob;
    private String dateOfJoining;
    private String salary;

    public EmployeeDTO(String empId, String namePrefix,
                       String firstName, String middleInitial,
                       String lastName, String gender,
                       String email, String dob,
                       String dateOfJoining, String salary) {
        this.empId = empId;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    public EmployeeDTO(String[] argArray) {

        this.empId = argArray[0];
        this.namePrefix = argArray[1];
        this.firstName = argArray[2];
        this.middleInitial = argArray[3];
        this.lastName = argArray[4];
        this.gender = argArray[5];
        this.email = argArray[6];
        this.dob = argArray[7];
        this.dateOfJoining =  argArray[8];
        this.salary = argArray[9];
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId='" + empId + '\'' +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }


    public String getEmpId() {
        logger.log(Level.FINE, "returning empID");
        return empId;
    }

    public String getMiddleInitial() {
        logger.log(Level.FINE, "returning middle initial");
        return middleInitial;
    }


    public String getGender() {
        logger.log(Level.FINE, "returning gender");
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        logger.log(Level.FINE, "returning email");
        return email;
    }
    public String getDob() {
        logger.log(Level.FINE, "returning dob");
        return dob;
    }

    public String getDoj() {
        logger.log(Level.FINE, "returning doj");
        return dob;
    }

    public String getSalary() {
        logger.log(Level.FINE, "returning salary");
        return salary;
    }

    public String getFirstName() {
        logger.log(Level.FINE, "returning firstname");
        return firstName;
    }

    public String getLastName() {
        logger.log(Level.FINE, "returning lastname");
        return lastName;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}