package Day7_8;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/*
题目七 :在 d 盘目录下有一个加密文件	a.txt（文件里只有英文和数字） ，密码是 “ heima”，
当密码输入正确时才能读取文件里的数据。	现要求用代码来模拟读取 文件的过程，
并统计文件里各个字母出现的次数，并把统计结果按照“	a：2 个； b：3 个；”的格式
输出到	d 盘的 count.txt 中
 */
public class Test06 {
    //设定密码
    static String password ="12345";
    public static void main(String[] args) throws IOException {
        //创建判断密码功能
        //最多只能输入5次

        //设置统计变量
        int count= 1;
        while(count<6){
            //创建键盘 录入对象
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入密码");
            System.out.println("第"+count+"尝试");
            //获得键盘录入数据
            String pass =sc.nextLine();
            if(pass.equals(password)){
                //释放资源
                sc.close();

                System.out.println("欢迎使用");
                //调用方法完成统计
                countout();
                System.out.println("欢迎下次使用");
                System.exit(1);
            }else {
                count++;
                System.out.println("还有"+(6-count)+"次机会");
            }
        }

        System.out.println("错误超过5次,退出系统");
        System.exit(1);

    }

    private static void countout()  throws IOException {
        //读取文件
        BufferedReader br = new BufferedReader(new FileReader("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_8\\Test06_a.txt"));
        //输出文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_8\\Test06_map.txt"));
        
        //最后的文件格式像键值对，也就是Map,而且是排序过的map，所以使用treemap存储统计结果
        
        //创建TreeMap对象
        TreeMap<Character, Integer> tm = new TreeMap<>();

        //每次读取一行,存在多行，所以保存到字符缓冲区,
        //创建字符缓冲缓冲区
        StringBuilder sb = new StringBuilder();
        String str=null;
        while((str=br.readLine())!=null){
            sb.append(str);
        }
        //释放输入流资源
        br.close();

        //把字符缓冲流转换为字符集合
        char[] chars = sb.toString().toCharArray();
        //遍历字符集合
        for (char aChar : chars) {
            //获得treemap的key集合，查看有没有存在这个key，没有就add
            //存在value++
            Set<Character> keys = tm.keySet();

            if(!keys.contains(aChar)){
                tm.put(aChar, 0);
            }else{
                int value=tm.get(aChar);
                value++;
                tm.put(aChar, value);
            }
        }

        //输出map到文件
        //为了格式好看，每写入10个，就换行
        int hs=1;
        Set<Map.Entry<Character, Integer>> entries = tm.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            String line =hs+"--"+entry.getKey()+":"+entry.getValue()+"个; ";
            bw.write(line);
            if(hs==10){
                hs=1;
                bw.newLine();
            }
            hs++;
            bw.flush();
        }

        //释放资源
        bw.close();
    }

}
