package com.sparta.lh;


public class EmployeeDTO {
    //want to create fields that represent CSV file
    //in CSV all values seen as strings
    //below are fields that represent entries for a given employee
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
    public EmployeeDTO(String empId, String namePrefix, String firstName, String middleInitial, String lastName,
                       String gender, String email, String dob, String dateOfJoining, String salary) {
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
    public EmployeeDTO (String[] fileRecord) {
        this.empId = fileRecord[0];
        this.namePrefix = fileRecord[1];
        this.firstName = fileRecord[2];
        this.middleInitial = fileRecord[3];
        this.lastName = fileRecord[4];
        this.gender = fileRecord[5];
        this.email = fileRecord[6];
        this.dob = fileRecord[7];
        this.dateOfJoining = fileRecord[8];
        this.salary = fileRecord[9];
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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
}