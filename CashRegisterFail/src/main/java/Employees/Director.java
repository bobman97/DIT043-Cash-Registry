package Employees;

public class Director extends Manager{
    private String dept;

    Director(String employeeID, String employeeName, double grossSalary, String degree, double netSalary, String dept, double taxPercentage) {
        super(employeeID, employeeName, grossSalary, netSalary, degree, taxPercentage);
        this.dept = dept; //Example: BSc, MSc, pHD
    }

    public String toString(){return (super.toString()+" Dept: "+dept);}

    public double calculateSalary() {
        setGrossSalary(super.calculateGrossSalary()+5000);
        if(getGrossSalary()<30000){
            super.calculateSalary();
        }else if(30000<=getGrossSalary()&&getGrossSalary()<50000){
            setTaxPercentage(0.2);
            setNetSalary(super.calculateGrossSalary());
        }else{
            setTaxPercentage(0.35);
            setNetSalary(super.calculateGrossSalary());
        }
        return super.calculateSalary();
    }

    public String getDept(){return dept;}

    public void setDept(){this.dept =dept;}
}
