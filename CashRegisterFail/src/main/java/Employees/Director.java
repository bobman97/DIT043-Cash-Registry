package Employees;

public class Director extends Manager{
    private String dept;

    Director(String employeeID, String employeeName, double grossSalary, String degree, double netSalary, String dept) {
        super(employeeID, employeeName, grossSalary, netSalary, degree);
        this.dept = dept; //Example: BSc, MSc, pHD
    }

    public String toString(){return (super.toString()+" Dept: "+dept);}

    public double calculateSalary() {

        return 0;
    }
}
