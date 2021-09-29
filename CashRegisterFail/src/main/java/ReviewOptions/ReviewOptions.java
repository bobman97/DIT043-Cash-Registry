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
    //William sus

    // Constructor YGM

    public ReviewOptions(ItemOptions itemID)  {
        systemOut = new SystemOutput();
        takeIn = new UserInput();
        reviewsList = new ArrayList<Reviews>();
        itemRegistry = itemID;


    }

    // Finding all reviews for an item
    private int[] findReviews(int idToSearch)   {
    // Look through all the reviews,
        // see if they have the item ID
        //if they do add to an array

        //after loop return the array
    }

    // Finding specific review for an item
    private int findReviewIndex(int idToSearch) {

    }

    public int findItem(int searchQuery)  {
        ArrayList<Item> items = itemRegistry.copyItems();
        int index;
        index = -1;
        for(int i = 0; i < itemRegistry.items.size(); i++)   {
            if(itemRegistry.get(i).id== searchQuery) {
                return i;
            }
        }
        return index;

    }

    public double calcMeanGrade(double meanGrade){
        int sumOfGrades = reviewsList.
        int numOfGrades = reviewsList.size();
        meanGrade = sumOfGrades / numOfGrades;

        return meanGrade;
    }

    public double calcMeanOfAllGrades(double meanOfAllGrades){
        double sumOfMeanGrades = += reviewsList.get(i).grade;
        double numOfMeanGrades = reviewsList.size();
        meanOfAllGrades = sumOfMeanGrades / numOfMeanGrades;

        return meanOfAllGrades;
    }

    // 1
    public void createReview() {
        String comment;
        int grade;
        int id;
        int index;


        id = takeIn.readInt("Enter item ID: ", "Item" + "<" + itemRegistry.+ "> was not registered yet.");
        index = findItem(id);

        grade = takeIn.getUserOption(5, "Enter item grade: ", "Grade values must be between 1 and 5.");

        System.out.println("Enter an item comment (Optional): ");
        comment = takeIn.readIn.nextLine();
        if (comment.isBlank() == true) {
            System.out.println("Your item review was registered successfully.");
            reviewsList.add(new Reviews(grade));
        } else {
            reviewsList.add(new Reviews(grade, comment));
            System.out.println("Item comment added. Your item review was registered successfully.");
        }
    }



    // 2
    public void printSpecificReview() {

        int reviewID = takeIn.readInt("Enter item ID: ", "Please input a valid item ID.");
        int index = findReviewIndex(reviewID);
        if(index != -1) {
            System.out.println("Grade: <" + reviewsList.get(i).grade + ">.<" + reviewsList.get(i).comment + ">");
        }
    }



    // 3
    public void printAllRevItem(){
            int reviewID = takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");
        int[] index = findReviews(reviewID);
        if(index != -1) {
            System.out.println("Grade: <" + reviewsList.get(i).grade + ">.<" + reviewsList.get(i).comment + ">");
        }

    }
    // 4
    public void printMeanGradeItem(){
            int reviewID = takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");

        System.out.println(calcMeanGrade());
    }
    // 5
    public void printAllCommentsItem(){
            int reviewID = takeIn.readInt("Enter an item ID: ", "Please input a valid item ID.");

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
}
