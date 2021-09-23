package ReviewOptions;


import ItemOptions.ItemOptions;

public class Reviews {

    String comment;
    public int grade;
    public double meanGrade;
    public double allMeanGrades;
    public int id;

    public Reviews(String comment, int grade, double meanGrade, double allMeanGradesItem, int id) {
    this.id = id;
    this.comment = comment;
    this.grade = grade;
    this.meanGrade = meanGrade;
    this.allMeanGrades = allMeanGradesItem;

    }
}
