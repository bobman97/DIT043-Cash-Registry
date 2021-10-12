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
    ItemOptions itemRegistry;
    ArrayList<Item> items;


    // Constructor YGM

    public ReviewOptions(ItemOptions itemID)  {
        systemOut = new SystemOutput();
        takeIn = new UserInput();
        itemRegistry = itemID;
        items = itemRegistry.copyItems();
    }

    public void runReviews(){
        String ID;
        int choice;
        do{
            do{
                systemOut.reviewMenu();
                choice=this.takeIn.getUserOption(10,"Enter an option! ","Wrong option, try again a number between 0-10!");
            }while(choice>10||choice<(0));

            switch (choice){
                case 0: //Return to main menu
                case 1: //Create review
                    int reviewGrade = takeIn.getUserOption(5, "Enter an item grade:", "Grade values must be between 1 and 5");
                    String reviewComment = takeIn.readComment("Enter an item comment (Optional): ");
                    ID = takeIn.readID("Enter item ID: ", )
                    Item item = ID
                    createReview();


                case 2: //Print specific review
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    int reviewIndex = 0;
                    printSpecificReview(ID, reviewIndex);
                    break;
                case 3: //Print all reviews of an item
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    printAllRevItem(ID);
                    break;
                case 4: //Print mean grade of an item
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    printMeanGradeItem(ID);
                    break;
                case 5: //Print all comments of an item
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    printAllCommentsItem(ID);
                    break;
                case 6: //Print all registered reviews
                    printAllRegisteredRev();
                    break;
                case 7: //Print item(s) w most reviews
                    printMostRevs();
                    break;
                case 8: //Print item(s) w least reviews
                    printLeastRevs();
                    break;
                case 9: //Print item(s) w best mean grade
                    printBestMeanGrade();
                    break;
                case 10: //Print item(s) w worst mean grade
                    printWorstMeanGrade();
                    break;
            }
        } while(choice!=0);
    }


    /*
    // Finding all reviews for an item
    private int findReviews(Item item)   {
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


     */
    public int findItem(String searchQuery)  {
        ArrayList<Item> items = itemRegistry.copyItems();
        int index;
        index = -1;
        for(int i = 0; i < items.size(); i++)   {
            if(items.get(i).id.equals(searchQuery)) {
                return i;
            }
        }
        return index;

    }

    public Item findItemObject(String index) {
        return items.get(findItem(index));
    }



    // 1
    public void createReview(Item item, int reviewGrade, String reviewComment, int reviewIndex) {
        item.getReviewList().add(new Reviews(reviewGrade, reviewComment, reviewIndex));
    }



    // 2
    public void printSpecificReview(String ID, int reviewIndex) {
        ID = "";
        reviewIndex = 0;
    String input;
    String error;

        input = "Please enter item ID: ";
        error = "Item " + ID + " has not been registered yet";

    }



    // 3
    public void printAllRevItem(String ID){


    }
    // 4
    public void printMeanGradeItem(String ID){



    }
    // 5
    public void printAllCommentsItem(String ID){

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
