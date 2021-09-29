package ItemOptions;
import CashRegister.SystemOutput;
import CashRegister.UserInput;
import TransacHistory.TransacHistory;

import java.util.ArrayList;

public class ItemOptions {
    SystemOutput sysOut;
    UserInput readIn;
    public ArrayList<Item> items;
    TransacHistory saveTransaction;


    // Constructor
    public ItemOptions(TransacHistory transactionHistory) {
        sysOut = new SystemOutput();
        readIn = new UserInput();
        items = new ArrayList<Item>();
        this.saveTransaction = transactionHistory;


        // Adds a bunch of items and randomizes up to 20 transactions.
        String[] tempItems = {"Pants", "Hat", "Legs", "Burak", "John", "Car", "Dental insurance", "Windows"};
        for(int i = 0; i < 8; i++) {
            double price = (Math.random() * 1000) + 1;
            items.add(new Item(i,tempItems[i], roundDecimal(price)));
        }
        for(int i = 0; i < Math.random() * 20; i++) {
            int tempID = (int) (Math.random() * items.size());
            int quantity = (int)(Math.random() * 20)+1;
            saveTransaction.purchaseSave(quantity, tempID, items.get(findItem(tempID)).price);
        }
    }

    public void addItem(String itemId, String itemName, double itemPrice) {
        int id;
        double price;
        String name;
        String error;
        String input;

        error = "Invalid data for item.";
        input = "Enter items ID: ";

        // Asks user for an ID and checks if duplicate.
        id = lookUpItem(input, error);

        // Reads the name of Item
        name = readIn.readString("Enter items name: ", error);

        // Reads the price of Item
        price = readIn.readDouble("Enter items price: ", error);
        price = roundDecimal(price);

        // Adds item to our ArrayList
        items.add(new Item(id, name, price)); // Object is stored in a list so we dont need reference.
        System.out.println("Item " + id + " was registered successfully.");
    }

    public void delItem() {
        int searchQuery;
        int index;
        int id;

        // Ask user for ID
        searchQuery = readIn.readInt("Specify the ID of the item: ", "Invalid data for item.");
        // Lookup if ID exists
        index = findItem(searchQuery);

        if(index != -1) {
            id = items.get(index).id;
            System.out.println("Item " + id + " was successfully removed.");
            items.remove(index);
        }
        else {
            System.out.println("Item " + searchQuery + " could not be removed.");
        }
    }

    public void printItems() {
        String headline = (items.isEmpty() ? "No items registered yet." : "All registered items: ");
        String itemInfo;
        System.out.println(headline);
        if(items.isEmpty() == false)    {
            for(int i = 0; i < items.size(); i++)   {
                itemInfo = items.get(i).id + ": " + items.get(i).name + ". " + items.get(i).price + " SEK.";
                System.out.println(itemInfo);
            }
        }
    }

    public void buyItem() {
        int quantity, id, index, discounted;
        double totalPrice, itemPrice;

        // can't use lookUpItem since we need a returned value -1 according to specifications :(
        //id = lookUpItem("Enter ID of item: ", "Invalid data for item.") ;
        id = readIn.readInt("Enter ID of item: ", "Invalid data for item.");
        index = findItem(id);
        if(index != -1) {
            quantity = readIn.readInt("Enter quantity of item: ", "Invalid data for item.");
            itemPrice = items.get(index).price;

            if (quantity > 4) {
                discounted = quantity - 4;
                quantity = 4;
            } else {
                discounted = 0;
            }
            totalPrice = roundDecimal((quantity * itemPrice) + (discounted * (itemPrice * (0.7))));
            System.out.println("Successfully purchased " + (quantity + discounted) + " x Item " + id + ": " + totalPrice + " SEK.");
            saveTransaction.purchaseSave(id, quantity, totalPrice);
        }
        else    {
            System.out.println("The purchase was not successful.");
        }
    }



    public void newItemName() {
        changeItem(1);
        System.out.println("Item's name was updated successfully.");
    }

    public void newItemPrice() {
        changeItem(2);
        System.out.println("Item's price was updated successfully.");
    }

    // 1 == name, 2 == price
    private void changeItem(int property)    {
        double price;
        int index, id;
        String input, error, name;

        input = "Enter a new value: ";
        error = "Invalid data for item.";

        if(items.isEmpty() == true) {
            System.out.println("No items registered");
        }
        else {
            // Gets ID
            id = readIn.readInt("Enter ID of item: ", "Invalid data for item.");
            // Gets index of ID
            index = findItem(id);

            switch (property) {
                case 1:
                    name = readIn.readString(input, error);
                    items.get(index).name = name;
                    break;
                case 2:
                    price = readIn.readDouble(input, error);
                    items.get(index).price = price;
                    break;
                default:
                    System.exit(1);
            }
        }
    }

    // This method will check if id exists then return the index of item in list.
    public int findItem(int searchQuery)  {
        int index;
        index = -1;
        for(int i = 0; i < items.size(); i++)   {
            if(items.get(i).id == searchQuery) {
                return i;
            }
        }
        return index;
    }

    // This method asks for and ID as input and checks if it's a duplicate
    private int lookUpItem(String input, String error)    {
        int id;
        boolean checkDuplicate;

        do {
            id = readIn.readInt(input, error);

            if(items.isEmpty() == false && findItem(id) != -1) { // If list is not empty, then check if ID is duplicate:
                System.out.println(error);
                checkDuplicate = true;
            }
            else    { // If list is empty or ID is not duplicate:
                checkDuplicate = false;
            }
        } while(checkDuplicate == true);
        return id;
    }

    // Removes any decimals over #.00.
    public double roundDecimal(double value)  {
        return (double)((long)(value * 100))/100;
    }

    public ArrayList<Item> copyItems()  {
        ArrayList<Item> itemsCopy = items;
        return itemsCopy;
    }

    /*
     ***********************
     *    METHODS   *
     ***********************
     */



}
