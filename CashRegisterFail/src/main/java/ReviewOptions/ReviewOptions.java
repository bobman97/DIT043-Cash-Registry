package ReviewOptions;

import CashRegister.UserInput;
import CashRegister.SystemOutput;
import ItemOptions.ItemOptions;
import ItemOptions.Item;


import java.util.ArrayList;
import java.util.List;

public class ReviewOptions {
    String ln = System.lineSeparator();
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
                    ID = takeIn.inputID("Enter item ID: ", "Please input a valid item ID");
                    reviewItem(ID, reviewGrade, reviewComment);
                    break;
                case 2: //Print specific review
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    Item item = findItemObject(ID);
                    int reviewNumber = takeIn.getUserOption(item.reviewsList.size(),"Enter review number: ", "Pl");
                    printSpecificReview(ID, reviewNumber);
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
                case 7: //Print item(s) w the most reviews
                    printMostRevs();
                    break;
                case 8: //Print item(s) w the least reviews
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
    public void reviewItem(String ID, int reviewGrade, String reviewComment) {
        Item item = findItemObject(ID);
        item.getReviewList().add(new Reviews(reviewGrade, reviewComment));
    }

    public void reviewItem(String ID, int reviewGrade) {
        Item item = findItemObject(ID);
        item.getReviewList().add(new Reviews(reviewGrade,""));
    }



    // 2
    public void printSpecificReview(String ID, int reviewNumber) {
        Item item = findItemObject(ID);
        reviewNumber = -1;
    String input;
    String error;

        input = "Please enter item ID: ";
        error = "Item " + ID + " has not been registered yet";

    }

    // 3
    public String printAllRevItem(String ID) {
        Item item = findItemObject(ID);
        String printReviews = "Reviews(s) for " + ID + ": " + item.name + ". " + item.price + " + SEK " + ln;
        String noReviewsError = "Item " + item.name + " has not been reviewed yet.";

        for (Reviews reviews : item.getReviewList()) {
            if (item.reviewsList.isEmpty()) {
                return noReviewsError;
            } else {
                printReviews += "Grade: " + reviews.getReviewGrade() + ". " + reviews.getReviewComment();
            }
        }
        return printReviews;
    }

    // 4
    public void printMeanGradeItem(String ID){
        Item item = findItemObject(ID);
        System.out.println(item.getMeanGrade());

    }
    //5
    public String printAllCommentsItem(String ID) {
        // When retrieving all comments, users must specify an item ID.
        // They must only retrieve written comments and they can be iterated as a collection of Strings.
          StringBuilder result = new StringBuilder();
         for (String comments : getWrittenComments(ID)) {
           result.append(comments).append(ln);
         }
         return result.toString();
    }

    public List<String> getWrittenComments(String ID) {
        List<String> comments = new ArrayList<>();
        if (findItemObject(ID) == null) {
            return comments;
        } else {
            for (Reviews reviews : findItemObject(ID).getReviewList()) {
                if (!reviews.getReviewComment().isEmpty()) {
                    comments.add(reviews.getReviewComment());
                }
            }
        }
        return comments;
    }

    // 6
    public String printAllRegisteredRev() {
    StringBuilder allReviews = new StringBuilder(ln + "All registered reviews: " +ln + "------------------------------" +ln);
    String noRegisteredReviewsError = "No items were reviewed yet";

    for (Item item : items) {
        String printReviews = "Reviews(s) for " + item.id + ": " + item.name + ". " + item.price + " + SEK " + ln;
        allReviews.append(printReviews);
        if (item.reviewsList.isEmpty()) {
            return noRegisteredReviewsError;
        } else {
            for (int i = 0; i < item.reviewsList.size(); i++) {
                printReviews = "Grade: " + item.reviewsList.get(i).getReviewGrade() + ". " + item.reviewsList.get(i).getReviewComment();
                allReviews.append(printReviews);
            }
            return allReviews.toString();
            }
        }
        return allReviews.toString();


    }
    // 7
    public String printMostRevs() {
        int numberOfReviews = 0;
        StringBuilder mostReviews = new StringBuilder("Most reviews: " + numberOfReviews + "review(s) each." + ln);

        if (items.isEmpty()) {
            return "No items registered yet.";
        }

        for (Item item : items) {
            numberOfReviews = item.reviewsList.size();
            String printItem = item.id + ": " + item.name + ". " + item.price + " SEK ";
            mostReviews.append(numberOfReviews);
            mostReviews.append(printItem);
            if (item.reviewsList.isEmpty()) {
                return "No items were reviewed yet";
            } else {
                for (int i = 0; i < item.reviewsList.size(); i++) {
                    printItem = items.get(i).id + ": " + items.get(i).name + ". " + items.get(i).price + " SEK ";
                    mostReviews.append(printItem);
                }
            }
        }
        return mostReviews.toString();
    }



    // 8
    public void printLeastRevs(){
    }

    // 9
    public void printBestMeanGrade(){

    }

    // 10
    public void printWorstMeanGrade(){

        }
//Getter methods
public List<int> getNumberOfReviews(String ID) {
    List<int> numberOfReviews = new ArrayList<>();
    if (findItemObject(ID) == null) {
        return numberOfReviews;
    } else {
        for (Reviews reviews : findItemObject(ID).getReviewList()) {
            if (!reviews.getReviewComment().isEmpty()) {
                numberOfReviews.add(reviews.getReviewComment());
            }
        }
    }
    return numberOfReviews;
}


}


