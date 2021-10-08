package TransacHistory;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import ItemOptions.Item;
import java.util.ArrayList;


public class TransacHistory {
    SystemOutput printMenu;
    UserInput readIn;
    ArrayList <Transaction> historyList;
    public ItemOptions itemsData;
    ArrayList<Item> items = new ArrayList<>();
    String ln= System.lineSeparator();


    public TransacHistory(){
        printMenu = new SystemOutput();
        readIn= new UserInput();
        historyList = new ArrayList<Transaction>();

    }

    public void runHistory(){
        int ID;
        int choice;
        do{
            do{
                printMenu.transacMenu();
                choice=this.readIn.getUserOption(8,"Enter an option! ","Wrong option, try again a number between 0-8!"+ln);
            }while(choice>8||choice<(0));

            switch (choice){
                case 0:

                    break;
                case 1:
                    allHistory(choice);
                    break;
                case 2:
                    allHistory(choice);
                    break;
                case 3:
                    allHistory(choice);
                    break;
                case 4:
                    printAllTransac();
                    break;
                case 5:
                    ID= inputID("Please give ID of the item: ", "You have given a non-existent ID");
                    itemHist(ID,choice);
                    break;
                case 6:
                    ID= inputID("Please give ID of the item: ", "You have given a non-existent ID");
                    itemHist(ID,choice);
                    break;
                case 7:
                    ID= inputID("Please give ID of the item: ", "You have given a non-existent ID");
                    itemHist(ID,choice);
                    break;
                case 8:
                    mostProfit();
                    break;
            }
        } while(choice!=0);
    }


    public void purchaseSave(int quantity, int id, double totalPrice){
       historyList.add(new Transaction(quantity, id, totalPrice));
    }

    private int inputID(String input, String error)    {
        int id;
        boolean checkExistance=false;


        do {
            id = readIn.readID(input, error);

            for(int i = 0; i < items.size(); i++)   {
                if(items.get(i).id == id) {
                    checkExistance=true;
                }
            }

            if(items.isEmpty() == false && !checkExistance) { // If list is not empty, then check if ID is duplicate:
                System.out.println(error);
            }
            else    { // If list is empty or ID is not duplicate:
                checkExistance = false;
            }
        } while(checkExistance == true);
        return id;
    }



    public void itemHist(int id, int selec){
        double sumProfits = 0;
        int unitsSold = 0;
        int totalTransactions = 0;
        int plusTrans = 0;

        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).ID){
                unitsSold += historyList.get(i).quantity;
                sumProfits += historyList.get(i).totalPrice;
                totalTransactions=i+1;
                plusTrans++;
            }
        }
        if(plusTrans>1){
            switch (selec){
                case 5: System.out.println("The sum of profit from item: " + sumProfits);
                break;

                case 6:System.out.println("Sum of all units sold: " + unitsSold);
                break;

                case 7: System.out.println("Total transactions registered with said item: " + totalTransactions);
                break;
            }

        }else{
            System.out.println("0");
        }
    }

    public void printHistory(int id){
        items = itemsData.copyItems();
        String itemName="";
        double price = 0;
        int transactionTotal = 0;
        boolean exist = false;
        for (int i=0; i<items.size();i++){
            if(id==items.get(i).id){

            }else{
                System.out.println("Item "+id+" was not registered yet.");
            }
        }
        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).ID){
                transactionTotal=i+1;
                itemName = items.get(i).name;
                price = items.get(i).price;
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


    public void allHistory(int choice){
        int unitsSold = 0;
        double totPurchase = 0;
        int regTrans = 0;
        System.out.println(historyList.get(0).totalPrice);

        for(int i = 0; i<historyList.size();i++){
            regTrans=i+1;
            totPurchase+=historyList.get(i).totalPrice;
            unitsSold+=historyList.get(i).quantity;
        }
        if(regTrans==0){
            System.out.println("0");
        }else {
            switch (choice){
                case 1: System.out.println("Sum of all item purchases: "+totPurchase);
                break;
                case 2: System.out.println("Total units sold: "+unitsSold);
                break;
                case 3: System.out.println("Total registered transactions: "+regTrans);
                break;
            }
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
        items = itemsData.copyItems();

        double highestProfit= 00;
        int id= 0;



        for(int i = 0; i<items.size();i++){
            double sold = 0.00;
            for(int y = 0; y<historyList.size();y++){
                double totSold = 0.00;
                if(historyList.get(y).ID==items.get(i).id){
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
