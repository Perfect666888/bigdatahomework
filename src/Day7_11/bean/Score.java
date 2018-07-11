package Day7_11.bean;

public class Score {
    private String sno;
    private String courceName;
    private int grade;

    public Score() {
        super();
    }

    @Override
    public String toString() {
        return "Score{" +
                "sno='" + sno + '\'' +
                ", courceName='" + courceName + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Score(String sno, String courceName, int grade) {

        this.sno = sno;
        this.courceName = courceName;
        this.grade = grade;
    }
}
