package ReviewOptions;


import ItemOptions.ItemOptions;

public class Reviews {

    String comment;
    public int grade;
    public double meanGrade;
    public double allMeanGrades;

    public Reviews(String comment, int grade, double meanGrade, double allMeanGrades) {
this.comment = comment;
this.grade = grade;
this.meanGrade = meanGrade;
this.allMeanGrades = allMeanGrades;

    }
}
