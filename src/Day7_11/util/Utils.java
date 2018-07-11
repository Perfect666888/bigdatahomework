package Day7_11.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Utils {

    //添加名次
    public static void AddRank(List<String> list){
        for (int i = 0; i < list.size(); i++) {
            //获得原先字符串
            String oldStr = list.get(i);
            //拼接新字符串+名次
            String newStr =oldStr+"\t"+(i+1);
            //覆盖原来位置的字符串
            list.set(i, newStr);
        }
    }


    //集合输出到文件方法
    public static <T> void ArrToFile(List<T> list,String fileName) throws Exception{
        //创建输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

        //遍历集合写入文件
        for (T t : list) {
            bw.write(t.toString());
            bw.newLine();
        }

        //释放资源
        bw.close();


    }



}
