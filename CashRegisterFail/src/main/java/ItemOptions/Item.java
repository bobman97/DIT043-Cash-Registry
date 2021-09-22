package ItemOptions;

public class Item {
    // This will be the objects that stores the information about all the items in the shop.
    String name;
    int price;
    int id;

    public Item(int id, String name, int price)   {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
