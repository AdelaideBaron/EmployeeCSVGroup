package com.sparta.ab.crud;

public class CRUD {
    public static void main() {
        EmployeeDAO employeeDAO = new EmployeeDAO(ConnectionManager.getConnection());
        employeeDAO.insertEmployee("1", "Mrs", "Lee", "D", "Halperin", "M", "leehalperin@gmail.com", "09/01/1998", "20/05/2022", "500000");
        employeeDAO.printAllEmployees();
        ConnectionManager.closeConnection();

    }

}
