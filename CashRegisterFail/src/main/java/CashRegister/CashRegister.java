package CashRegister;


import ItemOptions.ItemOptions;
import ReviewOptions.ReviewOptions;
import TransacHistory.TransacHistory;

import java.util.Scanner;



public class CashRegister {
    static UserInput readIn;
    static ItemOptions shop;
    static ReviewOptions reviews;
    static TransacHistory trans;
    static SystemOutput printMenu;
    final static boolean test = false;

    public static void main(String[] args)  {
        runProgram();
    }

    static void runProgram()    {
        printMenu = new SystemOutput();
        readIn = new UserInput();
        trans = new TransacHistory();
        shop = new ItemOptions(trans, test);
        reviews = new ReviewOptions(shop);
        // Here were initializing the itemsData reference in TransacHistory and assigning it to already created object shop
        trans.itemsData = shop;
        int menuChoice;

        /*
        ***************
        *  Main Menu  *
        ***************
        */

        // Menu will loop until program closes
        do {
            // Print main menu
            printMenu.mainMenu();

            // Ask user to select an option
            menuChoice = readIn.getUserOption(3, "Enter an option: ", "Invalid menu option. Please type another option");

            switch(menuChoice)  {
                case 0:
                    exitProgram();
                    break;
                case 1:
                    itemOptions(); // This calls the item options menu
                    break;
                case 2:
                    reviewOptions(); // This calls the review options menu
                    break;
                case 3:
                    TransacHistory();
                    break;
                default:
                    callError();
            }
        }while(menuChoice != 0);
    }

    static void itemOptions()   {
        shop.runProgram();
    }

    static void reviewOptions() {reviews.runReviews();}


    static void TransacHistory()   {trans.runHistory();}

    static void callError() {
        System.out.println("Something went wrong!");
        System.exit(1);
    }
    static void exitProgram()   {
        System.out.println("Thank you, come again!");
        readIn.closeScanner();
        System.exit(0);
    }
}
