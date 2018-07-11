package Day7_10;

/*题目八 :已知 User类，该类有 name（ String 类型）和 age（ int 类型）两个属性，
ArrayList<User>排序，要求按照	User对象的 age的倒序排序
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test01 {
    public static void main(String[] args) {

        ArrayList<Test01User> test01Users = new ArrayList<>();


        test01Users.add(new Test01User("一",1));
        test01Users.add(new Test01User("二",2));
        test01Users.add(new Test01User("三",33));
        test01Users.add(new Test01User("六",6));
        test01Users.add(new Test01User("四",4));
        test01Users.add(new Test01User("五",5));


        Collections.sort(test01Users, new Comparator<Test01User>() {
            @Override
            public int compare(Test01User o1, Test01User o2) {
                return o2.getAge()-o1.getAge();
            }
        });


        for (Test01User user : test01Users) {
            System.out.println(user);
        }



    }
}
