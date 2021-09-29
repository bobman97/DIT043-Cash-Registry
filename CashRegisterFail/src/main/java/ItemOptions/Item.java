package ItemOptions;

class Item {
    // This will be the objects that stores the information about all the items in the shop.
    protected final int id;
    protected String name;
    protected double price;

    public Item(int id, String name, double price)   {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
