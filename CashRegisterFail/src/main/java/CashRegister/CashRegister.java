package CashRegister;


import ItemOptions.ItemOptions;
import TransacHistory.TransacHistory;

import java.util.Scanner;

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
        shop = new ItemOptions();
        int menuChoice;


        // Menu will loop until program closes
        do {
            // Print main menu
            printMenu.mainMenu();

            // Ask user to select an option
            menuChoice = readIn.getUserOption(3, "Enter an option: ", "Invalid input!");

            switch(menuChoice)  {
                case 0:
                    exitProgram();
                    break;
                case 1:
                    itemOptions(); // This calls the item options menu
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
        }while(menuChoice != 0);

    }

    static void itemOptions()   {
        int menuChoice;
        do {
            printMenu.itemMenu();
            menuChoice = readIn.getUserOption(6,"Enter an option: ", "Incorrect value!");
            switch(menuChoice)  {
                case 0: // returns to main menu
                    break;
                case 1:
                    shop.addItem();
                    break;
                case 2:
                    shop.delItem();
                    break;
                case 3:
                    shop.printItems();
                    break;
                case 4:
                    shop.buyItem();
                    break;
                case 5:
                    shop.newItemName();
                    break;
                case 6:
                    shop.newItemPrice();
                    break;
                default:
                    callError();
                    break;
            }
        } while(menuChoice != 0);
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
