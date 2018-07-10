package Day7_6;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Demo06 {
    public static void main(String[] args) throws Exception {
        //泛型创建集合对象
        ArrayList<Demo01Person> arr =new ArrayList<Demo01Person>();
        arr.add(new Demo01Person());
        System.out.println("集合中元素个数"+arr.size());
        for (Demo01Person dp: arr) {
            System.out.println(dp);
        }


        //通过Class来添加元素
        Class c1 =arr.getClass();
        Method mname =c1.getMethod("add", Object.class);
        //添加元素
        mname.invoke(arr,new Demo01Person("老王",25));
        mname.invoke(arr,123);
        System.out.println("如今的元素个数"+arr.size());
        for (Object o:arr) {
            System.out.println(o);
        }




    }
}
