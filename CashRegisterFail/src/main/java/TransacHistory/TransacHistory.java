package TransacHistory;
import CashRegister.CashRegister;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import ItemOptions.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class TransacHistory {
    SystemOutput printMenu;
    UserInput readIn;
    List<Transaction> historyList;
    public ItemOptions itemsData;
    String ln= System.lineSeparator();
    ArrayList<Item> items;
    public static boolean hasRegistered = false;
    HashMap<String, Double> TotalProfitItems = new HashMap<>();


    public TransacHistory(){
        printMenu = new SystemOutput();
        readIn= new UserInput();
        historyList = new ArrayList<>();
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
                    System.out.println("Sum of all item purchases: "+allHistoryProfit());
                    break;
                case 2:
                    System.out.println("Total units sold: "+allHistoryUnitsSold());
                    break;
                case 3:
                    System.out.println("Total registered transactions: "+allHistoryTrans());
                    break;

                case 4:
                    System.out.println(printAllTrans());
                    break;
                case 5:
                    ID= readIn.readID("Please give ID of the item: ", "You have given a non-existent ID");
                    System.out.println("The sum of profit from item: "+itemHistoryProfit(ID));
                    break;
                case 6:
                    ID= readIn.readID("Please give ID of the item: ", "You have given a non-existent ID");
                    System.out.println("Sum of all units sold: "+itemHistoryUnitsSold(ID));
                    break;

                case 7:
                    ID= readIn.readID("Please give ID of the item: ", "You have given a non-existent ID");
                    System.out.println(printAllItemTrans(ID));
                    break;
                case 8:
                    System.out.println(mostProfit());
                    break;
            }
        } while(choice!=0);
    }


    public void purchaseSave(String id, int quantity, double totalPrice){
       historyList.add(new Transaction(id, quantity, totalPrice));
    }

    //GIVES TOTAL PROFIT EVER    1
    public double allHistoryProfit(){return roundDecimal(allHistoryContent()[0]);}

    //GIVES NUMBER OF UNITS SOLD EVER   2
    public int allHistoryUnitsSold(){
        return (int) allHistoryContent()[1];
    }

    //GIVES NUMBER OF TRANSACTIONS EVER  3
    public int allHistoryTrans(){
        return historyList.size();
    }

    //HANDLES ALL CALCULATION FOR ALL ITEMS
    public double[] allHistoryContent(){
        double[] allHistory = new double[3];
        double totPrice = 0;
        double totTrans = 0;
        double unitsSold = 0;

        for(int i =0; i<historyList.size();i++){
            totPrice += historyList.get(i).getTotalPrice();
            unitsSold += historyList.get(i).getQuantity();
            totTrans++;
        }
        allHistory[0] = totPrice;
        allHistory[1] = unitsSold;
        allHistory[2] = totTrans;

        return allHistory;
    }

    //GIVES TOTAL PROFIT FROM A SINGLE ITEM  5
    public double itemHistoryProfit(String id){return roundDecimal(itemHistoryContent(id)[0]);}

    //GIVES TOTAL UNITS SOLD OF A SINGLE ITEM  6
    public int itemHistoryUnitsSold(String id){return (int)itemHistoryContent(id)[1];}

    //GIVES TOTAL TRANSACTIONS A ITEM MADE IN TOTAL
    public int itemHistoryTotalTrans(String id){return (int) itemHistoryContent(id)[2];}

    //OPERATES AND CALCULATES A ITEM`S HISTORY
    public double[] itemHistoryContent(String id){
        double[] itemHistory = new double[3];
        double totPrice = 0;
        double totTrans= 0;
        double unitsSold=0;

        for(int i = 0; i<historyList.size(); i++){
            if(id.equals(historyList.get(i).getID())){
                totPrice+=historyList.get(i).getTotalPrice();
                totTrans++;
                unitsSold+=historyList.get(i).getQuantity();
            }
        }
        itemHistory[0]=totPrice;
        itemHistory[1]=unitsSold;
        itemHistory[2]=totTrans;
        return itemHistory;
    }

    //PRINTS HISTORY OF ONE SINGLE ITEM   7
    public String printAllItemTrans(String id) {
        String result = "";
        boolean itemExists = existanceChecker(id);
        if(itemExists){//checks if item id exists
            result = "Transactions for item: "+ id + ": "+ getName(id)+". "+printMenu.decimalFix(getPrice(id))+" SEK"+ln; //Preps first line DONT

            if(itemHistoryTotalTrans(id) > 0){//checks if any transaction for said item
                for(int i = 0; i<historyList.size(); i++){//loops entire history list
                    if(historyList.get(i).getID().equals(id)){
                        result += historyList.get(i).toString() + ln;//prints entire hstory list
                    }
                }
            }else {
                result+="No transactions have been registered for item "+id+" yet."; //no tranaction for item print
            }
        }else{// item id dosent exist so print so
            result+="Item "+id+" was not registered yet.";
        }

        return result;
    }

    public void totalProfitItemList(){
        for(int i = 0; i<historyList.size();i++){

            String id=historyList.get(i).getID();
            double totalProfit =itemHistoryProfit(id);
            TotalProfitItems.put(id,totalProfit);
        }
    }



    //PRINTS HISTORY OF ONE SINGLE ITEM   7
    /*public String printAllItemTrans(String id){
        String result = "Transactions for item: "+ id + ":"+ itemsData.getName(id)+". "+itemsData.getPrice(id)+" SEK"+ln;
        System.out.println(itemsData.existanceChecker(id)+" "+itemHistoryTotalTrans(id));
        if(itemsData.existanceChecker(id)&&itemHistoryTotalTrans(id)>0){
            for(int i = 0;i<historyList.size();i++){
                result +=historyList.get(i).toString()+ln;
            }
        }else if(itemsData.existanceChecker(id)){
            result+="No transactions have been registered for item "+id+" yet.";
        }else{
            result+="Item "+id+" was not registered yet";
        }
        return result;
    }
*/
    //PRINTS ALL THE PURCHASES EVER MADE   4
    public String printAllTrans(){
        String result = "All purchases made: "+ln
                +"Total profit: "+printMenu.decimalFix(allHistoryProfit())+" SEK"+ ln
                +"Total items sold: "+allHistoryUnitsSold()+" units"+ ln
                +"Total purchases made: "+allHistoryTrans()+" transactions"+ ln
                +"------------------------------------"+ln;

        if(allHistoryTrans()>0){
            for(int i = 0; i<historyList.size();i++){
                result+=historyList.get(i).toString()+ln;
            }
        }
        result+="------------------------------------"+ln;
        return result;
    }

    /*public String mostProfit(){
        String result ="";
        double mostProfit= 0;
        String mostProfitID = "";
        int mostProfitIndex =0 ;
        totalProfitItemList();
        if(hasRegistered&&0<historyList.size()){

            for(int i = 0;i<historyList.size();i++){
                String id =historyList.get(i).getID();

                if(TotalProfitItems.get(id)>mostProfit){
                    mostProfitID = id;
                    mostProfitIndex=i;
                }

                if(TotalProfitItems.get(mostProfitID)==TotalProfitItems.get(id)){
                    result+=historyList.get(i).toString()+ln;
                }
            }

            result+="Most profitable items:"+ln
                    +"Total profit: "+ mostProfit+ " SEK"+ln;



        }else if(!hasRegistered){
            result+="No items registered yet";
        }else{
            result+="No items were bought yet";
        }
            return result;
    }*/

    // MOST PROFIT ITEM(S)
    public String mostProfit(){
        String result = "";
        double mostProfit= 0;
        String mostProfitID = "";

        if(hasRegistered && 0<historyList.size()){
            for(int i = 0; i<historyList.size();i++){
                if(itemHistoryProfit(historyList.get(i).getID())>mostProfit){
                    mostProfitID = historyList.get(i).getID();
                    mostProfit=itemHistoryProfit(historyList.get(i).getID());
                }
            }
            result+="Most profitable items: "+ln
                    +"Total profit: "+ mostProfit+ " SEK"+ln;

            result+=itemsData.copyItems().get(getIndex(mostProfitID)).getId()+": "+getName(mostProfitID)+". "+getPrice(mostProfitID)+" SEK"+ln;


            for(int i = 0; i<historyList.size();i++){
                if(itemHistoryProfit(historyList.get(i).getID())==mostProfit&&historyList.get(i).getID()!=mostProfitID){
                    String idHist = historyList.get(i).getID();
                    result+=itemsData.copyItems().get(getIndex(idHist)).getId()+": "+getName(idHist)+". "+getPrice(idHist)+" SEK"+ln;
                }
            }

        }else if(!hasRegistered){
            result+="No items registered yet";
        }else{
            result+="No items were bought yet";
        }
        return result;
    }

    // CHECKS IF ATLEAST ONE ITEM HAS EVER BEEN CREATED
    public void hasRegistered() {hasRegistered=true;}
    // FETCHING INFORMATION FROM ITEMSCOPYARRAYLIST BELOW


    public int getIndex(String id){
        items = itemsData.copyItems();
        int index = 0;
        for(int i = 0; i<items.size();i++){
            if(id.equals(items.get(i).getId())){
                index=i;
            }
        }
        return index;
    }

    public String getName(String id){
        items = itemsData.copyItems();
        String name ="";
        int index = getIndex(id);
        name =items.get(index).getName();
        return name;
    }

    public double getPrice(String id){
        items = itemsData.copyItems();
        double price = 0;
        int index = getIndex(id);
        price = items.get(index).getPrice();
        return price;
    }

    public boolean existanceChecker (String id){//Checks if such item currently exists
        items = itemsData.copyItems();
        boolean existance = false;
        for(Item currentItem : items){
            if(id.equals(currentItem.getId())){
                existance = true;
            }
        }
        return existance;
    }

    private double roundDecimal(double value)  {return ((double)((long)(value * 100)))/100;}

}
