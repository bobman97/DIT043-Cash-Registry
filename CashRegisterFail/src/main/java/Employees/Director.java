package Employees;

public class Director extends Manager{
    private String dept;

    Director(String employeeID, String employeeName, double grossSalary, String degree, String dept) {
        super(employeeID, employeeName, grossSalary, degree);
        this.dept = dept; //Example: BSc, MSc, pHD
    }

    public String toString(){return (super.toString()+" Dept: "+dept);}

    public double calculateGrossSalary(){
        if(!super.getAlreadyExcected()){
            setBonus(5000);
            return super.calculateGrossSalary();
        }else {
            return super.getGrossSalary();
        }
    }

    public double calculateSalary() {

        if(getGrossSalary()<30000){
            setTaxPercentage(0.1);
            super.calculateSalary();
        }else if(30000<=getGrossSalary()&&getGrossSalary()<50000){
            setTaxPercentage(0.2);
        }else{
            setTaxPercentage(0.35);
        }
        return super.calculateSalary();
    }

    public String getDept(){return dept;}

    public void setDept(String dept){this.dept =dept;}
}
