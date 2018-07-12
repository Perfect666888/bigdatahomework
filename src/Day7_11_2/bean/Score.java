package Day7_11_2.bean;

public class Score {
    private String sno;
    private String courceNo;
    private int grade;

    public Score() {
        super();
    }

    @Override
    public String toString() {
        return "Score{" +
                "sno='" + sno + '\'' +
                ", courceNo='" + courceNo + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getCourceNo() {
        return courceNo;
    }

    public void setCourceNo(String courceNo) {
        this.courceNo = courceNo;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Score(String sno, String courceNo, int grade) {

        this.sno = sno;
        this.courceNo = courceNo;
        this.grade = grade;
    }
}
