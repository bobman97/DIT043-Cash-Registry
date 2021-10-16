package Employees;


public class RegEmployee extends Employee{


    RegEmployee(String employeeID, String employeeName, double grossSalary, double netSalary) {
        super(employeeID, employeeName, grossSalary, netSalary);
    }


    @Override
    public double calculateSalary() {

        return 0;
    }



}



