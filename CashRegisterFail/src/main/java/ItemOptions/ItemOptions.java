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
    static boolean hasRegistered = false;

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
        double price;
        String name, input, success, id;
        boolean checkItems;
        id = "";
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
            // If it's a test assign applied values to inner-scope values.
            if(FACADE) {
                id = itemID;
                name = itemName;
                price = unitPrice;
            }
            price = roundDecimal(price);
            // Adds item to our ArrayList
            items.add(new Item(id, name, price)); // Object is stored in a list so we dont need reference.

            // Print and return success message.
            success = "Item ID" + id + " was registered successfully.";
            System.out.println(success);
            this.hasRegistered = true;
            return success;
        }
        System.out.println(INVALID_DATA);

        return INVALID_DATA;
    }

    public String delItem(String itemID) {
        int index;
        String error, success, searchQuery, id;


        searchQuery = (FACADE ? itemID : readIn.readID(ASK_ITEM_ID, INVALID_DATA));

        // Lookup if ID exists and return index in ArrayList
        index = findItem(searchQuery);

        if (index != -1) {
            id = items.get(index).id;
            items.remove(index);
            success = "Item " + id + " was successfully removed.";
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
            itemInfo = items.get(i).id + ": " + items.get(i).name + ". " + decimalFix(items.get(i).price) + " SEK";
            System.out.println(itemInfo);
            allItems += itemInfo + System.lineSeparator();
        }
        return allItems;
    }

    public double buyItem(String itemID, int amount) {
        int quantity, index, discounted;
        String success, id;
        double totalPrice, itemPrice;

        if(FACADE) {
            itemID = removeID(itemID);
            // Check if test sent a number for ID.
            if(!readIn.isNumber(itemID)) {
                System.out.println(INVALID_DATA);
                return -1;
            }
            id = itemID;
            quantity = amount;
        }
        else    {
            // can't use lookUpItem since we need a returned value -1 according to specifications :(
            //id = lookUpItem("Enter ID of item: ", "Invalid data for item.") ;
            id = readIn.readID(ASK_ITEM_ID, INVALID_DATA);
            quantity = readIn.readInt(ASK_ITEM_QUANTITY, INVALID_DATA);
        }

        index = findItem(id);

        if(index == -1) {
            System.out.println(PURCHASE_NOT_SUCCESSFUL);
            return -1;
        }
        itemPrice = items.get(index).price;

        if (quantity > 4) {
            discounted = quantity - 4;
            quantity = 4;
        } else {
            discounted = 0;
        }

        totalPrice = roundDecimal((quantity * itemPrice) + (discounted * (itemPrice * (0.7))));
        success = "Successfully purchased " + (quantity + discounted) + " x Item " + id + ": " + decimalFix(totalPrice) + " SEK.";
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
        int index;
        double price;
        String name, error, success, id;

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

            id = itemID;
        }
        else {
            // Gets ID
            id = readIn.readID(ASK_ITEM_ID, INVALID_DATA);
            error = "Item " + id + " was not registered yet";
            success = "Item " + id + " was updated successfully.";
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
        String error, itemInfo, itemName, id;
        int index;
        double itemPrice;
        error = "Item " + itemID + " was not registered yet.";

        if (items.isEmpty() == true || !readIn.isNumber(itemID)) {
            System.out.println(error);
            return error;
        }

        id = itemID;
        index = findItem(id);

        if (index == -1) {
            System.out.println(error);
            return error;
        }
        id = items.get(index).id;
        itemName = items.get(index).name;
        itemPrice = items.get(index).price;
        itemInfo = id + ": " + itemName + ". " + decimalFix(itemPrice) + " SEK";
        System.out.println(itemInfo);
        return itemInfo;
    }


    /*
     ***********************
     *   GENERAL METHODS   *
     ***********************
     */



    // This method will check if id exists then return the index of item in our ArrayList.
    private int findItem(String searchQuery)  {
        int index;
        index = -1;

        if(items.size() == 0) // In case we forgot to check before calling method.
            return index;

        for(int i = 0; i < items.size(); i++)   {
            if(items.get(i).id.equals(searchQuery)) {
                return i;
            }
        }

        return index;
    }

    // This method asks for and ID as input and checks if it's a duplicate
    private String lookUpItem(String input, String error)    {
        String id;
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
        String name, id;
        double price;
        ArrayList<Item> itemsCopy = new ArrayList<>();

        for(int i = 0; i < items.size(); i++)   {
            id = items.get(i).getId();
            name = items.get(i).getName();
            price = items.get(i).getPrice();
            itemsCopy.add(new Item(id, name, price)); // Create a new object with same values and add to new arraylist
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



    /*
     ***********************
     *   BURAK's METHODS   *
     ***********************
     */



    //Please do not change anything below this. If you do please tell me and how you wish to change it.(So scared code will poo poo)
    //Burak Askan

    //This already exists in itemOptions. Please use findItem() instead. // William
    public int getIndex(String id){   //Gets index of a item in itemArrayList using id
        int index = 0;
        for(int i = 0;i<items.size();i++){
            if (id.equals(items.get(i).getId())){
                index = i;
            }
        }
        return index;
    }

     // This is longer than using .size()? // William
    public int getSize(){return items.size();}   //Gets size of items arraylist
    public String getName(int index){return items.get(index).getName();} //Gets name of a item from itemArrayList
    public double getPrice(int index){return items.get(index).getPrice();}//Gets price of a item in itemArrayList

    //NOT allowed, use findItem, if the item doesn't exist it will return -1 which is equal to "false" in this case // William
    public boolean existanceChecker (String id){//Checks if such item currently exists
        boolean existance = false;
        for(int i = 0; i<items.size();i++){
            if(id.equals(items.get(i).id)){
                existance = true;
            }
        }
        return existance;
    }
    public boolean checkRegistry(){return hasRegistered;}
}//checks if atleast one item has been registered at all


