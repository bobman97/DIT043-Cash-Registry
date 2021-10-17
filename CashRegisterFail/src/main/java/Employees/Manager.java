package Employees;

public class Manager extends Employee{
    private String degree;
    Manager(String employeeID, String employeeName, double grossSalary, double netSalary, String degree, double taxPercentage) {
        super(employeeID, employeeName, grossSalary, netSalary, taxPercentage);
        this.degree= degree;
    }

    public String toString(){return (degree+" "+super.toString());}

    public double calculateGrossSalary(){
        if(degree.equals("BSc.")){
            setGrossSalary(getGrossSalary()*1.1);
        }else if(degree.equals("MSc.")){
            setGrossSalary(getGrossSalary()*1.2);
        }else{
            setGrossSalary(getGrossSalary()*1.35);
        }
        return super.getGrossSalary();
    }

    public String getDegree(){return degree;}

    public void setDegree(){this.degree=degree;}

    /*public double calculateSalary() {//Needs to be double checked: degree can be changed and in turn netSalary.
        if(degree.equals("BSc.")){
            calculateGrossSalary();
            this.setNetSalary(super.calculateSalary());
        }else if(degree.equals("MSc.")){
            setGrossSalary(getGrossSalary()*1.2);
            this.setNetSalary(super.calculateSalary());
        }else{
            setGrossSalary(getGrossSalary()*1.35);
            this.setNetSalary(super.calculateSalary());
        }
        return super.getNetSalary();
    }*/
}
