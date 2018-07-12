package Day7_11_2.compare;

import java.util.Comparator;

public class SumSort implements Comparator<String> {
     int index;

    public SumSort(int index) {
        this.index = index;
    }

    @Override
    public int compare(String o1, String o2) {
        //按照指定格式去切割数据，一般我们把成绩放在最后
        String[] sp1 = o1.split("\t");
        String[] sp2 = o2.split("\t");

        //获得到的成绩转换为int类型
        int sp1Sum = Integer.parseInt(sp1[index]);
        int sp2Sum = Integer.parseInt(sp2[index]);

        //降序排列
        return sp2Sum-sp1Sum;

    }
}
