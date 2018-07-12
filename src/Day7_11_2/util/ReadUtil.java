package Day7_11_2.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReadUtil {

    public static <T> List<T> fileToArr(String filename,Class<T> tClass) throws Exception{
        //传递一个文件路径，和集合中的泛型类的class对象

        //创建集合存储读取到的内容
        ArrayList<T>  tArr= new ArrayList<>();


        //读取文件到缓冲流
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line =null;
        //读取数据
        while((line= br.readLine())!= null){
            //按照指定格式切割,获得对应的属性数据集合
            String[] datas = line.split(",");
            //应为获得到的数据可能有不同个数，所以不能直接赋值
            //需要通过set方法去赋值

            //通过class对象，去创建实例化对象,即添加到集合 中的实际对象数据\
            //必须有无参构造才可以实现
            T t = tClass.newInstance();

            //思路，获得该类有多少属性(私有化了，需要调用 获得全部属性方法)
            //再遍历属性去，通过获得属性名字，去拼接出set方法的名字
            //传 拼接后的名字,属性的参数类型，去查找有没有这个set方法
            //调用获得的方法,传递该数据赋值

            //获得属性
            Field[] declaredFields = tClass.getDeclaredFields();
            //通过获得到的数据集合长度，去控制属性，
            // 原因 属性可能比我们获得到的数据多，最后可能存在有空值
            for (int i = 0; i < datas.length; i++) {

                //根据下标获得属性
                Field declaredField = declaredFields[i];
                //获得属性名字
                String fieldName = declaredField.getName();

                //拼接名字
                String methodname ="set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);

                //获得属性的参数类型
                Class<?> type = declaredField.getType();

                //通过名字和类型去获得方法
                Method getmethod = tClass.getMethod(methodname, type);

                //调用方法进行赋值，无返回值
                //数据类型不一定都是String，先进行数据判断
                if(type==int.class){

                    getmethod.invoke(t, Integer.parseInt(datas[i]));
                }else{
                    getmethod.invoke(t, datas[i]);
                }
            }

            //赋值后的对象，添加到集合中
            tArr.add(t);
        }

        //返回存储好的集合
        return tArr;
    }

}
