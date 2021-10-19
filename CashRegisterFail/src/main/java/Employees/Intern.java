package Employees;

public class Intern extends Employee{
    int GPA;
    Intern(String employeeID, String employeeName, double grossSalary, int GPA) {
        super(employeeID, employeeName, grossSalary);
        this.GPA = GPA;
    }

    public String toString(){return super.toString()+" GPA: "+GPA;}

    public double calculateGrossSalary() {
        if(!super.getAlreadyExcected()){
            if (GPA <= 5) {
                super.setGrossSalary(0);
                super.setNetSalary(0);
                super.calculateSalary();
            } else if (5 < GPA && GPA <= 8) {
                super.calculateSalary();
            } else if (GPA > 8) {
                super.setGrossSalary(super.getGrossSalary() + 1000);
            }
            setAlreadyExcecuted();
            return getGrossSalary();
        }else {
            return getGrossSalary();
        }
    }
    public double calculateSalary() {
        setTaxPercentage(0);
        return super.calculateSalary();
    }

    public int getGPA(){return GPA;}

    public void setGPA(){this.GPA=GPA;}
}
