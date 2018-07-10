package Day7_8;

import java.util.ArrayList;
import java.util.HashSet;

/*
  * 题目一 :  将两个集合 {“a”，“b”，“ c”，“ d”，“ e”}和{“d”，“e”，“f ”，“g”，“h”},
          把这两个集合去除重复项合并成一个	,实现需求
  * */
public class Test01 {

    public static void main(String[] args) {
        //创建第一个集合
        ArrayList<Character> arr1 = new ArrayList<>();
        arr1.add('a');
        arr1.add('b');
        arr1.add('c');
        arr1.add('d');
        arr1.add('e');
        arr1.add('f');
        //创建第二个集合
        ArrayList<Character> arr2 = new ArrayList<>();
        arr2.add('a');
        arr2.add('b');
        arr2.add('c');
        arr2.add('s');
        arr2.add('x');
        arr2.add('g');

        System.out.println("------------方法1------------------");
        ArrayList<Character> arrnew =quchong(arr1,arr2);
        //遍历新集合
        for (Character cc : arrnew) {
            System.out.print("--"+cc);
        }

        System.out.println();
        System.out.println("-----------方法二------------");
        HashSet<Character> hs =quchong2(arr1,arr2);
        //遍历集合
        for (Character cc : hs) {
            System.out.print("--"+cc);
        }

    }

    private static HashSet<Character> quchong2(ArrayList<Character> arr1, ArrayList<Character> arr2) {
        HashSet<Character> newhs = new HashSet<>();
        //直接遍历集合添加，hashset的特性会自动去重复
        for (Character c : arr1) {
            newhs.add(c);
        }
        for (Character cc : arr2) {
            newhs.add(cc);
        }

        return newhs;
    }

    private static ArrayList<Character> quchong(ArrayList<Character> arr1, ArrayList<Character> arr2) {
        //新集合存放数组
        ArrayList<Character> arrnew = new ArrayList<>();
        //为了快速直接把集合1的值给到新集合
        arrnew=arr1;

        for (Character c : arr2) {
            //如果不包含就添加到新集合中
            if(!arrnew.contains(c)){
                arrnew.add(c);

            }
        }

        return arrnew;

    }
}
