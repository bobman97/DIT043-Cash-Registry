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
    private List<Employee> employeeList;
    String ln= System.lineSeparator();
    SystemOutput sout;
    private int bonus = 0;

    public EmployeeOptions(){
        printMenu = new SystemOutput();
        readIn= new UserInput();
        employeeList = new ArrayList<>();
        sout=new SystemOutput();
    }

    public void runEmployee() throws Exception {
        int choice;
        do{
            do{
                printMenu.employeeMenu();
                choice=this.readIn.getUserOption(9,"Enter an option! ","Wrong option, try again a number between 0-10!"+ln);
            }while(choice>9||choice<(0));

            switch (choice){
                case 0:

                    break;
                case 1:{
                    String employeeID = newID();
                    String employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
                    double grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
                    System.out.println(createEmployee(employeeID,employeeName,grossSalary));
                    break;
                }

                case 2:{
                    String employeeID = newID();
                    String employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
                    double grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
                    String degree = validDegree();
                    System.out.println(createEmployee(employeeID,employeeName,grossSalary,degree));
                    break;
                }
                case 3:{
                    String employeeID = newID();
                    String employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
                    double grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
                    String degree = validDegree();
                    String dept = newDept();
                    System.out.println(createEmployee(employeeID,employeeID,grossSalary,degree,dept));
                    break;
                }
                case 4:{
                    String employeeID = newID();
                    String employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
                    double grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
                    int gpa = validGPA();
                    System.out.println(createEmployee(employeeID,employeeName,grossSalary,gpa));
                    break;
                }
                case 5:{
                    String employeeID=existingID();
                    System.out.println(removeEmployee(employeeID));
                    break;
                }
                case 6:{
                    String employeeID=existingID();
                    System.out.println(printEmployee(employeeID));
                    break;
                }
                case 7:
                    System.out.println(printAllEmployees());
                    break;
                case 8:
                    System.out.println(getTotalNetSalary());
                    break;

                case 9:
                    System.out.println(printSortedEmployees());
                    break;
            }
        } while(choice!=0);
    }

    // FACADE TEST REQUIRED METHODS
    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty) throws Exception{

        employeeList.add(new Employee(employeeIDEmpty,employeeNameEmpty,grossSalaryEmpty));
        updateSalary();
        String result = "Employee "+ employeeIDEmpty+" was registered successfully.";
        return result;
    }

    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty,String degreeEmpty) throws Exception{

        employeeList.add(new Manager(employeeIDEmpty,employeeNameEmpty,grossSalaryEmpty,degreeEmpty));
        updateSalary();
        String result = "Employee "+ employeeIDEmpty+" was registered successfully.";
        return result;
    }

    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty, int gpaEmpty) throws Exception{

        employeeList.add(new Intern(employeeIDEmpty,employeeNameEmpty,grossSalaryEmpty,gpaEmpty));
        updateSalary();
        String result = "Employee "+ employeeIDEmpty+" was registered successfully.";
        return result;
    }

    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty,String degreeEmpty, String deptEmpty) throws Exception{

        employeeList.add(new Director(employeeIDEmpty,employeeNameEmpty,grossSalaryEmpty,degreeEmpty,deptEmpty));
        updateSalary();
        String result = "Employee "+ employeeIDEmpty+" was registered successfully.";
        return result;
    }

    public String printEmployee(String employeeID) throws Exception{
        return employeeList.get(findIndex(employeeID)).toString();
    }

    public double getNetSalary(String employeeID){
        double netSalary=0.00;
        for(int i = 0;i<employeeList.size();i++){
            if(employeeID.equals(employeeList.get(i).getEmployeeID())){
                netSalary=employeeList.get(i).calculateSalary();
            }
        }
        return netSalary;
    }

    public String removeEmployee(String empID) throws Exception {
        int indexTemp = findIndex(empID);
        employeeList.remove(indexTemp);
        String result= "Employee "+empID+" was successfully removed.";
        return result;
    }

    public String printAllEmployees() throws Exception {
        String result= "All registered employees: "+ln;
        for(int i = 0;i<employeeList.size();i++){
            result+=employeeList.get(i).toString()+ln;
        }
        return result;
    }

    public double getTotalNetSalary() throws Exception {
        double totalNetSalary=0.00;
        for(int i =0;i<employeeList.size();i++){
            totalNetSalary+=employeeList.get(i).calculateSalary();
        }
        return totalNetSalary;
    }

    public String printSortedEmployees() throws Exception {
        String result= "Employees sorted by gross salary (ascending order):"+ln;
        double temp = 0;
        double [] grossSort= new double[employeeList.size()];
        for(int i=0; i < employeeList.size(); i++){
            grossSort[i]=employeeList.get(i).getGrossSalary();
        }
        for(int i=0; i < employeeList.size(); i++){
            for(int j=1; j < (employeeList.size()-i); j++){
                if(grossSort[j-1] > grossSort[j]){
                    temp = grossSort[j-1];
                    grossSort[j-1] = grossSort[j];
                    grossSort[j] = temp;
                }

            }
        }
        for(int i = 0; i<employeeList.size();i++) {
            for (int j = 0; j < employeeList.size(); j++) {
                if (grossSort[i] == employeeList.get(j).getGrossSalary()) {
                    result += employeeList.get(j).toString() + ln;
                }
            }
        }

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

    //GENERAL UTILITY CODE**************************************************************

    //ASKAN BELOW HERE
    public String newID(){
        boolean duplicate = false;
        String userIn="";
        int tempIndex=0;
        do{
            userIn=readIn.readString("Please give ID of the employee: ","You have given a wrong ID");

            for(int i = 0;i<employeeList.size();i++){
                if(userIn.equals(employeeList.get(i).getEmployeeID())){
                    duplicate=true;
                    tempIndex=i;
                }
            }
            if(duplicate){
                if (!userIn.equals(employeeList.get(tempIndex).getEmployeeID())){
                    duplicate=false;
                }
            }
            if (duplicate){
                System.out.println(ln+"You have given a duplicate ID, please give a new one!"+ln);
            }

        }while(duplicate);

        return userIn;
    }

    public String existingID(){
        boolean duplicate = true;
        String userIn="";
        int tempIndex=0;
        do{
            userIn=readIn.readString("Please give ID of the employee: ","You have given a wrong ID");

            for(int i = 0;i<employeeList.size();i++){
                if(!userIn.equals(employeeList.get(i).getEmployeeID())){
                    duplicate=false;
                    tempIndex=i;
                }
            }
            if(!duplicate){
                if (userIn.equals(employeeList.get(tempIndex).getEmployeeID())){
                    duplicate=true;
                }
            }
            if (!duplicate){
                System.out.println(ln+"You have given a non-existing ID, please give a existing one!"+ln);
            }

        }while(!duplicate);

        return userIn;
    }

    public int validGPA(){
        int gpa;
        do {
            gpa=readIn.readInt("Please give GPA of the employee: ","You have given a wrong GPA");
            if(gpa>10){
                System.out.println(ln+"You have given a invalid GPA, please give a new one!"+ln);
            }
        }while(gpa>10&&gpa>0);
        return gpa;
    }

    public String validDegree(){
        String degree;
        boolean degreeConditions=false;
        do {
            degree=readIn.readString("Please give degree of the employee: ","You have given a wrong degree");
            if (!degree.equals("BSc.")||!degree.equals("MSc.")||!degree.equals("PhD")){
                degreeConditions=true;
            }
            if (degree.equals("BSc.")||degree.equals("MSc.")||degree.equals("PhD")){
                degreeConditions=false;
            }
            if(degreeConditions){
                System.out.println(ln+"You have given a invalid degree, please give a new one!"+ln);
            }

        }while(degreeConditions);
        return degree;
    }

    public String newDept(){// MAKE IT SO THAT NO OVERLAPPING DIRECTORS FOR SAME DEPARTMENT
        boolean duplicate = false;
        String userIn="";
        int tempIndex=0;
        do{
            userIn=readIn.readString("Please give department of the employee: ","You have given a wrong department");

            for(int i = 0;i<employeeList.size();i++){
                if(employeeList.get(i) instanceof Director){
                    String department=((Director) employeeList.get(i)).getDept();
                    if(department.equals(userIn)){
                        duplicate=true;
                        tempIndex=i;
                    }
                }
            }
            if(duplicate){
                if(employeeList.get(tempIndex) instanceof Director){
                    String department=((Director) employeeList.get(tempIndex)).getDept();
                    if(!department.equals(userIn)){
                        duplicate=false;
                    }
                }
            }
            if (duplicate){
                System.out.println(ln+"You have given a duplicate department, please give a new one!"+ln);
            }

        }while(duplicate);

        return userIn;
    }

    public int findIndex(String id){
        int index=0;
        for(int i = 0;i<employeeList.size();i++){
            if(id.equals(employeeList.get(i).getEmployeeID())){
                index=i;
            }
        }
        return index;
    }

    public void updateSalary(){
        for(int i = 0;i<employeeList.size();i++){
            employeeList.get(i).calculateGrossSalary();
        }
    }



    //WILLIAM BELOW HERE
}
