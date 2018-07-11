package Day7_11.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

//遍历的方法
public class ForUtils {

   //集合的遍历方法
    public static <T> void PrintArr(List<T> list){
        for (T t : list) {
            System.out.println(t);
        }
    }

    //Map的遍历方法
    public static  void PrintMap(Map map){
        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }

    }

}
