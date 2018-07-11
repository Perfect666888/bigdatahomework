package Day7_11.main;


import Day7_11.bean.Cource;
import Day7_11.bean.Score;
import Day7_11.bean.Student;
import Day7_11.util.ForUtils;
import Day7_11.util.ReadUtil;

import java.util.List;

public class mainRead {
    public static void main(String[] args) throws Exception {
       // List<Student> students = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\students.txt", Student.class);
       // List<Score> scores = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\score.txt", Score.class);
        List<Cource> cources = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\Cource.txt", Cource.class);

        //测试集合
//        ForUtils.PrintArr(students);
//        ForUtils.PrintArr(scores);
        ForUtils.PrintArr(cources);


    }
}
