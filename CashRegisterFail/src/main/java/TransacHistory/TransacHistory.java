package TransacHistory;
import CashRegister.CashRegister;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import ItemOptions.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TransacHistory {
    SystemOutput printMenu;
    UserInput readIn;
    List<Transaction> historyList;
    public ItemOptions itemsData;
    String ln= System.lineSeparator();
    ArrayList<Item> items;
    public static boolean hasRegistered = false;


    public TransacHistory(){
        printMenu = new SystemOutput();
        readIn= new UserInput();
        historyList = new ArrayList<>();
        this.itemsData = new ItemOptions(this, true);
        items = itemsData.copyItems();
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
        System.out.println(items.get(0).getName());
        String result = "";
        if(existanceChecker(id)){
            result = "Transactions for item: "+ id + ":"+ getName(id)+". "+getPrice(id)+" SEK"+ln;

            if(existanceChecker(id) && itemHistoryTotalTrans(id) > 0){
                for(int i = 0; i<historyList.size(); i++){
                    result += historyList.get(i).toString() + ln;
                }
            }else if(existanceChecker(id)){
                result+="No transactions have been registered for item "+id+" yet.";
            }
        }else{
            result+="Item "+id+" was not registered yet";
        }

        return result;
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
                +"Total profit: "+allHistoryProfit()+" SEK"+ ln
                +"Total items sold: "+allHistoryUnitsSold()+" units"+ ln
                +"Total purchases made: "+allHistoryTrans()+" transactions"+ ln
                +"------------------------------------"+ln;

        if(allHistoryTrans()>0){
            for(int i = 0; i<historyList.size();i++){
                result+=historyList.get(i).toString()+ln;
            }
        }
        result+="------------------------------------";
        return result;
    }

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
            result+="Most profitable items:"+ln
                    +"Total profit: "+ mostProfit+ " SEK"+ln;
            for(int i = 0; i<historyList.size();i++){
                if(mostProfitID.equals(historyList.get(i).getID())){
                    result+=historyList.get(i).getID()+": "+getName(mostProfitID)+". "+historyList.get(i).getTotalPrice()+" SEK"+ln;
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
        for(int i = 0; i<items.size();i++){
            if(id.equals(items.get(i).id)){
                existance = true;
            }
        }
        return existance;
    }

    private double roundDecimal(double value)  {return ((double)((long)(value * 100)))/100;}


    /*public int getIndex(String id){
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
        for(int i = 0; i<items.size();i++){
            if(id.equals(items.get(i).id)){
                existance = true;
            }
        }
        return existance;
    }
*/



   /* private String inputID(String input, String error)    {
        String id;
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

        return id;
    }


    public double itemHistProfits(String id){

        double sumProfits = (double) itemHistContents(id).get(0);
        double plusTrans = (int) itemHistContents(id).get(3);

        if(plusTrans>=1) {
            return sumProfits;
        }else{
            return 0;
        }
    }

    public int itemHistUnitsSold(String id){

        int unitsSold = (int) itemHistContents(id).get(1);
        int plusTrans = (int) itemHistContents(id).get(3);

        if(plusTrans>=1) {
            return unitsSold;
        }else{
            return 0;
        }
    }


    public ArrayList itemHistContents(String id){
        ArrayList<Object> itemHistContents = new ArrayList<>(4);

        double sumProfits = 0;
        int unitsSold = 0;
        int totalTransactions = 0;
        int plusTrans = 0;

        for(int i=0; i<historyList.size();i++){
            if(id.equals(historyList.get(i).getID())){
                unitsSold += historyList.get(i).getQuantity();
                sumProfits += historyList.get(i).getTotalPrice();
                totalTransactions=i+1;
                plusTrans++;
            }
        }
        itemHistContents.add(sumProfits);
        itemHistContents.add( unitsSold);
        itemHistContents.add( totalTransactions);
        itemHistContents.add( plusTrans);

        return itemHistContents;
    }

    public String printHistory(String id){

       String result = "";
        items = itemsData.copyItems();
        String itemName="";
        double price = 0;
        boolean exist = false;
        for (int i=0; i<items.size();i++){
            if(id.equals(items.get(i).id)){

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

    public double allHistoryProfit(){

        double totPurchase = (double) allHistoryContents().get(1);
        int regTrans = (int) allHistoryContents().get(2);
        if(regTrans==0){
            return 0;
        }else {
            return totPurchase;
        }
    }

    public int allHistoryUnitsSold(){

        int unitsSold = (int) allHistoryContents().get(0);
        int regTrans = (int) allHistoryContents().get(2);
        if(regTrans==0){
            return 0;
        }else {
           return unitsSold;
        }
    }

    public int allHistoryRegTrans(){

        int regTrans = (int) allHistoryContents().get(2);
        if(regTrans==0){
            return 0;
        }else {
            return regTrans;
        }
    }




    public ArrayList allHistoryContents(){
        ArrayList<Object> allHistContents = new ArrayList<>(4);
        int unitsSold = 0;
        double totPurchase = 0;
        int regTrans = 0;
        System.out.println(historyList.get(0).getTotalPrice());

        for(int i = 0; i<historyList.size();i++){
            regTrans=i+1;
            totPurchase+=historyList.get(i).getTotalPrice();
            unitsSold+=historyList.get(i).getQuantity();
        }
        allHistContents.add(unitsSold);
        allHistContents.add(totPurchase);
        allHistContents.add(regTrans);
        return allHistContents;
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
        String id;



        for(int i = 0; i<items.size();i++){
            double sold = 0.00;
            for(int y = 0; y<historyList.size();y++){
                double totSold = 0.00;
                if(historyList.get(y).getID().equals(items.get(i).id)){
                    totSold+=historyList.get(y).getTotalPrice();
                }
                if(totSold>sold){
                    sold=totSold;
                    id = historyList.get(y).getID();
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
*/
}
