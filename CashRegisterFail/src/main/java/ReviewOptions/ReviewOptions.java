package ReviewOptions;

import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.Item;
import ItemOptions.ItemOptions;
import ReviewOptions.Reviews;

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

    private int FindItem(int searchQuery)  {
        int index;
        index = -1;
        for(int i = 0; i < itemRegistry.items.size(); i++)   {
            if(itemRegistry.items.get(i).id == searchQuery) {
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
        double sumOfMeanGrades = 0;
        double numOfMeanGrades = 0;
        meanOfAllGrades = sumOfMeanGrades / numOfMeanGrades;

        return meanOfAllGrades;
    }

    // 1
    public void createReview(){
        takeIn.readInt("Enter item ID", "Item" + "<" + ID +"> was not registered yet.");

        System.out.println("Enter item grade: ");
        int itemGrade = takeIn.getUserOption(5, "Enter item grade: ", "Grade values must be between 1 and 5.");


        System.out.println("Enter item comment (Optional): ");
        String itemComment = takeIn.readIn.nextLine();
        if (itemComment.length() < 0){
            System.out.println("Your item review was registered successfully.");
    }


    // 2
    public void printSpecificReview() {
        int reviewID = takeIn.readInt("Enter review ID: ", "Please input a valid review ID.");
        int index = findReviewIndex(reviewID);
        if(index != -1) {
            System.out.println("Grade: <" + Reviews.grade + ">.<" + Reviews.comment + ">");
        }
    }



    // 3
    public void printAllRevItem(){
        System.out.println("Enter item ID: ");

        System.out.println(ItemReviews[i]);
    }
    // 4
    public void printMeanGradeItem(){
        System.out.println("Enter item ID: ");

        System.out.println(calcMeanGrade());
    }
    // 5
    public void printAllCommentsItem(){
        System.out.println("Enter item ID to print all item comments: ");
    }
    // 6
    public void printAllRegisteredRev(){
        System.out.println("Enter item ID to print all registered reviews for the item: ");

    }
    // 7
    public void printMostRevs(){
        System.out.println();
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
