package ReviewOptions;


import ItemOptions.ItemOptions;

public class Reviews {
    int reviewGrade;
    String reviewComment;
    int itemID;
    int reviewIndex;

    public Reviews(int reviewGrade, String reviewComment, int itemID, int reviewIndex) {
    this.reviewGrade = reviewGrade;
    this.reviewComment = reviewComment;
    this.itemID = itemID;
    this.reviewIndex = reviewIndex;


    }
}
