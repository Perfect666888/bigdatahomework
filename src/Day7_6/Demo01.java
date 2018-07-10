package Day7_6;

public class Demo01 {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(int.class==int.class);
        System.out.println(String.class==String.class);
        System.out.println(void.class);

        System.out.println(Demo01Person.class);
        Demo01Person dp1 =new Demo01Person("张三",22);
        Demo01Person dp2 =new Demo01Person("李四",23);
        System.out.println(dp1.getClass()==dp2.getClass());

        Class c1 =Class.forName("Day7_6.Demo01Person");
        System.out.println(c1);



    }
}
