package Day7_6;

public class Demo01Person {
    private String name;
    private  int age;
    public int a =10;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Demo01Person() {
        super();
    }

    public Demo01Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Demo01Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a=" + a +
                '}';
    }
}
