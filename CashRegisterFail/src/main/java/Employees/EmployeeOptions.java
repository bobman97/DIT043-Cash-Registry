package Employees;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import TransacHistory.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeOptions {
    SystemOutput printMenu;
    UserInput readIn;
    List<Transaction> employeeList;
    String ln= System.lineSeparator();

    public EmployeeOptions(){
        printMenu = new SystemOutput();
        readIn= new UserInput();
        employeeList = new ArrayList<>();
    }

    public void runEmployee(){
        int choice;
        do{
            do{
                printMenu.employeeMenu();
                choice=this.readIn.getUserOption(9,"Enter an option! ","Wrong option, try again a number between 0-10!"+ln);
            }while(choice>8||choice<(0));

            switch (choice){
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;

                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;

                case 7:

                    break;
                case 8:

                    break;

                case 9:

                    break;
            }
        } while(choice!=0);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception{
        String result = "";
        return result;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary,String degree) throws Exception{
        String result = "";
        return result;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception{
        String result = "";
        return result;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary,String degree, String dept) throws Exception{
        String result = "";
        return result;
    }

    public String printEmployee(String employeeID) throws Exception{
        String result= "";
        return result;
    }

    public double getNetSalary(String employeeID){
        double netSalary=0.00;
        return netSalary;
    }

    public String removeEmployee(String empID) throws Exception {
        String result= "";
        return result;
    }

    public String printAllEmployees() throws Exception {
        String result= "";
        return result;
    }

    public double getTotalNetSalary() throws Exception {
        double totalNetSalary=0.00;
        return totalNetSalary;
    }

    public String printSortedEmployees() throws Exception {
        String result= "";
        return result;
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        String result= "";
        return result;
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        String result= "";
        return result;
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        String result= "";
        return result;
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        String result= "";
        return result;
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        String result= "";
        return result;
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return null;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        String result= "";
        return result;

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        String result= "";
        return result;
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        String result= "";
        return result;
    }

}
