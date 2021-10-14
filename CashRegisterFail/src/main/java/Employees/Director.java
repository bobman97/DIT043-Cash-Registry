package Employees;

public class Director extends Manager{
    public String degree;

    Director(String employeeID, String employeeName, double grossSalary, String degree) {
        super(employeeID, employeeName, grossSalary);
        this.degree = degree; //Example: BSc, MSc, pHD


    }
}
