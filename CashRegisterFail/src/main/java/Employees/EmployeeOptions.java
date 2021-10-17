package Employees;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import TransacHistory.Transaction;

import java.util.ArrayList;
import java.util.List;

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


}
