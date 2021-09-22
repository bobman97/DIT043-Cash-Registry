package ItemOptions;
import CashRegister.SystemOutput;
import CashRegister.UserInput;
import java.util.ArrayList;

public class ItemOptions {
    SystemOutput sysOut;
    UserInput readIn;
    ArrayList<Item> items;


    // Constructor
    public ItemOptions() {
        sysOut = new SystemOutput();
        readIn = new UserInput();
        items = new ArrayList<Item>();
        int input;
    }

    public void addItem() {
        int id;
        int price;
        String name;
        String error;
        error = "Incorrect entry!";

        // Gets details
        id = readIn.readInt("Enter items ID: ", error);
        name = readIn.readString("Enter items name: ", error);
        price = readIn.readInt("Enter items price: ", error);

        // Adds item
        items.add(new Item(id, name, price)); // Object is stored in a list so we dont need reference.
    }

    public void delItem() {
        int searchQuery;

        searchQuery = readIn.readInt("Enter the item's ID: ", "Incorrect input!");
        int index = findItem(searchQuery);
        if(index != -1) {
            System.out.println("Found: " + items.get(index).name + " and deleted it");
            items.remove(index);
        }
        else
            System.out.println("Found nothing.");
    }

    public void printItems() {
    }

    public void buyItem() {
    }

    public void newItemName() {
    }

    public void newItemPrice() {
    }

    public int findItem(int searchQuery)  {
        int index;
        index = -1;
        for(int i = 0; i < items.size(); i++)   {
            if(items.get(i).id == searchQuery)
                return i;
        }
        return index;
    }

    /*
     ***********************
     *       METHODS       *
     ***********************
     */



}
