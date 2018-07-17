package Day7_14;

import java.io.File;

//[FK影视出品]法證先鋒.2006.EP01.双语字幕.TVRip.X264.aa88.mkv
//改为法證先鋒.2006.EP01.双语字幕.TVRip.X264.mkv

//文件重命名
public class ReName {
    public static void main(String[] args) {
        //读取文件夹名字
        File fileDir = new File("I:\\[法证先锋][双语字幕][TVRIP-MKV]\\[TVB][2006][法证先锋][双语字幕][TVRIP-MKV][FK]");
        //获得内容
        File[] files = fileDir.listFiles();
        //遍历
        for (File file : files) {
            //获得名字
            String oldname = file.getName();
            String regex = "]";
            int i = oldname.indexOf(regex);
            int i2= oldname.indexOf(".aa88");
//            System.out.println(i);
//            System.out.println(i2);
            if(i>=0){


          String newname = oldname.substring(i+1,i2)+".mkv";

                System.out.println(newname);

            File newfile = new File(fileDir, newname);
            file.renameTo(newfile);
            }






        }


    }

}
