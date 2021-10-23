package Employees;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import TransacHistory.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeOptions {
    SystemOutput printMenu;
    UserInput readIn;
    private List<Employee> employeeList;
    String ln= System.lineSeparator();
    SystemOutput sout;
    private int bonus = 0;

    final int modifyReg = 0;
    final int modifyIntern = 1;
    final int modifyManager = 2;
    final int modifyDirector  = 3;
    final int modifyName = 0;
    final int modifyPromote = 1;
    final int modifySalary = 2;
    final int modifyDep = 3;
    final int modifyDeg = 4;
    final int modifyGPA = 5;

    final String emptyDeg = null;
    final String emptyDep = null;
    final String emptyName = null;
    final int emptyGPA = -1;
    final int emptySalary = -1;



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
                    System.out.println(createEmployee(employeeID,employeeName,grossSalary,degree,dept));
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
                    String employeeID=validID();
                    System.out.println(removeEmployee(employeeID));
                    break;
                }
                case 6:{
                    String employeeID=validID();
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
        if (fetchEmployee(employeeID)==null){
            throw new Exception("Employee " + employeeID + " was not registered yet.");
        } else {
            updateSalary();
            return fetchEmployee(employeeID).toString();
        }
    }

    public double getNetSalary(String employeeID) throws Exception{
        if (employeeList.isEmpty()){
            throw new Exception("Employee " + employeeID + " was not registered yet.");
        }
        double netSalary=0.00;
        for(int i = 0;i<employeeList.size();i++){
            if(employeeID.equals(employeeList.get(i).getEmployeeID())){
                netSalary=employeeList.get(i).calculateSalary();
            }
        }
        return netSalary;
    }


    public String removeEmployee(String empID) throws Exception {
        if (fetchEmployee(empID) == null) {
            throw new Exception("Employee " + empID + " was not registered yet.");
        } else {
            employeeList.remove(fetchEmployee(empID));
            String result = "Employee " + empID + " was successfully removed.";
            return result;
        }
    }


    public String printAllEmployees() throws Exception {
        if (employeeList.isEmpty()){
            throw new Exception("No employees registered yet.");
        } else {
            String result = "All registered employees:" + ln;
            for (int i = 0; i < employeeList.size(); i++) {
                result += employeeList.get(i).toString() + ln;
            }
            return result;
        }
    }

    public double getTotalNetSalary() throws Exception {
    if (employeeList.isEmpty()){
        throw new Exception("No employees registered yet.");
    }
        double totalNetSalary=0.00;
        for(int i =0;i<employeeList.size();i++){
            totalNetSalary+=employeeList.get(i).calculateSalary();
        }

        return roundDecimal(totalNetSalary);
    }

    public String printSortedEmployees() throws Exception {
        if (employeeList.isEmpty()){
            throw new Exception("No employees registered yet.");
        } else {
            String result = "Employees sorted by gross salary (ascending order):" + ln;
            double temp = 0;
            double[] grossSort = new double[employeeList.size()];
            for (int i = 0; i < employeeList.size(); i++) {
                grossSort[i] = employeeList.get(i).getGrossSalary();
            }
            for (int i = 0; i < employeeList.size(); i++) {
                for (int j = 1; j < (employeeList.size() - i); j++) {
                    if (grossSort[j - 1] > grossSort[j]) {
                        temp = grossSort[j - 1];
                        grossSort[j - 1] = grossSort[j];
                        grossSort[j] = temp;
                    }

                }
            }
            for (int i = 0; i < employeeList.size(); i++) {
                for (int j = 0; j < employeeList.size(); j++) {
                    if (grossSort[i] == employeeList.get(j).getGrossSalary()) {
                        result += employeeList.get(j).toString() + ln;
                    }
                }
            }

            return result;
        }
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        int modify = getModifyValue(empID);

        if (newName.isBlank()){
            throw new Exception("Name cannot be blank.");
        }
        modifyEmployee(empID, newName, emptyDeg, emptyDep, emptySalary, emptyGPA, modify);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (newGPA < 0 || newGPA > 10) {
            throw new Exception(newGPA + " outside range. Must be between 0-10.");
        }
        modifyEmployee(empID, emptyName, emptyDeg, emptyDep, emptySalary, newGPA, modifyIntern);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (!newDegree.equals("BSc") && !newDegree.equals("MSc") && !newDegree.equals("PhD")){
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
        int modify = getModifyValue(empID); // This method used by both director and manager.

        modifyEmployee(empID, emptyName, newDegree, emptyDep, emptySalary, emptyGPA, modify);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (!newDepartment.equals("Business") && !newDepartment.equals("Human Resources") && !newDepartment.equals("Technical")){
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
        modifyEmployee(empID, emptyName, emptyDeg, newDepartment, emptySalary, emptyGPA, modifyDirector);
        return "Employee " + empID + " was updated successfully";
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (newSalary <= 0){
            throw new Exception("Salary must be greater than zero.");
        }
        String deg, dep;
        int index = findIndex(empID);
        int modify = getModifyValue(empID);

        modifyEmployee(empID, emptyName, emptyDeg, emptyDep, newSalary, emptyGPA, modify);
        return "Employee " + empID + " was updated successfully";
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        if (employeeList.isEmpty()) {
            throw new Exception("No employees registered yet.");
        }
        Map<String, Integer> map = new HashMap();
        int BSc = 0;
        int MSc = 0;
        int PhD = 0;
        for(int i = 0; i < employeeList.size(); i++)    {
            String deg = "";
            if(employeeList.get(i) instanceof Manager)
                deg = ((Manager) employeeList.get(i)).getDegree();
            else if(employeeList.get(i) instanceof Director)
                deg = ((Director) employeeList.get(i)).getDegree();
            switch(deg) {
                case "BSc":
                    BSc++;
                    break;
                case "MSc":
                    MSc++;
                    break;
                case "PhD":
                    PhD++;
            }
        }
        if(BSc > 0)
            map.put("BSc", BSc);
        if(MSc > 0)
            map.put("MSc", MSc);
        if(PhD > 0)
            map.put("PhD", PhD);
        return map;
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")){
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
        modifyEmployee(empID, emptyName, degree, emptyDep, emptySalary, emptyGPA, modifyManager);
        return empID + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (!department.equals("Business") && !department.equals("Human Resources") && !department.equals("Technical")){
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
        modifyEmployee(empID, emptyName, degree, department, emptySalary, emptyGPA, modifyDirector);
        return empID + " promoted successfully to Director.";
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        if (fetchEmployee(empID)==null){
            throw new Exception("Employee " + empID + " was not registered yet.");
        }
        if (gpa < 0 || gpa > 10) {
            throw new Exception(gpa + " outside range. Must be between 0-10.");
        }
        modifyEmployee(empID, emptyName, emptyDeg, emptyDep, emptySalary, gpa, modifyIntern);
        return empID + " promoted successfully to Intern.";
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

    public String validID(){
        boolean duplicate = true;
        String userIn;
        String valid="";
        do{
            userIn=readIn.readString("Please give ID of the employee: ","You have given a wrong ID");

            for(int i =0;i<employeeList.size();i++){
                if(userIn.equals(employeeList.get(i).getEmployeeID())){
                    valid=userIn;
                    duplicate=false;
                }
            }
            if(duplicate){
                System.out.println("You have given a non-existing ID, please try again!");
            }
            if(duplicate){
                for(int i =0;i<employeeList.size();i++){
                    if(!userIn.equals(employeeList.get(i).getEmployeeID())){
                        duplicate=true;
                    }
                }
            }

        }while(duplicate);
        return valid;
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
            if (!degree.equals("BSc")||!degree.equals("MSc")||!degree.equals("PhD")){
                degreeConditions=true;
            }
            if (degree.equals("BSc")||degree.equals("MSc")||degree.equals("PhD")){
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

    public Employee fetchEmployee(String empID){
        for (Employee employees : employeeList) {
            if (employees.getEmployeeID().equals(empID)) {
                return employees;
            }
        }
        return null;
    }

    public void updateSalary(){
        for(int i = 0;i<employeeList.size();i++){
            employeeList.get(i).calculateGrossSalary();
        }
    }

    private double roundDecimal(double value)  {return ((double)((long)(value * 100)))/100;}


    //WILLIAM BELOW HERE


    // Since there is an issue with set methods that were created in all the employee classes
    // I had to work around it by creating a new object everytime I want to modify something about an employee
    // Therefore I might as well have only one central method for handling all the updates and changes to employees
    public void modifyEmployee(String empID, String name, String degree, String department, double salary, int gpa, int empType) throws Exception {
        String newName, newDeg, newDep;
        int newGPA, index;
        double newSalary;
        Employee tempEmploy;

        // create a temporary reference to existing object to fetch existing values
        index = findIndex(empID);
        tempEmploy = employeeList.get(index);

         // Check if new attributes were sent as parameters, else fetch old ones:
        newName = (name != null ? name : tempEmploy.getEmployeeName());
        newSalary = (salary > 0 ? salary : tempEmploy.getRawSalary());

        // Check if new attributes were sent as parameters, else initialize as current value if manager/director or null/-1
        newDeg = (degree != null ? degree : (empType > 1 ? (empType == 2 ? ((Manager) tempEmploy).getDegree() : ((Director) tempEmploy).getDegree()) : emptyDeg));
        newDep = (department != null ? department : (empType == 3 ? ((Director) tempEmploy).getDept() : emptyDep));
        newGPA = (gpa > 0 ? gpa : (empType == 1 ? ((Intern)tempEmploy).getGPA() : emptyGPA));

        // Which employee type to create:
        switch(empType) {
            case modifyReg: // Modify regular
                employeeList.set(index, new Employee(empID, newName, newSalary));
                break;
            case modifyIntern: // Modify intern or promote to intern
                employeeList.set(index, new Intern(empID, newName, newSalary, newGPA));
                break;
            case modifyManager: // Modify Manager or promote to Manager
                employeeList.set(index, new Manager(empID, newName, newSalary, newDeg));
                break;
            case modifyDirector: // Modify Director or promote to Director
                employeeList.set(index, new Director(empID, newName, newSalary, newDeg, newDep));
                break;
        }

        // Update salary to calculate gross after creating new objects
        updateSalary();
    }

    public int getModifyValue(String empID) {
        int index = findIndex(empID);
        if(employeeList.get(index) instanceof Director)
            return modifyDirector;
        else if(employeeList.get(index) instanceof Intern)
            return modifyIntern;
        if(employeeList.get(index) instanceof Manager)
            return modifyManager;
        else
            return modifyReg;
    }
}
