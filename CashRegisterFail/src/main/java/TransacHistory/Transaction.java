package TransacHistory;

public class Transaction {
    final private String ID;
    final private double totalPrice;
    final private int quantity;


    public Transaction(String ID, int quantity, double totalPrice){
        this.ID = ID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

    }

    @Override
    public String toString() {return "ID"+ID + ": "+quantity+ " item(s). "+totalPrice+ " SEK";}

    public String getID(){return this.ID;}

    public int getQuantity(){return this.quantity;}

    public double getTotalPrice(){return this.totalPrice;}


}

