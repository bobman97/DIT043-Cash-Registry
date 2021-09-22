package CashRegister;

public class SystemOutput {

    public void mainMenu()  {

        System.out.println
                ("Main Menu: Please choose among the options below.\n" +
                "\n" +
                "0. Close system\n" +
                "1. Open item options.\n" +
                "2. Open review options\n" +
                "3. Open transaction history");//Prints the main menu. Double check the bkslsh n?
    }

    public void transacMenu(){
        System.out.println
                ("Item options menu:\n" +
                "0. Return to Main Menu.\n"+
                "1. Print total profit from all item purchases.\n" +
                "2. Print total units sold from all item purchases.\n" +
                "3. Print all total number of transactions made.\n"+
                "4. Print all transactions made.\n"+
                "5. Print the total profit of a specific item.\n"+
                "6. Print the number of units sold of a specific item.\n"+
                "7. Print all transactions of a specific item.\n"+
                "8. Print item with highest profit.\n"+
                "");
    }

    public void reviewMenu(){
        System.out.println
                ("Review options menu:\n"+
                "0. Return to Main Menu.\n"+
                "1. Create a review for an Item.\n"+
                "2. Print a specific review of an Item.\n"+
                "3. Print all reviews of an Item.\n"+
                "4. Print mean grade of an Item.\n"+
                "5. Print all comments of an Item.\n"+
                "6. Print all registered reviews.\n"+
                "7. Print item(s) with most reviews.\n"+
                "8. Print item(s) with least reviews.\n"+
                "9. Print item(s) with best mean review grade.\n"+
                "10. Print item(s) with worst mean review grade.\n"+
                "");

    }

    public void itemMenu(){
        System.out.println
                ("Item Options menu:\n"+
                 "0. Return to Main Menu.\n"+
                 "1. Create an Item.\n"+
                 "2. Remove an Item.\n"+
                 "3. Print all registered Items.\n"+
                 "4. Buy an Item.\n"+
                 "5. Update an item’s name.\n"+
                 "6. Update an item’s price.\n"+
                 ""
                );
    }

}
