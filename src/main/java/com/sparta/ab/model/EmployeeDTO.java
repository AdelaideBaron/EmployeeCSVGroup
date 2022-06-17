package com.sparta.ab.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import static com.sparta.ab.logging.LogConfig.logger;

public class EmployeeDTO {


    // DTO - Data Transfer Object
    String empId;
    String namePrefix;
    String firstName;
    String middleInitial;
    String lastName;
    String gender;
    String email;
    LocalDate dob;
    LocalDate dateOfJoining;
    Integer salary;

    public EmployeeDTO(String empId, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, String dob, String dateOfJoining, String salary) {
        this.empId = empId;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        setDob(dob);
        setDateOfJoining(dateOfJoining);
        setSalary(salary);
    }

    public EmployeeDTO(String empId, String namePrefix,
                       String firstName, String middleInitial,
                       String lastName, String gender,
                       String email, LocalDate dob,
                       LocalDate dateOfJoining, Integer salary) {
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


    public String getEmpId() {
        logger.log(Level.FINE, "returning empID");
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getNamePrefix() {
        logger.log(Level.FINE, "returning name prefix");
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public String getFirstName() {
        logger.log(Level.FINE, "returning first name");
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        logger.log(Level.FINE, "returning middle initial");
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        logger.log(Level.FINE, "returning last name");
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        logger.log(Level.FINE, "returning dob");
        return dob;
    }

    public void setDob(String dob) {
        this.dob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
    }

    public LocalDate getDateOfJoining() {
        logger.log(Level.FINE, "returning date of joining");
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = LocalDate.parse(dateOfJoining, DateTimeFormatter.ofPattern("M[M]/d[d]/yyyy"));
    }

    public Integer getSalary() {
        logger.log(Level.FINE, "returning salary");
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = Integer.valueOf(salary);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return empId + "," +
                namePrefix + "," +
                firstName + "," +
                middleInitial + "," +
                lastName + "," +
                gender + "," +
                email + "," +
                dob.format(formatter) + "," +
                dateOfJoining.format(formatter) + "," +
                salary;
    }

}
