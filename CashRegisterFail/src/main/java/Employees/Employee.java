package Employees;

public class Employee {
    private final String employeeID;
    private String employeeName;
    private double grossSalary;

    Employee(String employeeID, String employeeName, double grossSalary){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.grossSalary = grossSalary;


    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
}
