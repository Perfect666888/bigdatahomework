package Day7_6;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Demo02 {
    public static void main(String[] args) throws Exception {
        Class c2 =Class.forName("Day7_6.Demo01Person");
        //获取类的公共属性
        System.out.println("-----------公共属性");
        Field[] farr = c2.getFields();
        for (Field f :farr) {
            System.out.println(f);
        }

        //获得类的公共属性
        System.out.println("---------------指定公共属性");
        Field fp =c2.getField("a");
        System.out.println("公共属性的访问修饰符"+Modifier.toString(fp.getModifiers()));
        System.out.println(fp.getName());

        //获取类的所有属性
        System.out.println("----------------获得所有公共属性");
        Field[] fall =c2.getDeclaredFields();
        for (Field f:fall) {
            System.out.println(f);
            
        }

        //根具属性名去获得类属性
        System.out.println("-------------指定名称的属性");
        Field fname =c2.getDeclaredField("age");
        System.out.println("全部属性的访问修饰符"+Modifier.toString(fname.getModifiers()));
        System.out.println("属性的类型"+fname.getType());
        System.out.println("属性的名称"+fname.getName());




    }
}
