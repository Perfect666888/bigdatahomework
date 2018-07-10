package Day7_6;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Demo05 {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("Day7_6.Demo01Person");

        System.out.println("------只获取该类的所有方法");
        Method[] mall = c.getDeclaredMethods();
        System.out.println("方法的数量---" + mall.length);
        for (Method m : mall) {
            System.out.println(m);
        }

        System.out.println("-----------指定名字,参数获取指定方法");
        Method mname =c.getDeclaredMethod("setName", String.class);

        System.out.println("该方法的修饰符--"+Modifier.toString(mname.getModifiers()));
        System.out.println("方法的名称--"+mname.getName());
        System.out.println("方法的参数类型--"+mname.getParameterTypes()[0]);//getParameterTypes()获取到方法的参数的组数

        System.out.println("获得对象");
        Demo01Person dp =(Demo01Person) c.getConstructor().newInstance();

        mname.invoke(dp,"老王");
        System.out.println(dp);



    }
}
