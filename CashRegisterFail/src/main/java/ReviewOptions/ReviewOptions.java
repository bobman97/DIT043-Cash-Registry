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

    // Constructor YGM

    public ReviewOptions(ItemOptions itemID)  {
        systemOut = new SystemOutput();
        takeIn = new UserInput();
        reviewsList = new ArrayList<Item>();
        itemRegistry = itemID;


    }

    public double calcMeanGrade(double grade){
        int sumOfGrades = itemRegistry.;
        int numOfGrades = 0;
        int meanGrade = sumOfGrades / numOfGrades;


        return grade;
    }

    public double calcAllMeanGrades(double grades){


        return grades;
    }

    // 1
    public void createReview(){
        System.out.println("Enter item ID: ");


    }
    // 2
    public void printReview(){
        System.out.println("Enter item ID: ");

        System.out.println("Enter item grade: ");
        int itemGrade = takeIn.getUserOption(5, "Enter item grade: ", "Please enter an item grade between 1 and 5.");

            System.out.println("Item grade entered successfully.");

        System.out.println("Enter item comment (Optional): ");
        String itemComment = takeIn.readIn.nextLine();
        if (itemComment. = 0){

        } else {
            System.out.println("Item comment added.");
        }




    }
    // 3
    public void printAllRevItem(){
        System.out.println("Enter item ID: ");

        System.out.println(ItemReviews[i]);
    }
    // 4
    public void printMeanGrade(){
        System.out.println("Enter item ID: ");
        calcMeanGrade();
    }
    // 5
    public void printAllCommentsItem(){
        System.out.println("Enter item ID to print all item comments: ");
    }
    // 6
    public void printAllRegRev(){
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
