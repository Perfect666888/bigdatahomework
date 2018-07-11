package Day7_11.util;


import Day7_11.bean.Cource;
import Day7_11.bean.Score;
import Day7_11.compare.SumSort;

import java.util.*;


public class AvgUtils {


    //按照班级划分的平均分
    public static ArrayList<String> AvgByClass() throws Exception {

            /*在原先的班级排名上修改
            key 是班级  value为avg总分
            * */
        //获得班级个人总分排名
        //集合中的字符串格式为  学号，姓名，总分，名次
        HashMap<String, ArrayList<String>> classMap = SumUtils.SumSortByClass();

        //创建集合进行存储结果
        //格式  班级 平均分
        ArrayList<String> classAvgArr = new ArrayList<>();

        //班级的总分
        for (Map.Entry<String, ArrayList<String>> entry : classMap.entrySet()) {

            //在通过集合去获取班级总分
            ArrayList<String> value = entry.getValue();
            //获得班级的人数，(即集合的长度)
            int renShu = value.size();

            //定义初始总分值
            int classGradeSum = 0;
            //求班级总分
            for (String s : value) {
                //切割出分数
                String grade = s.split("\t")[2];
                //转换为int，并且完成累加
                classGradeSum += Integer.parseInt(grade);

            }
            //求出班级平均分
            int avgGrade = classGradeSum / renShu;

            //获得班级名字
            String className = entry.getKey();

            //拼接字符串
            String line = className + "\t" + String.valueOf(avgGrade);
            //添加到集合中
            classAvgArr.add(line);
        }

        //查看集合的结果
        //班级  平均分
        //ForUtils.PrintArr(classAvgArr);

        //这时候获取到的是没分文理科的平均分
        return classAvgArr;
    }


    //不分文理科的排名
    public static void sortAvg(ArrayList<String> classAvgArr) throws Exception {
        //对于集合进行排序
        Collections.sort(classAvgArr, new SumSort());

        //添加名次
        Utils.AddRank(classAvgArr);

        //查看集合的结果
        //添加名次后
        //班级  平均分 名次
        // ForUtils.PrintArr(classAvgArr);

        //输出到文件中
        //创建路径名字
        String fileName = "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\全校班级平均分排名.txt";
        Utils.ArrToFile(classAvgArr, fileName);


    }

    //分文理科的排名
    public static void sortAvgByArt(ArrayList<String> classAvgArr) throws Exception {
        //分为2个集合去存储文理科的平均分数据
        //理科
        ArrayList<String> lkAvgArr = new ArrayList<>();
        //文科
        ArrayList<String> wkAvgArr = new ArrayList<>();

        //开始划分数据
        for (String s : classAvgArr) {
            //判断是不是文理科开头即可
            if (s.startsWith("理科")) {
                lkAvgArr.add(s);
            } else {
                wkAvgArr.add(s);
            }
        }

        //对两个集合开始排序，添加名次
        //对于集合进行排序
        Collections.sort(lkAvgArr, new SumSort());
        Collections.sort(wkAvgArr, new SumSort());
        //添加名次
        Utils.AddRank(lkAvgArr);
        Utils.AddRank(wkAvgArr);

        //查看结果
        // ForUtils.PrintArr(lkAvgArr);
        // ForUtils.PrintArr(wkAvgArr);

        //创建路径名字
        String fileName = "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\理科班级平均分排名.txt";
        Utils.ArrToFile(lkAvgArr, fileName);

        //创建路径名字
        String fileName2 = "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\文科班级平均分排名.txt";
        Utils.ArrToFile(wkAvgArr, fileName2);


    }

    public static HashMap<String, ArrayList<Integer>> getCnoMap() throws Exception {
        //科目平均分
        //思路
        //创建hashmap 存放数据  key 课程编号 value 分数的集合 方便做平均分
        HashMap<String, ArrayList<Integer>> cnoMap = new HashMap<>();

        List<Score> scores = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\score.txt", Score.class);
        //把数据存入到map中
        //查看课程号在map中是否有值
        //没有就添加集合，有就存入集合
        for (Score score : scores) {
            //获得课程号
            String courceNo = score.getCourceNo();
            //获得map中对应的值
            ArrayList<Integer> value = cnoMap.get(courceNo);
            if (value == null) {
                //对于value初始化
                value = new ArrayList<>();
                value.add(score.getGrade());
                cnoMap.put(courceNo, value);
            } else {
                value.add(score.getGrade());
            }
        }
        return cnoMap;
    }

    public static void AvgByCno() throws Exception {

        HashMap<String, ArrayList<Integer>> cnoMap = AvgUtils.getCnoMap();

        //把获得到的求出数据存入新集合中
        ArrayList<String> avgArr = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Integer>> entry : cnoMap.entrySet()) {
            //获得value
            ArrayList<Integer> values = entry.getValue();

            //获得value的长度，方便求平均分
            int size = values.size();
            //求出集合中的数据和
            int valueSum = 0;
            for (Integer v : values) {
                valueSum += v;
            }
            //求出平均分
            int avg = valueSum / size;

            //拼接出字符串
            String line = entry.getKey() + "\t" + String.valueOf(avg);

            //添加到集合中
            avgArr.add(line);
        }

        //查看效果
//        ForUtils.PrintArr(avgArr);

        //进行排序和增加名次
        Collections.sort(avgArr, new SumSort());
        Utils.AddRank(avgArr);

        //查看结果
        //ForUtils.PrintArr(avgArr);

        //把课程编号换成课程名字
        List<Cource> cources = ReadUtil.fileToArr("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\data\\Cource.txt", Cource.class);

        for (Cource cource : cources) {
            //替换
            for (int i = 0; i < avgArr.size(); i++) {
                //按照条件切割出第一位，对比是否相同，相同就覆盖

                String[] strs = avgArr.get(i).split("\t");
                String oldCno = strs[0];
                if (oldCno.equals(cource.getCourceNo())) {
                    //定义新的格式
                    String line = cource.getCourceName() + "\t" + strs[1] + "\t" + strs[2];
                    //替换
                    avgArr.set(i, line);
                }

            }

        }
        //查看结果
        // ForUtils.PrintArr(avgArr);


        //输出到文件
        Utils.ArrToFile(avgArr, "F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_11\\Result\\课程平均分排名.txt");
    }


}



