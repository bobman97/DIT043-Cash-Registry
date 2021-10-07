package TransacHistory;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import ItemOptions.Item;
import java.util.ArrayList;


public class TransacHistory {
    SystemOutput sout;
    UserInput choice;
    ArrayList <Transaction> historyList;
    public ItemOptions itemsData;
    ArrayList<Item> nameOfList;


    public TransacHistory(){
        sout = new SystemOutput();
        choice = new UserInput();
        historyList = new ArrayList<Transaction>();
        ArrayList<Item> nameOfList = itemsData.copyItems();



    }

    public void purchaseSave(int quantity, int id, double totalPrice){
       historyList.add(new Transaction(quantity, id, totalPrice));
    }

    public void itemHist(int id){
        double sumProfits = 0;
        int unitsSold = 0;
        int totalTransactions = 0;
        int plusTrans = 0;

        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).ID){
                unitsSold += historyList.get(i).quantity;
                sumProfits += historyList.get(i).totalPrice;
                totalTransactions=i;
                plusTrans++;
            }
        }
        if(plusTrans>1){
            System.out.println("The sum of profit from item: " + sumProfits);
            System.out.println("Sum of all units sold: " + unitsSold);
            System.out.println("Total transactions registered with said item: " + totalTransactions);
        }else{
            System.out.println("0");
        }

    }

    public void printHistory(int id){
        String itemName="";
        double price = 0;
        int transactionTotal = 0;
        boolean exist = false;
        for (int i=0; i<nameOfList.size();i++){
            if(id==nameOfList.get(i).id){

            }else{
                System.out.println("Item "+id+"was not registered yet.");
            }
        }
        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).ID){
                transactionTotal=i;
                itemName = nameOfList.get(i).name;
                price = nameOfList.get(i).price;
                exist=true;
            }

        }
        if(!exist){
            System.out.println("Transactions for item: " + id +":"+itemName+". "+price+ " SEK");
            System.out.println("No transactions have been registered for item" +id+" yet.");
        }
        else {
            System.out.println("Transactions for item: " + id + ":" + itemName + ". " + price + " SEK");
            System.out.println("");

            for (int i = 0; i < historyList.size(); i++) {
                if (id == historyList.get(i).ID) {
                    System.out.println(historyList.get(i).ID + ": " + historyList.get(i).quantity + "item(s). " + historyList.get(i).totalPrice + " SEK");
                }
            }
        }
    }


    public void allHistory(){
        int unitsSold = 0;
        double totPurchase = 0;
        int regTrans = 0;

        for(int i = 0; i<historyList.size();i++){
            regTrans=i;
            totPurchase+=historyList.get(i).totalPrice;
            unitsSold+=historyList.get(i).quantity;
        }
        if(regTrans==0){
            System.out.println("0");
        }else {
            System.out.println("Sum of all item purchases: "+totPurchase);
            System.out.println("Total units sold: "+unitsSold);
            System.out.println("Total registered transactions: "+regTrans);
        }
    }

    public void printAllTransac(){
        int unitsSold = 0;
        double totPurchase = 0.00;
        int regTrans = 0;


        for(int i = 0; i<historyList.size();i++){
            regTrans=i;
            totPurchase+=historyList.get(i).totalPrice;
            unitsSold+=historyList.get(i).quantity;

        }

            System.out.println("All purchases made:");
            System.out.println("Total profit: "+totPurchase+ " SEK" );
            System.out.println("Total items sold: "+ unitsSold+ " units");
            System.out.println("Total purchases made: "+ regTrans+ " transactions");


            for (int i = 0; i<historyList.size();i++){
                System.out.println(historyList.get(i).ID+" "+historyList.get(i).quantity+"  item(s)."+ historyList.get(i).totalPrice+" SEK");
            }
            System.out.println("------------------------------------");

    }

    public void mostProfit(){

        double highestProfit= 00;
        int id= 0;



        for(int i = 0; i<nameOfList.size();i++){
            double sold = 0.00;
            for(int y = 0; y<historyList.size();y++){
                double totSold = 0.00;
                if(historyList.get(y).ID==nameOfList.get(i).id){
                    totSold+=historyList.get(y).totalPrice;
                }
                if(totSold>sold){
                    sold=totSold;
                    id=historyList.get(y).ID;
                }
            }
            if(sold>highestProfit){
                highestProfit=sold;
            }

        }

        System.out.println("Most profitable items: ");
        System.out.println("Total profit: "+highestProfit+ " SEK");

    }

}
