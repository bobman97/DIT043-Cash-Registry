package Employees;

public class Intern extends Employee{
    int GPA;
    Intern(String employeeID, String employeeName, double grossSalary, double netSalary, int GPA ) {
        super(employeeID, employeeName, grossSalary, netSalary);
        this.GPA = GPA;
    }

    public String toString(){return super.toString()+" GPA: "+GPA;}

    public double calculateSalary() {

        if(GPA<=5){
            super.setGrossSalary(0);
            super.setNetSalary(0);
        }else if(5<GPA&&GPA<8){
            super.setNetSalary(super.getGrossSalary());
        }else{
            super.setNetSalary(super.getGrossSalary()+1000);
        }

        return super.getNetSalary();
    }
}
