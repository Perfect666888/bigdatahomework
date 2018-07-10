package Day7_8;
//题目三 :把指定目录下 (包含子目录 )的所有图片，复制到另一个指定目录下

import java.io.*;

public class Test03 {
    //定义图片规则, 加static的原因，为了让类启动的时候就加载
    static String regex = ".+\\.([J][P][G]|[P][N][G]|[G][I][F])";

    public static void main(String[] args) {
        //创建源文件夹位置
        File start = new File("F:\\BigData\\java_Demo\\TestStart");
        //调用方法完成复制
        copyphoto(start);

    }

    private static void copyphoto(File start) {
        //获得文件夹内容的集合
        File[] files = start.listFiles();
        //遍历集合，
        for (File file : files) {
            //判断是否为文件夹
            if (file.isDirectory()) {
                copyphoto(file);
            } else {
                //判断是不是符合结尾规则
                if (file.getName().matches(regex)) {
                    //开始复制
                    copy(file);
                    System.out.println("复制");

                }
            }
        }

    }

    private static void copy(File file) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            File end = new File("F:\\BigData\\java_Demo\\TestEnd\\"+file.getName());

            bos = new BufferedOutputStream(new FileOutputStream(end));

            byte[] buf = new byte[1024];
            int n = 0;
            while ((n = bis.read(buf)) != -1) {
                bos.write(buf, 0, n);
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

}
