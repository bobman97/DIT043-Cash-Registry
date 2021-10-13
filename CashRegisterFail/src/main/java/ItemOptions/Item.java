package ItemOptions;

import ReviewOptions.Reviews;

import java.util.ArrayList;
import java.util.List;

public class Item {
    // This will be the objects that stores the information about all the items in the shop.
     public final String id;
     public String name;
     public double price;
     public List<Reviews> reviewsList;

     Item(String id, String name, double price)   {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reviewsList = new ArrayList<>();
    }

    public String getId() {return id;}

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }




    //John

    public List<Reviews> getReviewList() {
        return reviewsList;
    }


    public double getMeanGrade() {
         double sumOfGrades = 0;
         for (Reviews reviews : getReviewList()) {
             sumOfGrades += reviews.getReviewGrade();
         }
         return sumOfGrades / getReviewList().size();
    }
}
