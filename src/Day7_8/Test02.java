package Day7_8;

import javax.xml.stream.events.Characters;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/*
题目二：已知文件 a.txt 文件中的内容为“ AAbcdea22dferwplkCC321ou1”,请编写 程序读取该文件内容，
要求去掉重复字母	(区分大小写字母 )
并按照自然排序顺序 后输出到 b.txt 文件中。即 b.txt 文件内容应为 "abc......123..."这样的顺序输出
 */
public class Test02 {

    public static void main(String[] args) throws IOException {
        //读取文件，为了方便直接使用bufferedreader
        BufferedReader br = new BufferedReader(new FileReader("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_8\\Test02_a.txt"));

        //输出文件
        BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_8\\Test02_b.txt",true));

        //每次读取一行，作为一个字符串，为了排序，需要转换为字符数组，把字符数组排序后，直接存入新的文件中
        String readstr =null;
        while((readstr=br.readLine())!=null) {
            //直接调用方法进行排序，并返回排序后的字符串，写入到文件中
            String newstr = sort2(readstr);
            bw.write(newstr);
            bw.newLine();
            bw.flush();
        }

        //释放资源
        br.close();
        bw.close();

    }

    private static String sort2(String readstr) {
        char[] carr=readstr.toCharArray();
        //用treeset去实现排序
        TreeSet<Character> tscarr = new TreeSet<>();
        for (char c : carr) {
            tscarr.add(c);
        }

        //创建字符串缓冲区存放排序后的结果
        StringBuilder sb = new StringBuilder();
        for (Character cc : tscarr) {
            sb.append(cc);
        }

        return sb.toString();

    }

}
