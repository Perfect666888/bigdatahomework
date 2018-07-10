package Day7_6;

import java.lang.reflect.Method;

public class Demo04 {
    public static void main(String[] args) throws Exception {
        //使用Class对象获得指定类
        Class c =Class.forName("Day7_6.Demo01Person");

        System.out.println("---------获得本类以及父类的public方法");
        Method[] mparr =c.getMethods();
        for (Method mp: mparr) {
            System.out.println(mp);
        }

        System.out.println("-----------指定名字获取指定方法");
        Method mname =c.getMethod("toString");
        System.out.println(mname);

        System.out.println("-----------指定名字,参数获取指定方法");
        Method mnamec =c.getMethod("setName", String.class);
        System.out.println(mnamec);

        System.out.println("----------通过Class创建无参构造对象");
        Demo01Person dp1 =(Demo01Person) c.getConstructor().newInstance();
        System.out.println(dp1);

        System.out.println("-------------");
        mnamec.invoke(dp1,"老王");
        System.out.println(mname.invoke(dp1));
    }
}
