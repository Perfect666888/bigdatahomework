package Day7_11_2.bean;

public class Cource {
    private String courceNo;
    private String courceName;
    private int maxGrade;

    public Cource() {
        super();
    }

    @Override
    public String toString() {
        return "Cource{" +
                "courceNo='" + courceNo + '\'' +
                ", courceName='" + courceName + '\'' +
                ", maxGrade=" + maxGrade +
                '}';
    }

    public String getCourceNo() {
        return courceNo;
    }

    public void setCourceNo(String courceNo) {
        this.courceNo = courceNo;
    }

    public String getCourceName() {
        return courceName;
    }

    public void setCourceName(String courceName) {
        this.courceName = courceName;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public Cource(String courceNo, String courceName, int maxGrade) {

        this.courceNo = courceNo;
        this.courceName = courceName;
        this.maxGrade = maxGrade;
    }
}
