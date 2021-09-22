package CashRegister;


import ItemOptions.ItemOptions;
import TransacHistory.TransacHistory;

public class CashRegister {
    static UserInput readIn;
    static ItemOptions shop;
    static SystemOutput printMenu;

    public static void main(String[] args) {
        runProgram();
    }

    static void runProgram()    {
        printMenu = new SystemOutput();
        readIn = new UserInput();
        int option;


        // Menu will loop until program closes
        do {
            // Print main menu
            printMenu.mainMenu();

            // Ask user to select an option
            option = readIn.getUserOption(3, "Enter an option: ", "Invalid input!");

            switch(option)  {
                case 0:
                    exitProgram();
                    break;
                case 1:
                    itemOptions();
                    break;
                case 2:
                    break;
                case 3:
                    TransacHistory transactions = new TransacHistory();
                    transactions.printHello();
                    break;
                default:
                    callError();
            }
        }while(option != 0);

    }

    static void itemOptions()   {
        int option;
        do {
            // Print main menu
            printMenu.itemMenu();

            // Ask user to select an option
            option = readIn.getUserOption(5, "Enter an option: ", "Invalid input!");

            switch(option)  {
                case 0:
                    break;
                case 1:
                    itemOptions();
                    break;
                case 2:
                    break;
                case 3:
                    TransacHistory transactions = new TransacHistory();
                    transactions.printHello();
                    break;
                default:
                    callError();
            }
        }while(option != 0);
    }

    static void TransacHistory()   {
        System.out.println("You opened the item options menu");
    }

    static void callError() {
        System.out.println("Something went wrong!");
        System.exit(1);
    }
    static void exitProgram()   {
        System.out.println("Thank you, com again!");
        System.exit(0);
    }
}
