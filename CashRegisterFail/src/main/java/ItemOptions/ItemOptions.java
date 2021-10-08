package ItemOptions;
import CashRegister.SystemOutput;
import CashRegister.UserInput;
import TransacHistory.TransacHistory;
import TransacHistory.Transaction;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemOptions {

    // Constructor dependant variables
    SystemOutput sysOut;
    UserInput readIn;
    private ArrayList<Item> items;
    TransacHistory saveTransaction;
    final boolean FACADE;

    // Constants
    final int MAX_MENU_VALUE = 6;
    final int ZERO = 0;
    final int CHANGE_NAME = 1;
    final int CHANGE_PRICE = 2;
    final String PURCHASE_NOT_SUCCESSFUL = "Purchase was not successful.";
    final String PURCHASE_SUCCESSFUL = "Purchase was successful.";
    final String DATA_UPDATE_SUCCESFUL = "Updated items data.";
    final String NO_ITEMS_REGISTERED = "No items registered yet.";
    final String INVALID_DATA = "Invalid data for item.";
    final String INVALID_MENU_OPTION = "Invalid menu option. Please type another option";
    final String MENU_INPUT = "Enter an option: ";
    final String ASK_ITEM_ID = "Enter items ID: ";
    final String ASK_ITEM_NAME = "Enter items name: ";
    final String ASK_ITEM_PRICE = "Enter items price: ";
    final String ASK_ITEM_QUANTITY = "Enter quantity of item: ";
    final String NAME_NULL = "";
    final String ID_NULL = "";
    final double PRICE_NULL = 0.00;

    // Global
    DecimalFormat decimals = new DecimalFormat("#.00");


    /*
     ***********************
     *     CONSTRUCTOR     *
     ***********************
     */

    public ItemOptions(TransacHistory transactionHistory, boolean test) {
        sysOut = new SystemOutput();
        readIn = new UserInput();
        items = new ArrayList<Item>();
        this.saveTransaction = transactionHistory;
        this.FACADE = test;
    }

    /*
     ***********************
     *     MAIN METHOD     *
     ***********************
     */

    public void runProgram() {
        int menuChoice;
        do {
            do {
                sysOut.itemMenu();
                menuChoice = readIn.getUserOption(MAX_MENU_VALUE, MENU_INPUT, INVALID_MENU_OPTION);
            } while(menuChoice < ZERO || menuChoice > MAX_MENU_VALUE);
            switch(menuChoice)  {
                case 0: // returns to main menu
                    break;
                case 1:
                    //addItem(ID_NULL, NAME_NULL, PRICE_NULL);
                    addItem(ID_NULL, NAME_NULL, PRICE_NULL);
                    break;
                case 2:
                    delItem(ID_NULL);
                    break;
                case 3:
                    printAllItems();
                    break;
                case 4:
                    buyItem(ID_NULL, ZERO);
                    break;
                case 5:
                    newItemName();
                    break;
                case 6:
                    newItemPrice();
                    break;
                default:
                    //CashRegister.callError();
            }
        } while(menuChoice != ZERO);


    }

    /*
     ***********************
     *    EPIC FEATURES    *
     ***********************
     */

    public String addItem(String itemID, String itemName, double unitPrice) {
        int id;
        double price;
        String name, input, success;
        boolean checkItems;
        id = 0;
        name = "";
        price = 0;
        if(FACADE) {
            checkItems = itemTest(itemID, itemName, unitPrice);
        }
        else {
            // Asks user for an ID and checks if duplicate.
            id = lookUpItem(ASK_ITEM_ID, INVALID_DATA);

            // Reads the name of Item
            name = readIn.readString(ASK_ITEM_NAME, INVALID_DATA);

            // Reads the price of Item
            price = readIn.readDouble(ASK_ITEM_PRICE, INVALID_DATA);

            // All above methods will take care of errors
            checkItems = true;
        }

        if(checkItems) {
            // If it's a test assign applied values to innerscope values.
            if(FACADE) {
                id = Integer.parseInt(removeID(itemID));
                name = itemName;
                price = unitPrice;
            }
            price = roundDecimal(price);
            // Adds item to our ArrayList
            items.add(new Item(id, name, price)); // Object is stored in a list so we dont need reference.

            // Print and return success message.
            success = "Item ID" + id + " was registered successfully.";
            System.out.println(success);
            return success;
        }
        System.out.println(INVALID_DATA);
        return INVALID_DATA;
    }

    public String delItem(String itemID) {
        int searchQuery, index, id;
        String error, success;


        searchQuery = (FACADE ? Integer.parseInt(removeID(itemID)) : readIn.readInt(ASK_ITEM_ID, INVALID_DATA));

        // Lookup if ID exists and return index in ArrayList
        index = findItem(searchQuery);

        if (index != -1) {
            id = items.get(index).id;
            items.remove(index);
            success = "Item ID" + id + " was successfully removed.";
            System.out.println(success);
            return success;
        }
        error = "Item ID" + searchQuery + " could not be removed.";
        System.out.println(error);
        return error;
    }

    public String printAllItems() {
        String itemInfo, headline, allItems;
        headline = "All registered items:";
        allItems = headline + System.lineSeparator();

        if(items.isEmpty()) {
            System.out.println(NO_ITEMS_REGISTERED);
            return NO_ITEMS_REGISTERED;
        }
        System.out.println(headline);
        for(int i = 0; i < items.size(); i++)   {
            itemInfo = "ID" + items.get(i).id + ": " + items.get(i).name + ". " + decimalFix(items.get(i).price) + " SEK";
            System.out.println(itemInfo);
            allItems += itemInfo + System.lineSeparator();
        }
        return allItems;
    }

    public double buyItem(String itemID, int amount) {
        int quantity, id, index, discounted;
        String success;
        double totalPrice, itemPrice;

        if(FACADE) {
            itemID = removeID(itemID);
            // Check if test sent a number for ID.
            if(!readIn.isNumber(itemID)) {
                System.out.println(INVALID_DATA);
                return -1;
            }
            id = Integer.parseInt(itemID);
            quantity = amount;
        }
        else    {
            // can't use lookUpItem since we need a returned value -1 according to specifications :(
            //id = lookUpItem("Enter ID of item: ", "Invalid data for item.") ;
            id = readIn.readInt(ASK_ITEM_ID, INVALID_DATA);
            quantity = readIn.readInt(ASK_ITEM_QUANTITY, INVALID_DATA);
        }

        index = findItem(id);

        if(index == -1) {
            System.out.println(PURCHASE_NOT_SUCCESSFUL);
            return -1;
        }
        itemPrice = items.get(index).price;

        if (amount > 4) {
            discounted = amount - 4;
            amount = 4;
        } else {
            discounted = 0;
        }

        totalPrice = roundDecimal((amount * itemPrice) + (discounted * (itemPrice * (0.7))));
        success = "Successfully purchased " + (amount + discounted) + " x Item " + id + ": " + decimalFix(totalPrice) + " SEK.";
        System.out.println((FACADE ? PURCHASE_SUCCESSFUL : success));
        saveTransaction.purchaseSave(id, quantity, totalPrice);
        return totalPrice;

    }

    public void newItemName() {
        changeItem(ID_NULL,NAME_NULL,PRICE_NULL,CHANGE_NAME);
        //System.out.println("Item's name was updated successfully.");
    }

    public void newItemPrice() {
        changeItem(ID_NULL,NAME_NULL,PRICE_NULL,CHANGE_PRICE);
        //System.out.println("Item's price was updated successfully.");
    }

    // property: 1 == name, 2 == price
    public String changeItem(String itemID, String newName, double newPrice, int property)    {
        int index, id;
        double price;
        String name, error, success;

        if(items.isEmpty() == true) {
            System.out.println(NO_ITEMS_REGISTERED);
            return NO_ITEMS_REGISTERED;
        }

        if(FACADE) {
            error = "Item " + itemID + " was not registered yet";
            success = "Item " + itemID + " was updated successfully.";
            itemID = removeID(itemID);

            if (!readIn.isNumber(itemID)) {
                System.out.println(error);
                return error;
            }

            id = Integer.parseInt(itemID);
        }
        else {
            // Gets ID
            id = readIn.readInt(ASK_ITEM_ID, INVALID_DATA);
            error = "Item " + id + " was not registered yet";
            success = "Item ID" + id + " was updated successfully.";
        }

        // Gets index of ID
        index = findItem(id);
        if(index == -1)  {
            System.out.println(error);
            return error;
        }
        switch (property) {
            case 1:
                name = (FACADE ? newName : readIn.readString(ASK_ITEM_NAME, INVALID_DATA));
                items.get(index).name = name;
                break;
            case 2:
                price = (FACADE ? newPrice : readIn.readDouble(ASK_ITEM_PRICE, INVALID_DATA));
                items.get(index).price = price;
                break;
            default:
                System.exit(1);
        }
        System.out.println(success);
        return success;
    }

    /*
     ************************
     *      FACADE RUN      *
     ************************
     */

    // Tests if all items pass the requirements.
    private boolean itemTest(String id, String name, double price)  {
        if(!readIn.isNumber(id) || name.isEmpty() || price < 0)
            return false;
        else
            return true;
    }

    public String printItem(String itemID) {
        String error, itemInfo, itemName;
        int index, id;
        double itemPrice;
        error = "Item " + itemID + " was not registered yet.";

        itemID = removeID(itemID);

        if (items.isEmpty() == true || !readIn.isNumber(itemID)) {
            System.out.println(error);
            return error;
        }

        id = Integer.parseInt(itemID);
        index = findItem(id);

        if (index == -1) {
            System.out.println(error);
            return error;
        }
        id = items.get(index).id;
        itemName = items.get(index).name;
        itemPrice = items.get(index).price;
        itemInfo = "ID" + id + ": " + itemName + ". " + decimalFix(itemPrice) + " SEK";
        System.out.println(itemInfo);
        return itemInfo;
    }


    /*
     ***********************
     *   GENERAL METHODS   *
     ***********************
     */

    // This method will check if id exists then return the index of item in our ArrayList.
    private int findItem(int searchQuery)  {
        int index;
        index = -1;

        if(items.size() == 0) // In case we forgot to check before calling method.
            return index;

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
            id = readIn.readID(input, error);

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
    private double roundDecimal(double value)  {
        return (double)((long)(value * 100))/100;
    }

    // returns a copy of all the items in the shop.
    public ArrayList<Item> copyItems()  {
        int id;
        String name;
        double price;
        ArrayList<Item> itemsCopy = new ArrayList<Item>((items.size() >0?items.size():0));
        if(items.size()>0){

            for(int i = 0; i < items.size(); i++)   {
                id = items.get(i).id;
                name = items.get(i).name;
                price = items.get(i).price;
                itemsCopy.add(new Item(id, name, price)); // Create a new object with same values and add to new arraylist
            }
        }


        return itemsCopy;
    }

    // remove ID part of itemID
    private String removeID(String itemID)  {
        itemID = (itemID.startsWith("ID") ? itemID.substring(2, itemID.length()) : itemID);
        return itemID;
    }

    // returns a String with two decimals
    private String decimalFix(double value)   {
        return decimals.format(value);
    }
}
