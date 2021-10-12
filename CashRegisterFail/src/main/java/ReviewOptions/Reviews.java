package ReviewOptions;


import ItemOptions.ItemOptions;

public class Reviews {
    int reviewGrade;
    String reviewComment;
    int reviewIndex;

    public Reviews(int reviewGrade, String reviewComment, int reviewIndex) {
    this.reviewGrade = reviewGrade;
    this.reviewComment = reviewComment;
    this.reviewIndex = reviewIndex;
    }

    public int getReviewGrade() {
        return reviewGrade;
    }

    public void setReviewGrade(int reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public int getReviewIndex() {
        return reviewIndex;
    }

    public void setReviewIndex(int reviewIndex) {
        this.reviewIndex = reviewIndex;
    }
}
