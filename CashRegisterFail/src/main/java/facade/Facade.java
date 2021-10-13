package facade;

import ItemOptions.ItemOptions;
import ReviewOptions.ReviewOptions;
import TransacHistory.TransacHistory;

import java.util.List;

public class Facade {

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.
    TransacHistory trans;
    ItemOptions shop;
    ReviewOptions reviews;
    final boolean test;
    public Facade(){
        test = true;
        trans = new TransacHistory();
        shop = new ItemOptions(trans, test);
        //reviews = new ReviewOptions(shop);
    }

    public String createItem(String itemID, String itemName, double unitPrice){
        return shop.addItem(itemID, itemName, unitPrice);
    }

    public String printItem(String itemID) {
        return shop.printItem(itemID);
    }

    public String removeItem(String itemID) {
        return shop.delItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return false;
    }

    public double buyItem(String itemID, int amount) {
        return shop.buyItem(itemID, amount);
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {return "";}

    public String reviewItem(String itemID, int reviewGrade) {
        return "";
    }

    public String getItemCommentsPrinted(String itemID) {
        return "";
    }

    public List<String> getItemComments(String itemID) {
        return null;
    }

    public double getItemMeanGrade(String itemID) {
        return -1.0;
    }

    public int getNumberOfReviews(String itemID) {
        return -1;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return "";
    }

    public String getPrintedReviews(String itemID) {
        return "";
    }

    public String printMostReviewedItems() {
        return "";
    }

    public List<String> getMostReviewedItems() {
        return null;
    }

    public List<String> getLeastReviewedItems() {
        return null;
    }

    public String printLeastReviewedItems() {
        return "";
    }

    public double getTotalProfit() {
        return trans.allHistoryProfit() ;
        //trans.allHistoryProfit();
        }


        public String printItemTransactions(String itemID) {
        return trans.printAllItemTrans(itemID);       //trans.printHistory(itemID);
    }

    public int getTotalUnitsSold() {
        return trans.allHistoryUnitsSold(); //return trans.allHistoryUnitsSold();
    }

    public int getTotalTransactions() {
        return trans.allHistoryTrans();//trans.allHistoryRegTrans();
    }

    public double getProfit(String itemID) {
        return trans.itemHistoryProfit(itemID);//trans.itemHistProfits(itemID);
    }

    public int getUnitsSolds(String itemID) {
        return trans.itemHistoryUnitsSold(itemID);// trans.itemHistUnitsSold(itemID);
    }

    public String printAllTransactions() {
        return trans.printAllTrans();// trans.printAllTransac();
    }

    public String printWorseReviewedItems() {
        return "";
    }

    public String printBestReviewedItems() {
        return "";
    }

    public List<String> getWorseReviewedItems() {
        return null;
    }

    public List<String> getBestReviewedItems() {
        return null;
    }

    public String printAllReviews() {
        return "";
    }

    public String updateItemName(String itemID, String newName) {
        double fakePrice = 0.0;
        int property = 1;
        return shop.changeItem(itemID, newName, fakePrice, property);
    }

    public String updateItemPrice(String itemID, double newPrice) {
        String fakeName = "";
        int property = 2;
        return shop.changeItem(itemID, fakeName, newPrice, property);
    }

    public String printAllItems() {
        return shop.printAllItems();
    }

    public String printMostProfitableItems() {
        return trans.mostProfit();//trans.mostProfit();
    }
}