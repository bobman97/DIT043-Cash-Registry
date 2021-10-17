package Employees;

public class Employee {
    private final String employeeID;
    private String employeeName;
    private double grossSalary;
    private double netSalary;
    private double taxPercentage=0.1;//<- does this work?

    Employee(String employeeID, String employeeName, double grossSalary, double netSalary, double taxPercentage){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
        this.taxPercentage=taxPercentage;
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

    public void setTaxPercentage(double taxPercentage){this.taxPercentage = taxPercentage;}

    public double getTaxPercentage(double taxPercentage){return taxPercentage;}

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setNetSalary(double netSalary){this.netSalary=netSalary;}

    public double getNetSalary(){return netSalary;}

    public double calculateSalary(){return netSalary = grossSalary - (grossSalary * taxPercentage);}

    public String toString(){return (employeeName+"`s gross salary is "+grossSalary+" SEK per month.");}
}
