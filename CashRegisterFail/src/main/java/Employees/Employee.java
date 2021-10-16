package Employees;

public class Employee {
    private final String employeeID;
    private String employeeName;
    private double grossSalary;
    private double netSalary;

    Employee(String employeeID, String employeeName, double grossSalary, double netSalary){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
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

    public void setNetSalary(double netSalary){this.netSalary=netSalary;}

    public double getNetSalary(){return netSalary;}

    public double calculateSalary(){return netSalary = grossSalary - (grossSalary * 0.1);}

    public String toString(){return (employeeName+"`s gross salary is "+grossSalary+" SEK per month.");}
}
