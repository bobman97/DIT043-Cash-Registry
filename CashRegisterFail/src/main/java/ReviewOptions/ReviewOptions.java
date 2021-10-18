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
                    if (!reviewComment.isEmpty()) {
                        reviewItem(ID, reviewGrade, reviewComment);
                        } else {
                        reviewItem(ID, reviewGrade);
                    }
                    break;
                case 2: //Print specific review
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    Item item = findItemObject(ID);
                    int reviewNumber = takeIn.getUserOption(item.reviewsList.size(),"Enter review number: ", "Pl");
                    printSpecificReview(ID, reviewNumber);
                    break;
                case 3: //Print all reviews of an item
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    getPrintedItemReview(ID);
                    break;
                case 4: //Print mean grade of an item
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    printMeanGradeItem(ID);
                    break;
                case 5: //Print all comments of an item
                    ID = takeIn.inputID("Enter an item ID: ", "Please input a valid item ID.");
                    getItemCommentsPrinted(ID);
                    break;
                case 6: //Print all registered reviews
                    getPrintedReviews();
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
            if(items.get(i).getId().equals(searchQuery)) {
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
    public String printSpecificReview(String ID, int reviewNumber) {
        Item item = findItemObject(ID);
        reviewNumber = reviewNumber - 1;
        if (item.reviewsList.isEmpty()){
            return "Item " + item.getName() + " has not been reviewed yet.";
        } else if (reviewNumber < 1 || reviewNumber > item.reviewsList.size()){
            return "Invalid review number. Choose between 1 and " + getNumberOfReviews(ID);
        } else {
            item.reviewsList.get(reviewNumber);
            return "Grade: " + item.getItemMeanGrade() + ". " + getItemCommentsPrinted(ID);
        }


    }

    // 3
    public String getPrintedItemReview(String ID) {
        Item item = findItemObject(ID);
        String printReviews = "Reviews(s) for " + ID + ": " + item.getName() + ". " + item.getPrice() + " + SEK " + ln;
        String noReviewsError = "Item " + item.getName() + " has not been reviewed yet.";

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
    public double printMeanGradeItem(String ID){
        Item item = findItemObject(ID);
        return item.getItemMeanGrade();

    }
    //5
    public String getItemCommentsPrinted(String ID) {
        // When retrieving all comments, users must specify an item ID.
        // They must only retrieve written comments and they can be iterated as a collection of Strings.
          StringBuilder result = new StringBuilder();
         for (String comments : getItemComments(ID)) {
           result.append(comments).append(ln);
         }
         return result.toString();
    }



    // 6
    public String getPrintedReviews() {
    StringBuilder allReviews = new StringBuilder(ln + "All registered reviews: " +ln + "------------------------------" +ln);
    String noRegisteredReviewsError = "No items were reviewed yet";

    for (Item item : items) {
        String printReviews = "Reviews(s) for " + item.getId() + ": " + item.getName() + ". " + item.getPrice() + " + SEK " + ln;
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
            String printItem = item.getId() + ": " + item.getName() + ". " + item.getPrice() + " SEK ";
            mostReviews.append(numberOfReviews);
            mostReviews.append(printItem);
            if (item.reviewsList.isEmpty()) {
                return "No items were reviewed yet";
            } else {
                for (int i = 0; i < item.reviewsList.size(); i++) {
                    printItem = items.get(i).getId() + ": " + items.get(i).getName() + ". " + items.get(i).getPrice() + " SEK ";
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

    public List<String> getItemComments(String ID) {
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

public List<Integer> getNumberOfReviews(String ID) {
    List<Integer> numberOfReviews = new ArrayList<>();
    if (findItemObject(ID) == null) {
        return numberOfReviews;
    }
    numberOfReviews.add(findItemObject(ID).getReviewList().size());
    return numberOfReviews;
}

public List<String> getMostReviewedItems(){
        List<String> mostReviewedItems = new ArrayList<>();
        int numberOfReviews = 0;
        // you make this variable at the top
    // when you add to the List<String> you update this variable whenever the for loop finds a reviewslist bigger than the variable
        for (Item item : items) {
            String ID = item.getId();
            if (item.reviewsList.size() > numberOfReviews) {
                mostReviewedItems.add(item.toString());
                numberOfReviews = item.reviewsList.size();
            } else if (item.reviewsList.size() == numberOfReviews) {
                mostReviewedItems.add(item.toString());
            }
        }
        return mostReviewedItems;
}

public List<String> getLeastReviewedItems(){
    List<String> leastReviewedItems = new ArrayList<>();
    int minReview = 0;
    // you make this variable at the top
    // when you add to the List<String> you update this variable whenever the for loop finds a reviewslist bigger than the variable
    for (Item item : items) {
        // Set a minimum value, which is the first item you get in the for loop
        minReview = item.getReviewList().size();
        if (item.reviewsList.size() == minReview) {
            leastReviewedItems.add(item.toString());
        } else if (item.reviewsList.size() < minReview) {
            leastReviewedItems.add(item.toString());
        }
    }
    return leastReviewedItems;
    }

}


