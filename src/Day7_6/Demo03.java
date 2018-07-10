package Day7_6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Demo03 {
    public static void main(String[] args) throws Exception {
        //使用Class对象获得类的构造方法
        Class c =Class.forName("Day7_6.Demo01Person");


        System.out.println("-------获得公共的构造方法");
        Constructor[] con  =c.getConstructors();
        for (Constructor cc:con) {
            System.out.println(cc);
        }

        System.out.println("---------根据指定参数获得构造方法");
        Constructor conname= c.getConstructor(String.class,int.class);
        System.out.println("构造方法的访问修饰符:"+Modifier.toString(conname.getModifiers()));
        System.out.println("构造方法的名字:"+conname.getName());
        System.out.print("构造的参数:");
        //获得构造方法中的参数类型
        Class[] c1=conname.getParameterTypes();
        System.out.println(Arrays.toString(c1));


        System.out.println("--------不用new的关键字创建对象");

        Demo01Person p1 =(Demo01Person) conname.newInstance("老王",22);
        System.out.println(p1);

    }
}
