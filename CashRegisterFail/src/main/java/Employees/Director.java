package Employees;

public class Director extends Manager{
    private String dept;

    Director(String employeeID, String employeeName, double grossSalary, String degree, String dept) {
        super(employeeID, employeeName, grossSalary, degree);
        this.dept = dept; //Example: BSc, MSc, pHD
    }

    public String toString(){return (super.toString()+" Dept: "+dept);}

    public double calculateSalary() {
        setBonus(5000);
        return super.calculateSalary();
    }

    public String getDept(){return dept;}

    public void setDept(String dept){this.dept =dept;}
}
