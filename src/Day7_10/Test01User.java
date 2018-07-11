package Day7_10;


//已知 User类，该类有 name（ String 类型）和 age（ int 类型）两个属性，
public class Test01User {
    private String name;
    private int age;

    public Test01User() {
        super();
    }

    @Override
    public String toString() {
        return "Test01User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Test01User(String name, int age) {

        this.name = name;
        this.age = age;
    }
}
