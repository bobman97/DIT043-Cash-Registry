package Employees;
import CashRegister.SystemOutput;

public class Employee {
    private final String employeeID;
    private String employeeName;
    private double grossSalary;
    private double netSalary;
    private double taxPercentage=0.1;//<- does this work?
    SystemOutput sout;
    private boolean alreadyExcecuted = false;

    Employee(String employeeID, String employeeName, double grossSalary){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.grossSalary = grossSalary;
        sout=new SystemOutput();
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

    public boolean getAlreadyExcected(){return alreadyExcecuted;}

    public void setAlreadyExcecuted(){alreadyExcecuted=true;}

    public double calculateGrossSalary(){return grossSalary;}

    public double calculateSalary(){return netSalary = grossSalary - (grossSalary * taxPercentage);}

    private double roundDecimal(double value)  {return ((double)((long)(value * 100)))/100;}

    public String toString(){
        String soutGrossSalary = sout.decimalFix(roundDecimal(grossSalary));
        if(grossSalary==0.0){
            soutGrossSalary="0.00";
        }
        return (employeeName+"'s gross salary is "+soutGrossSalary+" SEK per month.");
    }
}
