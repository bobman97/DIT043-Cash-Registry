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
        itemsData = new ItemOptions(this,true);

    }

    public void runHistory(){
        String ID;
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
                case 2:
                case 3:
                    allHistory(choice);
                    break;

                case 4:
                    printAllTransac();
                    break;
                case 5:
                case 6:
                    ID= inputID("Please give ID of the item: ", "You have given a non-existent ID");
                    itemHist(ID,choice);
                    break;

                case 7:
                    ID=inputID("Please give ID of the item: ", "You have given a non-existent ID");
                    printHistory(ID);
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

    private String inputID(String input, String error)    {
        int id;
        boolean checkExistance=false;

        do {
            id = readIn.readID(input, error);

            for(int i = 0; i < items.size(); i++)   {
                if(items.get(i).id.equals(id)) {
                    checkExistance=true;
                }
            }

            if(!items.isEmpty() && !checkExistance) {
                System.out.println(error);
            }
            else    {
                checkExistance = false;
            }
        } while(checkExistance);
        String stringID= id+"";
        return stringID;
    }



    public String itemHist(String idString, int selec){
        int id=Integer.parseInt(idString);
        String result ="";
        double sumProfits = 0;
        int unitsSold = 0;
        int totalTransactions = 0;
        int plusTrans = 0;

        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).getID()){
                unitsSold += historyList.get(i).getQuantity();
                sumProfits += historyList.get(i).getTotalPrice();
                totalTransactions=i+1;
                plusTrans++;
            }
        }
        if(plusTrans>1){
            switch (selec){
                case 5: result+=("The sum of profit from item: " + sumProfits);
                return result;


                case 6:result+=("Sum of all units sold: " + unitsSold);
                return result;


                case 7: result+=("Total transactions registered with said item: " + totalTransactions);
                return result;

            }

        }else{
            result+=("0");
            return result;
        }
        return result;
    }

    public String printHistory(String idString){
       int id = Integer.parseInt(idString);
       String result = "";
        items = itemsData.copyItems();
        String itemName="";
        double price = 0;
        boolean exist = false;
        for (int i=0; i<items.size();i++){
            if(id==items.get(i).id){ //currently bug

            }else{
                result +=("Item "+id+" was not registered yet."+ln);
            }
        }
        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).getID()){
                itemName = items.get(i).name;
                price = items.get(i).price;
                exist=true;
            }
        }
        if(!exist){
            result+=("Transactions for item: " + id +":"+itemName+". "+price+ " SEK"+ln);
            result+=("No transactions have been registered for item" +id+" yet."+ln);
        }
        else {
            result+=("Transactions for item: " + id + ":" + itemName + ". " + price + " SEK"+ln);


            for (int i = 0; i < historyList.size(); i++) {
                if (id == historyList.get(i).getID()) {
                     result+=(historyList.get(i).toString()+ln);
                }
            }

        }
        return result;
    }


    public String allHistory(int choice){
        String result="";
        int unitsSold = 0;
        double totPurchase = 0;
        int regTrans = 0;
        System.out.println(historyList.get(0).getTotalPrice());

        for(int i = 0; i<historyList.size();i++){
            regTrans=i+1;
            totPurchase+=historyList.get(i).getTotalPrice();
            unitsSold+=historyList.get(i).getQuantity();
        }
        if(regTrans==0){
            result+=("0");
            return result;
        }else {
            switch (choice){
                case 1: result =("Sum of all item purchases: "+totPurchase);
                return result;

                case 2: result=("Total units sold: "+unitsSold);
                return result;

                case 3: result=("Total registered transactions: "+regTrans);
                return result;

            }
        }
        return result;
    }

    public String printAllTransac(){
        StringBuilder result= new StringBuilder("All purchases made:");
        int unitsSold = 0;
        double totPurchase = 0.00;
        int regTrans = 0;


        for(int i = 0; i<historyList.size();i++){
            regTrans=i+1;
            totPurchase+=historyList.get(i).getTotalPrice();
            unitsSold+=historyList.get(i).getQuantity();

        }


            result.append(ln).append("Total profit: ").append(totPurchase).append(" SEK").append(ln);
            result.append("Total items sold: ").append(unitsSold).append(" units").append(ln);
            result.append("Total purchases made: ").append(regTrans).append(" transactions").append(ln);

            result.append("------------------------------------").append(ln);

            for (int i = 0; i<historyList.size();i++){
                result.append(historyList.get(i).toString()).append(ln);
            }
            result.append("------------------------------------");
            return result.toString();

    }

    public String mostProfit(){
        String result = "";
        items = itemsData.copyItems();

        double highestProfit= 00;
        int id= 0;



        for(int i = 0; i<items.size();i++){
            double sold = 0.00;
            for(int y = 0; y<historyList.size();y++){
                double totSold = 0.00;
                if(historyList.get(y).getID()==items.get(i).id){
                    totSold+=historyList.get(y).getTotalPrice();
                }
                if(totSold>sold){
                    sold=totSold;
                    id=historyList.get(y).getID();
                }
            }
            if(sold>highestProfit){
                highestProfit=sold;
            }

        }


        result+="Most profitable items: "+ln;
        result+="Total profit: "+highestProfit+ " SEK";
        return result;

    }

}
