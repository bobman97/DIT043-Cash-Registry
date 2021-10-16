package Employees;

public class Manager extends Employee{
    private String degree;
    Manager(String employeeID, String employeeName, double grossSalary, double netSalary, String degree) {
        super(employeeID, employeeName, grossSalary, netSalary);
        this.degree= degree;
    }

    public String toString(){return (degree+" "+super.toString());}

    public double calculateSalary() {//Needs to be double checked: degree can be changed and in turn netSalary.
        if(degree=="BSc."){
            this.setNetSalary(super.calculateSalary() * 1.10);
        }else if(degree=="MSc."){
            this.setNetSalary(super.calculateSalary() * 1.20);
        }else{
            this.setNetSalary(super.calculateSalary()*1.35);
        }
        return super.getNetSalary();
    }
}
