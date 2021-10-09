package TransacHistory;

public class Transaction {
    int ID;
    double totalPrice;
    int quantity;


    public Transaction(int ID, int quantity, double totalPrice){
        this.ID = ID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;

    }

    public void toString(int ID, int quantity, double totalPrice){
        System.out.println(ID + ": "+quantity+ " item(s). "+totalPrice+ " SEK");
    }



}
