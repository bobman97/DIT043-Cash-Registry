package ItemOptions;

public class Item {
    // This will be the objects that stores the information about all the items in the shop.
    int id;
    String name;
    double price;

    public Item(int id, String name, double price)   {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
