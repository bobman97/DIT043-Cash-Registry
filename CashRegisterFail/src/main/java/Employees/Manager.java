package Employees;

public class Manager extends Employee{
    private String degree;
    private int bonus=0;
    Manager(String employeeID, String employeeName, double grossSalary, String degree) {
        super(employeeID, employeeName, grossSalary);
        this.degree= degree;
    }

    public String toString(){return (degree+" "+super.toString());}

    public double calculateGrossSalary(){
        if(degree.equals("BSc.")){
            setGrossSalary(getGrossSalary()*1.1+bonus);
        }else if(degree.equals("MSc.")){
            setGrossSalary(getGrossSalary()*1.2+bonus);
        }else{
            setGrossSalary(getGrossSalary()*1.35+bonus);
        }
        return super.getGrossSalary();
    }

    public double calculateSalary(){
        calculateGrossSalary();
        return super.calculateSalary();}

    public String getDegree(){return degree;}

    public void setDegree(String degree){this.degree=degree;}

    public void setBonus(int bonus){this.bonus=bonus;}




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
