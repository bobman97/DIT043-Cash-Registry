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

    public EmployeeOptions(){
        printMenu = new SystemOutput();
        readIn= new UserInput();
        employeeList = new ArrayList<>();
    }

    public void runEmployee() throws Exception {
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
                    System.out.println(createEmployee("","",0));
                    break;
                case 2:
                    System.out.println(createEmployee("","",0,""));
                    break;
                case 3:
                    System.out.println(createEmployee("","",0,"",""));
                    break;

                case 4:
                    System.out.println(createEmployee("","",0,0));
                    break;
                case 5:
                    System.out.println(removeEmployee(""));
                    break;
                case 6:
                    System.out.println(printEmployee(""));
                    break;

                case 7:
                    System.out.println(printAllEmployees());
                    break;
                case 8:
                    System.out.println(getTotalNetSalary());
                    break;

                case 9:

                    break;
            }
        } while(choice!=0);
    }

    // FACADE TEST REQUIRED METHODS
    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty) throws Exception{
        String employeeID="";
        String employeeName="";
        double grossSalary=0.00;

        employeeID = newID();
        employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
        grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");

        employeeList.add(new Employee(employeeID,employeeName,grossSalary));
        String result = ln+"Employee "+ employeeID+" was registered successfully.";
        return result;
    }

    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty,String degreeEmpty) throws Exception{
        String employeeID="";
        String employeeName="";
        double grossSalary=0.00;
        String degree="";

        employeeID = newID();
        employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
        grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
        degree = validDegree();

        employeeList.add(new Manager(employeeID,employeeName,grossSalary,degree));
        String result = ln+"Employee "+ employeeID+" was registered successfully.";
        return result;
    }

    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty, int gpaEmpty) throws Exception{
        String employeeID="";
        String employeeName="";
        double grossSalary=0.00;
        int gpa=0;

        employeeID = newID();
        employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
        grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
        gpa = validGPA();

        employeeList.add(new Intern(employeeID,employeeName,grossSalary,gpa));
        String result = ln+"Employee "+ employeeID+" was registered successfully.";
        return result;
    }

    public String createEmployee(String employeeIDEmpty, String employeeNameEmpty, double grossSalaryEmpty,String degreeEmpty, String deptEmpty) throws Exception{
        String employeeID="";
        String employeeName="";
        double grossSalary=0.00;
        String degree="";
        String dept="";

        employeeID = newID();
        employeeName = readIn.readString("Please give name of the employee: ", "You have given an invalid name");
        grossSalary = readIn.readDouble("Please give gross-salary of the employee: ", "You have given a invalid gross-salary");
        degree = validDegree();
        dept = newDept();


        employeeList.add(new Director(employeeID,employeeName,grossSalary,degree,dept));
        String result = ln+"Employee "+ employeeID+" was registered successfully.";
        return result;
    }

    public String printEmployee(String employeeID) throws Exception{
        return employeeList.get(findIndex(existingID())).toString();
    }

    public double getNetSalary(String employeeID){
        double netSalary=0.00;
        return netSalary;
    }

    public String removeEmployee(String empID) throws Exception {

        int indexTemp = findIndex(existingID());
        System.out.println(employeeList.size());
        employeeList.remove(indexTemp);
        System.out.println(employeeList.size());
        String result= "Employee <ID> was successfully removed.";
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
        String result= "";
        int temp = 0;
        for(int i=0; i < employeeList.size(); i++){
            for(int j=1; j < (employeeList.size()-i); j++){


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



    //WILLIAM BELOW HERE
}
