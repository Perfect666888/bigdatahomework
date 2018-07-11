package Day7_11.compare;

import java.util.Comparator;

public class SumSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        //按照指定格式去切割数据，一般我们把成绩放在最后
        String[] sp1 = o1.split("\t");
        String[] sp2 = o2.split("\t");

        //获得到的成绩转换为int类型
        int sp1Sum = Integer.parseInt(sp1[sp1.length - 1]);
        int sp2Sum = Integer.parseInt(sp2[sp2.length - 1]);

        //降序排列
        return sp2Sum-sp1Sum;

    }
}
