package Day7_11_2.util;

import Day7_11_2.bean.Cource;
import Day7_11_2.bean.Score;
import Day7_11_2.bean.Student;
import Day7_11_2.compare.SumSort;
import Day7_11_2.util.ReadUtil;
import Day7_11_2.util.Utils;

import java.util.*;

public class SumUtils {

    //获得学生总分map
    public static HashMap<String, Integer> getSumMap() throws Exception{

       // 获得每个学生 学号，总分
        //key 学号  value 总分  用hashmap存储
        //创建hashmap存储
        HashMap<String, Integer> sumMap = new HashMap<>();
        //用之前获得到成绩表集合更方便
        List<Score> scores = Day7_11.util.ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\score.txt", Score.class);

        //把scores中的数据转移到hashmap中
        //同一个学号的完成成绩累加
        for (Score score : scores) {
            //判断hashmap中是否已经有该学号
            //直接判断值是否为空更方便
            if(sumMap.get(score.getSno())==null){
                //不存在就添加进去
                sumMap.put(score.getSno(), score.getGrade());
            }else{
                //存在就累加
                sumMap.put(score.getSno(), sumMap.get(score.getSno())+score.getGrade());
            }
        }

        //查看结果
        //ForUtils.PrintMap(sumMap);

        return sumMap;
    }

    //分文理科，按照总成绩排名
    public static void SumSortByArts() throws Exception {
        /*
        2.1.2	按总分排名（文理科分开），将排名后的结果保存到文件，
        需要有学号，姓名，性别，学生班级，总分，名次
        */
        //创建hashmap存储
        HashMap<String, Integer> sumMap = SumUtils.getSumMap();

        //把学生集合按照文理科分为2个集合
        //获得全部学生的集合
        List<Student> students = Day7_11.util.ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\students.txt", Student.class);

        //改为map去存储
        //key 是文理科 value是学生的集合
        HashMap<String, ArrayList<String>> wlkMap = new HashMap<>();


        //集合的字符串格式是
        //学号，姓名，性别，学生班级，总分
        for (Student student : students) {
            //获得有多少科目（文科还是理科）,作为key
            String key =student.getClassName().substring(0,2);
            //拼接集合字符串格式
            String line =student.getSno()+"\t"+student.getSname()+"\t"+student.getSex()+"\t"+student.getClassName()+"\t"+sumMap.get(student.getSno());

            //查找该key在这个是否存在
            ArrayList<String> values = wlkMap.get(key);
            if(values==null){
                //初始化
                values=new ArrayList<String>();
                values.add(line);
                //添加到map中
                wlkMap.put(key, values);
            }else{
                values.add(line);
            }

        }

        //添加排名
        for (Map.Entry<String, ArrayList<String>> entry : wlkMap.entrySet()) {
            //获得值 集合
            ArrayList<String> values = entry.getValue();

            SumSort sumSort = new SumSort(4);

            //排序
            Collections.sort(values, sumSort);
            //添加名次
            Utils.AddRank(values);
        }

        //遍历查看结果
        //ForUtils.PrintMap(wlkMap);


        //输出到文件
        for (Map.Entry<String, ArrayList<String>> entry : wlkMap.entrySet()) {
            //获得文件名
            String fileName = "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11_2\\Result\\"+ entry.getKey()+"Map.txt";
            Utils.ArrToFile(entry.getValue(), fileName);


        }






    }

    //分班级，按照总成绩排名
   public static  HashMap<String, ArrayList<String>> SumSortByClass() throws Exception {
        /*
        2.1.1	分班级排名，将排名后的结果保存到文件，
        需要有学号，姓名，总分，名次。文件名称使用对应科目名称
        */
        //创建hashmap存储
        HashMap<String, Integer> sumMap = SumUtils.getSumMap();

        //按照班级分，可以把班级作为key
        // 一个班的 学号，姓名，总分，名次 作为value


        //获得全部学生的集合
        List<Student> students = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\students.txt", Student.class);

        //创建map数据
        HashMap<String, ArrayList<String>> classMap = new HashMap<>();

        //遍历学生集合，把班级数据放入到map中
        for (Student student : students) {
            //获得班级号
            String className = student.getClassName();
            //获得对应的学生成绩
            Integer sumGrade = sumMap.get(student.getSno());
            //拼接需要存入集合的字符串格式
            String line =student.getSno()+"\t"+student.getSname()+"\t"+sumGrade;

            //获得map中的value
            ArrayList<String> valueArr = classMap.get(className);

            //查看该班级在map中有没有值
            if(valueArr==null){
                //没有，需要初始化
                valueArr=new ArrayList<>();
                valueArr.add(line);
                classMap.put(className, valueArr);
            }else {
                valueArr.add(line);
            }
        }

        //排序并添加名次

        for (Map.Entry<String, ArrayList<String>> entry : classMap.entrySet()) {
            //排序
            SumSort sumSort = new SumSort(2);
            Collections.sort(entry.getValue(), sumSort);
            //添加名次
            Utils.AddRank(entry.getValue());

        }

        return classMap;



    }




}
