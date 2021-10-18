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
        return super.calculateSalary();
    }

    public String getDegree(){return degree;}

    public void setDegree(){this.degree=degree;}

    public void setBonus(int bonus){this.bonus=bonus;}


}
