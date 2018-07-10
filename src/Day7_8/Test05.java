package Day7_8;

import java.io.*;

/*题目五 :在 c盘下有篇 txt 文本,读取这个文本并统计出这个文本中指定字符的个数
(如指定字符为 ”我”),将该字符与对应的次数输出到当前项目的	key.txt 中
,例如 :输 出格式为 :我=10
 */
public class Test05 {
    public static void main(String[] args) throws IOException {
        //目录
        File start = new File("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_8\\Test05_a.txt");

        char key ='华';
        File end =new File("F:\\BigData\\IDEA\\bigdatahomework\\src\\Day7_8\\Test05_key.txt");

        //调用方法统计
        count(key,start,end);
    }

    private static void count(char key, File start, File end) throws IOException {
        //读取文件
        BufferedReader br = new BufferedReader(new FileReader(start));
        BufferedWriter bw = new BufferedWriter(new FileWriter(end));

        //设置统计变量
        int index=0;
        String str =null;
        while ((str=br.readLine())!=null){
            char[] carr =str.toCharArray();
            for (char c : carr) {
                if(key==c){
                    index++;
                }
            }
        }
        String result =key+"="+index;
        bw.write(result);
        bw.flush();

        //释放资源
        bw.close();
        br.close();

    }
}
