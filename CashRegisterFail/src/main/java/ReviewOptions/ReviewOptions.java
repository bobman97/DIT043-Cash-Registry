package ReviewOptions;

import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import ReviewOptions.Reviews;
import ItemOptions.Item;


import java.util.ArrayList;

public class ReviewOptions {

    SystemOutput systemOut;
    UserInput takeIn;
    ArrayList<Reviews> reviewsList;
    ItemOptions itemRegistry;
    ArrayList<Item> nameOfList;


    // Constructor YGM

    public ReviewOptions(ItemOptions itemID)  {
        systemOut = new SystemOutput();
        takeIn = new UserInput();
        reviewsList = new ArrayList<Reviews>();
        itemRegistry = itemID;
        nameOfList = itemRegistry.copyItems();



    }

    // Finding all reviews for an item
    private int findReviews(int itemID)   {
    // Look through all the reviews,
        // see if they have the item ID
        //if they do add to an array

        for (int i = 0; i < reviewsList.size(); i++){


            }

        //after loop return the array
    }

    // Finding specific review for an item
    private int findReviewIndex(int itemID, int reviewIndex) {

    }

    public int findItem(int searchQuery)  {
        ArrayList<Item> items = itemRegistry.copyItems();
        int index;
        index = -1;
        for(int i = 0; i < items.size(); i++)   {
            if(items.get(i).id == searchQuery) {
                return i;
            }
        }
        return index;

    }

    public double calcMeanGrade(int itemID){
        int sumOfGrades = 0;
        int numOfGrades = 0;
        for (int i = 0; i < reviewsList.size(); i++){
            if (itemID == reviewsList.get(i).itemID) {
            sumOfGrades += reviewsList.get(i).reviewGrade;
            numOfGrades++;
            }
        }
        double meanGrade = sumOfGrades / numOfGrades;

        return meanGrade;
    }

    public double calcMeanOfAllGrades(double meanGrade){
       double sumOfMeanGrades = 0;
       double numOfMeanGrades = reviewsList.size();
        for (int i = 0; i < reviewsList.size(); i++){
            sumOfMeanGrades += calcMeanGrade(i);

            }

        double meanOfAllGrades = sumOfMeanGrades / numOfMeanGrades;

        return meanOfAllGrades;
    }

    // 1
    public void createReview(int itemID) {
        String reviewComment;
        int reviewGrade;
        int reviewIndex;


        itemID = takeIn.readInt(

        reviewIndex = findItem(itemID);

        reviewGrade = takeIn.getUserOption(5, "Enter item grade: ", "Grade values must be between 1 and 5.");

        System.out.println("Enter an item comment (Optional): ");
        reviewComment = takeIn.readIn.nextLine();
        if (reviewComment.isBlank() == true) {
            System.out.println("Your item review was registered successfully.");
            reviewsList.add(new Reviews(reviewGrade, reviewComment, itemID, reviewIndex));
        } else {
            reviewsList.add(new Reviews(reviewGrade, reviewComment, itemID, reviewIndex));
            System.out.println("Item comment added. Your item review was registered successfully.");
        }
    }



    // 2
    public void printSpecificReview(int itemID, int reviewIndex) {
    int id = 0;
    int index;
    String input;
    String error;


    index = findItem(itemID);

        input = "Please enter item ID: ";
        error = "Item " + id + " has not been registered yet";

        id = takeIn.readID(input, error);

        reviewIndex = takeIn.readInt("Enter review number:","Please enter a valid review index as an integer.");
        if(index != -1) {
            System.out.println("Grade: <" + reviewsList.get(index).reviewGrade + ">.<" + reviewsList.get(index).reviewComment + ">");
        }
    }



    // 3
    public void printAllRevItem(int itemID){
            int id = takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");

        System.out.println(reviewsList.get());

    }
    // 4
    public void printMeanGradeItem(){
            int id = takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");

        System.out.println(calcMeanGrade());
    }
    // 5
    public void printAllCommentsItem(){
            int id = takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");

            System.out.println();
    }
    // 6
    public void printAllRegisteredRev(){
        takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");

    }
    // 7
    public void printMostRevs(){

    }
    // 8
    public void printLeastRevs(){
        System.out.println();
    }
    // 9
    public void printBestMeanGrade(){
        System.out.println();
    }
    // 10
    public void printWorstMeanGrade(){
        System.out.println();


    }

     */
}
