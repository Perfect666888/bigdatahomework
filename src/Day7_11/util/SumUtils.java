package Day7_11.util;

import Day7_11.bean.Score;
import Day7_11.bean.Student;
import Day7_11.compare.SumSort;

import java.util.*;

public class SumUtils {

    //获得总分map
    public static HashMap<String, Integer> getSumMap() throws Exception{

       // 获得每个学生 学号，总分
        //key 学号  value 总分  用hashmap存储
        //创建hashmap存储
        HashMap<String, Integer> sumMap = new HashMap<>();
        //用之前获得到成绩表集合更方便
        List<Score> scores = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\score.txt", Score.class);

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
        List<Student> students = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\students.txt", Student.class);

        //创建2个集合去存放学生集合,直接转为字符串类型，方便拼接总成绩和名次
        //理科集合
        ArrayList<String> lkArr = new ArrayList<>();
        //文科集合
        ArrayList<String> wkArr = new ArrayList<>();

        //遍历全部学生集合，去分割
        //直接拼接上学生成绩
        for (Student student : students) {

            //获得学生的学号
            //根据学号去找成绩,转换为字符串
            String sumGrade = sumMap.get(student.getSno()).toString();
            //拼接好需要写入的字符串内容
            //学号，姓名，性别，学生班级，总分
            String line =student.getSno()+"\t"+student.getSname()+"\t"+student.getSex()+"\t"+student.getClassName()+"\t"+sumGrade;
            //分割 为2个集合
            if(student.getClassName().startsWith("理科")){
                lkArr.add(line);
            }else {
                wkArr.add(line);
            }
        }

        //查看是否获取到数据
        //ForUtils.PrintArr(lkArr);
        //ForUtils.PrintArr(wkArr);

        //总分排名
        //都是list，可以使用collections.sort方法
        //需要创建一个类去实现comparator
        Collections.sort(lkArr, new SumSort());
        Collections.sort(wkArr, new SumSort());

        //查看排序后的结果
       // ForUtils.PrintArr(lkArr);
      //ForUtils.PrintArr(wkArr);


        //添加名次
        Utils.AddRank(lkArr);
        Utils.AddRank(wkArr);

        //查看添加名次后的结果
         //ForUtils.PrintArr(lkArr);
       // ForUtils.PrintArr(wkArr);

        //写入到文件中
        Utils.ArrToFile(lkArr, "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\理科总分排名.txt");
        Utils.ArrToFile(wkArr, "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\文科总分排名.txt");






    }

    //分班级，按照总成绩排名
    public static void SumSortByClass() throws Exception {
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
        //输出到文件中
        for (Map.Entry<String, ArrayList<String>> entry : classMap.entrySet()) {
            //排序
            Collections.sort(entry.getValue(), new SumSort());
            //添加名次
            Utils.AddRank(entry.getValue());

            //输出到文件
            String filename ="F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\"+entry.getKey()+".txt";
            Utils.ArrToFile(entry.getValue(), filename);

        }

    }


}
