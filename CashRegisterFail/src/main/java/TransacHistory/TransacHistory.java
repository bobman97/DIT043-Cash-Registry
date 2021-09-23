package TransacHistory;
import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import java.util.ArrayList;


public class TransacHistory {
    SystemOutput sout;
    UserInput choice;
    ArrayList <Transaction> historyList;
    ItemOptions itemsData;


    public TransacHistory(){
        sout = new SystemOutput();
        choice = new UserInput();
        historyList = new ArrayList<Transaction>();

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

        for(int i=0; i<historyList.size();i++){
            if(id==historyList.get(i).ID){
                System.out.println(historyList.get(i).ID+": "+historyList.get(i)+ "item(s)" );

            }
        }
    }




}
