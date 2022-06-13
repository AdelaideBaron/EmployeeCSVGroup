package com.sparta.ab;

public class EmployeeDTO {



    // DTO - Data Transfer Object
    private String empId;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private String dob; //private LocalDate dob;
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
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}
